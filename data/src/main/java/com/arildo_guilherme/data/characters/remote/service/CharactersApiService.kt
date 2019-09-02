package com.arildo_guilherme.data.characters.remote.service

import com.arildo_guilherme.data.characters.model.CharactersResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApiService {

    @GET("v1/public/characters")
    suspend fun characters(@Query("offset") offset: Int, @Query("limit") limit: Int): Response<CharactersResult>

    @GET("v1/public/characters/{id}")
    suspend fun character(@Path("id") id: Int): Response<CharactersResult>
}