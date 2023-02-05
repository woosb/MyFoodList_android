package com.example.myfoodlist.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myfoodlist.R;
import com.example.myfoodlist.common.CommonUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import org.jetbrains.annotations.NotNull;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private Context context;
    private String imgRoot;

    public MarkerInfoWindowAdapter(Context context) {
        this.context = context;
        this.imgRoot = CommonUtil.getImgPath();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View getInfoContents(@NonNull @NotNull Marker marker) {

        Place place = (Place)marker.getTag();
        if(place == null){ // 구글 지도 클릭 시 생성되는 마커는 place 데이터가 없으므로 return null
            return null;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.marker_info_contents, null);
        TextView textView = view.findViewById(R.id.text_view_title);
        textView.setText(place.getName());

        TextView title = view.findViewById(R.id.text_view_title);
        title.setText(place.getName());
        TextView address = view.findViewById(R.id.text_view_address);
        address.setText(place.getAddress());
        TextView rating = view.findViewById(R.id.text_view_rating);
        rating.setText(getStar(place.getRating()));
        ImageView imageView = view.findViewById(R.id.im_thumbnail);

        //마커에 이미지 세팅
        String thumbnail = imgRoot + place.getThumbnail();
        Bitmap bitmap = BitmapFactory.decodeFile(thumbnail);
        imageView.setImageBitmap(bitmap);

        return view;
    }

    private String getStar(double score){
        // ★☆ 별찍기
        Log.e("scord1", score + "");
        int v = (int) (score/2/10);
        Log.e("v", v + "");
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 5; i++){
            if(i < v){
                sb.append("★");
            }else{
                sb.append("☆");
            }
        }
        Log.e("score", sb.toString());
        return sb.toString();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View getInfoWindow(@NonNull @NotNull Marker marker) {
        return null;
    }
}
