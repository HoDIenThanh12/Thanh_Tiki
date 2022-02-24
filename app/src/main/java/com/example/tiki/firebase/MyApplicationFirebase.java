package com.example.tiki.firebase;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplicationFirebase extends Application {
    public static final String Channel_ID="pushNotification";
    @Override
    public void onCreate() {
        super.onCreate();
        createChanneNotification();
    }

    //@SuppressLint("ObsoleteSdkInt")
    private void createChanneNotification() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(Channel_ID,
                    "Channel Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);
            //distable music defaul
//            channel.setSound(null, null);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if(manager!=null)
                manager.createNotificationChannel(channel);
        }
    }
}
