package com.example.baithithu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class SinhVienHolder extends RecyclerView.ViewHolder {

    protected TextView textId;
    protected ImageView imageDelete;
    protected TextView textTen;




    public SinhVienHolder(@NonNull View itemView) {
        super(itemView);

        textId = (TextView) itemView.findViewById(R.id.textId);
        imageDelete = (ImageView) itemView.findViewById(R.id.imageDelete);
        textTen = (TextView) itemView.findViewById(R.id.textTen);

    }
}
