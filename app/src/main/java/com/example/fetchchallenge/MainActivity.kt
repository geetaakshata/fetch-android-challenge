package com.example.fetchchallenge

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchchallenge.databinding.ActivityMainBinding
import com.example.fetchchallenge.ui.adapter.ItemAdapter
import com.example.fetchchallenge.viewmodel.ItemViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ItemViewModel by viewModels()
    private val adapter = ItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun observeViewModel() {
        viewModel.items.observe(this) { items ->
            adapter.setItems(items)
        }

        viewModel.uiState.observe(this) { state ->
            updateUiVisibility(state)

            if (state.showError) {
                binding.errorText.text = state.errorMessage
                Snackbar.make(binding.root, state.errorMessage ?: "Unknown error", Snackbar.LENGTH_LONG)
                    .setAction("Retry") { viewModel.loadItems() }
                    .show()
            }
        }
    }

    private fun updateUiVisibility(state: com.example.fetchchallenge.viewmodel.UiState) {
        binding.apply {
            progressBar.visibility = if (state.showLoading) View.VISIBLE else View.GONE
            recyclerView.visibility = if (state.showContent) View.VISIBLE else View.GONE
            errorText.visibility = if (state.showError) View.VISIBLE else View.GONE
        }
    }

}