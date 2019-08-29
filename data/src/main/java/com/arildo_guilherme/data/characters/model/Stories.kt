package com.arildo_guilherme.data.characters.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stories(
    var available: Int,
    var collectionURI: String,
    var items: ArrayList<Resource>,
    var returned: Int
) : Parcelable