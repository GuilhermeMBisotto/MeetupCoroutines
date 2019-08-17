package com.arildo_guilherme.meetupcoroutines.ui.characters.adapters

import com.arildo_guilherme.data.characters.model.Character
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseRecyclerViewAdapter

class CharactersAdapter(
    private val itemClicked: (Character) -> Unit
) : BaseRecyclerViewAdapter<Character>(arrayListOf()) {

    override fun getObjForPosition(position: Int): Any = getList()[position]
    override fun getLayoutIdForPosition(position: Int): Int = R.layout.item_character
    override fun getAdapter(position: Int): BaseRecyclerViewAdapter<Character> = this
    override fun getItemCount(): Int = getList().size

    fun onItemClicked(item: Character) = itemClicked(item)
}