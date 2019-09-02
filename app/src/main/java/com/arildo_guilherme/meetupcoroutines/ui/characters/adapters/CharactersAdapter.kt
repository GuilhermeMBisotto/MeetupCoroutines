package com.arildo_guilherme.meetupcoroutines.ui.characters.adapters

import androidx.recyclerview.widget.DiffUtil
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

    override fun setData(data: List<Character>?) {
        val diffUtils = DiffUtil.calculateDiff(CharactersDiffUtils(getList(), data))

        getList().clear()
        data?.run {
            getList().addAll(data)
        }
        diffUtils.dispatchUpdatesTo(this)
    }

    fun onItemClicked(item: Character) = itemClicked(item)
}