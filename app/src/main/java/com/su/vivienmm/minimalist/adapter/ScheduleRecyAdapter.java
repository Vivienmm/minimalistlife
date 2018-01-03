package com.su.vivienmm.minimalist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.su.vivienmm.minimalist.R;
import com.su.vivienmm.minimalist.base.BaseRecyclerViewAdapter;
import com.su.vivienmm.minimalist.data.ScheduleEntity;
import com.su.vivienmm.minimalist.view.HorizontalProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chinaso on 2017/11/30.
 */

public class ScheduleRecyAdapter extends BaseRecyclerViewAdapter<ScheduleEntity> {
    private List<ScheduleEntity> mSList;

    public ScheduleRecyAdapter(List<ScheduleEntity> lists) {
        super(lists);
        mSList = lists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_schedule, null, false);

        return new ScheItemViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ScheItemViewHolder) holder).cardTitleTv.setText(mSList.get(position).getTitle());
        ((ScheItemViewHolder) holder).dateTv.setText(mSList.get(position).getDate());
       // System.out.println("curr--"+mSList.get(position).getCurrentNum()+"total--"+mSList.get(position).getTotalNum()+"percent"+(float)(mSList.get(position).getCurrentNum()/mSList.get(position).getTotalNum()));
        ((ScheItemViewHolder) holder).progressBar.setProgressWithAnimation(mSList.get(position).getCurrentNum()*100/mSList.get(position).getTotalNum());

    }

    public class ScheItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardTitleTv)
        TextView cardTitleTv;
        @BindView(R.id.dateTv)
        TextView dateTv;
        @BindView(R.id.hintTv)
        TextView hintTv;
        @BindView(R.id.progressBar)
        HorizontalProgressBar progressBar;

        public ScheItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
