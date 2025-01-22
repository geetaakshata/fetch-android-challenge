package com.example.fetchchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchchallenge.data.model.Item
import com.example.fetchchallenge.data.repository.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val repository = ItemRepository()

    private val _items = MutableLiveData<Map<Int, List<Item>>>()
    val items: LiveData<Map<Int, List<Item>>> = _items

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState


    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            try {
                _uiState.value = UiState(isLoading = true)

                repository.getItems()
                    .fold(
                        onSuccess = { rawItems ->
                            val filteredAndSortedItems = rawItems
                                .filter { !it.name.isNullOrBlank() }
                                .sortedWith(
                                    compareBy<Item> { it.listId }
                                        .thenBy { it.name }
                                )
                            val groupedItems = filteredAndSortedItems.groupBy { it.listId }
                            _items.value = groupedItems
                            _uiState.value = UiState(hasContent = true)
                        },
                        onFailure = { exception ->
                            _uiState.value = UiState(
                                errorMessage = "Failed to load items: ${exception.message}"
                            )
                        }
                    )
            } catch (e: Exception) {
                _uiState.value = UiState(
                    errorMessage = "An unexpected error occurred: ${e.message}"
                )
            }
        }
    }
}