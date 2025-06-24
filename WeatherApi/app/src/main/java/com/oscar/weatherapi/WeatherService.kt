package com.oscar.weatherapi

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getWeatherByCity(@Query("q") city: String, @Query("appid") apiKey: String, @Query("units") units: String = "metric"): WeatherResponse
}