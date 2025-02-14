package com.example.suitmediatestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediatestapp.databinding.ItemLoadingBinding

class StateLoadingAdapter(private val retry: () -> Unit) : LoadStateAdapter<StateLoadingAdapter.StateLoadingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateLoadingViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StateLoadingViewHolder(binding, retry)
    }
    override fun onBindViewHolder(holder: StateLoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
    class StateLoadingViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMessage.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState is LoadState.Error
            binding.errorMessage.isVisible = loadState is LoadState.Error
        }
    }
}