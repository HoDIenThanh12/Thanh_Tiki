package com.example.tiki.ggmap;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tiki.R;

public class Fragment2 extends Fragment {

    private View mView;
    private Button btnS;
    private EditText txt;
    private static UpdateDataFr1 mUpdateDataFr1;
    public interface UpdateDataFr1{
        void updateDataF1(String s);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mUpdateDataFr1= (UpdateDataFr1) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView=  inflater.inflate(R.layout.fragment_2, container, false);
        inits();
        return mView;
    }

    private void inits() {
        btnS=mView.findViewById(R.id.btnSendF2);
        txt=mView.findViewById(R.id.txt2);
        btnS.setOnClickListener(v->{
            updateData();
        });
    }

    private void updateData() {
        String emailupdate= txt.getText().toString().trim();
        mUpdateDataFr1.updateDataF1(emailupdate);
    }

    public void NhanData(String s){
        txt.setText(s);
    }
}