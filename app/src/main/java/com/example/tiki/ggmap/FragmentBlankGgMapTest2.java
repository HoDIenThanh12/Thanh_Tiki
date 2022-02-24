package com.example.tiki.ggmap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.ggmap.roomdatabase.ggMapEntity;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
//channel LocationRequest  because version api
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FragmentBlankGgMapTest2 extends Fragment implements OnMapReadyCallback, LocationListener {
    private View fView;
    private TextView txtLong, txtLat;
    private MainActivityGgMapTset2 mMainActivityGgMapTset2;
    private LocalLocation iLocalLocation;
    private GoogleMap fGoogleMap;
    private Location fLocation;
    private LocationManager fLocationManager;
    private FragmentManager fFragmentManager;
    private SupportMapFragment fSupportMapFragment;
    private FusedLocationProviderClient fFunctionalInterface;
    private LocationRequest fLocationRequest;


    public static FragmentBlankGgMapTest2 getInstance() {
        FragmentBlankGgMapTest2 x = new FragmentBlankGgMapTest2();
        return x;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fView = inflater.inflate(R.layout.fragment_blank_gg_map_test2, container, false);
        mMainActivityGgMapTset2 = (MainActivityGgMapTset2) getActivity();
        uiit();
        getAllMap();
//        fFunctionalInterface = LocationServices.getFusedLocationProviderClient(mMainActivityGgMapTset2);
//        fLocationRequest= new LocationRequest();
//        fLocationRequest.setInterval(4 * 1000)
//                .setFastestInterval(1 * 1000)
//                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        setRequset();
        getRequest();
        return fView;
    }

    private void setRequset() {
        fFunctionalInterface = LocationServices.getFusedLocationProviderClient(mMainActivityGgMapTset2);
        fLocationRequest= new LocationRequest();
        fLocationRequest.setInterval(4 * 1000)
                .setFastestInterval(2 * 1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //getRequest();
    }

    private void getRequest() {
        Log.d("status task---> : ", "  onSuccess:");
        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(fLocationRequest).build();

        SettingsClient client = LocationServices.getSettingsClient(mMainActivityGgMapTset2);

        Task<LocationSettingsResponse> fFResponseTask = client.checkLocationSettings(request);

        fFResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                Log.d("status task fail ---> : ", "  success:");
                startLocationRequseUpdate();
            }
        });
        //fail
        Log.d("status task fail ---> : ", "  fail:");
        fFResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ResolvableApiException exception = (ResolvableApiException) e;
                try {
                    Log.d("status task fail ---> : ", "  fail:");
                    exception.startResolutionForResult(mMainActivityGgMapTset2, 1000);
                } catch (IntentSender.SendIntentException sendIntentException) {
                    sendIntentException.printStackTrace();
                }
            }
        });


    }

    private void startLocationRequseUpdate() {
        if (ActivityCompat.checkSelfPermission(mMainActivityGgMapTset2, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mMainActivityGgMapTset2, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fFunctionalInterface.requestLocationUpdates(fLocationRequest, locationCallback, Looper.getMainLooper());
    }

    private void uiit() {
//        txtLat=fView.findViewById(R.id.tv_latitude);
//        txtLong=fView.findViewById(R.id.tv_latitude);
        iLocalLocation=mMainActivityGgMapTset2;
        fFragmentManager=getChildFragmentManager();
        fSupportMapFragment= (SupportMapFragment) fFragmentManager.findFragmentById(R.id.frg_GgMapTest2);
    }
    private void getAllMap() {
        fSupportMapFragment.getMapAsync(this);
//        fSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(@NonNull GoogleMap googleMap) {
//                if(fLocationManager==null){
//                    fLocationManager= (LocationManager) mMainActivityGgMapTset2.getSystemService(mMainActivityGgMapTset2.LOCATION_SERVICE);
//                }
//                fGoogleMap=googleMap;
//                LatLng latLng=new LatLng(10.9993719, 106.6854911);
//                MarkerOptions options=new MarkerOptions();
//                options.position(latLng);
//                options.title("nhậu phê quá");
//                fGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//                fGoogleMap.addMarker(options);
//            }
//        });
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
    LocationCallback locationCallback=new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if(locationResult==null)
                return;
            fLocation = locationResult.getLastLocation();
            Log.e("dateStartList ---> : ","id:  request");
//            Toast.makeText(mMainActivityGgMapTset2, "lat: "+fLocation.getLatitude(),
//                    Toast.LENGTH_SHORT).show();
            iLocalLocation = mMainActivityGgMapTset2;
            sendDatas(locationResult.getLastLocation());
            //iLocalLocation.SendLocation();
        }
    };

    private void sendDatas(Location l) {
        fLocation = l;
        List<Address> lAdres=null;
        Geocoder geocoder= new Geocoder(mMainActivityGgMapTset2, Locale.getDefault());
        try {
            lAdres=geocoder.getFromLocation(fLocation.getLatitude(),fLocation.getLongitude(),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(lAdres!=null){
            LatLng latLng=new LatLng(lAdres.get(0).getLatitude(), lAdres.get(0).getLongitude());
            ggMapEntity test=new ggMapEntity(latLng.longitude,latLng.latitude,
                    lAdres.get(0).getCountryName(), lAdres.get(0).getAddressLine(0));
            iLocalLocation.SendLocation(test);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        fFunctionalInterface.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                fLocation=task.getResult();
                getMap(googleMap, fLocation);
            }
        });

//        if(fLocationManager==null){
//           fLocationManager= (LocationManager) mMainActivityGgMapTset2.getSystemService(mMainActivityGgMapTset2.LOCATION_SERVICE);
//        }
//        fGoogleMap=googleMap;
//        Geocoder geocoder =new Geocoder(mMainActivityGgMapTset2, Locale.getDefault());
//        List<Address> ml=null;
//        try {
//            ml=geocoder.getFromLocation(fLocation.getLatitude(),fLocation.getLongitude(),1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        LatLng latLng=new LatLng(ml.get(0).getLatitude(), ml.get(0).getLongitude());
//        MarkerOptions options=new MarkerOptions();
//        options.position(latLng);
//        options.title("nhậu phê quá");
//        fGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//        fGoogleMap.addMarker(options);
    }
    private void getMap(GoogleMap ggMap, Location l){
        Log.e("gd---> ",""+fLocation.getLatitude());
        if(fLocationManager==null){
            fLocationManager= (LocationManager) mMainActivityGgMapTset2.getSystemService(mMainActivityGgMapTset2.LOCATION_SERVICE);
        }


//        Geocoder geocoder=new Geocoder(mMainActivityGgMapTset2);
//        List<Address> ml=null;
//        try {
//            ml=geocoder.getFromLocation(fLocation.getLatitude(),fLocation.getLongitude(),1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        fGoogleMap=ggMap;
//        LatLng latLng=new LatLng(fLocation.getLatitude(),fLocation.getLongitude());
//        MarkerOptions options =new MarkerOptions().position(latLng)
//                .title("đi nhậu không");
//        fGoogleMap.clear();
//        fGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//        fGoogleMap.addMarker(options);

        //PolygonOptions pgOption=new PolygonOptions();
        ///////////////////////////
        LatLng start = new LatLng(10.974416870548373, 106.68871791136647);
        LatLng pozatea = new LatLng(10.973021913251218, 106.6819064222979);
        LatLng ccPH = new LatLng(10.973119245772564, 106.68190642224937);
        LatLng TDMU = new LatLng(10.980788002377901, 106.67544741019194);
        LatLng becamax = new LatLng(10.9766321386463, 106.67055764088327);
        List<LatLng> lLatLngs=new ArrayList<>();
        lLatLngs.add(start);
        lLatLngs.add(pozatea);
        lLatLngs.add(ccPH);
        lLatLngs.add(TDMU);
        fGoogleMap.addMarker(new MarkerOptions().position(start).title("dfgdfgdf"));
        fGoogleMap.addPolyline(new PolylineOptions().addAll(lLatLngs)
                .width(10)
                .color(Color.RED)
        );

        fGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(start));
        fGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start, 18));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fFunctionalInterface.removeLocationUpdates(locationCallback);
    }
}