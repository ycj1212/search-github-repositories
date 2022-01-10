package com.example.searchgithubrepositories.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.searchgithubrepositories.api.GithubService

class GithubRepoPagingSource(
    private val service: GithubService,
    private val query: String
) : PagingSource<Int, GithubRepoItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepoItem> =
        try {
            val page = params.key ?: 1
            val response = service.searchRepositories(query = query, perPage = 10, page = page)

            LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = if (response.totalCount <= 10 * page) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, GithubRepoItem>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}
