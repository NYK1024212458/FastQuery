package com.fastquery.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fastquery.R;
import com.fastquery.utils.HttpUtil;
import com.fastquery.utils.UnicodeConvertString;

import java.io.IOException;
import java.net.URLConnection;

public class TestLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_login;
    private EditText et_phone_number, et_Verification_code;
    private TextView tv_getVerification;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login);
        et_phone_number = findViewById(R.id.et_phone_number);
        et_Verification_code = findViewById(R.id.et_Verification_code);
        result = findViewById(R.id.result);

        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);
        tv_getVerification = findViewById(R.id.tv_getVerification);
        tv_getVerification.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_getVerification:
                String phoneNumber = et_phone_number.getText().toString().trim();
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String codeResult = HttpUtil.sendPost("http://2624375038.top/index/main/sms", phoneNumber, "");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    String[] split = codeResult.split(":");
                                    Log.i("code", split.toString());
                                    String unicode = split[2].replace("}", "");
                                    String unicodeNew = unicode.replace("\"", "");
                                    Log.i("unicodeNew", unicode);
                                    String code = UnicodeConvertString.unicodeToCn(unicodeNew);
                                    Log.i("code", code);
                                    result.setText(code);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }.start();
                break;


            case R.id.bt_login:
                String phone = et_phone_number.getText().toString().trim();
                String code = et_Verification_code.getText().toString().trim();


                new Thread() {
                    @Override
                    public void run() {
                        try {
                            String loginResult = HttpUtil.sendPost("http://2624375038.top/index/main/verify", phone, code);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String[] split = loginResult.split(":");
                                    Log.i("code", split.toString());

                                    String unicode = split[2].replace("}", "");
                                    String unicodeNew = unicode.replace("\"", "");

                                    Log.i("code", unicodeNew);

                                    String code = UnicodeConvertString.unicodeToCn(unicodeNew);
                                    Log.i("code", code);

                                    result.setText(code);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();


                break;


        }
    }
}