package com.fastquery.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpUtil {

    public static String sendPost(String url, String phone,String code) throws IOException {


        URL realurl = null;
        InputStream in = null;
        HttpURLConnection conn = null;
        try {
            realurl = new URL(url);
            conn = (HttpURLConnection) realurl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            // Post 请求不能使用缓存
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);

            // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
            // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
            // 要注意的是connection.getOutputStream会隐含的进行connect。
            conn.connect();

            DataOutputStream out = new DataOutputStream(conn
                    .getOutputStream());
            // The URL-encoded contend
            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
            String content;
            if (code.equals("")){
                content = "phone=" + URLEncoder.encode(phone, "UTF-8");
            }else {


                content = "phone=" + URLEncoder.encode(phone, "UTF-8");
                content += "&code=" + URLEncoder.encode(code, "UTF-8");
            }
            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
            out.writeBytes(content);

            out.flush();
            out.close();
            in = conn.getInputStream();
        } catch (MalformedURLException eio) {

        }
        return inputStream2String(in);
    }


    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }
}
