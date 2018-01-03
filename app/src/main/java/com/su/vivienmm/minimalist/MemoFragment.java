package com.su.vivienmm.minimalist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.internal.ThemeSingleton;
import com.su.vivienmm.minimalist.adapter.DividerListItemDecoration;
import com.su.vivienmm.minimalist.adapter.MemoRecyAdapter;
import com.su.vivienmm.minimalist.adapter.OnRecyclerItemClickListener;
import com.su.vivienmm.minimalist.adapter.RecyItemTouchHelperCallback;
import com.su.vivienmm.minimalist.base.BaseFragment;
import com.su.vivienmm.minimalist.data.FileCacheUtil;
import com.su.vivienmm.minimalist.data.MemoEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chinaso on 2017/11/28.
 */

public class MemoFragment extends BaseFragment {
    @BindView(R.id.memoRecy)
    RecyclerView memoRecy;
    @BindView(R.id.addFloatBtn)
    FloatingActionButton addFloatBtn;
    Unbinder unbinder;
    List<MemoEntity> mStringList;
    private MemoRecyAdapter mRecyAdapter;
    private EditText contentInput;
    private String cachePath = "memo_cache";
    private FileCacheUtil cacheUtil;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_memo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        addFloatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMemo();
            }
        });
        cacheUtil = new FileCacheUtil(getActivity(), cachePath);
        initRecy();
        return rootView;
    }

    private void addNewMemo() {
        MaterialDialog dialog =
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.memo_dia_title)
                        .customView(R.layout.add_memo_dialog, true)
                        .positiveText(R.string.memo_dia_positive_btn)
                        .negativeText(android.R.string.cancel)
                        .onPositive(
                                (dialog1, which) -> addContent())
                        .build();

        final View positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
        //noinspection ConstantConditions
        contentInput = dialog.getCustomView().findViewById(R.id.contentEdt);
        contentInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        positiveAction.setEnabled(s.toString().trim().length() > 0);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });

        int widgetColor = ThemeSingleton.get().widgetColor;
        MDTintHelper.setTint(
                contentInput,
                widgetColor == 0 ? ContextCompat.getColor(getActivity(), R.color.colorAccent) : widgetColor);

        dialog.show();
        positiveAction.setEnabled(false); // disabled by default
    }

    private void addContent() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        java.util.Date date = new java.util.Date();
        String str = sdf.format(date);

        mStringList.add(new MemoEntity(str, contentInput.getText().toString()));
        mRecyAdapter.notifyDataSetChanged();

        Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
    }


    private void initRecy() {
        initMemoList();
        mRecyAdapter = new MemoRecyAdapter(getActivity(), mStringList);

        memoRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        memoRecy.addItemDecoration(new DividerListItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        memoRecy.setHasFixedSize(true);

        RecyItemTouchHelperCallback itemTouchHelperCallback = new RecyItemTouchHelperCallback(mRecyAdapter);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(memoRecy);

        memoRecy.addOnItemTouchListener(new OnRecyclerItemClickListener(memoRecy) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                MemoRecyAdapter.MemoItemViewHolder mViewHolder = (MemoRecyAdapter.MemoItemViewHolder) viewHolder;
                String tvString = mViewHolder.contentTv.getText().toString();
                Toast.makeText(getActivity(), "逗逗~" + tvString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                Toast.makeText(getActivity(), "" + "讨厌，不要老是摸人家啦...", Toast.LENGTH_SHORT).show();
            }
        });
        memoRecy.setAdapter(mRecyAdapter);
    }

    private void initMemoList() {
        if (mStringList == null) {
            mStringList = new ArrayList<>();
        }
        if (cacheUtil.readList() != null) {
            mStringList.addAll(cacheUtil.readList());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        cacheUtil.clearList();
        cacheUtil.saveList(mRecyAdapter.getLists());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
