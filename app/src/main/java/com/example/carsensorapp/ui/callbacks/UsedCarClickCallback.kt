package com.example.carsensorapp.ui.callbacks

import com.example.carsensorapp.services.models.UsedCarModel

interface UsedCarClickCallback {
    fun onClick(usedCarModel: UsedCarModel)
}