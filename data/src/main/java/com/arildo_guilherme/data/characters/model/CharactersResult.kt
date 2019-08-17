package com.arildo_guilherme.data.characters.model

data class CharactersResult(
    var code: Int,
    var status: String,
    var copyright: String,
    var attributionText: String,
    var attributionHTML: String,
    var etag: String,
    var data: CharactersData
)