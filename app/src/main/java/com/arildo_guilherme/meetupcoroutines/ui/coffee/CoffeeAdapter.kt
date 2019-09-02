package com.arildo_guilherme.meetupcoroutines.ui.coffee

import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseRecyclerViewAdapter

class CoffeeAdapter : BaseRecyclerViewAdapter<String>(arrayListOf()) {

    override fun getItemCount(): Int = getList().size
    override fun getObjForPosition(position: Int): Any = getList()[position]
    override fun getLayoutIdForPosition(position: Int): Int = R.layout.item_coffee
    override fun getAdapter(position: Int): BaseRecyclerViewAdapter<String> = this
}