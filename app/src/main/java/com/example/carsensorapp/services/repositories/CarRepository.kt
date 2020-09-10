package com.example.carsensorapp.services.repositories

import com.example.carsensorapp.Constant
import com.example.carsensorapp.services.api.ICarsensorAPIService
import com.example.carsensorapp.services.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CarRepository {
    private val retrofit = Retrofit.Builder()
                                   .baseUrl(Constant.BASE_URL)
                                   .addConverterFactory(MoshiConverterFactory.create())
                                   .build()

    private val service = retrofit.create(ICarsensorAPIService::class.java)

    suspend fun getCars(
        key: String,
        brand: String,
        model: String,
        pref: String,
        color: String
    ): Call<SearchCarResponseModel> = service.getCars(key, brand, model, pref, color, "json")

    suspend fun getBrands(): Call<BrandMasterRootResponseModel> = service.getBrandMaster()

    suspend fun getPrefs(): Call<PrefMasterResponseModel> = service.getPrefMaster()

    suspend fun getColors(): Call<ColorMasterResponseModel> = service.getColorMaster()

    companion object Factory {
        val instance: CarRepository
            @Synchronized get() {
                return CarRepository()
            }
    }
}