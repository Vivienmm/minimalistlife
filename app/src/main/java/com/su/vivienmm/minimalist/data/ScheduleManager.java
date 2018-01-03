package com.su.vivienmm.minimalist.data;

import com.su.vivienmm.greendao.gen.ScheduleEntityDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinaso on 2017/12/1.
 */

public class ScheduleManager {
    private List<ScheduleEntity> scheList = new ArrayList<>();
    private ScheduleEntityDao entityDao;

    public ScheduleManager() {
        entityDao = GreenDaoManager.getInstance().getSession().getScheduleEntityDao();
        scheList = entityDao.queryBuilder().build().list();
    }

    public List<ScheduleEntity> getScheList() {
        return scheList;
    }

    public void saveScheList(List<ScheduleEntity> mList) {
        entityDao.deleteAll();
        for (int i = 0; i < mList.size(); i++) {
            ScheduleEntity item = mList.get(i);
            entityDao.insert(item);
        }
    }


}
