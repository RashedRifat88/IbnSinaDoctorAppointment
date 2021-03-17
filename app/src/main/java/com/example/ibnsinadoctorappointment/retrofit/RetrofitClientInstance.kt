package com.example.ibnsinadoctorappointment.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {
    val client = OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }



//    private val BASE_URL = ""
//
//    private val httpClient = OkHttpClient.Builder()
//
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(ApiConstants.getBaseUrl())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(httpClient.build())
//        .build()
//
//    fun<T> buildService (service : Class <T>) : T {
//        return  retrofit.create(service)
//    }

}