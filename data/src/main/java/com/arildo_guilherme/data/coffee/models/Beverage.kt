package com.arildo_guilherme.data.coffee.models

sealed class Beverage {
    data class Cappuccino(val order: Menu.Cappuccino, val espressoShot: Espresso, val steamedMilk: Milk.SteamedMilk): Beverage()
}