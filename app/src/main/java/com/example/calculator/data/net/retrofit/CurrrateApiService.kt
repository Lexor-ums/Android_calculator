package com.example.calculator.data.net.retrofit

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurRateApiService {

    @GET("?get=rates")
    fun getActualAsync(@Query("pairs") pairs: String,
                       @Query("key") key: String): Deferred<Response<CurrencyResponse>>

}