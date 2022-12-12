package com.example.myfoodlist.room;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.R;
import com.example.myfoodlist.main.AddStoreDetailActivity;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder>{

    private List<StoreData> dataList;

    private Activity context;
    private StoreDb database;

    public StoreListAdapter(AddStoreDetailActivity addStoreDetailActivity, List<StoreData> storeDataList) {
        this.context = addStoreDetailActivity;
        this.dataList = storeDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public StoreListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StoreListAdapter.ViewHolder holder, int position) {
        final StoreData data = dataList.get(position);
        database = StoreDb.getInstance(context);
        holder.tv_name.setText(data.getName());
        holder.tv_addr.setText(data.getAddress());
        holder.tv_score.setText(data.getScore()+"");
        holder.tv_latLng.setText(data.getLatitude() + "/" +data.getLongitude());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name, tv_addr, tv_score, tv_latLng;
        ImageView btEdit, btDelete;
        public ViewHolder(@NonNull View view)
        {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_addr = view.findViewById(R.id.tv_addr);
            tv_score = view.findViewById(R.id.tv_score);
            tv_latLng = view.findViewById(R.id.tv_latLng);
            btEdit = view.findViewById(R.id.bt_edit);
            btDelete = view.findViewById(R.id.bt_delete);

        }
    }
}
