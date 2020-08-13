package com.fastquery.weiget;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.fastquery.R;
import com.fastquery.model.CustomerModel;
import com.fastquery.network.HttpTools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fastquery.utils.getMobleSystemOsVersion.getSDKVersionNumber;

public class OnLineCustomerDialog {


    private static ImageView iv_customer_img;
    private static TextView customer_type;
    private static TextView number;
    private static Button bt_copy;

    public static void showDialog(final Context context){


        AlertDialog.Builder dialog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);


        View view =LayoutInflater.from(context).inflate(R.layout.dialog_online_customer,null);

        dialog.setView(view);
        AlertDialog alertDialog = dialog.create();


        customer_type = view.findViewById(R.id.tv_customer_type);
        number = view.findViewById(R.id.tv_number);
        bt_copy = view.findViewById(R.id.bt_copy);

        iv_customer_img = view.findViewById(R.id.iv_customer_img);
        ImageView iv_close = view.findViewById(R.id.iv_close);

        TextView text = view.findViewById(R.id.mast_fix);

        String content = "2. 点击右上角“+”>选择“添加朋友”>粘贴微信号>搜索添加";






        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);
        ForegroundColorSpan foregroundColorSpanStart = new ForegroundColorSpan(Color.parseColor("#FF0000"));
        ForegroundColorSpan foregroundColorSpanEnd = new ForegroundColorSpan(Color.parseColor("#FF0000"));


        spannableStringBuilder.setSpan(foregroundColorSpanStart,8,11,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableStringBuilder.setSpan(foregroundColorSpanEnd,14,20,Spannable.SPAN_EXCLUSIVE_INCLUSIVE);


        text.setText(spannableStringBuilder);

        getCustomerImg(context);


        alertDialog.show();


        bt_copy.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {

                if(getSDKVersionNumber() >= 11){
                    android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboardManager.setText(number.getText().toString().trim());

                }else{
                    // 得到剪贴板管理器
                    android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboardManager.setPrimaryClip(ClipData.newPlainText(null, number.getText().toString().trim()));
                }
                Toast.makeText(context, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
            }


        });


        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });






    }

    private static void getCustomerImg(Context context) {


        Call<CustomerModel> customerModelCall = HttpTools.getApiService().customerInfo();
        customerModelCall.enqueue(new Callback<CustomerModel>() {
            @Override
            public void onResponse(Call<CustomerModel> call, Response<CustomerModel> response) {
                if (response.body()!=null){


                    if (response.body().getTypeb()!=null){

                        Glide.with(context).load("http://2624375038.top/static/uploads/"+response.body().getTypeb()).into(iv_customer_img);

                    }

                    if (response.body().getType()!=null){

                        customer_type.setText(response.body().getTypec());

                    }
                    if (response.body().getTypec()!=null){

                        number.setText(response.body().getType());


                    }





                }else {


                    Toast.makeText(context,"客服数据为空",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<CustomerModel> call, Throwable t) {

                Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            }

         
        });


    }


}
