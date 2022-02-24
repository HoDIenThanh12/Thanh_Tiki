package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.ListClassAdapper;
import com.example.tiki.app_canhbao.entity.ClassCB;

import java.util.ArrayList;
import java.util.List;

public class MainActivityClassCB extends AppCompatActivity {
    RecyclerView rcvClassCB;
    ListClassAdapper adapper;
    List<ClassCB> mClassCBList;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class_cb);
        inits();
    }
    private void inits() {
        rcvClassCB=findViewById(R.id.rcv_ClassCB);
        mClassCBList=new ArrayList<>();
        adapper=new ListClassAdapper(this);
        linearLayoutManager=new LinearLayoutManager(this);
        rcvClassCB.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvClassCB.addItemDecoration(item);
        rcvClassCB.setAdapter(adapper);
        getAllData();
    }
    private void getAllData(){
        mClassCBList.add(new ClassCB("123","234"));
        mClassCBList.add(new ClassCB("123","234"));
        mClassCBList.add(new ClassCB("123","234"));
        mClassCBList.add(new ClassCB("123","234"));
        mClassCBList.add(new ClassCB("123","234"));
        for (int i=0;i<50;i++){
            mClassCBList.add(new ClassCB("123","234"));
        }
        adapper.setlClassCB(mClassCBList);
        //rcvClassCB.setAdapter(adapper);
    }
}