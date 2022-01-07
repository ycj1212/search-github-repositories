package com.example.searchgithubrepositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgithubrepositories.GithubRepoAdapter.GithubRepoViewHolder
import com.example.searchgithubrepositories.databinding.ItemGithubRepoBinding

class GithubRepoAdapter : ListAdapter<GithubRepo, GithubRepoViewHolder>(GithubRepoDiffCallback()) {
    class GithubRepoViewHolder(
        private val binding: ItemGithubRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GithubRepo) = with(binding) {
            tvGithubRepoName.text = item.name
            tvGithubRepoLanguage.text = item.language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepoViewHolder =
        GithubRepoViewHolder(
            ItemGithubRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GithubRepoViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }
}

private class GithubRepoDiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
    override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
        oldItem == newItem
}
