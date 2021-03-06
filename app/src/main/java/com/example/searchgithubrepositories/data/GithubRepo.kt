package com.example.searchgithubrepositories.data

data class GithubRepo(
    val name: String,
    val imageUrl: String,
    val language: String
) {
    companion object {
        fun from(item: GithubRepoItem) = with(item) {
            GithubRepo(
                name = fullName,
                imageUrl = owner.avatarUrl,
                language = language ?: ""
            )
        }
    }
}
