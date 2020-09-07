package com.example.carsensorapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.example.carsensorapp.Constant
import com.example.carsensorapp.repositories.CarRepository
import com.example.carsensorapp.services.api.CodeNamePair
import com.example.carsensorapp.services.api.responses.BrandModel
import com.example.carsensorapp.services.api.responses.PrefModel
import com.example.carsensorapp.services.api.responses.UsedCarModel
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
        _loadLiveData()
    }

    private fun _loadLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                val request = _repository.getCars(
//                    Constant.API_KEY,
//                    "AD",
//                    "A1",
//                    "13",
//                    "WT"
//                )
//                val request = _repository.getCars(
//                    Constant.API_KEY,
//                    "",
//                    "",
//                    "",
//                    ""
//                )
//                val response = request.execute()
//                if (response.isSuccessful) {
//                    Log.d("MainViewModel", response.body()?.results_returned.toString())
//                    carsLiveData.postValue(response.body()?.usedcar)
//                }

                val brandsRequest = _repository.getBrands()
                val brandsResponse = brandsRequest.execute()
                if (brandsResponse.isSuccessful) {
                    brandsLiveData.postValue(brandsResponse.body()?.brand)
                }

                val prefsRequest = _repository.getPrefs();
                val prefsResponse = prefsRequest.execute()
                if (prefsResponse.isSuccessful) {
                    prefLiveData.postValue((prefsResponse.body()?.pref))
                }

                val colorsRequest = _repository.getColors()
                val colorsResponse = colorsRequest.execute()
                if (colorsResponse.isSuccessful) {
                    colorLiveData.postValue(colorsResponse.body()?.color)
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}