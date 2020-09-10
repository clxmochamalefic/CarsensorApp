package com.example.carsensorapp.services.models

data class SearchCarResponseModel (
    val api_version: Float,
    val results_available: Int,
    val results_returned: Int,
    val results_start: Int,
    val usedcar: List<UsedCarModel>
)

data class UsedCarModel (
    val id: String,
    val brand: CodeNamePair,
    val model: String,
    val grade: String,
    val price: String,
    val inspection: String,
    val maintenance: String,
    val warranty: String,
    val recycle: String,
    val engine: String,
    val desc: String,
    val body: CodeNamePair,
    val odd: String,
    val year: Int,
    val shop: ShopModel,
    val color: String,
    val maintenance_comment: String,
    val maintenance_fee: Int,
    val photo: PhotoModel,
    val sub_img: SubImageModel,
    val urls: UrlsModel,
    val warranty_comment: String,
    val warranty_distance: String,
    val warranty_length: String,
    val warranty_fee: Int
)

data class ShopModel (
    val name: String,
    val pref: CodeNamePair,
    val lat: Double,
    val lng: Double,
    val datum: String
)

data class PhotoModel (
    val main: PhotoMainModel,
    val sub: List<String>
)

data class PhotoMainModel (
    val l: String,
    val s: String,
    val caption: String
)

data class SubImageModel (
    val l: List<String>,
    val caption: String
)

data class UrlsModel (
    val pc: String,
    val mobile: String,
    val qr: String
)