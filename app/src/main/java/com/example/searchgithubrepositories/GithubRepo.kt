package com.example.searchgithubrepositories

data class GithubRepo(
    val name: String,
    val imageUrl: String,
    val language: String
) {
    companion object {
        fun from(item: Item) = with(item) {
            GithubRepo(
                name = fullName,
                imageUrl = owner.avatarUrl,
                language = language ?: ""
            )
        }
    }
}
