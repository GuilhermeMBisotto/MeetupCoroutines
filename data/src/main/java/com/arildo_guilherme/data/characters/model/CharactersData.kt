package com.arildo_guilherme.data.characters.model

data class CharactersData(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: ArrayList<Character>
) {
    fun hasMore(): Boolean {
        return (offset * limit) + count < total
    }
}