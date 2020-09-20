package com.example.carsensorapp.services.models

data class SearchCarRootResponseModel (
    val results: SearchCarResponseModel
)

data class SearchCarResponseModel (
    val api_version: Float,
    val results_available: Int,
    val results_returned: Int,
    val results_start: Int,
    val usedcar: List<UsedCarModel>
)

data class UsedCarModel (
    val brand: CodeNamePair,
    val model: String,
    val grade: String,
    val price: String,
    val width: Int,
    val height: Int,
    val length: Int,
    val period: String,
    val person: Int,
    val series: String,
    val desc: String,
    val body: CodeNamePair,
    val photo: PhotoModel,
    val urls: UrlsModel
)

data class PhotoModel (
    val main: PhotoMainModel
)

data class PhotoMainModel (
    val l: String,
    val s: String,
    val caption: String
)

data class UrlsModel (
    val pc: String,
    val mobile: String,
    val qr: String
)