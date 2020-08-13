package com.fastquery.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.fastquery.R;
import com.fastquery.model.LoginModel;
import com.fastquery.model.VerificationCodeModel;
import com.fastquery.network.HttpTools;
import com.fastquery.utils.CheckMobileNumber;
import com.fastquery.utils.HttpUtils;
import com.fastquery.utils.OkHttpUtil;
import com.fastquery.utils.PermissionUtils;
import com.fastquery.utils.StatusBarUtils;
import com.fastquery.utils.UnicodeConvertString;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    public static List<String> sNeedReqPermissions = new ArrayList<>();

    static {
        sNeedReqPermissions.add(Manifest.permission.READ_PHONE_STATE);
        sNeedReqPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        sNeedReqPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        sNeedReqPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    private PermissionUtils mPermissionUtils;


    private EditText et_phone_number, et_Verification_code;
    private Button bt_login;
    private TextView tv_getVerification;


    @NonNull
    private Disposable subscribe;
    private Call<VerificationCodeModel> verificationCode;
    private String phoneNumber;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        StatusBarUtils.transparencyBar(this);
        //StatusBarUtils.setStatusBarColor(this,0xFF1DA8E8);
        mPermissionUtils = new PermissionUtils(this);

        mPermissionUtils.request(sNeedReqPermissions, 100, new PermissionUtils.CallBack() {
            @Override
            public void grantAll() {


            }

            @Override
            public void denied() {
                finish();
            }
        });


        et_phone_number = findViewById(R.id.et_phone_number);
        et_Verification_code = findViewById(R.id.et_Verification_code);


        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        tv_getVerification = findViewById(R.id.tv_getVerification);
        tv_getVerification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {


            case R.id.tv_getVerification:

                phoneNumber = et_phone_number.getText().toString().trim();

                if (phoneNumber.isEmpty()) {

                    Toast.makeText(this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
                    return;

                }


                if (!CheckMobileNumber.checkPhone(phoneNumber)) {

                    Toast.makeText(this, "您输入的手机号码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
                    return;
                }


                getHttpVerification(phoneNumber);

                timeDown(60L);


                break;


            case R.id.bt_login:


                /**
                 * 测试使用
                 * */


              //  startActivity(new Intent(LoginActivity.this, MainActivity.class));


                String phone = et_phone_number.getText().toString().trim();
                String code = et_Verification_code.getText().toString().trim();

                boolean isLega = checkLoginInfo(phone, code);
                if (!isLega)
                    return;


                goLogin(phone,code);
                break;


        }

    }


 private  String TAG =  getClass().getSimpleName().toString();
    private void goLogin(String phone, String code) {
        Log.d(TAG, "goLogin: 登录的phone和code  "+  phone  + "\n"+  code);
      Call<LoginModel>  loginCall = HttpTools.getApiService().goLogin(phone, code);
        Log.d(TAG, "goLogin: ");
        loginCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.d(TAG, "onResponse: 获取返回 "+ response.body().getSms());
                Log.d(TAG, "onResponse: ");
                if (response.body() != null) {
                    String  message = UnicodeConvertString.unicodeToCn(response.body().getSms());
                    
                    if (response.body().getCode() == 200) {
                        Toast.makeText(LoginActivity.this,message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,  message, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "登录失败"+t.toString(), Toast.LENGTH_SHORT).show();
            }


        });

    }

    private void getHttpVerification(String phone) {


        verificationCode = HttpTools.getApiService().getVerificationCode(phone);
        verificationCode.enqueue(new Callback<VerificationCodeModel>() {
            @Override
            public void onResponse(Call<VerificationCodeModel> call, Response<VerificationCodeModel> response) {
                if (response.body() != null) {

                    String message = UnicodeConvertString.unicodeToCn(response.body().getSms());
                    if (response.body().getCode() == 200) {

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();


                    } else {


                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                    }


                }
            }

            @Override
            public void onFailure(Call<VerificationCodeModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }

        });

    }

    private boolean checkLoginInfo(String phoneNumber,String verificationCode ) {


        //检查手机号码或者验证码是否为空

        if (verificationCode.isEmpty() || phoneNumber.isEmpty()) {

            Toast.makeText(this, "请输入您的手机号码或者验证码", Toast.LENGTH_SHORT).show();
            return false;
        }

        //检查手机号码是否合法

        if (!CheckMobileNumber.checkPhone(phoneNumber)) {

            Toast.makeText(this, "您输入的手机号码不正确，请重新输入！", Toast.LENGTH_SHORT).show();

            return false;

        }
        
        return true;

    }


    private void timeDown(final Long time) {

        subscribe = Observable.interval(0L, 1L, TimeUnit.SECONDS, AndroidSchedulers.mainThread())

                .subscribe(new Consumer() {

                    @Override
                    public void accept(Object aLong) throws Throwable {
                        Long offset = time - (Long) aLong;

                        if (offset <= 0) {

                            if (subscribe != null && !subscribe.isDisposed())

                                subscribe.dispose();
                            tv_getVerification.setEnabled(true);
                            tv_getVerification.setText("再次获取验证码");


                        } else {
                            tv_getVerification.setEnabled(false);
                            tv_getVerification.setText(offset + " 秒");
                        }
                    }


                });

    }


    @Override
    protected void onDestroy() {
        /**
         * 一定要解绑Disposable否则内存溢出
         * */


        if (subscribe != null && !subscribe.isDisposed()) {

            subscribe.dispose();

        }

        super.onDestroy();
    }







}
