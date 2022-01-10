package com.example.searchgithubrepositories.data

import androidx.paging.*
import com.example.searchgithubrepositories.api.GithubService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepository @Inject constructor(private val service: GithubService) {
    fun searchRepositories(query: String): Flow<PagingData<GithubRepo>> =
        Pager(
            PagingConfig(pageSize = 10),
            pagingSourceFactory = { GithubRepoPagingSource(service, query) }
        ).flow
            .map { pagingData ->
                pagingData.map { item ->
                    GithubRepo.from(item)
                }
            }
}
