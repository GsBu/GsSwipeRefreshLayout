package com.example.gs.gsswiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.gs.gsswiperefreshlayout.adapter.GsListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private static final String TAG = ListViewActivity.class.getSimpleName();

    private ListView lv1;
    private SwipeRefreshLayout srl1;

    private List<String> mDataList3 = new ArrayList<>();
    private GsListViewAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lv1 = findViewById(R.id.lv_1);
        srl1 = findViewById(R.id.srl_1);

        //srl1.setProgressViewOffset(false, 30, 100);
        //srl1.setDistanceToTriggerSync(600);
        //srl1.setProgressViewEndTarget(false, 400);
        //srl1.setProgressViewEndTarget(true, srl1.getProgressViewEndOffset());

        initData();
        mAdapter3 = new GsListViewAdapter(this, R.layout.item_gs, mDataList3);
        initView();
        initSwipeRefreshLayout();
    }

    private void initData(){
        for(int i = 0; i < 7; i++){
            mDataList3.add("数据" + i);
        }
    }

    private void initView(){
        lv1.setAdapter(mAdapter3);
    }

    private void initSwipeRefreshLayout() {
        srl1.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
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
                srl1.setRefreshing(false);
            }
        }, 2000);
    }
}
