package com.example.mvulatrustmobileapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

internal interface RetrofitAPI {
    @GET
    fun getMessage(@Url url: String?): Call<MsgModal?>?
}