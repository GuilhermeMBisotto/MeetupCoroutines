package com.arildo_guilherme.data.characters

import com.arildo_guilherme.data.characters.contract.CharactersDataSource
import com.arildo_guilherme.data.characters.contract.CharactersRepository
import com.arildo_guilherme.data.characters.model.Character

class CharactersRepositoryImpl(private val remote: CharactersDataSource.Remote) :
    CharactersRepository {

    override suspend fun getCharacters(offset: Int, limit: Int): ArrayList<Character>? {
        val response = remote.getCharacters(offset, limit)
        return response?.body()?.data?.results
    }

    override suspend fun getCharacter(id: Int): ArrayList<Character>? {
        val response = remote.getCharacter(id)
        return response?.body()?.data?.results
    }
}