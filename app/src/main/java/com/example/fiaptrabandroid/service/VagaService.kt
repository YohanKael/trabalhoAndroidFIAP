package com.example.fiaptrabandroid.service

import com.example.fiaptrabandroid.model.Results
import retrofit2.Call
import retrofit2.http.GET

interface VagaService {

    @GET("remote-jobs/")
    fun getAllJobs(): Call<Results>
}