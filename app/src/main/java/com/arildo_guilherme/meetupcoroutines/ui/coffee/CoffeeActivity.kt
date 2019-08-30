package com.arildo_guilherme.meetupcoroutines.ui.coffee

import android.os.Bundle
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
            viewModel.getOrdersOneBarista()
        }
        btnTwoBarista.setOnClickListener {
            viewModel.getOrdersTwoBaristas()
        }
    }
}