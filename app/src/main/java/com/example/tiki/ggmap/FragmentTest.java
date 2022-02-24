package com.example.tiki.ggmap;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiki.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class FragmentTest extends Fragment {
    private GoogleMap mGoogleMap;
    private SupportMapFragment mSupportMapFragment;
    private Location mLocation;
    private LocationManager mLocationManager;
    private FragmentManager mFragmentManager;
    private View mView;
    private MainActivityDMGGM mMainActivityDMGGM;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_test, container, false);
        mFragmentManager = getChildFragmentManager();
        mMainActivityDMGGM= (MainActivityDMGGM) getActivity();
        mSupportMapFragment = (SupportMapFragment) mFragmentManager.findFragmentById(R.id.frgGgMapTest);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                if(mLocationManager==null){
                    mLocationManager= (LocationManager) mMainActivityDMGGM.getSystemService(mMainActivityDMGGM.LOCATION_SERVICE);
                }
                mGoogleMap=googleMap;
                LatLng latLng=new LatLng(10.9993719, 106.6854911);
                MarkerOptions options=new MarkerOptions();
                options.position(latLng);
                options.title("sdgfdsgdf");
                MarkerOptions place1,place2;
                LatLng latLng1, latLng2;
                latLng1=new LatLng(10.9553719, 106.6854911);
                latLng2=new LatLng(10.9993719, 106.6854911);
                place1 = new MarkerOptions().position(latLng1).title("Location 1");
                place2 = new MarkerOptions().position(latLng2).title("Location 2");

                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 10));
                mGoogleMap.addMarker(place1);
                mGoogleMap.addMarker(place2);
            }
        });
        return mView;
    }
}