package com.example.baithithu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienHolder> {

    private MainActivity context;
    private ArrayList<SinhVien> list;
    int layout;

    public SinhVienAdapter(MainActivity context, ArrayList<SinhVien> list,int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
    }


    @NonNull
    @Override
    public SinhVienHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        return new SinhVienHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SinhVienHolder holder, final int i) {
        final SinhVien sinhvien = list.get(i);
        holder.textId.setText("ID: " + sinhvien.getID());
        holder.textTen.setText("Tên sinh viên: "+sinhvien.getName());

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.xoa(sinhvien.getmID(),sinhvien.getName(),i);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.hienThi(sinhvien,i);

            }
        });

    }

    @Override
    public int getItemCount() {

        if (list == null)
        return 0;
        else return list.size();
    }
}