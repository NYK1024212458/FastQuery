package com.fastquery.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fastquery.R;
import com.fastquery.model.VipPriceModel;
import com.fastquery.network.HttpTools;
import com.fastquery.utils.StatusBarUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberStandardActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout type_one;
    private LinearLayout type_two;
    private Button quick_open;
    private String type="two";
    private TextView tv_month_price;
    private TextView tv_year_price;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_member_standard);


        StatusBarUtils.transparencyBar(this);
        StatusBarUtils.setStatusBarColor(this, 0xFF2EB3EE);

        ImageView iv_break = findViewById(R.id.iv_break);
        iv_break.setOnClickListener(this);

        tv_month_price = findViewById(R.id.tv_month_price);
        tv_year_price = findViewById(R.id.tv_year_price);

        type_one = findViewById(R.id.type_one);
        type_two = findViewById(R.id.type_two);
        type_one.setOnClickListener(this);
        type_two.setOnClickListener(this);

        quick_open = findViewById(R.id.quick_open);
        quick_open.setOnClickListener(this);

        getVipPrice("1");
        getVipPrice("2");


    }

    private void getVipPrice(String id) {


        Call<VipPriceModel> vipPriceMonth = HttpTools.getApiService().getVipPrice(id);
        vipPriceMonth.enqueue(new Callback<VipPriceModel>() {
            @Override
            public void onResponse(Call<VipPriceModel> call, Response<VipPriceModel> response) {
                if (response.body() != null) {

                       /* String vipType = UnicodeConvertString.unicodeToCn(response.body().getName());
                        if (vipType.equals("月会员价格"))*/
                    if (id.equals("1"))
                        tv_month_price.setText(response.body().getDataa());
                    else if (id.equals("2"))
                        tv_year_price.setText(response.body().getDataa());

                }

            }

            @Override
            public void onFailure(Call<VipPriceModel> call, Throwable t) {
                Toast.makeText(MemberStandardActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }


        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.iv_break:

                finish();
                break;


            case R.id.type_one:

                type_one.setBackgroundResource(R.drawable.vip_bj);
                type_two.setBackgroundResource(R.drawable.vip_bj1);
                type="one";
                break;


            case R.id.type_two:


                type_two.setBackgroundResource(R.drawable.vip_bj);
                type_one.setBackgroundResource(R.drawable.vip_bj1);
                type="two";
                break;

            case R.id.quick_open:


                break;

        }


    }
}
