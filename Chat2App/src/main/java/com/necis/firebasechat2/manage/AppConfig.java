package com.necis.firebasechat2.manage;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Jarcode on 2016-03-27.
 */
public class AppConfig extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
