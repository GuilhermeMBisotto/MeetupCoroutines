package com.arildo_guilherme.meetupcoroutines.ui.characters.adapters

import com.arildo_guilherme.data.characters.model.Character
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseRecyclerViewAdapter

class CharactersSectionAdapter(
    private val itemClicked: (Character) -> Unit
) : BaseRecyclerViewAdapter<Character>(arrayListOf()) {

    override fun getAdapter(position: Int): BaseRecyclerViewAdapter<Character> =
        CharactersAdapter {
            onItemClicked(it)
        }

    override fun getLayoutIdForPosition(position: Int): Int = R.layout.item_characters_sections
    override fun getItemCount(): Int = getList().size
    override fun getObjForPosition(position: Int): Any = getList()[position]

    private fun onItemClicked(item: Character) = itemClicked(item)
}