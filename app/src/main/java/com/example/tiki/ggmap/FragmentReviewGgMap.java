package com.example.tiki.ggmap;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiki.R;
import com.example.tiki.ggmap.roomdatabase.ggMapEntity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public class FragmentReviewGgMap extends Fragment {
    private List<ggMapEntity> lGgMap;
    private  List<LatLng> fLatLng ;
    private View fView;
    private GoogleMap fGoogleMap;
    private Location fLocation;
    private LocationManager fLocationManager;
    private FusedLocationProviderClient fFunctionalInterface;
    private MainActivityGgMapTset2 mainGgMap;
    private FragmentManager fFragmentManager;
    private SupportMapFragment fSupportMapFragment;

//    public static FragmentReviewGgMap getInsstant(List<LatLng> lngList){
//        FragmentReviewGgMap reviewGgMap =new FragmentReviewGgMap();
//        if(fLatLng==null)
//            fLatLng=lngList;
//        Bundle bundle=new Bundle();
//        //reviewGgMap.setArguments();
//        return reviewGgMap;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView= inflater.inflate(R.layout.fragment_review_gg_map, container, false);
        mainGgMap= (MainActivityGgMapTset2) getActivity();
        getDataMap(fView);
        return fView;
    }

    private void getDataMap(View fView) {
        lGgMap=mainGgMap.getMl();
        fLatLng=new ArrayList<>();
        int i=0;
        for(ggMapEntity e: lGgMap){
            LatLng l=new LatLng(e.get_latitude(), e.get_longitude());

                fLatLng.add(l);
            Log.d("lenght---> ",i+" "+e.get_latitude()+
                    " | longi: ---> "+i +" "+e.get_longitude());
            Log.d("lenght---> ",i+" "+l.latitude+
                    "longi: ---> "+i +" "+l.longitude);
            i++;
//            if(e!=null)
//                fLatLng.add(new LatLng(e.get_latitude(), e.get_longitude()));
        }
        //Log.e("lenght---> "," "+fLatLng.size());
        fFragmentManager=getChildFragmentManager();
        fSupportMapFragment= (SupportMapFragment) fFragmentManager.findFragmentById(R.id.review_ggMap);
        fFunctionalInterface= LocationServices.getFusedLocationProviderClient(mainGgMap);
        fSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //LatLng l1=new LatLng(10.9993719,106.6854911);
                fGoogleMap=googleMap;
                MarkerOptions options=new MarkerOptions();
                options.position(fLatLng.get(0));
                options.title("đã tạo");
                //Log.d("lenght---> ","lati: "+fLatLng.get(0).latitude+"| longi:  "+fLatLng.get(0).longitude);
                //Draw a map
                fGoogleMap.clear();
                fGoogleMap.addPolyline(new PolylineOptions().addAll(fLatLng).width(10).color(Color.RED));
                fGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fLatLng.get(0), 18));
                fGoogleMap.addMarker(options);
            }
        });
    }
}