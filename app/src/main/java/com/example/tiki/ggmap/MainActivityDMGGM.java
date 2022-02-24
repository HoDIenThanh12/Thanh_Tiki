package com.example.tiki.ggmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.tiki.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivityDMGGM extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mggMap;
    private MarkerOptions place1, place2;
    private SupportMapFragment myMapFragment;
    private FragmentManager fm;
    private FusedLocationProviderClient functionalInterface;
    private LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dmggm);
        EditText tS=findViewById(R.id.txtStart);
        EditText tSt=findViewById(R.id.txtTheEnd);
        Button b=findViewById(R.id.btn);
        FrameLayout k=findViewById(R.id.mapNearBy);
        place1 = new MarkerOptions().position(new LatLng(27.658143, 85.3199503)).title("Location 1");
        place2 = new MarkerOptions().position(new LatLng(27.667491, 85.3208583)).title("Location 2");

        getall();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ádas","ádasd");

                searchs(tS.getText().toString().trim(), tSt.getText().toString().trim());
            }
        });
    }
        private void getall() {
        Fragment fragment=new FragmentTest();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mapNearBy, fragment)
                .commit();
    }
    private void searchs(String trim, String trim1) {
        if(trim.equals("") && !trim1.equals("")){
            Log.d("ádas","ádasd");
            return;
        }
        try {
            Log.d("ádas","---> "+ trim+" | "+trim1);
            Uri uri= Uri.parse("https://www.google.com/maps/dir/"+trim+"/"+trim1);
            Intent intent=new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.app.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            String url = "https://maps.googleapis.com/maps/api/directions/"
                    + trim + "?" + trim1 + "&key=" + getString(R.string.google_maps_key);
            startActivity(intent);
        }
        catch (Exception e){
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
//        mggMap = googleMap;
//        Log.d("mylog----> ", "Added Markers");
//        mggMap.addMarker(place1);
//        mggMap.addMarker(place2);
    }
}