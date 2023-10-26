package com.example.mvulatrustmobileapp;


import com.example.mvulatrustmobileapp.MsgModal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

interface RetrofitAPI {

    @GET
    Call<MsgModal>getMessage(@Url String url);
}
