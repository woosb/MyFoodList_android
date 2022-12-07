package com.example.myfoodlist.samples;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfoodlist.R;
import com.example.myfoodlist.room.MainAdapter;
import com.example.myfoodlist.room.MainData;
import com.example.myfoodlist.room.RoomDb;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment implements View.OnClickListener{

    public BlankFragment(){}

    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    RoomDb database;
    MainAdapter adapter;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Context context = getContext();

        editText = view.findViewById(R.id.edit_text);
        btAdd = view.findViewById(R.id.bt_add);
        btReset = view.findViewById(R.id.bt_reset);
        recyclerView = view.findViewById(R.id.recycler_view);

        database = RoomDb.getInstance(context);
        dataList = database.mainDao().getAll();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));



        return view;
    }

    @Override
    public void onClick(View view) {
    }
}