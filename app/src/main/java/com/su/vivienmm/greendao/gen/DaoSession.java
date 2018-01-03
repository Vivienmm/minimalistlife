package com.su.vivienmm.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.su.vivienmm.minimalist.data.ScheduleEntity;

import com.su.vivienmm.greendao.gen.ScheduleEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig scheduleEntityDaoConfig;

    private final ScheduleEntityDao scheduleEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        scheduleEntityDaoConfig = daoConfigMap.get(ScheduleEntityDao.class).clone();
        scheduleEntityDaoConfig.initIdentityScope(type);

        scheduleEntityDao = new ScheduleEntityDao(scheduleEntityDaoConfig, this);

        registerDao(ScheduleEntity.class, scheduleEntityDao);
    }
    
    public void clear() {
        scheduleEntityDaoConfig.clearIdentityScope();
    }

    public ScheduleEntityDao getScheduleEntityDao() {
        return scheduleEntityDao;
    }

}
