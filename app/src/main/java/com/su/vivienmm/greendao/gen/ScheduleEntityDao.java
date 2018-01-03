package com.su.vivienmm.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.su.vivienmm.minimalist.data.ScheduleEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SCHEDULE_ENTITY".
*/
public class ScheduleEntityDao extends AbstractDao<ScheduleEntity, Void> {

    public static final String TABLENAME = "SCHEDULE_ENTITY";

    /**
     * Properties of entity ScheduleEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property OrderId = new Property(0, int.class, "orderId", false, "ORDER_ID");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Date = new Property(2, String.class, "date", false, "DATE");
        public final static Property TotalNum = new Property(3, int.class, "totalNum", false, "TOTAL_NUM");
        public final static Property CurrentNum = new Property(4, int.class, "currentNum", false, "CURRENT_NUM");
    }


    public ScheduleEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ScheduleEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SCHEDULE_ENTITY\" (" + //
                "\"ORDER_ID\" INTEGER NOT NULL ," + // 0: orderId
                "\"TITLE\" TEXT," + // 1: title
                "\"DATE\" TEXT," + // 2: date
                "\"TOTAL_NUM\" INTEGER NOT NULL ," + // 3: totalNum
                "\"CURRENT_NUM\" INTEGER NOT NULL );"); // 4: currentNum
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_SCHEDULE_ENTITY_ORDER_ID_DATE_DESC ON \"SCHEDULE_ENTITY\"" +
                " (\"ORDER_ID\" ASC,\"DATE\" DESC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SCHEDULE_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ScheduleEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getOrderId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
        stmt.bindLong(4, entity.getTotalNum());
        stmt.bindLong(5, entity.getCurrentNum());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ScheduleEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getOrderId());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(3, date);
        }
        stmt.bindLong(4, entity.getTotalNum());
        stmt.bindLong(5, entity.getCurrentNum());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public ScheduleEntity readEntity(Cursor cursor, int offset) {
        ScheduleEntity entity = new ScheduleEntity( //
            cursor.getInt(offset + 0), // orderId
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // date
            cursor.getInt(offset + 3), // totalNum
            cursor.getInt(offset + 4) // currentNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ScheduleEntity entity, int offset) {
        entity.setOrderId(cursor.getInt(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDate(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTotalNum(cursor.getInt(offset + 3));
        entity.setCurrentNum(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(ScheduleEntity entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(ScheduleEntity entity) {
        return null;
    }

    @Override
    public boolean hasKey(ScheduleEntity entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}