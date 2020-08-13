package com.fastquery.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.fastquery.R;
import com.fastquery.fragment.LocationSeekFragment;
import com.fastquery.fragment.MineFragment;
import com.fastquery.fragment.QuickSeekFragment;
import com.fastquery.fragment.VideoDemoFragment;
import com.fastquery.map.LocationInstance;
import com.fastquery.map.SensorInstance;
import com.fastquery.network.ApiService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private LinearLayout location_seek, quick_seek, video_demo, mine;
    private ImageView iv_location_seek, iv_quick_seek, iv_video_demo, iv_mine;
    private TextView tv_location_seek, tv_quick_seek, tv_video_demo, tv_mine;
    private FrameLayout content;



    FragmentManager supportFragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ArrayList<Fragment> list;
    private String phone;


    ApiService apiService;


    private MapView mMapView;
    private BaiduMap mMap;
    private LocationInstance mLocationInstance;
    private BDLocation mLastLocation;
    private SensorInstance mSensorInstance;
    private boolean mIsFirstLocation = true;
    private LocationSeekFragment locationSeekFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        phone = getIntent().getStringExtra("phone");

        initView();

    }



    private void initView() {

        content = findViewById(R.id.content);




        location_seek = findViewById(R.id.location_seek);
        quick_seek = findViewById(R.id.quick_seek);
        video_demo = findViewById(R.id.video_demo);
        mine = findViewById(R.id.mine);
        iv_location_seek = findViewById(R.id.iv_location_seek);
        iv_quick_seek = findViewById(R.id.iv_quick_seek);
        iv_video_demo = findViewById(R.id.iv_video_demo);
        iv_mine = findViewById(R.id.iv_mine);

        tv_location_seek = findViewById(R.id.tv_location_seek);
        tv_quick_seek = findViewById(R.id.tv_quick_seek);
        tv_video_demo = findViewById(R.id.tv_video_demo);
        tv_mine = findViewById(R.id.tv_mine);


        location_seek.setOnClickListener(this);
        quick_seek.setOnClickListener(this);
        video_demo.setOnClickListener(this);
        mine.setOnClickListener(this);


        locationSeekFragment = new LocationSeekFragment();
        QuickSeekFragment quickSeekFragment = new QuickSeekFragment();
        VideoDemoFragment videoDemoFragment = new VideoDemoFragment();
        MineFragment mineFragment = new MineFragment();

        list = new ArrayList<>();
        list.add(locationSeekFragment);
        list.add(quickSeekFragment);
        list.add(videoDemoFragment);
        list.add(mineFragment);


        supportFragmentManager = getSupportFragmentManager();
        fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, list.get(0));
        fragmentTransaction.commit();
        iv_location_seek.setBackground(getDrawableId(R.drawable.dingwei));
        tv_location_seek.setTextColor(0xFF000000);


    }







    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.location_seek:

                showHintView(0);

                fragmentTransaction = supportFragmentManager.beginTransaction();   //开启事物
                fragmentTransaction.replace(R.id.content, list.get(0));
                fragmentTransaction.commit();//提交事物



                break;

            case R.id.quick_seek:

                showHintView(1);
                fragmentTransaction = supportFragmentManager.beginTransaction();   //开启事物
                fragmentTransaction.replace(R.id.content, list.get(1));
                fragmentTransaction.commit();

                break;

            case R.id.video_demo:
                showHintView(2);
                fragmentTransaction = supportFragmentManager.beginTransaction();   //开启事物
                fragmentTransaction.replace(R.id.content, list.get(2));
                fragmentTransaction.commit();
                break;

            case R.id.mine:
                showHintView(3);
                fragmentTransaction = supportFragmentManager.beginTransaction();   //开启事物
                fragmentTransaction.replace(R.id.content, list.get(3));
                fragmentTransaction.commit();
                break;
        }


    }


    public void showHintView(int index) {


        if (index == 0) {

            iv_location_seek.setBackground(getDrawableId(R.drawable.dingwei));
            tv_location_seek.setTextColor(0xFF000000);
            iv_quick_seek.setBackground(getDrawableId(R.drawable.jingzhun1));
            tv_quick_seek.setTextColor(0xFF888888);
            iv_video_demo.setBackground(getDrawableId(R.drawable.video1));
            tv_video_demo.setTextColor(0xFF888888);
            iv_mine.setBackground(getDrawableId(R.drawable.mine1));
            tv_mine.setTextColor(0xFF888888);
        } else if (index == 1) {

            iv_location_seek.setBackground(getDrawableId(R.drawable.dingwei1));
            tv_location_seek.setTextColor(0xFF888888);
            iv_quick_seek.setBackground(getDrawableId(R.drawable.jingzhun));
            tv_quick_seek.setTextColor(0xFF000000);
            iv_video_demo.setBackground(getDrawableId(R.drawable.video1));
            tv_video_demo.setTextColor(0xFF888888);
            iv_mine.setBackground(getDrawableId(R.drawable.mine1));
            tv_mine.setTextColor(0xFF888888);

        } else if (index == 2) {


            iv_location_seek.setBackground(getDrawableId(R.drawable.dingwei1));
            tv_location_seek.setTextColor(0xFF888888);
            iv_quick_seek.setBackground(getDrawableId(R.drawable.jingzhun1));
            tv_quick_seek.setTextColor(0xFF888888);
            iv_video_demo.setBackground(getDrawableId(R.drawable.video));
            tv_video_demo.setTextColor(0xFF000000);
            iv_mine.setBackground(getDrawableId(R.drawable.mine1));
            tv_mine.setTextColor(0xFF888888);


        } else if (index == 3) {


            iv_location_seek.setBackground(getDrawableId(R.drawable.dingwei1));
            tv_location_seek.setTextColor(0xFF888888);
            iv_quick_seek.setBackground(getDrawableId(R.drawable.jingzhun1));
            tv_quick_seek.setTextColor(0xFF888888);
            iv_video_demo.setBackground(getDrawableId(R.drawable.video1));
            tv_video_demo.setTextColor(0xFF888888);
            iv_mine.setBackground(getDrawableId(R.drawable.mine));
            tv_mine.setTextColor(0xFF000000);


        }


    }


    public Drawable getDrawableId(int id) {


        return MainActivity.this.getResources().getDrawable(id);


    }









}
