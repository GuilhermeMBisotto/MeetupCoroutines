package com.arildo_guilherme.meetupcoroutines.ui.characters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildo_guilherme.data.characters.contract.CharactersRepository
import com.arildo_guilherme.data.characters.model.Character
import com.arildo_guilherme.meetupcoroutines.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.system.measureTimeMillis

class CharactersViewModel(private val repository: CharactersRepository) : BaseViewModel() {

    companion object {
        private val ids = listOf(
            1009144,
            1009149,
            1017100,
            1011136,
            1011031,
            1010870,
            1010846,
            1009150,
            1010699,
            1011297,
            1011198,
            1011031,
            1009146,
            1011334,
            1011176,
            1010354,
            1011266,
            1011175,
            1016823
        )
    }

    private val _characters = MutableLiveData<ArrayList<Character>>()
    val characters = Transformations.map(_characters) { it }

//    fun getCharacters() = launch {
//        val response = repository.getCharacters(offset, limit)
//        _characters.postValue(response)
//    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    suspend fun getCharacters() {
        val idsChannel = produce(CoroutineName("getCharacters")) {
            ids.forEach { send(it) }
        }

        val time = measureTimeMillis {
            coroutineScope {
                launch(CoroutineName("c_one")) { processIds(idsChannel, "c_one") }
                launch(CoroutineName("c_two")) { processIds(idsChannel, "c_two") }
                launch(CoroutineName("c_three")) { processIds(idsChannel, "c_three") }
                launch(CoroutineName("c_four")) { processIds(idsChannel, "c_four") }
            }
        }

        Log.e(">>>>>>TIME", "$time")
    }

    @ObsoleteCoroutinesApi
    private suspend fun processIds(idsAux: ReceiveChannel<Int>, name: String) {
        idsAux.consumeEach {
            coroutineScope {
                val responseDeferred = async {
                    repository.getCharacter(
                        it
                    )
                }

                responseDeferred.await()?.firstOrNull()?.let { char ->
                    var list = _characters.value
                    if (list.isNullOrEmpty()) list = arrayListOf()

                    char.coroutineName = name
                    list.add(char)
                    _characters.postValue(list)
                }
            }
        }
    }
}