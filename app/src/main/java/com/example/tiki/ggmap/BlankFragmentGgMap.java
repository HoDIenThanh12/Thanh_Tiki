package com.example.tiki.ggmap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.SearchView;

import com.example.tiki.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;


public class BlankFragmentGgMap extends Fragment implements LocationListener {

    private View mView;
    private MainActivityGgMap mGgMap;

    private SupportMapFragment myMapFragment;
    private GoogleMap mMap;
    private LocationRequest mLocationRequest;
    private FragmentManager fm;
    private Location mLocation;
    private LocationManager mLocationManager;
    private SearchView mSearchView;
    private TextView i;
    private List<Address> mList;
    private FusedLocationProviderClient functionalInterface;
    //if use static
//    private static SenDataListener mSenDataListener;
//    public static BlankFragmentGgMap getInstance(String s, SenDataListener send)
    //if use not static
//    private  SenDataListener mSenDa1;
//    public static BlankFragmentGgMap getInstance(String s){
//        BlankFragmentGgMap blankFragmentGgMap =new BlankFragmentGgMap();
//        Bundle bundle= new Bundle();
//        bundle.putString("key", s);
//        blankFragmentGgMap.setArguments(bundle);
//        return blankFragmentGgMap;
//    }
    private static SendLoaction mSendLoaction;

    public static BlankFragmentGgMap getInstance(String mL, SendLoaction n) {
        mSendLoaction = n;
        BlankFragmentGgMap fg = new BlankFragmentGgMap();
        Bundle bundle = new Bundle();
        bundle.putString("keyList", mL);
        fg.setArguments(bundle);
        return fg;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_blank_gg_map2, container, false);
        //mView=  inflater.inflate(R.layout.fragment_blank_gg_map2, null);
        mGgMap = (MainActivityGgMap) getActivity();
        fm = getChildFragmentManager();
        myMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.frgGgMap);

        initUI();
        return mView;
    }

    private void initUI() {
        i = mView.findViewById(R.id.l);
        i.setText(getArguments().getString("keyList"));
        functionalInterface = LocationServices.getFusedLocationProviderClient(mGgMap);
        if (getArguments().getString("keyList") != null) {
            String s = getArguments().getString("keyList");
            Log.d("Dữ liệu đã có--> ", "" + getArguments().getString("keyList"));
            //checkAddress(getArguments().getString("keyList"));
            loadGgMap(s);
        } else {
            loadGgMap(null);
        }
    }

    private void loadGgMap(String s) {
        myMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                if (mLocationManager == null)
                    mLocationManager = (LocationManager) mGgMap.getSystemService(mGgMap.LOCATION_SERVICE);
                // TODO Auto-generated method stub
                LatLng latLng = null;
                MarkerOptions options = new MarkerOptions();
                BlankFragmentGgMap.this.seGgMap(googleMap);
                if (s == null) {
                    mMap.clear();
                    //initiale marrket map
                    double l = 106.6854911;
                    double la = 10.9733719;
                    latLng = new LatLng(10.9993719, 106.6854911);
                    options.position(latLng);
                    options.title("đi nhậu không");
                    options.visible(true);
                    Log.d("-->  ", "" + options.getAnchorU());
                } else {
                    List<Address> ad = null;
                    Geocoder geocoder = new Geocoder(mGgMap);
                    try {
                        ad = geocoder.getFromLocationName(s, 1);
                    } catch (Exception e) {
                    }
                    if (ad != null) {
                        mMap.clear();
                        Address a = ad.get(0);
                        latLng = new LatLng(a.getLatitude(), a.getLongitude());
                        options.position(latLng);
                        options.title("đã nhậu xong");

                    }
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                mMap.addMarker(options);
            }

        });
    }


    private void seGgMap(GoogleMap gM) {
        mMap = gM;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        if (ActivityCompat.checkSelfPermission(mGgMap, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mGgMap, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
//        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
//            @Override
//            public boolean onMyLocationButtonClick() {
//                checkGPS();
//                mMap.clear();
//                Geocoder geocoder = new Geocoder(mGgMap, Locale.getDefault());
//                if (ActivityCompat.checkSelfPermission(mGgMap, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mGgMap, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                    return false;
//                }
//                Task<Location> locationTask = functionalInterface.getLastLocation();
//                locationTask.addOnCompleteListener(new OnCompleteListener<Location>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Location> task) {
//                        try {
//                            mMap.clear();
//                            Location m=task.getResult();
//                            List<Address> addresses=geocoder.getFromLocation(m.getLatitude(),m.getLongitude(), 1);
//                            Log.d("latidute---->: ",""+addresses.get(0).getLatitude());
//                            Log.d(" longi---->: ",""+addresses.get(0).getLongitude());
//                            Log.d("Ciuntry---->: ",""+ addresses.get(0).getCountryName());
//                            Log.d(" locality---->: ",""+addresses.get(0).getLocality());
//                            Log.d(" addressline---->: ",""+addresses.get(0).getAddressLine(0));
//                            if(addresses!=null){
//                                MarkerOptions options=new MarkerOptions().position(new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude() ));
//                                options.title("đang nhậu dỡ");
//                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude()), 10));
//                                mMap.addMarker(options);
//
//                            }
//
//                        }catch (Exception e){
//                        }
//                    }
//                });
//                return true;
//            }
//        });
    }

    private void checkGPS() {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}