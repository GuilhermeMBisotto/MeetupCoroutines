package com.arildo_guilherme.data.characters.model

data class Stories(
    var available: Int,
    var collectionURI: String,
    var items: ArrayList<Resource>,
    var returned: Int
)