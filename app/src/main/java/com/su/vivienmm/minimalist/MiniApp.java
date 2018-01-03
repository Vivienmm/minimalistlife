package com.su.vivienmm.minimalist;

import android.app.Application;

/**
 * Created by chinaso on 2017/12/1.
 */

public class MiniApp extends Application {
    private static MiniApp miniApp;


    @Override
    public void onCreate() {
        super.onCreate();
        miniApp=this;
    }
    public static MiniApp getMiniApp() {
        return miniApp;
    }

    public static void setMiniApp(MiniApp miniApp) {
        MiniApp.miniApp = miniApp;
    }
}
