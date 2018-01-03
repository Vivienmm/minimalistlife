package com.su.vivienmm.minimalist.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;

import java.io.Serializable;

import static java.security.PublicKey.serialVersionUID;

/**
 * Created by chinaso on 2017/11/30.
 */
@Entity(indexes = {
@Index(value = "orderId, date DESC", unique = true)
})

public class ScheduleEntity implements Serializable, Parcelable {
    static final long serialVersionUID = 42L;
    private String date;
    private String title;
    private int totalNum;
    private int currentNum;
    private int orderId;

    @Keep
    public ScheduleEntity(int orderId,String title, String date, int totalNum, int currentNum) {
       this.orderId=orderId;
        this.title = title;
        this.date = date;
        this.date = date;
        this.totalNum = totalNum;
        this.currentNum = currentNum;
    }

    @Generated(hash = 908005657)
    public ScheduleEntity() {
    }

    protected ScheduleEntity(Parcel in) {
        date = in.readString();
        title = in.readString();
        totalNum = in.readInt();
        currentNum = in.readInt();
        orderId = in.readInt();
    }

    public static final Creator<ScheduleEntity> CREATOR = new Creator<ScheduleEntity>() {
        @Override
        public ScheduleEntity createFromParcel(Parcel in) {
            return new ScheduleEntity(in);
        }

        @Override
        public ScheduleEntity[] newArray(int size) {
            return new ScheduleEntity[size];
        }
    };

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(title);
        dest.writeInt(totalNum);
        dest.writeInt(currentNum);
        dest.writeInt(orderId);
    }
    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
