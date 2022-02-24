package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.User;
import com.example.tiki.databinding.ActivityMainRegisterBinding;

public class MainActivityRegister extends AppCompatActivity {
    private ActivityMainRegisterBinding binding;
    private AuthAccount account;
    private TextView tvBackL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_register);
        //1:User người dùng, 1:SV sinh viên, 2:GV giảng viên.
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main_register );
        action();
    }
    private void action() {
        account=AuthAccount.getInstant();
        binding.cirRegisterButtonDK.setOnClickListener(v->{
            RegisterUser();
        });
        binding.btnimgRegister.setOnClickListener(v->{resumLog();});
        binding.tvBackL.setOnClickListener(v->{resumLog();});
    }
    private void resumLog() {
        finish();
    }

    private void RegisterUser(){
        String e=binding.edtEmail.getText().toString().trim();
        String p=binding.edtPass.getText().toString().trim();
        String n=binding.edtName.getText().toString().trim();
        String pe=binding.edtPassEnter.getText().toString().trim();
        User u ;
        int gt;
        int idRB=binding.grCategory.getCheckedRadioButtonId();
//        if(idRB == binding.rdsSV.getId()){
//            gt=1;
//        }
//        else{
//            gt=2;
//        }
        gt = idRB == binding.rdsSV.getId() ?1:2;
        if(!CheckInput(n,e,p,pe)){
            return;
        }
        u=new User(n,e,p,gt);
        //u=new User();
        account.Register(this, u, MainActivityLogIn.class);
        Toast.makeText(this, "Tạo mới thành công", Toast.LENGTH_SHORT).show();
    }
    private boolean CheckInput(String n, String e, String p, String pe ){

        if(e.equals("")||p.equals("")||n.equals("") || pe.equals("")){
            Toast.makeText(this, "Còn thông tin chưa nhập?", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            if(checkEmail(e)){
                if(p.equals(pe)){
                    return true;
                }
                else {
                    Toast.makeText(this, "PassWord nhập lại chưa đúng?", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else {
                Toast.makeText(this, "Email chưa đúng định dạng?", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
    private boolean checkEmail(String e){
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if (e.matches(emailPattern))
            return true;
        return false;
    }
}