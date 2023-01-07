package com.example.myfoodlist.common;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {

    public static String getYmd(){
        String ymd = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            LocalDateTime now = LocalDateTime.now();
            ymd = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        }
        return ymd;
    }

    public static String getDateTime(){
        String date = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime now = LocalDateTime.now();
            LocalTime time = LocalTime.now();
            String ymd = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            date = ymd + "-" + time;
        }
        return date;
    }
}
