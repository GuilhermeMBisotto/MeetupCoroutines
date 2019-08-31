package com.arildo_guilherme.data.characters.remote.datasource

import com.arildo_guilherme.data.characters.contract.CharactersDataSource
import com.arildo_guilherme.data.characters.model.CharactersResult
import com.arildo_guilherme.data.characters.remote.service.CharactersApiService

class CharactersRemoteDataSource(private val api: CharactersApiService) :
    CharactersDataSource.remote {

    override suspend fun getcharacters(offset: Int, limit: Int): CharactersResult =
        api.characters(offset, limit)

    override suspend fun getcharacter(id: Int): CharactersResult = api.character(id)
}