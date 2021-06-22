package com.example.acedev.covidtracker.APIClasses

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

object Client {
    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.covid19india.org/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api = retrofit.create(RetrofitCall::class.java)
}

interface RetrofitCall{
    @GET("data.json")
    suspend fun getData() : Response<StateResponses>
}