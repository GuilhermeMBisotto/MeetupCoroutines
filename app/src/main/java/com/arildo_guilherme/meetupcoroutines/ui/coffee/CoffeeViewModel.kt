package com.arildo_guilherme.meetupcoroutines.ui.coffee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.arildo_guilherme.data.coffee.EspressoMachine
import com.arildo_guilherme.data.coffee.models.*
import com.arildo_guilherme.data.log
import com.arildo_guilherme.meetupcoroutines.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.system.measureTimeMillis

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class CoffeeViewModel : BaseViewModel() {

    companion object {
        private val list: ArrayList<String> = arrayListOf()
        private lateinit var espressoMachine: EspressoMachine
    }

    private val _coffees = MutableLiveData<ArrayList<String>>()
    val coffees = Transformations.map(_coffees) { it }

    fun getOrdersOneBarista(orders: List<Menu.Cappuccino>) = runBlocking {
        orders.forEach { log(it) }

        list.clear()
        _coffees.postValue(list)
        espressoMachine = EspressoMachine(this)

        val ordersChannel = produce(CoroutineName("cashier")) {
            orders.forEach { send(it) }
        }

        val time = measureTimeMillis {
            coroutineScope {
                launch(CoroutineName("barista-1")) {
                    list.add("• barista-1 processOrders")
                    processOrders(ordersChannel, espressoMachine, "barista-1")
                }
            }
        }
        list.add("• time: $time ms")
        _coffees.postValue(list)
        log("time: $time ms")

        espressoMachine.destroy()
    }

    fun getOrdersTwoBaristas(orders: List<Menu.Cappuccino>) = runBlocking {
        orders.forEach { log(it) }

        list.clear()
        _coffees.postValue(list)
        espressoMachine = EspressoMachine(this)

        val ordersChannel = produce(CoroutineName("cashier")) {
            orders.forEach { send(it) }
        }

        val time = measureTimeMillis {
            coroutineScope {
                launch(CoroutineName("barista-1")) {
                    list.add("• barista-1 processOrders")
                    processOrders(ordersChannel, espressoMachine, "barista-1")
                }
                launch(CoroutineName("barista-2")) {
                    list.add("• barista-2 processOrders")
                    processOrders(ordersChannel, espressoMachine, "barista-2")
                }
            }
        }
        list.add("• time: $time ms")
        _coffees.postValue(list)
        log("time: $time ms")

        espressoMachine.destroy()
    }

    private suspend fun processOrders(
        orders: ReceiveChannel<Menu.Cappuccino>,
        espressoMachine: EspressoMachine,
        baristaName: String
    ) {
        orders.consumeEach {
            val groundBeans = grindCoffeeBeans(it.beans(), baristaName)
            coroutineScope {
                val espressoDeferred = async {
                    list.add("• $baristaName, Pull Espresso Shot")
                    espressoMachine.pullEspressoShot(groundBeans)
                }
                val steamedMilkDeferred = async {
                    list.add("• $baristaName, Steam Milk")
                    espressoMachine.steamMilk(it.milk())
                }
                val cappuccino =
                    makeCappuccino(
                        it,
                        espressoDeferred.await(),
                        steamedMilkDeferred.await(),
                        baristaName
                    )
                list.add("• $baristaName, serve: $cappuccino")
                log("• $baristaName, serve: $cappuccino")
            }
        }
    }

    private suspend fun grindCoffeeBeans(
        beans: CoffeeBean,
        baristaName: String
    ): CoffeeBean.GroundBeans {
        list.add("• $baristaName, grinding coffee beans")
        log("$baristaName, grinding coffee beans")
        delay(100)
        return CoffeeBean.GroundBeans(beans)
    }

    private suspend fun makeCappuccino(
        order: Menu.Cappuccino, espresso: Espresso,
        steamedMilk: Milk.SteamedMilk,
        baristaName: String
    ): Beverage.Cappuccino {
        list.add("• $baristaName, making cappuccino")
        log("$baristaName, making cappuccino")
        delay(10)
        return Beverage.Cappuccino(order, espresso, steamedMilk)
    }
}