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

   @ColumnInfo(name="latLng")
   private LatLng latLng;

   @ColumnInfo(name="address")
   private String address;

   @ColumnInfo(name="addressDetail")
   private String addressDetail;

   @ColumnInfo(name="thumbnail")
   private String thumbnail;

   @ColumnInfo(name="visitedYmd")
   private LocalDate visitedYmd;


   @ColumnInfo(name="insYmd")
   private LocalDate insYmd;

   @ColumnInfo(name="modYmd")
   private LocalDate modYmd;

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

   public LatLng getLatLng() {
      return latLng;
   }

   public void setLatLng(LatLng latLng) {
      this.latLng = latLng;
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

   public String getThumbnail() {
      return thumbnail;
   }

   public void setThumbnail(String thumbnail) {
      this.thumbnail = thumbnail;
   }

   public LocalDate getInsYmd() {
      return insYmd;
   }

   public void setInsYmd(LocalDate insYmd) {
      this.insYmd = insYmd;
   }

   public LocalDate getModYmd() {
      return modYmd;
   }

   public void setModYmd(LocalDate modYmd) {
      this.modYmd = modYmd;
   }

   public LocalDate getVisitedYmd() {
      return visitedYmd;
   }

   public void setVisitedYmd(LocalDate visitedYmd) {
      this.visitedYmd = visitedYmd;
   }
}
