package com.example.myfoodlist.room;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.R;
import com.example.myfoodlist.main.AddStoreDetailActivity;
import com.example.myfoodlist.main.StoreListActivity;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.ViewHolder>{

    private List<StoreData> dataList;

    private Activity context;
    private StoreDb database;

    public StoreListAdapter(StoreListActivity addStoreDetailActivity, List<StoreData> storeDataList) {
        this.context = addStoreDetailActivity;
        this.dataList = storeDataList;
        notifyDataSetChanged();
    }

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

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreData storeData = dataList.get(holder.getAdapterPosition());
                database.storeDataDao().delete(storeData);

                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreData storeData = dataList.get(holder.getAdapterPosition());

                final Long sId = storeData.getId();
                String name = storeData.getName();
                String addr = storeData.getAddress();
                String score = String.valueOf(storeData.getScore());
                String memo = storeData.getMemo();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);

                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setLayout(width, height);
                dialog.show();

                final EditText et_name = dialog.findViewById(R.id.et_name);
                final EditText et_addr = dialog.findViewById(R.id.et_addr);
                final EditText et_score = dialog.findViewById(R.id.et_score);
                final EditText et_memo = dialog.findViewById(R.id.et_memo);

                et_name.setText(name);
                et_addr.setText(addr);
                et_score.setText(score);
                et_memo.setText(memo);

                Button bt_update = dialog.findViewById(R.id.bt_update);

                bt_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Log.e("test", "test");
                        notifyDataSetChanged();
                    }
                });

            }
        });
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
