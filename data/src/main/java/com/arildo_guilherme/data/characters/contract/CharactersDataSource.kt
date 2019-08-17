package com.arildo_guilherme.data.characters.contract

import com.arildo_guilherme.data.characters.model.CharactersResult
import retrofit2.Response

interface CharactersDataSource {

    interface Remote {
        suspend fun getCharacters(offset: Int, limit: Int): Response<CharactersResult>?
        suspend fun getCharacter(id: Int): Response<CharactersResult>?
    }
}