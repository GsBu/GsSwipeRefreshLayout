package com.example.gs.gsswiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gs.gsswiperefreshlayout.adapter.GsRecyclerViewAdapter;
import com.example.gs.gsswiperefreshlayout.gs.GsSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView rvRecyclerView;
    private GsSwipeRefreshLayout srlSwipeRefreshLayout;

    private List<String> mDataList = new ArrayList<>();
    private GsRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRecyclerView = findViewById(R.id.rv_1);
        srlSwipeRefreshLayout = findViewById(R.id.srl_1);

        initData();
        mAdapter = new GsRecyclerViewAdapter(this, mDataList);
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initData(){
        for(int i = 0; i < 10; i++){
            mDataList.add("数据" + i);
        }
    }

    private void initRecyclerView(){
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvRecyclerView.setAdapter(mAdapter);
    }

    private void initSwipeRefreshLayout() {
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        srlSwipeRefreshLayout.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        srlSwipeRefreshLayout.setSize(GsSwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srlSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // 通过 setEnabled(false) 禁用下拉刷新
        //srlSwipeRefreshLayout.setEnabled(false);

        // 设定下拉圆圈的背景
        srlSwipeRefreshLayout.setProgressBackgroundColor(R.color.RED);

        /*
         * 设置手势下拉刷新的监听
         */
        srlSwipeRefreshLayout.setOnRefreshListener(
                new GsSwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法
                        Log.e(TAG, "刷新onRefresh");
                        addData(0);
                    }
                }
        );
    }

    private void addData(final int index){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataList.add(index, "增加"+mDataList.size());
                mAdapter.notifyDataSetChanged();
                srlSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
