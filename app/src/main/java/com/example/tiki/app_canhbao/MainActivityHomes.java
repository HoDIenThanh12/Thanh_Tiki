package com.example.tiki.app_canhbao;

import static com.example.tiki.app_canhbao.constants.CONSTANTS.ID_ACCOUNT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.app_canhbao.adappers.MyViewPager2Adapper;
import com.example.tiki.app_canhbao.backend.AuthAccount;
import com.example.tiki.app_canhbao.constants.CONSTANTS;
import com.example.tiki.app_canhbao.fragments.FragmentAbout;
import com.example.tiki.app_canhbao.fragments.FragmentHome;
import com.example.tiki.app_canhbao.fragments.FragmentLogin;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivityHomes extends AppCompatActivity {
    private BottomNavigationView mNavigationBarView;
    private ViewPager2 mViewPager2;
    private MyViewPager2Adapper mAdapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_homes2);
        inits();
        //loadHome();
    }
//    private void loadHome() {
//        Fragment fr= new  FragmentHome();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_page, fr)
//                .addToBackStack(null)
//                .commit();
//    }
    @SuppressLint("NonConstantResourceId")
    private void inits() {
        mViewPager2=findViewById(R.id.viewpage2_fragment);
        mAdapper=new MyViewPager2Adapper(this);
        mNavigationBarView =findViewById(R.id.bottom_navigation);
        mViewPager2.setAdapter(mAdapper);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:{
                        mNavigationBarView.getMenu().findItem(R.id.page_Home).setChecked(true);
                        break;
                    }
                    case 1:{
                        mNavigationBarView.getMenu().findItem(R.id.page_Meetting).setChecked(true);
                        break;
                    }
                    case 2:{
                        mNavigationBarView.getMenu().findItem(R.id.page_Timetable).setChecked(true);
                        break;
                    }
                    case 3:{
                        mNavigationBarView.getMenu().findItem(R.id.page_About).setChecked(true);
                        break;
                    }
                }
            }
        });
        mNavigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.page_Home:{
                        mViewPager2.setCurrentItem(0);
                        break;
                    }
                    case R.id.page_Meetting:{
                        mViewPager2.setCurrentItem(1);
                        break;
                    }
                    case R.id.page_Timetable:{
                        mViewPager2.setCurrentItem(2);
                        break;
                    }
                    case R.id.page_About:{
                        mViewPager2.setCurrentItem(3);
                        break;
                    }
                }
                return true;
            }
        });







//        mNavigationBarView.setOnItemSelectedListener(i->{
//            Fragment fragment;
//            switch (i.getItemId()){
//                case R.id.page_Home:{
//                    Toast.makeText(this, "Homes", Toast.LENGTH_SHORT).show();
//                    fragment =new FragmentLogin();
//                    getFragment(fragment);
//                    return true;
//                }
//                case R.id.page_2:{
//                    Toast.makeText(this, "xếp lịch", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                case R.id.page_3:{
//                    Toast.makeText(this, "Danh sách", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                case R.id.page_About:{
//                    fragment=new FragmentAbout();
////                    String ID= AuthAccount.getInstant().getIdUser();
////                    Bundle bundle =new Bundle();
////                    bundle.putString(ID_ACCOUNT, ID);
////                    fragment.setArguments(Bundle);
//                    getFragment(fragment);
//                    Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//            }
//            return false;
//        });
    }
//    private void getFragment(Fragment f){
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_page,f)
//                .commit();
//    }
}