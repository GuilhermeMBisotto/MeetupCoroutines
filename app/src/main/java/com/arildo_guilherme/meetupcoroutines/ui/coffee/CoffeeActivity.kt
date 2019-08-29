package com.arildo_guilherme.meetupcoroutines.ui.coffee

import android.os.Bundle
import com.arildo_guilherme.data.coffee.models.CoffeeBean
import com.arildo_guilherme.data.coffee.models.Menu
import com.arildo_guilherme.data.coffee.models.Milk
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityCoffeeBinding
import kotlinx.android.synthetic.main.activity_coffee.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.ext.android.inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class CoffeeActivity : BaseActivity<ActivityCoffeeBinding>(R.layout.activity_coffee) {

    companion object {
        private val orders = listOf(
            Menu.Cappuccino(CoffeeBean.Regular, Milk.Whole),
            Menu.Cappuccino(CoffeeBean.Premium, Milk.Breve),
            Menu.Cappuccino(CoffeeBean.Regular, Milk.NonFat),
            Menu.Cappuccino(CoffeeBean.Decaf, Milk.Whole),
            Menu.Cappuccino(CoffeeBean.Regular, Milk.NonFat),
            Menu.Cappuccino(CoffeeBean.Decaf, Milk.NonFat)
        )
    }

    private val viewModel: CoffeeViewModel by inject()
    private val adapter: CoffeeAdapter by lazy {
        CoffeeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewModel = viewModel
        binding.recyclerViewCoffee.adapter = adapter
    }

    override fun subscribeUi() {
        super.subscribeUi()

        btnOneBarista.setOnClickListener {
            viewModel.getOrdersOneBarista(orders)
        }
        btnTwoBarista.setOnClickListener {
            viewModel.getOrdersTwoBaristas(orders)
        }
    }
}