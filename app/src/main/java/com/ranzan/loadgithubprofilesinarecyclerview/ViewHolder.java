package com.ranzan.loadgithubprofilesinarecyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView name, login;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        intiViews(itemView);
    }

    private void intiViews(View itemView) {
        imageView = itemView.findViewById(R.id.image);
        name = itemView.findViewById(R.id.name);
        login = itemView.findViewById(R.id.login);
    }

    public void setData(ResponseItem responseItem) {
        name.setText(responseItem.getName());
        login.setText(responseItem.getId() + "");
    }
}
