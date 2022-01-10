package com.example.searchgithubrepositories.data

import com.example.searchgithubrepositories.api.GithubService
import javax.inject.Inject

class GithubRepository @Inject constructor(private val service: GithubService) {
    suspend fun searchRepositories(query: String): List<GithubRepo> {
        val response = service.searchRepositories(query = query)
        return response.items.map { item -> GithubRepo.from(item) }
    }
}
