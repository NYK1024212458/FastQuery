package com.fastquery.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.fastquery.R;
import com.fastquery.map.LocationInstance;
import com.fastquery.map.SensorInstance;
import com.fastquery.model.FindLocationModel;
import com.fastquery.model.PublicWithMsgModel;
import com.fastquery.network.HttpTools;
import com.fastquery.utils.CheckMobileNumber;
import com.fastquery.utils.StatusBarUtils;
import com.fastquery.utils.UnicodeConvertString;
import com.fastquery.weiget.CommonDialog;
import com.fastquery.weiget.CustomToast;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationSeekFragment extends Fragment implements View.OnClickListener {
    private MapView mMapView;
    private BaiduMap mMap;
    private LocationInstance mLocationInstance;
    private BDLocation mLastLocation;
    private SensorInstance mSensorInstance;
    private boolean mIsFirstLocation = true;
    private String phone;
    private TextView title;

    File mFile;
    private Button pressLocation;
    private String phone_number;
    private EditText input_phone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone = getActivity().getIntent().getStringExtra("phone");
        // EventBus.getDefault().register(getActivity());

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_seek, null);


        mMapView = view.findViewById(R.id.mapView);
        mMap = mMapView.getMap();
        mMap.setMapStatus(MapStatusUpdateFactory.zoomTo(17f));
        // 开启定位图层
        mMap.setMyLocationEnabled(true);


        startLocation();

        input_phone = view.findViewById(R.id.et_input_phone);

        pressLocation = view.findViewById(R.id.bt_location);
        pressLocation.setOnClickListener(this);


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtils.transparencyBar(getActivity());
        StatusBarUtils.StatusBarLightMode(getActivity());


    }










   /* private void httpLocation() {

        if (mLastLocation==null){

            return;
        }



        Call<LoginModel> locationCall =  HttpTools.getApiService().location(phone, String.valueOf(mLastLocation.getLatitude()), String.valueOf(mLastLocation.getLongitude()));

        locationCall.enqueue(new Callback<LoginModel>() {
            @Override
            protected void fail(Call<LoginModel> call, Throwable t) {

                Toast.makeText(getActivity(), "发送失败"+toString(), Toast.LENGTH_SHORT).show();


            }

            @Override
            protected void success(Call<LoginModel> call, Response<LoginModel> response) {

                if (response.body()!=null){

                    String responsCode = UnicodeConvertString.unicodeToCn(response.body().getSms());

                    if (response.body().getCode()==200){




                        Toast.makeText(getActivity(), responsCode, Toast.LENGTH_SHORT).show();


                    }else {

                        Toast.makeText(getActivity(), responsCode, Toast.LENGTH_SHORT).show();



                    }

                }

            }
        });





    }*/


    @Override
    public void onStart() {
        super.onStart();
        if (mLocationInstance != null)

            mLocationInstance.start();

        if (mSensorInstance != null)
            mSensorInstance.start();

    }


    @Override
    public void onStop() {
        super.onStop();
        if (mLocationInstance != null)
            mLocationInstance.stop();
        if (mSensorInstance != null)
            mSensorInstance.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {

            EventBus.getDefault().unregister(this);

        }
        if (mMapView != null)
            mMapView.onDestroy();

    }


    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null)
            mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }

    private void startLocation() {

        mLocationInstance = new LocationInstance(getActivity(),
                new LocationInstance.MyLocationListener() {
                    @Override
                    public void onReceiveLocation(BDLocation location) {
                        super.onReceiveLocation(location);


                        Bundle bundle = new Bundle();
                        bundle.putString("address", location.getAddrStr());

                        Log.i("位置1", location.getAddrStr());
                        Log.i("位置2", location.getCity());
                        Log.i("位置3", location.getCountry());

                        View view = LayoutInflater.from(getActivity()).inflate(R.layout.mark_point, null);
                        TextView addressOne = view.findViewById(R.id.tv_address_one);
                        TextView address = view.findViewById(R.id.tv_address);
                        addressOne.setText(location.getStreet() + location.getStreetNumber());


                        address.setText(location.getCity() + location.getDistrict() + location.getStreet());

                        mMap.clear();
                        //定义Maker坐标点
                        LatLng point = new LatLng(location.getLatitude(),
                                location.getLongitude());


                        //构建Marker图标
                        BitmapDescriptor bitmap = BitmapDescriptorFactory
                                .fromView(view);
                        //构建MarkerOption，用于在地图上添加Marker
                        OverlayOptions iconOption = new MarkerOptions()
                                .position(point)
                                .animateType(MarkerOptions.MarkerAnimateType.drop)//从天而降的方式
                                .extraInfo(bundle)
                                .icon(bitmap);

                        //在地图上添加Marker，并显示
                        mMap.addOverlay(iconOption);


                        mMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(point));


                        mLocationInstance.stop();

// TODO: 2020/8/8 上传本机经纬度并且获取是否为vip用户以及流量是否到期
                        //  HttpTools.getApiService().location(phone,location.getLatitude(),location.getLongitude())


                    }


                });


