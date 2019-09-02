package com.arildo_guilherme.data.characters.contract

import com.arildo_guilherme.data.characters.model.Character

interface CharactersRepository {
    suspend fun getCharacters(offset: Int, limit: Int): ArrayList<Character>?
    suspend fun getCharacter(id: Int): ArrayList<Character>?
}