package com.example.calculator.api

import com.example.calculator.dao.Responce
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurRateApiService {

    @GET("url = https://currate.ru/api/")
    fun getActual(@Query("rates&pairs") pairs: ArrayList<String>) : Call<Responce>

}