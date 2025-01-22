package com.example.fetchchallenge.viewmodel

data class UiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val hasContent: Boolean = false
) {
    val showLoading: Boolean = isLoading && !hasContent
    val showError: Boolean = errorMessage != null && !hasContent
    val showContent: Boolean = hasContent && !isLoading && errorMessage == null
}