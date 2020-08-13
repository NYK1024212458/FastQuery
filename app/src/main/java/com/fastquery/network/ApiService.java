package com.fastquery.network;

import com.fastquery.model.CustomerModel;
import com.fastquery.model.FindLocationModel;
import com.fastquery.model.LoginModel;
import com.fastquery.model.GoVerifyModel;
import com.fastquery.model.PublicWithMsgModel;
import com.fastquery.model.UserDataModel;
import com.fastquery.model.VerificationCodeModel;
import com.fastquery.model.VipPriceModel;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    /**
     * 获取验证码
     *
     * @param phone 手机号码
     * @return
     */
    @FormUrlEncoded
    @POST("sms")
    Call<VerificationCodeModel> getVerificationCode(@Field("phone") String phone);


    /**
     * 登录
     */
  /*  @FormUrlEncoded
    @POST("verify")
    Call<LoginModel> goLogin(@Field("phone") String phone, @Field("code") String code);*/


    @FormUrlEncoded
    @POST("verify")
    Call<LoginModel> goLogin(@Field("phone") String phone, @Field("code") String code);


    /**
     * 上传本机位置
     */

    @FormUrlEncoded
    @POST("position")
    Call<LoginModel> location(@Field("phone") String phone, @Field("positionx") String positionx, @Field("positiony") String positiony);


    /**
     * 获取好友位置
     */


    @FormUrlEncoded
    @POST("get_position")
    Call<FindLocationModel> getFriendAddress(@Field("phone") String phone, @Field("user_phone") String user_phone);


    /**
     * 个人中心页面去校验接口
     */


    @FormUrlEncoded
    @POST("key_code")
    Call<GoVerifyModel> goVerify(@Field("phone") String phone, @Field("code") String code);


    /**
     * 演示视频
     */


    @FormUrlEncoded
    @POST("about")
    Call<PublicWithMsgModel> videoAndCustomer(@Field("id") String id);


    /**
     * 客服图片
     */

    @POST("Kefu")
    Call<CustomerModel> customerInfo();


    /**
     * 查询用户信息
     */
    @FormUrlEncoded
    @POST("user_data")
    Call<UserDataModel> getUserData(@Field("phone") String phone);


    /**
     * 激活用户
     */
    @FormUrlEncoded
    @POST("key_code")
    Call<PublicWithMsgModel> activeUser(@Field("phone") String phone, @Field("code") String code);


    /**
     * 会员价格
     * */

    @FormUrlEncoded
    @POST("money_month")
    Call<VipPriceModel> getVipPrice(@Field("id")String id);
}