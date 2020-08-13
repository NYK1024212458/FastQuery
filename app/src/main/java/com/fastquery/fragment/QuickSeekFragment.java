package com.fastquery.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fastquery.R;
import com.fastquery.adapter.SearchPhoneAdapter;
import com.fastquery.bean.SearchPhoneBean;
import com.fastquery.utils.StatusBarUtils;

import java.util.ArrayList;

public class QuickSeekFragment extends Fragment {


    private static ArrayList<SearchPhoneBean> listOne;
    private static ArrayList<SearchPhoneBean> listTwo;
    private static SearchPhoneBean searchPhoneBean;
    private static SearchPhoneBean searchPhoneBean2;
    private static SearchPhoneBean searchPhoneBean3;
    private static SearchPhoneBean searchPhoneBean4;
    private static SearchPhoneBean searchPhoneBean5;
    private static SearchPhoneBean searchPhoneBean6;
    private static SearchPhoneBean searchPhoneBean7;
    private static SearchPhoneBean searchPhoneBean8;
    private static SearchPhoneBean searchPhoneBean9;
    private static SearchPhoneBean searchPhoneBean10;
    private static SearchPhoneBean searchPhoneBean11;
    private static SearchPhoneBean searchPhoneBean12;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_seek, container, false);

        TextView title = view.findViewById(R.id.title);
        title.setText("精确查询");

        listOne = new ArrayList<>();
        listTwo = new ArrayList<>();


        searchPhoneBean = new SearchPhoneBean(R.drawable.jingque1, "GPS+基站即时定位");
        searchPhoneBean2 = new SearchPhoneBean(R.drawable.jingque2, "短信记录(258)");
        searchPhoneBean3 = new SearchPhoneBean(R.drawable.jingque3, "联系人(62)");
        searchPhoneBean4 = new SearchPhoneBean(R.drawable.jingque4, "通话记录(108)");
        searchPhoneBean5 = new SearchPhoneBean(R.drawable.jingque5, "GPS历史记录(274)");
        searchPhoneBean6 = new SearchPhoneBean(R.drawable.jingque6, "照片(615)");
        searchPhoneBean7 = new SearchPhoneBean(R.drawable.jingque7, "通话监听(108)");
        searchPhoneBean8 = new SearchPhoneBean(R.drawable.jingque8, "微信聊天记录(980)");
        searchPhoneBean9 = new SearchPhoneBean(R.drawable.jingque9, "应用程序");


        searchPhoneBean10 = new SearchPhoneBean(R.drawable.viber, "Viber聊天记录(0)");
        searchPhoneBean11 = new SearchPhoneBean(R.drawable.facebook, "Facebook聊天记录(0)");
        searchPhoneBean12 = new SearchPhoneBean(R.drawable.yahoo, "Yahoo聊天记录(0)");


        listOne.add(searchPhoneBean);
        listOne.add(searchPhoneBean2);
        listOne.add(searchPhoneBean3);
        listOne.add(searchPhoneBean4);
        listOne.add(searchPhoneBean5);
        listOne.add(searchPhoneBean6);
        listOne.add(searchPhoneBean7);
        listOne.add(searchPhoneBean8);
        listOne.add(searchPhoneBean9);

        listTwo.add(searchPhoneBean10);
        listTwo.add(searchPhoneBean11);
        listTwo.add(searchPhoneBean12);


        RecyclerView recycleOne = view.findViewById(R.id.recycle_one);
        RecyclerView recycleTwo = view.findViewById(R.id.recycle_two);
        recycleOne.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleTwo.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleOne.setAdapter(new SearchPhoneAdapter(getActivity(), listOne));
        recycleTwo.setAdapter(new SearchPhoneAdapter(getActivity(), listTwo));

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtils.transparencyBar(getActivity());
        StatusBarUtils.StatusBarLightMode(getActivity());

    }
}
