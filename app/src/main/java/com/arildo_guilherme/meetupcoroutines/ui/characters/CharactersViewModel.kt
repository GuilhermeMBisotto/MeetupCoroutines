package com.arildo_guilherme.meetupcoroutines.ui.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildo_guilherme.data.characters.contract.CharactersRepository
import com.arildo_guilherme.data.characters.model.Character
import com.arildo_guilherme.meetupcoroutines.base.BaseViewModel
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharactersRepository) : BaseViewModel() {

    companion object {
        private const val LIMIT = 20
        private const val OFFSET = 0
    }

    private val _characters = MutableLiveData<ArrayList<Character>>()
    val characters = Transformations.map(_characters) { it }

    fun getCharacters() = launch {
        val response = repository.getCharacters(OFFSET, LIMIT)
        response?.run {
            _characters.postValue(response)
        }
    }
}