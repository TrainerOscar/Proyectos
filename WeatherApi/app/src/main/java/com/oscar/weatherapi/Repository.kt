package com.oscar.weatherapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Repository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: WeatherService = retrofit.create(WeatherService::class.java)
}