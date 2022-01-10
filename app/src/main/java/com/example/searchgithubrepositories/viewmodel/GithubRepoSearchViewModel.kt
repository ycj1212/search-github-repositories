package com.example.searchgithubrepositories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.searchgithubrepositories.data.GithubRepo
import com.example.searchgithubrepositories.data.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GithubRepoSearchViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    private var currentQueryString: String? = null
    private var currentSearchResult: Flow<PagingData<GithubRepo>>? = null

    fun searchGithubRepo(query: String): Flow<PagingData<GithubRepo>> {
        val newResult = repository.searchRepositories(query).cachedIn(viewModelScope)
        currentQueryString = query
        currentSearchResult = newResult
        return newResult
    }
}
