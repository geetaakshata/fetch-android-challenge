package com.example.fetchchallenge.data.model

import androidx.databinding.ObservableField

data class HeaderUiModel(
    val listId: Int
) {
    val headerText = ObservableField<String>()

    init {
        headerText.set("List $listId")
    }
}