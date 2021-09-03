package com.ranzan.loadgithubprofilesinarecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BlankFragment extends Fragment {
    private EditText editText;
    private Button btn;
    private RecyclerView recyclerView;
    private List<ResponseItem> list;
    private Adapter adapter;
    private ImageView profilePic;
    private TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        adapter = new Adapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void initViews(View view) {
        name = view.findViewById(R.id.profileName);
        profilePic = view.findViewById(R.id.profileImage);
        editText = view.findViewById(R.id.et);
        recyclerView = view.findViewById(R.id.recyclerView);
        btn = view.findViewById(R.id.btn);
        setRecyclerView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiCall();
            }
        });
    }

    private void apiCall() {
        ServiceApi serviceApi = Network.getRetrofitInstance().create(ServiceApi.class);
        serviceApi.getUser(editText.getText().toString()).enqueue(new Callback<List<ResponseItem>>() {
            @Override
            public void onResponse(Call<List<ResponseItem>> call, Response<List<ResponseItem>> response) {

                if (response.body() != null) {
                    list = response.body();
                    adapter.updateData(list);
                    name.setText(response.body().get(0).getOwner().getLogin());
                    Glide.with(getContext()).load(response.body().get(0).getOwner().getAvatarUrl()).into(profilePic);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}