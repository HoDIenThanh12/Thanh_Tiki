package com.example.tiki.ggmap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
//import android.location.LocationRequest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import android.util.Log;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.databinding.ActivityMapsTestGgMap1Binding;
import com.example.tiki.ggmap.roomdatabase.ggMapEntity;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivityTestGgMap1 extends FragmentActivity implements OnMapReadyCallback, LocationListener, LocalLocation {

    private GoogleMap mMap;
    private ActivityMapsTestGgMap1Binding binding;
    private LocationRequest v;
    private FusedLocationProviderClient functionalInterface;
    private ggMapEntity mLocationTest;

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if(locationResult==null){
                Log.d("status permission---> : "," null :");
                return;
            }

            Log.d("locationResult size---> : "," :"+locationResult.getLocations().size());
            //when have locationresult
            List<Address> addresses=null;
            Location location=locationResult.getLastLocation();
            Geocoder geocoder =new Geocoder(MapsActivityTestGgMap1.this, Locale.getDefault());
            try {
                addresses=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1 );
            } catch (IOException e) {
                e.printStackTrace();
            }

//            for(Location location: locationResult.getLocations()){
//                Log.d("status permission---> : "," đã có :"+location.getLatitude()+" | "+location.getLatitude());
                Toast.makeText(MapsActivityTestGgMap1.this,
                        "location chi tiết: "+
                        "\nlatidute---->: "+addresses.get(0).getLatitude()+
                                "\nlongi---->: "+addresses.get(0).getLongitude()+
                                "\nCiuntry---->: "+ addresses.get(0).getCountryName()+
                                "\nlocality---->: "+addresses.get(0).getLocality()+
                                "\naddressline---->: "+addresses.get(0).getAddressLine(0),
                        Toast.LENGTH_LONG).show();
            mLocationTest =new ggMapEntity(addresses.get(0).getLatitude(),addresses.get(0).getLongitude(), addresses.get(0).getCountryName() , addresses.get(0).getAddressLine(0));
            MapsActivityTestGgMap1.this.SendLocation(mLocationTest);
//            }
        }
    };


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsTestGgMap1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        functionalInterface = LocationServices.getFusedLocationProviderClient(MapsActivityTestGgMap1.this);
        v = new LocationRequest();
        v.setInterval(4 * 1000);
        v.setFastestInterval(1 * 1000);
        v.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        getRQ();
    }
    private void check(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
            getRQ();
        }
        else{
            checkAsktask();
        }
    }
    //kiểm tra người dùng đã cấp quyền chưa dành cho API 23 trở đi
    private void checkAsktask() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //nên gán cố định REQUEST_CODE =1000 đầu tiên
        if(requestCode==1000){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //permission grandted
                getRQ();
                Log.d("status permission---> : "," permission genarated:");
            }
            else{
                ///permission not enable
                Log.d("status permission---> : "," permission dined:");
            }
        }
    }

    private void getRQ() {
        Log.d("status permission---> : "," đã vô get QR:");
        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(v).build();

        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> tlo = client.checkLocationSettings(request);
        tlo.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(@NonNull LocationSettingsResponse locationSettingsResponse) {
                startLocationUpdate();
                Log.d("status permission---> : "," đã vô get onSuccess:");
            }
        });
        //fail requestlocation
        tlo.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ResolvableApiException){
                    ResolvableApiException t= (ResolvableApiException) e;
                    try {
                        t.startResolutionForResult(MapsActivityTestGgMap1.this, 1000);
                        Log.d("status permission---> : "," fail:");
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }
            }
        });
    }
    private void stopQR(){
        functionalInterface.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopQR();
    }

    private void startLocationUpdate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("status permission---> : "," đã vô get startL và bằng null:");
            return;
        }
        Log.d("status permission---> : "," đã vô get start:");
        functionalInterface.requestLocationUpdates(v, locationCallback, Looper.getMainLooper());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(10.9733719, 106.6854911);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Đi nhậu không")
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.cho))
        );
        LatLng start = new LatLng(10.974416870548373, 106.68871791136647);
        LatLng pozatea = new LatLng(10.973021913251218, 106.6819064222979);
        LatLng ccPH = new LatLng(10.973119245772564, 106.68190642224937);
        LatLng TDMU = new LatLng(10.980788002377901, 106.67544741019194);
        LatLng becamax = new LatLng(10.9766321386463, 106.67055764088327);

        mMap.addPolyline(new PolylineOptions().add(
                start,pozatea, ccPH, becamax, TDMU)
                    .width(10)
                    .color(Color.RED)
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
        //mMap.setMapStyle(GoogleMap.MAP_TYPE_SATELLITE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "em đã tới", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void SendLocation(ggMapEntity l) {
        l=mLocationTest;
    }
}