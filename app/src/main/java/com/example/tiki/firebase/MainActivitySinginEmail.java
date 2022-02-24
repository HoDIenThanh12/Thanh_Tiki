package com.example.tiki.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiki.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivitySinginEmail extends AppCompatActivity {
    private TextView txtE, txtP, txtS;
    Button btnLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_singin_email);
        inits();
    }

    private void inits() {
        txtE=findViewById(R.id.txt_Email);
        txtP=findViewById(R.id.txt_Pass);
        txtS=findViewById(R.id.tv_Singin);
        btnLog=findViewById(R.id.btn_Login);
        btnLog =findViewById(R.id.btn_Login);
        btnLog.setOnClickListener(v->{
            Logins();
        });
        txtS.setOnClickListener(v->{
            AuthEmails a=new AuthEmails().getInstant();
            a.Navigations(this,MainActivitySingUpEmail.class);
        });
    }
    private void Logins(){
        String e=txtE.getText().toString().trim();
        String p=txtP.getText().toString().trim();
        btnLog.setOnClickListener(v->{
//            AuthEmails a=new AuthEmails().getInstant();
//            a.Log(this, e,p, MainActivityHomes.class);
//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference("message");
//
//            myRef.push().setValue("dsfsdfsdfsd");
        });
    }
}