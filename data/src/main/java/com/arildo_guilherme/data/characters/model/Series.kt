package com.arildo_guilherme.data.characters.model

data class Series(
    var available: Int,
    var collectionURI: String,
    var items: ArrayList<Resource>
)