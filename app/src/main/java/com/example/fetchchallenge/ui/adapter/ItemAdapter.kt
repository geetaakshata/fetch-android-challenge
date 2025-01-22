package com.example.fetchchallenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchchallenge.data.model.HeaderUiModel
import com.example.fetchchallenge.data.model.Item
import com.example.fetchchallenge.data.model.ItemUiModel
import com.example.fetchchallenge.databinding.ItemHeaderBinding
import com.example.fetchchallenge.databinding.ItemRowBinding

class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Any> = emptyList()

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    data class HeaderItem(val listId: Int)

    fun setItems(groupedItems: Map<Int, List<Item>>) {
        val flatList = mutableListOf<Any>()
        groupedItems.forEach { (listId, items) ->
            flatList.add(HeaderUiModel(listId))
            flatList.addAll(items.map { ItemUiModel(it) })
        }
        this.items = flatList
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is HeaderUiModel -> TYPE_HEADER
            is ItemUiModel -> TYPE_ITEM
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = ItemRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is HeaderUiModel -> (holder as HeaderViewHolder).bind(item)
            is ItemUiModel -> (holder as ItemViewHolder).bind(item)
        }
    }

    class HeaderViewHolder(
        private val binding: ItemHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(header: HeaderUiModel) {
            binding.header = header
            binding.executePendingBindings()
        }
    }

    class ItemViewHolder(
        private val binding: ItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemUiModel) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}