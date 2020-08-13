package com.fastquery.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.fastquery.R;
import com.fastquery.activity.AboutUsActivity;
import com.fastquery.activity.FlowStandardActivity;
import com.fastquery.activity.MemberStandardActivity;
import com.fastquery.activity.QueryRecordActivity;
import com.fastquery.bean.VipSuccessEvent;
import com.fastquery.model.UserDataModel;
import com.fastquery.network.HttpTools;
import com.fastquery.utils.CheckMobileNumber;
import com.fastquery.utils.StatusBarUtils;
import com.fastquery.weiget.GoVerifyDialog;
import com.fastquery.weiget.OnLineCustomerDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineFragment extends Fragment implements View.OnClickListener {


    private String phone;
    private FrameLayout vip_show;
    private TextView tv_vip_state;
    private ImageView iv_vip_mark;
    private FrameLayout commonUser;
    private FrameLayout commonUserBg;
    private LinearLayout goOpenVip;
    private TextView functionType;
    private TextView vipState;
    private TextView tv_go_verify;
    private TextView goVerify;
    private TextView phoneNumber;
    private ImageView headImg;
    private TextView content;
    private LinearLayout member_standard;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone = getActivity().getIntent().getStringExtra("phone");
        EventBus.getDefault().register(this);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {

            EventBus.getDefault().unregister(this);

        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtils.transparencyBar(getActivity());


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        getUserVipLevel();


        View view = inflater.inflate(R.layout.fragment_mine, null);

        commonUser = view.findViewById(R.id.fl_user_common);
        commonUserBg = view.findViewById(R.id.fl_common_user_bg);

        phoneNumber = view.findViewById(R.id.tv_phone);
        phoneNumber.setText(CheckMobileNumber.hideMiddleMobile(phone));

        headImg = view.findViewById(R.id.iv_head_img);


        goVerify = view.findViewById(R.id.go_verify);
        goVerify.setOnClickListener(this);
        vip_show = view.findViewById(R.id.vip_show);

        vipState = view.findViewById(R.id.tv_vip_state);


        goOpenVip = view.findViewById(R.id.go_open_vip);
        goOpenVip.setOnClickListener(this);

        content = view.findViewById(R.id.tv_content);

        functionType = view.findViewById(R.id.tv_function_type);

        tv_vip_state = view.findViewById(R.id.tv_vip_state);
        iv_vip_mark = view.findViewById(R.id.iv_vip_mark);


        LinearLayout flowStandard = view.findViewById(R.id.flow_standard);
        flowStandard.setOnClickListener(this);


        member_standard = view.findViewById(R.id.member_standard);
        member_standard.setOnClickListener(this);
        LinearLayout onLineCustomer = view.findViewById(R.id.online_customer);
        onLineCustomer.setOnClickListener(this);

        LinearLayout query_record = view.findViewById(R.id.query_record);
        query_record.setOnClickListener(this);
        LinearLayout aboutUs = view.findViewById(R.id.about_us);
        aboutUs.setOnClickListener(this);


        tv_go_verify.setOnClickListener(this);


        return view;
    }

    private void getUserVipLevel() {


        Call<UserDataModel> userDataModelCall = HttpTools.getApiService().getUserData(phone);
        userDataModelCall.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {

                if (response.body() != null) {

                    Glide.with(getContext()).load("http://2624375038.top/static/uploads/" + response.body().getHead_img()).asBitmap().centerCrop().into(new BitmapImageViewTarget(headImg) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            headImg.setImageDrawable(circularBitmapDrawable);
                        }
                    });


                    String vip1_time = response.body().getVip1_time();
                    String vip2_time = response.body().getVip2_time();
                    String username = response.body().getUsername();

                    String status = response.body().getStatu();

                    /**
                     * 普通用户
                     * */
                    if (status.equals("1")) {


                        if (member_standard.getVisibility()==View.VISIBLE){
                            member_standard.setVisibility(View.GONE);

                        }


                        if (commonUserBg.getVisibility() == View.GONE) {

                            commonUserBg.setVisibility(View.VISIBLE);
                        }

                        if (vip_show.getVisibility() == View.VISIBLE) {

                            vip_show.setVisibility(View.GONE);
                        }


                        if (goVerify.getVisibility() == View.GONE) {
                            goVerify.setVisibility(View.VISIBLE);

                        }
                        if (iv_vip_mark.getVisibility() == View.VISIBLE) {

                            iv_vip_mark.setVisibility(View.GONE);

                        }


                        /**
                         * vip1
                         * */
                    } else if (status.equals("2")) {



                        if (member_standard.getVisibility()==View.GONE){
                            member_standard.setVisibility(View.VISIBLE);

                        }


                        if (commonUserBg.getVisibility() == View.VISIBLE) {

                            commonUserBg.setVisibility(View.GONE);

                        }
                        if (vip_show.getVisibility() == View.GONE) {
                            vip_show.setVisibility(View.VISIBLE);

                        }
                        if (goVerify.getVisibility() == View.VISIBLE) {

                            goVerify.setVisibility(View.GONE);

                        }

                        if (iv_vip_mark.getVisibility() == View.GONE) {

                            iv_vip_mark.setVisibility(View.VISIBLE);

                        }
                        functionType.setText("非VIP会员有功能限制");


                        /**
                         * vip2
                         * */
                    } else if (status.equals("3")) {



                        if (member_standard.getVisibility()==View.GONE){
                            member_standard.setVisibility(View.VISIBLE);

                        }

                        if (goVerify.getVisibility() == View.VISIBLE) {

                            goVerify.setVisibility(View.GONE);

                        }

                        if (goOpenVip.getVisibility() == View.VISIBLE) {

                            goOpenVip.setVisibility(View.GONE);

                        }

                        if (vip_show.getVisibility() == View.GONE) {

                            vip_show.setVisibility(View.VISIBLE);

                        }

                        functionType.setText("已成为VIP会员");
                        content.setText("到期时间：" + response.body().getVip2_time());
                        iv_vip_mark.setBackgroundResource(R.drawable.huiyuan1);


                    }


                }


            }

            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.go_verify:

                GoVerifyDialog.showDialog(getActivity(), phone);

                break;

            case R.id.go_open_vip:
                break;


            case R.id.online_customer:

                OnLineCustomerDialog.showDialog(getActivity());

                break;


            case R.id.flow_standard:

                startActivity(new Intent(getActivity(), FlowStandardActivity.class));

                break;


            case R.id.member_standard:


                Intent intent = new Intent(getActivity(), MemberStandardActivity.class);
                startActivity(intent);


                break;


            case R.id.query_record:

                Intent intent_query_record = new Intent(getActivity(), QueryRecordActivity.class);
                startActivity(intent_query_record);

                break;


            case R.id.about_us:


                Intent intentAbout = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intentAbout);


                break;


        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(VipSuccessEvent messageEvent) {

        if (messageEvent.isVip()) {


            vip_show.setVisibility(View.VISIBLE);
            tv_vip_state.setText("您正在使用正版软件(有效期99年)");
            iv_vip_mark.setVisibility(View.GONE);


        }


    }


}
