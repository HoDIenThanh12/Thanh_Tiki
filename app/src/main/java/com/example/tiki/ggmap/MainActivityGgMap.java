package com.example.tiki.ggmap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tiki.R;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

public class MainActivityGgMap extends AppCompatActivity implements SenDataListener , ISendDataListener, Fragment2.UpdateDataFr1, SendLoaction{
    FrameLayout mFrameLayout;
    private String s="main ";
    private Button bt;
    private EditText edt;
    private Fragment mFragment, fragment1, fragment2;
    private FrameLayout fr1, fr2;
    private SearchView mSearchViewMain;
    private String mL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gg_map2);
        inits();
        //inits1();
        getFragmentGgMap();
    }

//    private void inits1() {
//        fr1=findViewById(R.id.frame1);
//        fr2=findViewById(R.id.frame2);
//        fragment1=new Fragment1();
//        fragment2=new Fragment2();
//        getall();
//    }

//    private void getall() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(fr1.getId(), fragment1)
//                .add(fr2.getId(), fragment2)
//                .commit();
//    }

    private void inits(){
        bt=findViewById(R.id.btn);
        edt=findViewById(R.id.txt);
        mFrameLayout=findViewById(R.id.frame_ggMaps);
        mFragment =new BlankFragmentGgMap();
        mSearchViewMain=findViewById(R.id.search_adressMain);
        bt.setOnClickListener(v->{
            if(mL==null || mL.equals("")){
                Log.d("Chưa nhập dl","");
                //Toast.makeText(this,"dl trống", Toast.LENGTH_LONG).show();
            }
            else{
            s=edt.getText().toString().trim();
            Bundle bundle=new Bundle();
            bundle.putString("key",s);
            mFragment.setArguments(bundle);


//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.frame_ggMaps,  BlankFragmentGgMap.getInstance(s))
//                    .commit();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_ggMaps,  BlankFragmentGgMap.getInstance(mL, this))
                    .commit();
            }

        });


        mSearchViewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("đang trìm",""+query);
//                String qery= mSearchViewMain.getQuery().toString();
                String qery ="06 Trần Văn Ơn, Phú Hoà, Thủ Dầu Một, Bình Dương, Việt Nam";
                List<Address> laddress=null;
                if(query!=null || !qery.equals("")){
                    try {
                        Geocoder geocoder=new Geocoder(MainActivityGgMap.this);
                        laddress =geocoder.getFromLocationName(qery,1);
                        mL="06 Trần Văn Ơn, Phú Hoà, Thủ Dầu Một, Bình Dương, Việt Nam";
                        Log.d("dữ liệu convert: -->> "," "+ laddress.get(0));
                    }
                    catch (Exception e){
                        Log.d("đang trìm",""+"lỗi dữ liệu nhập");
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }
    private void getFragmentGgMap(){
        Bundle bundle=new Bundle();
        bundle.putString("kry",s);
        mFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_ggMaps, mFragment)
                .commit();
        //FragmentTransaction
    }

    public String getS() {
        return s;
    }

    @Override
    public void SenData(String s) {
        s=edt.getText().toString().trim();
    }

    @Override
    public void Seand(String s) {
//        Fragment2 mFragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.frame2);
//        mFragment2.NhanData(s);
    }

    @Override
    public void updateDataF1(String s) {
//        Fragment1 mFragment1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.frame1);
//        mFragment1.nhanData2(s);
    }

    @Override
    public void SendLoacal(String laddress) {
        laddress=mL;
    }
}