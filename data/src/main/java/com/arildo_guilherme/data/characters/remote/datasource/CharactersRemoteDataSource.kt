package com.arildo_guilherme.data.characters.remote.datasource

import com.arildo_guilherme.data.characters.contract.CharactersDataSource
import com.arildo_guilherme.data.characters.model.CharactersResult
import com.arildo_guilherme.data.characters.remote.service.CharactersApiService
import retrofit2.Response

class CharactersRemoteDataSource(private val api: CharactersApiService) :
    CharactersDataSource.Remote {

    override suspend fun getCharacters(offset: Int, limit: Int): Response<CharactersResult>? =
        api.characters(offset, limit).execute().body()

    override suspend fun getCharacter(id: Int): Response<CharactersResult> ?= api.character(id).execute().body()
}