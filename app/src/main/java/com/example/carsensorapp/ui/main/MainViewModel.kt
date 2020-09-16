package com.example.carsensorapp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.carsensorapp.Constant
import com.example.carsensorapp.services.repositories.CarRepository
import com.example.carsensorapp.services.models.CodeNamePair
import com.example.carsensorapp.services.models.BrandModel
import com.example.carsensorapp.services.models.PrefModel
import com.example.carsensorapp.services.models.UsedCarModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _repository = CarRepository.instance

    var carsLiveData: MutableLiveData<List<UsedCarModel>> = MutableLiveData()

    var brandsLiveData: MutableLiveData<List<BrandModel>> = MutableLiveData()
    var prefLiveData: MutableLiveData<List<PrefModel>> = MutableLiveData()
    var colorLiveData: MutableLiveData<List<CodeNamePair>> = MutableLiveData()

    init {
        loadLiveData()
    }

    fun fetchUsedCarLiveData(name: String, brandCode: String, prefCode: String, colorCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = _repository.getCars(
                    Constant.API_KEY,
                    brandCode,
                    name,
                    prefCode,
                    colorCode
                )
                val response = request.execute()
                if (response.isSuccessful) {
                    carsLiveData.postValue(response.body()?.results?.usedcar)
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun loadLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val brandsRequest = _repository.getBrands()
                val brandsResponse = brandsRequest.execute()
                if (brandsResponse.isSuccessful) {
                    val mutableBrands = brandsResponse.body()?.results?.brand?.toMutableList()
                    mutableBrands?.add(0, BrandModel("", "< 未選択 >", CodeNamePair("", "")))
                    brandsLiveData.postValue(mutableBrands)
                }

                val prefsRequest = _repository.getPrefs();
                val prefsResponse = prefsRequest.execute()
                if (prefsResponse.isSuccessful) {
                    val mutablePrefs = prefsResponse.body()?.results?.pref?.toMutableList()
                    mutablePrefs?.add(0, PrefModel("", "< 未選択 >", CodeNamePair("", "")))
                    prefLiveData.postValue(mutablePrefs)
                }

                val colorsRequest = _repository.getColors()
                val colorsResponse = colorsRequest.execute()
                if (colorsResponse.isSuccessful) {
                    val mutableColors = colorsResponse.body()?.results?.color?.toMutableList()
                    mutableColors?.add(0, CodeNamePair("", "< 未選択 >"))
                    colorLiveData.postValue(mutableColors)
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}