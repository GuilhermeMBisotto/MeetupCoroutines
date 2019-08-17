package com.arildo_guilherme.meetupcoroutines.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildo_guilherme.data.characters.contract.CharactersRepository
import com.arildo_guilherme.data.characters.model.Character
import com.arildo_guilherme.meetupcoroutines.base.BaseViewModel
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharactersRepository) : BaseViewModel() {

    companion object {
        var offset = 0
        var limit = 20
    }

    // val characters = liveData(coroutineContext) {
    //     emit(repository.getCharacters(offset, limit))
    // }
    private val _characters = MutableLiveData<ArrayList<Character>>()
    val characters = Transformations.map(_characters) { it }

    fun getCharacters() = launch {
        val response = repository.getCharacters(offset, limit)
        _characters.postValue(response)
    }
}