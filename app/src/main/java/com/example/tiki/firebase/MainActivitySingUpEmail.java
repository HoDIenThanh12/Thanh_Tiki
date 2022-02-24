package com.example.tiki.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivitySingUpEmail extends AppCompatActivity {
    private TextView txtEU, txtPU, txtPUR,txtSUL;
    private Button btnSU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sing_up);
        inits();
    }

    private void inits() {
        txtEU=findViewById(R.id.txt_EmailU);
        txtPU=findViewById(R.id.txt_PassU);
        txtPUR=findViewById(R.id.txt_PassUR);
        btnSU=findViewById(R.id.btn_SingUp);
        txtSUL=findViewById(R.id.tv_Lgin);
        txtSUL.setOnClickListener(v->{
            AuthEmails a=new AuthEmails().getInstant();
            a.Navigations(this, MainActivitySinginEmail.class);
        });
        btnSU.setOnClickListener(v->{
            onClickSU();
        });
    }

    private void onClickSU() {
        if(txtPU.getText().toString().trim().equals(txtPUR.getText().toString().trim())){

        }
        String e=txtEU.getText().toString().trim();
        String p=txtPU.getText().toString().trim();
        Toast.makeText(MainActivitySingUpEmail.this,
                "classname: "+MainActivitySingUpEmail.this.getClass().getSimpleName(),
                                    Toast.LENGTH_SHORT).show();
        AuthEmails a =new AuthEmails().getInstant();
        a.Singins(this,"thanh1245@gmail.com","123456", MainActivitySinginEmail.class);
    }
}