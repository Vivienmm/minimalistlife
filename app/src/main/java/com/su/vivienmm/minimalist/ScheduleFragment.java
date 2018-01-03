package com.su.vivienmm.minimalist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.su.vivienmm.minimalist.adapter.OnRecyclerItemClickListener;
import com.su.vivienmm.minimalist.adapter.RecyItemTouchHelperCallback;
import com.su.vivienmm.minimalist.adapter.ScheduleRecyAdapter;
import com.su.vivienmm.minimalist.base.BaseFragment;
import com.su.vivienmm.minimalist.data.ScheduleEntity;
import com.su.vivienmm.minimalist.data.ScheduleManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chinaso on 2017/11/28.
 */

public class ScheduleFragment extends BaseFragment {
    @BindView(R.id.scheRecy)
    RecyclerView scheRecy;
    @BindView(R.id.addScheFB)
    FloatingActionButton addScheFB;
    Unbinder unbinder;
    List<ScheduleEntity> mScheduleList = new ArrayList<>();
    private ScheduleRecyAdapter mRecyAdapter;
    private EditText totalNumEdt;
    private EditText currentNumEdt;
    private EditText contentEdt;
    private ScheduleManager manager;
    private ScheduleEntity modifyEntity;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_schedule;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        addScheFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewSchedule(mScheduleList.size());
            }
        });
        manager = new ScheduleManager();
        initRecy();
        return rootView;
    }

    private void addNewSchedule(int id) {
        MaterialDialog dialog =
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.sch_dia_title)
                        .customView(R.layout.add_sche_dialog, true)
                        .positiveText(R.string.sch_dia_positive_btn)
                        .negativeText(android.R.string.cancel)
                        .onPositive(
                                (dialog1, which) -> getTask(id))
                        .build();

        final View positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        //noinspection ConstantConditions
        contentEdt = dialog.getCustomView().findViewById(R.id.contentEdt);
        totalNumEdt = dialog.getCustomView().findViewById(R.id.totalEdt);
        currentNumEdt = dialog.getCustomView().findViewById(R.id.currentEdt);
        dialog.show();
    }

    private void getTask(int id) {
        String title = contentEdt.getText().toString();
        int total = Integer.parseInt(totalNumEdt.getText().toString());
        int current = Integer.parseInt(currentNumEdt.getText().toString());

        if (id <= mScheduleList.size() - 1) {
            mScheduleList.remove(modifyEntity.getOrderId());
            modifyEntity.setCurrentNum(current);
            modifyEntity.setTitle(title);
            modifyEntity.setTotalNum(total);
            mScheduleList.add(modifyEntity.getOrderId(), modifyEntity);
        } else {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            java.util.Date date = new java.util.Date();
            String str = sdf.format(date);
            ScheduleEntity newEntity = new ScheduleEntity(mScheduleList.size(), title, str, total, current);
            mScheduleList.add(newEntity);
        }

        mRecyAdapter.notifyDataSetChanged();
    }

    private void initRecy() {
        mScheduleList.addAll(manager.getScheList());
        mRecyAdapter = new ScheduleRecyAdapter(mScheduleList);

        scheRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        scheRecy.setHasFixedSize(true);
        RecyItemTouchHelperCallback itemTouchHelperCallback = new RecyItemTouchHelperCallback(mRecyAdapter);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(scheRecy);

        scheRecy.addOnItemTouchListener(new OnRecyclerItemClickListener(scheRecy) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                ScheduleRecyAdapter.ScheItemViewHolder mViewHolder = (ScheduleRecyAdapter.ScheItemViewHolder) viewHolder;
                ScheduleEntity entity = mScheduleList.get(mViewHolder.getAdapterPosition());
                modifySchedule(entity);
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(getActivity(), "" + "讨厌，不要老是摸人家啦...", Toast.LENGTH_SHORT).show();
            }
        });
        scheRecy.setAdapter(mRecyAdapter);
    }

    private void modifySchedule(ScheduleEntity entity) {
        modifyEntity = entity;
        addNewSchedule(entity.getOrderId());
        contentEdt.setText(entity.getTitle());
        currentNumEdt.setText(entity.getCurrentNum() + "");
        totalNumEdt.setText(entity.getTotalNum() + "");
    }

    @Override
    public void onPause() {
        super.onPause();
        manager.saveScheList(mRecyAdapter.getLists());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
