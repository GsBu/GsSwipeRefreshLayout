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
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView rv1, rv2;
    private GsSwipeRefreshLayout srl1, srl2;

    private List<String> mDataList1 = new ArrayList<>();
    private List<String> mDataList2 = new ArrayList<>();
    private GsRecyclerViewAdapter mAdapter1, mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv1 = findViewById(R.id.rv_1);
        rv2 = findViewById(R.id.rv_2);
        srl1 = findViewById(R.id.srl_1);
        srl2 = findViewById(R.id.srl_2);

        initData();
        mAdapter1 = new GsRecyclerViewAdapter(this, mDataList1);
        mAdapter2 = new GsRecyclerViewAdapter(this, mDataList2);
        initRecyclerView();
        initSwipeRefreshLayout();
    }

    private void initData(){
        for(int i = 0; i < 3; i++){
            mDataList1.add("数据" + i);
            mDataList2.add("数据" + i);
        }
    }

    private void initRecyclerView(){
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rv1.setAdapter(mAdapter1);

        rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        rv2.setAdapter(mAdapter2);
    }

    private void initSwipeRefreshLayout() {
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        //srlSwipeRefreshLayout.setProgressViewOffset(true, 50, 200);

        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        //srlSwipeRefreshLayout.setSize(GsSwipeRefreshLayout.LARGE);

        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        srl1.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        // 通过 setEnabled(false) 禁用下拉刷新
        //srlSwipeRefreshLayout.setEnabled(false);

        // 设定下拉圆圈的背景
        //srl1.setProgressBackgroundColor(R.color.RED);
        srl1.setProgressViewOffset(false, 30, 100);
        //srl1.setProgressViewEndTarget(true, srl1.getProgressViewEndOffset());

        /*
         * 设置手势下拉刷新的监听
         */
        srl1.setOnRefreshListener(
                new GsSwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法
                        Log.e(TAG, "刷新onRefresh");
                        addData1(0);
                    }
                }
        );
        srl2.setOnRefreshListener(
                new GsSwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法
                        Log.e(TAG, "刷新onRefresh");
                        addData2(0);
                    }
                }
        );
    }

    private void addData1(final int index){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataList1.add(index, "增加"+mDataList1.size());
                mAdapter1.notifyDataSetChanged();
                srl1.setRefreshing(false);
            }
        }, 2000);
    }

    private void addData2(final int index){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataList2.add(index, "增加"+mDataList2.size());
                mAdapter2.notifyDataSetChanged();
                srl2.setRefreshing(false);
            }
        }, 2000);
    }
}
