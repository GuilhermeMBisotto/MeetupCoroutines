package com.arildo_guilherme.meetupcoroutines.ui.characters.adapters

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.arildo_guilherme.data.characters.model.Character
import com.arildo_guilherme.meetupcoroutines.base.BaseRecyclerViewAdapter.Companion.BUNDLE_ITEMS

class CharactersDiffUtils(
    private val oldList: List<Character>?,
    private val newList: List<Character>?
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (oldList.isNullOrEmpty() && newList.isNullOrEmpty()) {
            false
        } else {
            if (!oldList.isNullOrEmpty() && !newList.isNullOrEmpty()) {
                val old = oldList[oldItemPosition]
                val new = newList[newItemPosition]

                old.id == new.id
            } else {
                false
            }
        }

    override fun getOldListSize(): Int = if (oldList.isNullOrEmpty()) 0 else oldList.size

    override fun getNewListSize(): Int = if (newList.isNullOrEmpty()) 0 else newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (oldList.isNullOrEmpty() && newList.isNullOrEmpty()) {
            false
        } else {
            if (!oldList.isNullOrEmpty() && !newList.isNullOrEmpty()) {
                val old = oldList[oldItemPosition]
                val new = newList[newItemPosition]

                old == new
            } else {
                false
            }
        }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        if (oldList.isNullOrEmpty() && newList.isNullOrEmpty()) {
            return null
        } else {

            var oldItem: Character? = null
            oldList?.run {
                oldItem = this[oldItemPosition]
            }

            var newItem: Character? = null
            newList?.run {
                newItem = this[newItemPosition]
            }

            val diff = Bundle()
            if (newItem !== oldItem) {
                diff.putParcelable(BUNDLE_ITEMS, newItem)
            }

            return if (diff.size() == 0) {
                null
            } else {
                diff
            }
        }
    }
}