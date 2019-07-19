package com.example.calculator.api

import com.example.calculator.data.dao.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurRateApiService {

    @GET("?get=rates")
    fun getActual(@Query("pairs") pairs: String, @Query("key") key: String): Call<Response>

}