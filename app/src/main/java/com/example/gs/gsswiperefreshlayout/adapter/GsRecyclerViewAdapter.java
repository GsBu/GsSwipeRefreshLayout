package com.example.gs.gsswiperefreshlayout.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gs.gsswiperefreshlayout.R;

import java.util.List;

/**
 * @author longlong.bu
 * @since 2018/8/14
 */
public class GsRecyclerViewAdapter extends
        RecyclerView.Adapter<GsRecyclerViewAdapter.GsViewHolder>{

    private List<String> mDataList;
    private Context mContext;

    public GsRecyclerViewAdapter(){

    }

    public GsRecyclerViewAdapter(Context context, List data){
        mContext = context;
        mDataList = data;
    }

    @NonNull
    @Override
    public GsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_gs, parent, false);
        GsViewHolder holder = new GsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GsViewHolder holder, int position) {
        if(mDataList == null){
            return;
        }

        holder.tv1.setText(mDataList.get(position));

        if(position % 2 == 0){
            holder.llRootView.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.LEMONCHIFFON));
        }else {
            holder.llRootView.setBackgroundColor(mContext.getResources()
                    .getColor(R.color.LIGHTSTEELBLUE));
        }
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    class GsViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout llRootView;
        private TextView tv1;

        public GsViewHolder(View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv_1);
            llRootView = itemView.findViewById(R.id.ll_root_view);
        }
    }
}
