package com.example.myfoodlist.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(tableName = "store")
public class StoreData implements Serializable {

   @PrimaryKey(autoGenerate = true)
   private Long id;

   @ColumnInfo(name="name")
   private String name;

   @ColumnInfo(name="score")
   private int score;

   @ColumnInfo(name="latitude")
   private double latitude;

   @ColumnInfo(name="longitude")
   private double longitude;

   @ColumnInfo(name="address")
   private String address;

   @ColumnInfo(name="addressDetail")
   private String addressDetail;

   @ColumnInfo(name="memo")
   private String memo;

   @ColumnInfo(name="thumbnail")
   private String thumbnail;

   @ColumnInfo(name="visitedYmd")
   private String visitedYmd;


   @ColumnInfo(name="insYmdHms")
   private String insYmdHms;

   @ColumnInfo(name="modYmdHms")
   private String modYmdHms;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }


   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getAddressDetail() {
      return addressDetail;
   }

   public void setAddressDetail(String addressDetail) {
      this.addressDetail = addressDetail;
   }

   public String getMemo() {
      return memo;
   }

   public void setMemo(String memo) {
      this.memo = memo;
   }

   public String getThumbnail() {
      return thumbnail;
   }

   public String getVisitedYmd() {
      return visitedYmd;
   }

   public void setVisitedYmd(String visitedYmd) {
      this.visitedYmd = visitedYmd;
   }

   public String getInsYmdHms() {
      return insYmdHms;
   }

   public void setInsYmdHms(String insYmdHms) {
      this.insYmdHms = insYmdHms;
   }

   public String getModYmdHms() {
      return modYmdHms;
   }

   public void setModYmdHms(String modYmdHms) {
      this.modYmdHms = modYmdHms;
   }

   public void setThumbnail(String thumbnail) {
      this.thumbnail = thumbnail;
   }

   public double getLatitude() {
      return latitude;
   }

   public void setLatitude(double latitude) {
      this.latitude = latitude;
   }

   public double getLongitude() {
      return longitude;
   }

   public void setLongitude(double longitude) {
      this.longitude = longitude;
   }

   @Override
   public String toString() {
      return "StoreData{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", score=" + score +
              ", latitude='" + latitude + '\'' +
              ", longitude='" + longitude + '\'' +
              ", address='" + address + '\'' +
              ", addressDetail='" + addressDetail + '\'' +
              ", memo='" + memo + '\'' +
              ", thumbnail='" + thumbnail + '\'' +
              ", visitedYmd='" + visitedYmd + '\'' +
              ", insYmd='" + insYmdHms + '\'' +
              ", modYmd='" + modYmdHms + '\'' +
              '}';
   }
}
