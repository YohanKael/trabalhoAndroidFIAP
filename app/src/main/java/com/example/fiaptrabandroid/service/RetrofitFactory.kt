package com.example.fiaptrabandroid.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val bASEURL = "https://jobicy.com/api/v2/"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(bASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJobService(): VagaService{
        return retrofitFactory.create(VagaService::class.java)
    }

}