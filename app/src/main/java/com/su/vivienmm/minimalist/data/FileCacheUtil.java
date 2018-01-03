package com.su.vivienmm.minimalist.data;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinaso on 2017/12/1.
 */

public class FileCacheUtil<T> {
    private Context mContext;
    private String cachePath;
    public  FileCacheUtil(Context context,String path){
        mContext=context;
        cachePath=path;
    }
    public void saveList( List<T> datas) {
        if (null == datas) {
            return;
        }
        ObjectOutputStream oos = null;
        try {
            FileOutputStream stream = mContext.openFileOutput(cachePath, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(stream);
            oos.writeObject(datas);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List readList() {
        List<T> datas = null;
        ObjectInputStream ois = null;
        FileInputStream stream;
        try {
            stream = mContext.openFileInput(cachePath);
            ois = new ObjectInputStream(stream);
            datas = (ArrayList<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return datas;
    }

    public void clearList() {
        File mFile = mContext.getFileStreamPath(cachePath);
        if (mFile.exists()) {
            mFile.delete();
        }
    }
}
