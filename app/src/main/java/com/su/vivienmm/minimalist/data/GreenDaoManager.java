package com.su.vivienmm.minimalist.data;


import com.su.vivienmm.greendao.gen.DaoMaster;
import com.su.vivienmm.greendao.gen.DaoSession;
import com.su.vivienmm.minimalist.MiniApp;

/**
 * Created by chinaso on 2016/10/11.
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public GreenDaoManager() {
      //  DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(SoAPP.getApp(), "notes-db", null);
       GreenDaoOpenHelper devOpenHelper = new GreenDaoOpenHelper(MiniApp.getMiniApp());
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            mInstance = new GreenDaoManager();
        }
        return mInstance;
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }
}
