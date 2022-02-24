package com.example.tiki.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tiki.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivityFirebase1 extends AppCompatActivity {
    public static final String Channel_ID="pushNotification";
    public static final String TAG =MainActivityFirebase1.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_firebase1);
        //createChanneNotification();
        //getTest();
    }
    private void getTest(){
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d(TAG,"data---> " +token);
                        Toast.makeText(MainActivityFirebase1.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }
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