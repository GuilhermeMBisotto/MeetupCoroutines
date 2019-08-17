package com.arildo_guilherme.data.characters.contract

import com.arildo_guilherme.data.characters.model.Character

interface CharactersRepository {
    suspend fun getcharacters(offset: Int, limit: Int): ArrayList<Character>
    suspend fun getcharacter(id: Int): ArrayList<Character>
}