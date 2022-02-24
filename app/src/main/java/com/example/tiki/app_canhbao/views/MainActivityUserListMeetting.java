package com.example.tiki.app_canhbao.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityUserListMeetting extends AppCompatActivity {
    TextView tvNameMeettingList;
    Button btnSearch, btnNewUser;
    EditText txtKeySearch;
    ListView lvListUser;
    String[] items;
    List<String> mUserList;
    ArrayList<String> mUserArrayList;
    ArrayAdapter<String> mUserArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_list_meetting);
        inits();
    }
    private void inits() {
        txtKeySearch=findViewById(R.id.txt_keyNameUserMeetting);
        tvNameMeettingList=findViewById(R.id.tv_NameMeettingList);
        btnSearch =findViewById(R.id.btn_SearchUserMeetting);
        btnNewUser=findViewById(R.id.btn_AddUserMeetting);
        lvListUser=findViewById(R.id.lv_ListUser);
        getAll();
        txtKeySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivityUserListMeetting.this.mUserArrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void getAll(){
        items = new String[]{"Java", "JavaScript", "C#", "PHP", "С++", "Python", "C", "SQL", "Ruby", "Objective-C"};
        mUserArrayList   = new ArrayList<>(Arrays.asList(items));
        mUserArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUserArrayList);
        lvListUser.setAdapter(mUserArrayAdapter);
    }
}