package com.su.vivienmm.minimalist.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by chinaso on 2017/12/1.
 */

public class MemoEntity implements Serializable, Parcelable {
    String date;
    String content;

    public MemoEntity(String date, String content) {
        this.date = date;
        this.content = content;
    }

    protected MemoEntity(Parcel in) {
        date = in.readString();
        content = in.readString();
    }

    public static final Creator<MemoEntity> CREATOR = new Creator<MemoEntity>() {
        @Override
        public MemoEntity createFromParcel(Parcel in) {
            return new MemoEntity(in);
        }

        @Override
        public MemoEntity[] newArray(int size) {
            return new MemoEntity[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(content);
    }
}
