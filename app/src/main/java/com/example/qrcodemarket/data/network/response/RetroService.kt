package com.example.qrcodemarket.data.network.response

import com.example.qrcodemarket.data.model_new.dataHistory
import com.example.qrcodemarket.data.model_new.dataStatistical
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RetroService {

    @GET("allaccess/{id}")
    @Headers("Content-Type: application/json")
    fun dataAccessMarket(@Path("id") citizenId:Int): Call<dataHistory>

    @GET("accessalluser")
    @Headers("Content-Type: application/json")
    fun dataStatistical(): Call<dataStatistical>

}