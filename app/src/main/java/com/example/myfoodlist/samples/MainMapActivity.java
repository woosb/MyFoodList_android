package com.example.myfoodlist.samples;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.example.myfoodlist.R;
import com.example.myfoodlist.main.MarkerInfoWindowAdapter;
import com.example.myfoodlist.main.Place;
import com.example.myfoodlist.main.PlacesReader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.example.myfoodlist.databinding.ActivityMainMapBinding;

import java.util.List;

public class MainMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMainMapBinding binding;
    private List<Place> places = new PlacesReader().read();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.getMapAsync(googleMap -> {
            addMarkers(googleMap);
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.52487, 126.92723)));
            googleMap.setTrafficEnabled(true);
        });
    }

    private void addMarkers(GoogleMap googleMap){
        for(Place place : places){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(place.getName());
            markerOptions.position(place.getLagLng());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(place);
            googleMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter(this));
        }
    }

    Marker marker = null;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                // ?????? ?????????
                mOptions.title("?????? ??????");
                Double latitude = point.latitude; // ??????
                Double longitude = point.longitude; // ??????
                // ????????? ?????????(????????? ?????????) ??????
                mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                // LatLng: ?????? ?????? ?????? ?????????
                mOptions.position(new LatLng(latitude, longitude));
                // ??????(???) ??????
                if(marker == null){
                    marker = googleMap.addMarker(mOptions);
                }else{
                    marker.remove();
                    marker = googleMap.addMarker(mOptions);
                }
            }
        });
    }
}