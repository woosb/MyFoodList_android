package com.example.myfoodlist.main;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfoodlist.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.util.List;

public class MapsFragment extends Fragment {

    private List<Place> places = new PlacesReader().read();
    private GoogleMap mMap;
    private Marker marker = null;

    private LatLng latLng;
    private double mlatitude;
    private double mlongitude;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            mMap = googleMap;
            mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                @Override
                public void onMapClick(LatLng point) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(point)
                            .zoom(15)
                            .build();

                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    MarkerOptions mOptions = new MarkerOptions();
                    // 마커 타이틀
                    mOptions.title("마커 좌표");
                    Double latitude = point.latitude; // 위도
                    Double longitude = point.longitude; // 경도
                    // 마커의 스니펫(간단한 텍스트) 설정
                    mOptions.snippet(latitude.toString() + ", " + longitude.toString());

                    latLng = new LatLng(latitude, longitude);
                    mlatitude = latitude;
                    mlongitude = longitude;

                    // LatLng: 위도 경도 쌍을 나타냄
                    mOptions.position(latLng);
                    // 마커(핀) 추가
                    if(marker == null){
                        marker = googleMap.addMarker(mOptions);
                    }else{
                        marker.remove();
                        marker = googleMap.addMarker(mOptions);
                    }
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView btn_add_store;
        btn_add_store = view.findViewById(R.id.btn_add_store);
        btn_add_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddStoreDetailActivity.class);
                intent.putExtra("latitude", mlatitude);
                intent.putExtra("longitude", mlongitude);
                startActivity(intent);
            }
        });


        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
            mapFragment.getMapAsync(googleMap -> {
                addMarkers(googleMap);
                googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.52487, 126.92723)));
                googleMap.setTrafficEnabled(true);
            });
        }
    }

    private void addMarkers(GoogleMap googleMap){
        for(Place place : places){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(place.getName());
            markerOptions.position(place.getLagLng());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            Marker marker = googleMap.addMarker(markerOptions);
            marker.setTag(place);
            googleMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter(this.getContext()));
        }
    }
}