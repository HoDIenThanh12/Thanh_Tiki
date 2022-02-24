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


public class Fragment1 extends Fragment {

    private View mView;
    private Button btnS;
    private EditText txt;
    private ISendDataListener mISendDataListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mISendDataListener = (ISendDataListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_1, container, false);
        inits();

        return mView;
    }
    private void inits() {
        btnS=mView.findViewById(R.id.btnSendF1);
        txt=mView.findViewById(R.id.txt1);
        btnS.setOnClickListener(v->{
            Send();
        });
    }
    public void nhanData2(String s){
        txt.setText(s);
    }
    private void Send() {
        String email= txt.getText().toString().trim();
        mISendDataListener.Seand(email);
    }
}