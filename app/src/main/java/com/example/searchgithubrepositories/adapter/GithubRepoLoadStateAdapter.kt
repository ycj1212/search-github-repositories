package com.example.searchgithubrepositories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgithubrepositories.databinding.ItemNetworkStateBinding

class GithubRepoLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<GithubRepoLoadStateAdapter.LoadStateViewHolder>() {
    class LoadStateViewHolder(
        private val binding: ItemNetworkStateBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) = with(binding) {
            pbLoadingBar.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMsg.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder(
            ItemNetworkStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry
        )

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}
