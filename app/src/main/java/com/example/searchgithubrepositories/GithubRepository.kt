package com.example.searchgithubrepositories

class GithubRepository(private val service: GithubService) {
    fun searchRepositories(query: String): List<GithubRepo> {
        val response = service.searchRepositories(query = query)
        return response.items.map { item -> GithubRepo.from(item) }
    }
}
