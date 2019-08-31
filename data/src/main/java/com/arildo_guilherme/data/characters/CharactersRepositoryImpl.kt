package com.arildo_guilherme.data.characters

import com.arildo_guilherme.data.characters.contract.CharactersDataSource
import com.arildo_guilherme.data.characters.contract.CharactersRepository
import com.arildo_guilherme.data.characters.model.Character

class CharactersRepositoryImpl(private val remote: CharactersDataSource.remote) :
    CharactersRepository {

    override suspend fun getcharacters(offset: Int, limit: Int): ArrayList<Character> {
        val response = remote.getcharacters(offset, limit)
        return response.data.results
    }

    override suspend fun getcharacter(id: Int): ArrayList<Character> {
        val response = remote.getcharacter(id)
        return response.data.results
    }
}