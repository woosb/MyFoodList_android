package com.example.myfoodlist.main;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
/*
* TODO
*  저장 된 위치정보 데이터를 가져와서 뿌려주도록 해아함
* */
public class PlacesReader {
    public List<Place> read(){
        List<Place> places = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Place place = new Place();
            place.setName("sample" + i);
            place.setAddress("sample addr " + i);
            place.setLagLng(new LatLng(37.52487, 126.92723 + i));
            places.add(place);
        }
        return places;
    }
}