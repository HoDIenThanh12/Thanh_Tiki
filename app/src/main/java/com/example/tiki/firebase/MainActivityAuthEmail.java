package com.example.tiki.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.tiki.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityAuthEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auth_email);
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActi();
            }
        }, 2000);
    }

    private void nextActi() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            //not login
            Log.d("Login---> ","fail");
        }
        else {
            //success login
            Log.d("Login---> ","success");
//            Intent intent=new Intent(this, MainActivityFirebase1.class);
//            startActivity(intent);
        }
    }
}