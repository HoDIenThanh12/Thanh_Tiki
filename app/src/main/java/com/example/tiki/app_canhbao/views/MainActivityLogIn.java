package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.databinding.ActivityMainLogInBinding;

public class MainActivityLogIn extends AppCompatActivity {
    private ActivityMainLogInBinding binding;
    private AuthAccount account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_log_in);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_log_in);
        binding.edtEmail.setText("thanh123@gmail.com");
        binding.edtPass.setText("123456");
        binding.cirLoginButtonDangnhap.setOnClickListener(v->{
            actionLog();
        });
        binding.btnRegister.setOnClickListener(v->{
            actionRegister();
        });
    }

    private void actionRegister() {
        account=AuthAccount.getInstant();
        account.Navigations(this, MainActivityRegister.class);
    }

    private void actionLog() {
        account=AuthAccount.getInstant();
        String e= binding.edtEmail.getText().toString().trim();
        String p= binding.edtPass.getText().toString().trim();
        if(!checkEmail(e)){
            Toast.makeText(this, "Lỗi email hoặc mật khẩu?", Toast.LENGTH_SHORT).show();
            return;
        }
        account.Log(this, e,p, MainActivityHomes.class);
    }
    private boolean checkEmail(String e){
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (e.matches(emailPattern))
            return true;
        return false;
    }
}