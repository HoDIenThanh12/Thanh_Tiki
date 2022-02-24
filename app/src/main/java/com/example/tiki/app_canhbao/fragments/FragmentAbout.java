package com.example.tiki.app_canhbao.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.MainActivityHomes;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.entity.User;

public class FragmentAbout extends Fragment {

    private View fView;
    private MainActivityHomes mMainActivityHomes;
    private EditText tvID, tvNameA, tvClass, tvMSSV, tvKhoa, tvCategory, tvEmail, tvPass;
    private RadioGroup rdsgCatelogy;
    private RadioButton rdstbn1, rdsbtn2;
    private Button btnUpdateEditor;
    private boolean isbtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_about, container, false);
        mMainActivityHomes= (MainActivityHomes) getActivity();
        tvNameA=fView.findViewById(R.id.tv_NameA);
        inits();
        getAboutAccount();
        return fView;
    }
    private void inits(){
        tvID=fView.findViewById(R.id.tv_IDAccountA);
        tvNameA=fView.findViewById(R.id.tv_NameA);
        tvKhoa=fView.findViewById(R.id.tv_KhoaA);
        tvClass=fView.findViewById(R.id.tv_ClassA);
        tvMSSV=fView.findViewById(R.id.tv_MSSVA);
        rdsgCatelogy=fView.findViewById(R.id.gr_CategoryA);
        tvEmail=fView.findViewById(R.id.tv_EmailA);
        tvPass=fView.findViewById(R.id.tv_PassA);
        btnUpdateEditor=fView.findViewById(R.id.btn_Update_Editor);
        rdstbn1=fView.findViewById(R.id.rds_SVA);
        rdsbtn2=fView.findViewById(R.id.rds_GVA);
        enabledFalse();
        btnUpdateEditor.setOnClickListener(v->{updateAccount();});
    }
    public void getAboutAccount(){
        //rdsgCatelogy.get;
        User u=AuthAccount.getInstant().userAccount;
        tvID.setText(u.get_id()+"");
        tvNameA.setText(u.get_Name());
        tvKhoa.setText(u.get_Khoa());
        tvClass.setText(u.get_Class());
        tvMSSV.setText(u.get_MSSV());
        //tvCategory.setText(String.valueOf(u.get_category()));
        if(u.get_category()==1){
            Log.e("---> "," SV");
            rdstbn1.setChecked(true);
        }
        else
        {
            rdsbtn2.setChecked(true);
            Log.e("---> "," GV");
        }

        tvEmail.setText(u.get_Email());
        tvPass.setText(u.get_Pass());
    }
    private void updateAccount(){
        if(isbtn){
            if(!checkInfo()){
                Toast.makeText(mMainActivityHomes, "Có thông tin bị lỗi?", Toast.LENGTH_SHORT).show();
            }
            else {
                User u=AuthAccount.getInstant().userAccount;
                u.set_Name(tvNameA.getText().toString().trim());
                u.set_Class(tvClass.getText().toString().trim());
                btnUpdateEditor.setText("Sữa hồ sơ");
                enabledFalse();
                AuthAccount.getInstant().updateAccount(u);
                isbtn=false;
            }
        }
        else {
            btnUpdateEditor.setText("Cập nhật hồ sơ");
            enabledTrue();
            isbtn=true;
        }
    }
    private void enabledTrue(){
        tvID.setEnabled(true);
        tvNameA.setEnabled(true);
        tvKhoa.setEnabled(true);
        tvPass.setEnabled(true);
        tvMSSV.setEnabled(true);
        rdstbn1.setEnabled(true);
        rdsbtn2.setEnabled(true);
        //tvCategory.setEnabled(true);
        tvEmail.setEnabled(true);
        tvClass.setEnabled(true);
    }
    private void enabledFalse(){
        tvID.setEnabled(false);
        tvNameA.setEnabled(false);
        tvKhoa.setEnabled(false);
        tvPass.setEnabled(false);
        tvMSSV.setEnabled(false);
        rdstbn1.setEnabled(false);
        rdsbtn2.setEnabled(false);
        //tvCategory.setEnabled(false);
        tvEmail.setEnabled(false);
        tvClass.setEnabled(false);
    }
    private boolean checkInfo(){
        if(tvNameA.getText().toString().trim().equals("") ||
                tvEmail.getText().toString().trim().equals("") ||
                tvClass.getText().toString().trim().equals("")){
            return  false;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isbtn=false;
        enabledFalse();
    }
}