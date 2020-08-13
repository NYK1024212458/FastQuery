package com.fastquery.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.Layout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;

import com.fastquery.R;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ViewToBitMap {


    public static Bitmap onCut(int id,Activity activity){


        View view = LayoutInflater.from(activity).inflate(id, null);

        view.buildDrawingCache();

        //状态栏高度
        Rect rect=new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int stateBarHeight=rect.top;
        Display display=activity.getWindowManager().getDefaultDisplay();

        //获取屏幕宽高
        int widths=display.getWidth();
        int height=display.getHeight();

        //设置允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        //去掉状态栏高度
        Bitmap bitmap=Bitmap.createBitmap(view.getDrawingCache(),0,stateBarHeight,widths,height-stateBarHeight);

        view.destroyDrawingCache();
        return bitmap;
    }




    /**
     * 将Bitmap转成本地图片
     * @param path 保存为本地图片的地址
     * @param bitmap 要转化的Bitmap
     */
    public static void saveImage(String path, Bitmap bitmap) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, bos);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
