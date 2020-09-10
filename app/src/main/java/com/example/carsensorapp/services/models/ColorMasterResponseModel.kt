package com.example.carsensorapp.services.models

data class ColorMasterRootResponseModel (
    val results: ColorMasterResponseModel
)

data class ColorMasterResponseModel (
    val api_version: Float,
    val results_available: Int,
    val results_returned: Int,
    val results_start: Int,
    val color: List<CodeNamePair>
)