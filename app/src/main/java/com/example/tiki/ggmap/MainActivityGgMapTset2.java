package com.example.tiki.ggmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.ggmap.roomdatabase.ggMapDatabase;
import com.example.tiki.ggmap.roomdatabase.ggMapEntity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivityGgMapTset2 extends AppCompatActivity implements LocalLocation, OnMapReadyCallback {
    LocationTest l;
    Button btnShow, btnDel;
    RecyclerView lv;
    private ggMapAdapper mapAdapper;
    private boolean isShow;
    private boolean isDele;
    private List<ggMapEntity> ml ;
    private  Date dateStart = null;
    private Date dateEnd=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gg_map_tset2);
        inits();
    }

    private void inits() {
        btnShow=findViewById(R.id.btn_showData);
        lv=findViewById(R.id.l_DataLoca);
        LinearLayoutManager manager =new LinearLayoutManager(this);
        //manager.setReverseLayout(true);
        lv.setLayoutManager(manager);
        RecyclerView.ItemDecoration item= new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        lv.addItemDecoration(item);
        mapAdapper=new ggMapAdapper();
        //mapAdapper.setLggMap();
        //lv.setAdapter(mapAdapper);
        ml=new ArrayList<>();
        //ll=new ArrayAdapter<ggMapEntity>(this,android.R.layout.simple_list_item_1, arrLocal);
        ml=ggMapDatabase.getInstance(this).GgMapDAO().getListGgMap();
        if(ml!=null && !ml.isEmpty()){
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
            String daStart=ml.get(0).get_time();
            try {
                dateStart=df.parse(daStart);
                Calendar calendar= Calendar.getInstance();
                calendar.setTime(dateStart);
                calendar.add(Calendar.DATE,1);
                dateEnd=df.parse(df.format(calendar.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
//        try {
//            dateStart=df.parse("2022/01/18 09:24:00");
//            dateEnd=df.parse("2022/01/18 09:24:00");
//            Log.e("sttus ---> : ",""
//                    +df.parse("2022/01/18 09:24:00").compareTo(df.parse("2022/01/18 09:23:00")));
//            Log.e("sttus ---> : ",""
//                    +df.parse("2022/01/18 09:24:00").compareTo(df.parse("2022/01/18 09:24:00")));
//            Log.e("sttus ---> : ",""
//                    +df.parse("2022/01/18 09:24:00").compareTo(df.parse("2022/01/18 09:25:00")));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        loadData(ml);
        getFragment();
        btnShow.setOnClickListener(v->{showDta();});
        btnDel=findViewById(R.id.btn_deleteAllData);
        btnDel.setOnClickListener(v->{
            deleAllData();
        });
    }

    private void deleAllData() {
        if(isShow){
            new AlertDialog.Builder(this)
                    .setTitle("Cảnh báo!")
                    .setMessage("Bạn có chắc chắn muốn xóa không?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ggMapDatabase.getInstance(MainActivityGgMapTset2.this).GgMapDAO().deleteAllGgMap();
                            ml.clear();
                            ml=null;
                            isDele=true;
                            loadData(ml);
                        }
                    })
                    .setNegativeButton("Cancel",null)
                    .show();
        }
        else{
            Toast.makeText(this, "Hãy chọn Show Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDta() {
        Date currentTime = Calendar.getInstance().getTime();
        if(!isShow){
            lv.setVisibility( View.VISIBLE);
            isShow=true;
            btnShow.setText("Hide Data");
        }
        else {
            lv.setVisibility( View.GONE);
            btnShow.setText("Show Data");
            isShow=false;
        }

    }

    private void getFragment() {
        //Fragment fragment =new FragmentBlankGgMapTest2();
        Fragment fragment =new FragmentReviewGgMap();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fram_GgmapTest2,fragment)
                .commit();
    }
    public int i=0;
    @Override
    public void SendLocation(ggMapEntity l) {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        //arrLocal.add(df.format(currentTime));
        try {
            if(ml==null || ml.isEmpty()) {
                dateStart = df.parse(df.format(currentTime));
                Calendar calendar= Calendar.getInstance();
                calendar.setTime(dateStart);
                calendar.add(Calendar.DATE,1);
                dateEnd=df.parse(df.format(calendar.getTime()));
                //dateEnd=df.parse("2022/01/18 09:59:00");
            }
            Date local= df.parse(df.format(currentTime));
            Log.d("latitude --->:  ", ""+l.get_latitude());
            Log.d("longitude --->:  ", ""+l.get_longitude());
            Log.d("date start --->:  ", ""+df.format(dateStart));
            Log.d("date end --->:  ", ""+df.format(dateEnd));
            Log.d("date local --->:  ", ""+df.format(local));
            assert local != null;
            Log.d("check date local with date end --->:  ", ""+local.compareTo(dateEnd));
            //Toast.makeText(this, "so sánh "+ local.compareTo(dateEnd), Toast.LENGTH_SHORT).show();
            if( local.compareTo(dateEnd)!=1){
                l.set_time(df.format(local));
                //ggMapDatabase.getInstance(this).GgMapDAO().insertGgMap(l);
                if(isDele){
                    ml=new ArrayList<>();
                    isDele=false;
                }
                else{
                    //ggMapDatabase.getInstance(this).GgMapDAO().insertGgMap(l);
                    //ml.add(l);
                }
                //loadData(ml);
            }
            //ml.add(l);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //ml.add(l);
    }

    private void loadData(List<ggMapEntity> p) {
        mapAdapper.setLggMap(p);
        if(p!=null)
            lv.scrollToPosition(p.size()-1);
        lv.setAdapter(mapAdapper);
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        
    }

    public List<ggMapEntity> getMl() {
        return ml;
    }
}