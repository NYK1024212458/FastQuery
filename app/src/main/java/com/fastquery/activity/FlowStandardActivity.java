package com.fastquery.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fastquery.R;

public class FlowStandardActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_break;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_standard);
        iv_break = findViewById(R.id.iv_break);
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("流量标准");

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