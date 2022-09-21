package com.example.testapp

import android.os.Parcel
import android.os.Parcelable

data class SharedBooking(
    var title: String?,
    var time: String?,
    var date: String?,
) : Parcelable {

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel?, int: Int) {
        parcel?.writeString(title)
        parcel?.writeString(time)
        parcel?.writeString(date)
    }

    companion object CREATOR : Parcelable.Creator<SharedBooking> {
        override fun createFromParcel(parcel: Parcel): SharedBooking {
            return SharedBooking(parcel)
        }

        override fun newArray(size: Int): Array<SharedBooking?> {
            return arrayOfNulls(size)
        }
    }
}