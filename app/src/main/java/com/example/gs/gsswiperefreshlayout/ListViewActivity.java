package com.example.gs.gsswiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.gs.gsswiperefreshlayout.adapter.GsListViewAdapter;
import com.example.gs.gsswiperefreshlayout.gs.GsSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private static final String TAG = ListViewActivity.class.getSimpleName();

    private ListView lv1;
    private GsSwipeRefreshLayout srl3;

    private List<String> mDataList3 = new ArrayList<>();
    private GsListViewAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lv1 = findViewById(R.id.lv_1);
        srl3 = findViewById(R.id.srl_3);

        initData();
        mAdapter3 = new GsListViewAdapter(this, R.layout.item_gs, mDataList3);
        initView();
        initSwipeRefreshLayout();
    }

    private void initData(){
        for(int i = 0; i < 3; i++){
            mDataList3.add("数据" + i);
        }
    }

    private void initView(){
        lv1.setAdapter(mAdapter3);
    }

    private void initSwipeRefreshLayout() {
        srl3.setOnRefreshListener(
                new GsSwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法
                        Log.e(TAG, "刷新onRefresh");
                        addData3(0);
                    }
                }
        );
    }

    private void addData3(final int index){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDataList3.add(index, "增加"+mDataList3.size());
                mAdapter3.notifyDataSetChanged();
                srl3.setRefreshing(false);
            }
        }, 2000);
    }
}
