package com.arildo_guilherme.data.characters.model

data class Events(
    var available: Int,
    var collectionURI: String,
    var items: ArrayList<Resource>,
    var returned: Int
)