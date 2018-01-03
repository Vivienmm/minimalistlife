package com.su.vivienmm.minimalist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.su.vivienmm.minimalist.R;
import com.su.vivienmm.minimalist.base.BaseRecyclerViewAdapter;
import com.su.vivienmm.minimalist.data.MemoEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chinaso on 2017/11/29.
 */

public class MemoRecyAdapter extends BaseRecyclerViewAdapter<MemoEntity> {
    private List<MemoEntity> mList;
    private Context mContext;

    public MemoRecyAdapter(Context context, List<MemoEntity> lists) {
        super(lists);
        mContext = context;
        mList = lists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_memo, null, false);
        return new MemoItemViewHolder(mView);
}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MemoItemViewHolder) {
            ((MemoItemViewHolder) holder).contentTv.setText(mList.get(position).getContent());
        }
    }


   public class MemoItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contentTv)
       public TextView contentTv;

        public MemoItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
