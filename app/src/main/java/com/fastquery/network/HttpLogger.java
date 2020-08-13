package com.fastquery.network;

import android.util.Log;



import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        Log.d("httpInfo", message);
    }
}