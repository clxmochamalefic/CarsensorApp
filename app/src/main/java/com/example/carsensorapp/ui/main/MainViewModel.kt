package com.example.carsensorapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
                    brandsLiveData.postValue(brandsResponse.body()?.results?.brand)
                }

                val prefsRequest = _repository.getPrefs();
                val prefsResponse = prefsRequest.execute()
                if (prefsResponse.isSuccessful) {
                    prefLiveData.postValue((prefsResponse.body()?.results?.pref))
                }

                val colorsRequest = _repository.getColors()
                val colorsResponse = colorsRequest.execute()
                if (colorsResponse.isSuccessful) {
                    colorLiveData.postValue(colorsResponse.body()?.results?.color)
                }

            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}