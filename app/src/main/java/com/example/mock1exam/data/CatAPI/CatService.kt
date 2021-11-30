package com.example.mock1exam.data.CatAPI

import android.util.Log
import com.example.assignment4.utils.map.CatApi
import com.example.mock1exam.data.CatAPI.responses.CatResponse
import com.example.mock1exam.utils.Resource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val api_endpoint : String = "https://api.thecatapi.com/"

class CatService {
    private val api: CatApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(api_endpoint)
        .build()
        .create(CatApi::class.java)

    suspend fun getCatsByBreed(
        breed: String = "",
        limit: Int = 1
    ): Resource<List<CatResponse>> {
        val response = try {
            api.getCatsResult(breed, limit)
        } catch (e: Exception) {
            return Resource.Error(e.message!!)
        }
        return Resource.Success(response)
    }
}