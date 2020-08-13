package com.fastquery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fastquery.R;
import com.fastquery.model.PublicWithMsgModel;
import com.fastquery.network.HttpTools;
import com.fastquery.utils.UnicodeConvertString;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_content;
    private ImageView iv_break;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_us);
        iv_break = findViewById(R.id.iv_break);
        iv_break.setOnClickListener(this);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("关于我们");

        tv_content = findViewById(R.id.tv_content);

        getAboutUsInfo();

    }

    private void getAboutUsInfo() {

        Call<PublicWithMsgModel> publicWithMsgModelCall = HttpTools.getApiService().videoAndCustomer("4");
        publicWithMsgModelCall.enqueue(new Callback<PublicWithMsgModel>() {
            @Override
            public void onResponse(Call<PublicWithMsgModel> call, Response<PublicWithMsgModel> response) {
                if (response.body()!=null){

                    String code = UnicodeConvertString.unicodeToCn(response.body().getCode());

                    if (code.equals("返回关于我们的文本")){

                        String content = UnicodeConvertString.unicodeToCn(response.body().getMsg());
                        tv_content.setText(content);
                    }

                }
            }

            @Override
            public void onFailure(Call<PublicWithMsgModel> call, Throwable t) {

                Toast.makeText(AboutUsActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }


        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.iv_break:
                finish();
                break;



        }
    }
}
