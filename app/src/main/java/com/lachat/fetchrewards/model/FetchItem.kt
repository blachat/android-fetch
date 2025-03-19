package com.lachat.fetchrewards.model

import android.os.Parcel
import android.os.Parcelable

data class FetchItem(
    val id: Int,
    val listId: Int,
    val name: String?,
) : Parcelable {  // Added Parcelable implementation to object via nav args
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(listId)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FetchItem> {
        override fun createFromParcel(parcel: Parcel): FetchItem {
            return FetchItem(parcel)
        }

        override fun newArray(size: Int): Array<FetchItem?> {
            return arrayOfNulls(size)
        }
    }
}
