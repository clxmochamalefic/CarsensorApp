package com.example.carsensorapp.services.api

import com.example.carsensorapp.Constant
import com.example.carsensorapp.services.models.BrandMasterResponseModel
import com.example.carsensorapp.services.models.ColorMasterResponseModel
import com.example.carsensorapp.services.models.PrefMasterResponseModel
import com.example.carsensorapp.services.models.SearchCarResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ICarsensorAPIService {
    @GET("usedcar/v1/")
    fun getCars(
        @Query("key") key: String = Constant.API_KEY,
        @Query("brand") brand: String,
        @Query("model") model: String,
        @Query("pref") pref: String,
        @Query("color") color: String,
        @Query("format") format: String = "json"
    ): Call<SearchCarResponseModel>

    @GET("brand/v1/")
    fun getBrandMaster(
        @Query("key") key: String = Constant.API_KEY,
        @Query("format") format: String = "json"
    ): Call<BrandMasterResponseModel>

    @GET("pref/v1/")
    fun getPrefMaster(
        @Query("key") key: String = Constant.API_KEY,
        @Query("format") format: String = "json"
    ): Call<PrefMasterResponseModel>

    @GET("color/v1/")
    fun getColorMaster(
        @Query("key") key: String = Constant.API_KEY,
        @Query("format") format: String = "json"
    ): Call<ColorMasterResponseModel>
}