package com.arildo_guilherme.meetupcoroutines.ui.characters

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

/* annotation class ObsoleteCoroutinesApi (source)
 * Marks declarations that are obsolete in coroutines API,
 * which means that the design of the corresponding declarations has
 * serious known flaws and they will be redesigned in the future.
 * Roughly speaking, these declarations will be deprecated in the
 * future but there is no replacement for them yet, so they cannot be deprecated right away.
 */
@ObsoleteCoroutinesApi
class CharactersMultiCoroutinesViewModel(private val repository: CharactersRepository) : BaseViewModel() {

    companion object {
        private val list: ArrayList<Character> = arrayListOf()
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

    private val _time = MutableLiveData<Long>()
    val time = Transformations.map(_time) { it }

    val isLoading = MutableLiveData(true)


    /* annotation class ExperimentalCoroutinesApi (source)
     * Marks declarations that are still experimental in coroutines API,
     * which means that the design of the corresponding declarations has open
     * issues which may (or may not) lead to their changes in the future.
     * Roughly speaking, there is a chance that those declarations will
     * be deprecated in the near future or the semantics of their behavior may
     * change in some way that may break some code.
     */
    @ExperimentalCoroutinesApi
    suspend fun getCharacters() {
        val idsChannel = produce(CoroutineName("getCharacters")) {
            ids.forEach { send(it) }
        }

        _time.postValue(measureTimeMillis {
            coroutineScope {
                launch(CoroutineName("c_one")) { processIds(idsChannel, "c_one") }
                launch(CoroutineName("c_two")) { processIds(idsChannel, "c_two") }
                launch(CoroutineName("c_three")) { processIds(idsChannel, "c_three") }
                launch(CoroutineName("c_four")) { processIds(idsChannel, "c_four") }
            }
        })
    }

    private suspend fun processIds(idsAux: ReceiveChannel<Int>, name: String) {
        idsAux.consumeEach {
            coroutineScope {
                val responseDeferred = async {
                    repository.getCharacter(
                        it
                    )
                }

                responseDeferred.await()?.firstOrNull()?.let { char ->
                    char.coroutineName = name
                    list.add(char)
                    _characters.postValue(list)
                    isLoading.postValue(false)
                }
            }
        }
    }

    override fun onCleared() {
        list.clear()
        super.onCleared()
    }
}