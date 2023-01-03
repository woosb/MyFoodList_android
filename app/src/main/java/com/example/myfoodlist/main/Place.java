package com.example.myfoodlist.main;

import com.example.myfoodlist.room.StoreData;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/*
* TODO
*  위치정보에 대한 설명, 저장할 때 필요한 사진은 여러가지가 될 수있으므로 기눙추가 필요
* */
public class Place {
    private String name;
    private LatLng lagLng;
    private String address;
    private String thumbnail;
    private double rating;

    public static Place storeDatatoPlace(StoreData data){
        Place place = new Place();
        place.setName(data.getName());
        place.setLagLng(new LatLng(data.getLatitude(), data.getLongitude()));
        place.setAddress(data.getAddress());
        place.setRating(data.getScore());
        return place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLagLng() {
        return lagLng;
    }

    public void setLagLng(LatLng lagLng) {
        this.lagLng = lagLng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
