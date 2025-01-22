package com.example.fetchchallenge.data.model

import androidx.databinding.ObservableField

data class ItemUiModel(
    val item: Item
) {
    val nameText = ObservableField<String>()
    val idText = ObservableField<String>()

    init {
        nameText.set(item.name)
        idText.set("ID: ${item.id}")
    }
}