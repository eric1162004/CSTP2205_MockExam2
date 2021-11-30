package com.example.assignment4.utils.map

import com.example.mock1exam.data.CatAPI.responses.CatResponse
import com.example.mymaptesting.responses.LocationResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatApi {
    @GET("v1/breeds?")
    suspend fun getCatsResult(
        @Query("attach_breed") attach_breed: String,
        @Query("limit") limit: Int
    ): List<CatResponse>
}