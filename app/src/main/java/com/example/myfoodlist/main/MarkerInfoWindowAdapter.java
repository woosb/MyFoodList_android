package com.example.myfoodlist.main;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.myfoodlist.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{
    private Context context;

    public MarkerInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View getInfoContents(@NonNull @NotNull Marker marker) {

        Place place = (Place)marker.getTag();

        View view = LayoutInflater.from(context).inflate(R.layout.marker_info_contents, null);
        TextView textView = view.findViewById(R.id.text_view_title);
        textView.setText(place.getName());

        TextView textView1 = view.findViewById(R.id.text_view_address);
        textView1.setText(place.getAddress());
        TextView textView2 = view.findViewById(R.id.text_view_rating);
        textView2.setText(place.getLagLng() + "");
        ImageView imageView = view.findViewById(R.id.im_thumbnail);

        if(false){
            //사진파일 저장폴더 TODO 사용자가 저장한 사진파일 불러오는 로직 추가
            File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            String[] photoLists = storageDir.list();
            String name = photoLists[0];
            File file = new File(storageDir+File.separator+name);
            Uri uri = Uri.parse(file.getPath());
            imageView.setImageURI(uri);
        }

        return view;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View getInfoWindow(@NonNull @NotNull Marker marker) {
        return null;
    }
}
