package com.ranzan.loadgithubprofilesinarecyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceApi {
    @GET("/users/{user}/repos")
    Call<List<ResponseItem>> getUser(@Path("user") String name);

}
