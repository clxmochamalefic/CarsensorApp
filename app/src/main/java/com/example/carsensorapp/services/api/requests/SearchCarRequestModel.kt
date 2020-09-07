package com.example.carsensorapp.services.api.requests

data class SearchCarRequestModel(
    val key: String,
    val model: String,
    val pref: Int,
    val color: Int
)