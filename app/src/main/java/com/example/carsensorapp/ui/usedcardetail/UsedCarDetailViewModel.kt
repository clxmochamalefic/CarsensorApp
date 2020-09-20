package com.example.carsensorapp.ui.usedcardetail

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.carsensorapp.services.models.UsedCarDetailModel
import kotlinx.coroutines.launch

class UsedCarDetailViewModel(
    private val myApplication: Application,
    private val usedCarDetailModel: UsedCarDetailModel?
) : AndroidViewModel(myApplication) {

    val usedCarLiveData: MutableLiveData<UsedCarDetailModel> = MutableLiveData()

    var usedCar = ObservableField<UsedCarDetailModel>()

    init {
        viewModelScope.launch {
            usedCarLiveData.postValue(usedCarDetailModel)
        }
    }

    fun setUsedCarDetailModel(model: UsedCarDetailModel)
    {
        usedCar.set(model);
    }

    class Factory(
        private val application: Application,
        private val usedCarDetailModel: UsedCarDetailModel
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKD_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UsedCarDetailViewModel(application, usedCarDetailModel) as T
        }
    }
}