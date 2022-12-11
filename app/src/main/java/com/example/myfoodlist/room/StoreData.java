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
   private double score;

   @ColumnInfo(name="latitude")
   private String latitude;

   @ColumnInfo(name="longitude")
   private String longitude;

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


   @ColumnInfo(name="insYmd")
   private String insYmd;

   @ColumnInfo(name="modYmd")
   private String modYmd;

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

   public double getScore() {
      return score;
   }

   public void setScore(double score) {
      this.score = score;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
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

   public String getInsYmd() {
      return insYmd;
   }

   public void setInsYmd(String insYmd) {
      this.insYmd = insYmd;
   }

   public String getModYmd() {
      return modYmd;
   }

   public void setModYmd(String modYmd) {
      this.modYmd = modYmd;
   }

   public void setThumbnail(String thumbnail) {
      this.thumbnail = thumbnail;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
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
              ", insYmd='" + insYmd + '\'' +
              ", modYmd='" + modYmd + '\'' +
              '}';
   }
}
