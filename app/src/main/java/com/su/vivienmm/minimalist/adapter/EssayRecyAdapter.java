package com.su.vivienmm.minimalist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopeer.shadow.ShadowView;
import com.su.vivienmm.minimalist.R;
import com.su.vivienmm.minimalist.base.BaseRecyclerViewAdapter;
import com.su.vivienmm.minimalist.data.MemoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chinaso on 2017/11/30.
 */

public class EssayRecyAdapter extends BaseRecyclerViewAdapter<MemoEntity> {
    private List<MemoEntity> mList;

    public EssayRecyAdapter(List<MemoEntity> lists) {
        super(lists);
        mList = lists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_essay, null, false);
        return new EssayItemViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EssayItemViewHolder) holder).essayTv.setText(mList.get(position).getContent());
        ((EssayItemViewHolder) holder).essayDateTv.setText(mList.get(position).getDate());
    }

    public class EssayItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.essayTv)
        public TextView essayTv;
        @BindView(R.id.essayDateTv)
        public TextView essayDateTv;
        @BindView(R.id.shadow_view)
        ShadowView shadowView;

        public EssayItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
