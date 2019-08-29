package com.arildo_guilherme.data.characters.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Resource(
    var resourceURI: String,
    var name: String,
    var type: String
) : Parcelable