package com.example.qrcodemarket.data.network


import com.example.qrcodemarket.data.model.getMarket
import com.example.qrcodemarket.data.model_new.Market
import com.example.qrcodemarket.data.model_new.Statistical
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


interface SyntheticApi {

    @GET("accessalluser")
    suspend fun Statistical(): Response<List<Statistical>>

    @GET("allmarkets")
    suspend fun dataMarket(): Response<List<Market>>


    companion object {
        operator fun invoke(): SyntheticApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.8:80/myapi/public/")
                .build()
                .create(SyntheticApi::class.java)
        }
    }
}