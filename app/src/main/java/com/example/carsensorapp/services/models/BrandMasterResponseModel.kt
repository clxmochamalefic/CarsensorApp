package com.example.carsensorapp.services.models

data class BrandMasterResponseModel (
    val api_version: Float,
    val results_available: Int,
    val results_returned: Int,
    val results_start: Int,
    val brand: List<BrandModel>
)

data class BrandModel (
    val code: String,
    val name: String,
    val country: CodeNamePair
)