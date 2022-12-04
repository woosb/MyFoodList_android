package com.example.myfoodlist;

import com.google.android.gms.maps.model.LatLng;

/*
* TODO
*  위치정보에 대한 설명, 저장할 때 필요한 사진은 여러가지가 될 수있으므로 기눙추가 필요
* */
public class Place {
    String name;
    LatLng lagLng;
    String address;
    String thumbnail;
    float rating;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLagLng(LatLng lagLng) {
        this.lagLng = lagLng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
