package com.fastquery.utils;

public class UnicodeConvertString {


    public static String unicodeToString(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            String temp = "";
            int strInt = str.charAt(i);
            if (strInt > 127) {
                temp += "\\u" + Integer.toHexString(strInt);
            } else {
                temp = String.valueOf(str.charAt(i));
            }
            result += temp;
        }
    return result;
    }




    //Unicode转中文方法
    public static String unicodeToCn(String unicode) {

        String codeStr = unicodeToString(unicode);


        /** 以 \ u 分割，codeStr，因此中间加了一个空格*/
        String[] strs = codeStr.split("\\\\u");
        String returnStr = "";
        // codeStr \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

}
