package com.example.carsensorapp.services.models

import kotlinx.serialization.Serializable

@Serializable
data class UsedCarDetailModel (
    val brandName: String?,
    val model: String?,
    val grade: String?,
    val price: String?,
    val width: Int?,
    val height: Int?,
    val length: Int?,
    val period: String?,
    val person: Int?,
    val series: String?,
    val desc: String?,
    val bodyName: String?,
    val photoLargeUrl: String?,
    val mobileUrl: String?
)
    : android.os.Parcelable {
    constructor(model: UsedCarModel) : this(
        model.brand.name,
        model.model,
        model.grade,
        model.price,
        model.width,
        model.height,
        model.length,
        model.period,
        model.person,
        model.series,
        model.desc,
        model.body.name,
        model.photo.main.l,
        model.urls.mobile
    ) {
    }

    constructor(parcel: android.os.Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readInt() ?: 0,
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: android.os.Parcel, flags: Int) {
        parcel.writeString(brandName ?: "")
        parcel.writeString(model ?: "")
        parcel.writeString(grade ?: "")
        parcel.writeString(price ?: "")
        parcel.writeInt(width ?: 0)
        parcel.writeInt(height ?: 0)
        parcel.writeInt(length ?: 0)
        parcel.writeString(period ?: "")
        parcel.writeInt(person ?: 0)
        parcel.writeString(series ?: "")
        parcel.writeString(desc ?: "")
        parcel.writeString(bodyName ?: "")
        parcel.writeString(photoLargeUrl ?: "")
        parcel.writeString(mobileUrl ?: "")
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : android.os.Parcelable.Creator<UsedCarDetailModel> {
        override fun createFromParcel(parcel: android.os.Parcel): UsedCarDetailModel {
            return UsedCarDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<UsedCarDetailModel?> {
            return arrayOfNulls(size)
        }
    }
}