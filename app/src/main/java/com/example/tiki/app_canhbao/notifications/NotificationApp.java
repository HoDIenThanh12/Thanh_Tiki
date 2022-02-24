package com.example.tiki.app_canhbao.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class NotificationApp extends Application {
    public final String CHANEL_1_ID="ID1";
    public final String CHANEL_2_ID="ID2";

    @Override
    public void onCreate() {
        super.onCreate();
        notificatios();
    }

    private void notificatios() {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            NotificationChannel chanel_1=new NotificationChannel(CHANEL_1_ID, "Thông báo đẩy",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService(NotificationManager.class);
            if(manager!=null)
                manager.createNotificationChannel(chanel_1);
        }
    }
}
