package com.example.assignment4.utils.map

import com.example.mock1exam.utils.Resource
import com.example.mymaptesting.responses.LocationResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mapbox_base_url: String = "https://api.mapbox.com/"
val mapbox_access_token : String = "pk.eyJ1IjoiZXJpYzExNjIwMDQiLCJhIjoiY2t2d29heGx1MGtrbjJvdDM0d2hjdnVhdSJ9.fprIOurhSzjFs378lvMBIw"

class MapService {
    private val api: MapApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(mapbox_base_url)
        .build()
        .create(MapApi::class.java)

    suspend fun getLocationResult(
        searchText: String,
        accessToken: String = mapbox_access_token,
        limit: Int = 1
    ): Resource<LocationResult> {
        val response = try {
            api.getLocationResult(searchText, accessToken, limit)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}