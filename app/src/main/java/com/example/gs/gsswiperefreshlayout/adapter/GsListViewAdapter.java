package com.example.gs.gsswiperefreshlayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gs.gsswiperefreshlayout.R;

import java.util.List;

/**
 * 作者    你的名字
 * 时间    2018/12/7 16:42
 * 文件    GsSwipeRefreshLayout
 * 描述
 */
public class GsListViewAdapter extends ArrayAdapter<String>{
    private int mResourceId;

    public GsListViewAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //为子项动态加载布局
        View view = LayoutInflater.from(getContext()).inflate(mResourceId, null);
        TextView fruitName = (TextView) view.findViewById(R.id.tv_1);
        fruitName.setText(getItem(position));
        return view;
    }

}
