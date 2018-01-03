package com.su.vivienmm.minimalist.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015-11-27.
 */
public abstract class BaseFragment extends Fragment {
    public View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView != null) {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null)
                parent.removeView(mRootView);
        } else {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ButterKnife.bind(this, mRootView);
            if (savedInstanceState != null) {
                onRestartInstance(savedInstanceState);
            }
            initWidget();
        }
        return mRootView;
    }

    protected void initWidget() {

    }

    protected abstract int getLayoutId();

    protected void onRestartInstance(Bundle bundle) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
