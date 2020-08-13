package com.fastquery.utils;

import android.content.Context;
import android.widget.Toast;

import com.fastquery.model.PublicWithMsgModel;
import com.fastquery.network.HttpTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicHttpMethod {


    public static String getResponse(Context context, String id){

         String[] url = new String[1];


        Call<PublicWithMsgModel> publicWithMsgModelCall = HttpTools.getApiService().videoAndCustomer("5");
        publicWithMsgModelCall.enqueue(new Callback<PublicWithMsgModel>() {
            @Override
            public void onResponse(Call<PublicWithMsgModel> call, Response<PublicWithMsgModel> response) {
                if (response.body()!=null){


                    if (response.body().getCode().equals("200")){

                        // url[0] = response.body().getMsg();
                        Toast.makeText(context,"请求视频成功",Toast.LENGTH_SHORT).show();


                    }


                }

            }

            @Override
            public void onFailure(Call<PublicWithMsgModel> call, Throwable t) {
                Toast.makeText(context,"请求失败"+t.toString(),Toast.LENGTH_SHORT).show();

            }


        });

        return url[0]==null?"无视频资源":url[0];
    }




}