/**
 *
 * 方向传感器
 * */

   /*     mSensorInstance = new SensorInstance(getActivity().getApplicationContext());
        mSensorInstance.setOnOrientationChangedListener(
                new SensorInstance.OnOrientationChangedListener() {
                    @Override
                    public void onOrientation(float x) {
                        // 设置定位图标；
                        if (mLastLocation == null) {
                            return;
                        }

                        // 构造定位数据
                        MyLocationData locData = new MyLocationData.Builder()
                                .accuracy(mLastLocation.getRadius())
                                // 此处设置开发者获取到的方向信息，顺时针0-360
                                .direction(x).latitude(mLastLocation.getLatitude())
                                .longitude(mLastLocation.getLongitude()).build();

                        // 设置定位数据
                        mMap.setMyLocationData(locData);

                        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
                        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                                .fromResource(R.drawable.ziji);



                        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL,
                                true, null);
                        mMap.setMyLocationConfiguration(config);


                        if (mIsFirstLocation) {
                            mIsFirstLocation = false;
                            LatLng point = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                            mMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(point));
                        }


                        // 当不需要定位图层时关闭定位图层
//                mMap.setMyLocationEnabled(false);

                    }
                });*/


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.bt_location:

                String phone = input_phone.getText().toString().trim();
                if (phone.isEmpty()) {

                    Toast.makeText(getActivity(), "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (!CheckMobileNumber.checkPhone(phone)) {
                    Toast.makeText(getActivity(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }


                String content = "您的定位软件还没有授权，无法正常使用，请联系客服获取授权码.";
                CommonDialog.showDialog(getActivity(), content);

                // TODO: 2020/8/7 如果激活成功显示好友位置


                phone_number = input_phone.getText().toString().trim();

                locationYourFriendAddress(phone_number, phone);


                break;


        }


    }


   /* @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(VipSuccessEvent messageEvent) {

        if (messageEvent.isVip()){




        }



    }
*/

    /**
     * 拿到好友的经纬度
     */

    public void locationYourFriendAddress(String phone, String user_phone) {


        Call<FindLocationModel> friendAddressCall = HttpTools.getApiService().getFriendAddress(phone, user_phone);
        friendAddressCall.enqueue(new Callback<FindLocationModel>() {
            @Override
            public void onResponse(Call<FindLocationModel> call, Response<FindLocationModel> response) {
                if (response.body() != null) {

                    // TODO: 2020/8/7 拿经纬度

                    if (response.body().getCode()!=0&& response.body().getCode()== 400) {

                        String message = UnicodeConvertString.unicodeToCn(response.body().getSms());

                        CustomToast.makeText(getActivity(), message, 1, 0, 0).show();


                    }else if (response.body().getPositionx()!=null&&response.body().getPositiony()!=null){


                        locationFriendAddress(Double.parseDouble(response.body().getPositionx()),Double.parseDouble(response.body().getPositiony()));



                    }







                }
            }

            @Override
            public void onFailure(Call<FindLocationModel> call, Throwable t) {

                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }


        });

    }


    /**
     * 定位好友位置
     */

    public void locationFriendAddress(double x, double y) {


        mMap.clear();
        //定义Maker坐标点
        LatLng point = new LatLng(x,
                y);


        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.haoyou);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions iconOption = new MarkerOptions()
                .position(point)
                .animateType(MarkerOptions.MarkerAnimateType.drop)//从天而降的方式
                .icon(bitmap);

        //在地图上添加Marker，并显示
        mMap.addOverlay(iconOption);
        mMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(point));
        mLocationInstance.stop();
    }


}
