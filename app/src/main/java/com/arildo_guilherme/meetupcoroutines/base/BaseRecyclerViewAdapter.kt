package com.arildo_guilherme.meetupcoroutines.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.arildo_guilherme.meetupcoroutines.BR
import com.arildo_guilherme.meetupcoroutines.utils.bindingadapters.helpers.BindableAdapter

abstract class BaseRecyclerViewAdapter<T>(
    private var items: MutableList<T>
) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder<T>>(), BindableAdapter<List<T>> {

    companion object {
        const val BUNDLE_ITEMS = "items"
    }

    protected abstract fun getObjForPosition(position: Int): Any
    protected abstract fun getLayoutIdForPosition(position: Int): Int
    protected abstract fun getAdapter(position: Int): BaseRecyclerViewAdapter<T>
    protected fun getList() = items

    class BaseViewHolder<T>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(obj: Any) {
            binding.setVariable(BR.obj, obj)
            binding.executePendingBindings()
        }

        fun bind(
            adapter: BaseRecyclerViewAdapter<T>
        ) {
            binding.setVariable(BR.adapter, adapter)
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), viewType, parent, false
            )
        )

    override fun getItemCount(): Int = 0

    override fun onBindViewHolder(
        holder: BaseViewHolder<T>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val args = (payloads[0]) as Bundle
            for (key in args.keySet()) {
                if (key == BUNDLE_ITEMS) {
                    holder.bind(getAdapter(position))
                    holder.bind(getObjForPosition(position))
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(getAdapter(position))
        holder.bind(getObjForPosition(position))
    }

    override fun getItemViewType(position: Int): Int = getLayoutIdForPosition(position)

    override fun setData(data: List<T>?) {
        this.items.clear()
        data?.run {
            this@BaseRecyclerViewAdapter.items.addAll(data)
        }
        notifyDataSetChanged()
    }
}