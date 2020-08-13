package com.fastquery.weiget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.fastquery.R;
import com.fastquery.bean.VipSuccessEvent;
import com.fastquery.model.GoVerifyModel;
import com.fastquery.network.HttpTools;
import com.fastquery.utils.DensityUtil;
import com.fastquery.utils.UnicodeConvertString;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GoVerifyDialog {


    public static void showDialog(final Context context,String phone) {


        AlertDialog.Builder dialog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle);


        View view = LayoutInflater.from(context).inflate(R.layout.dialog_view, null);

        dialog.setView(view);
        AlertDialog alertDialog = dialog.create();
        Window alertWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lParams = alertWindow.getAttributes();
        lParams.height = DensityUtil.dip2px(context,200);
        lParams.width = DensityUtil.dip2px(context,340);
        alertWindow.setAttributes(lParams);
        alertDialog.show();

        final EditText authorizationCode = view.findViewById(R.id.et_authorizationCode);
        Button contactCustomer = view.findViewById(R.id.bt_ContactCustomer);

        contactCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = authorizationCode.getText().toString().trim();
                if (code.isEmpty()) {

                    Toast.makeText(context, "请输入核验码", Toast.LENGTH_SHORT).show();
                    return;
                }

                goVerify(phone,code);


            }

            private void goVerify(String phone,String code) {

                Call<GoVerifyModel> call = HttpTools.getApiService().goVerify(phone, code);
                call.enqueue(new Callback<GoVerifyModel>() {
                    @Override
                    public void onResponse(Call<GoVerifyModel> call, Response<GoVerifyModel> response) {
                        if (response.body()!=null){

                            String responBody = UnicodeConvertString.unicodeToCn(response.body().getMsg());


                            if (response.body().getCode()==200){

                                EventBus.getDefault().post(new VipSuccessEvent(true));
                                Toast.makeText(context,responBody , Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();


                            }else {
                                Toast.makeText(context,responBody , Toast.LENGTH_SHORT).show();

                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<GoVerifyModel> call, Throwable t) {
                        Toast.makeText(context, "激活失败"+t.toString(), Toast.LENGTH_SHORT).show();

                    }


                });

            }
        });






    }


}
