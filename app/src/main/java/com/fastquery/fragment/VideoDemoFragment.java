package com.fastquery.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fastquery.R;
import com.fastquery.model.PublicWithMsgModel;
import com.fastquery.network.HttpTools;
import com.fastquery.utils.StatusBarUtils;
import com.fastquery.utils.UnicodeConvertString;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoDemoFragment extends Fragment implements View.OnClickListener {

    private VideoView viewView;
    private ImageView onOrOff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video_demo, null);
        TextView title = view.findViewById(R.id.title);
        title.setText("演示视频");
        getVideoPath();
        //Toast.makeText(getActivity(),"视频地址为:"+videoPath,Toast.LENGTH_SHORT).show();


        viewView = view.findViewById(R.id.video_view);


        onOrOff = view.findViewById(R.id.iv_on_off);
        onOrOff.setOnClickListener(this);

        return view;

    }

    private void getVideoPath() {



        Call<PublicWithMsgModel> publicWithMsgModelCall = HttpTools.getApiService().videoAndCustomer("5");
        publicWithMsgModelCall.enqueue(new Callback<PublicWithMsgModel>() {
            @Override
            public void onResponse(Call<PublicWithMsgModel> call, Response<PublicWithMsgModel> response) {
                if (response.body()!=null){


                    String code = UnicodeConvertString.unicodeToCn(response.body().getCode());

                    if (code.equals("返回演示视频")){

                        String msg = response.body().getMsg();
                        //Toast.makeText(getActivity(),"请求视频成功",Toast.LENGTH_SHORT).show();
                        viewView.setVideoURI(Uri.parse("http://2624375038.top/static/uploads/"+response.body().getMsg()));

                    }


                }
            }

            @Override
            public void onFailure(Call<PublicWithMsgModel> call, Throwable t) {

                Toast.makeText(getActivity(),"请求失败"+t.toString(),Toast.LENGTH_SHORT).show();
            }


        });





    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.iv_on_off:

                if (viewView.isPlaying()){
                    onOrOff.setBackgroundResource(R.drawable.bofang);
                    viewView.pause();

                }else {
                    onOrOff.setBackgroundResource(R.drawable.pause);
                    viewView.start();

                }

                break;




        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StatusBarUtils.transparencyBar(getActivity());
        StatusBarUtils.StatusBarLightMode(getActivity());
    }
}
