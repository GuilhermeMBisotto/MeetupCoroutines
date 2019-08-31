package com.arildo_guilherme.data.characters.contract

import com.arildo_guilherme.data.characters.model.CharactersResult

interface CharactersDataSource {

    interface remote {
        suspend fun getcharacters(offset: Int, limit: Int): CharactersResult
        suspend fun getcharacter(id: Int): CharactersResult
    }
}