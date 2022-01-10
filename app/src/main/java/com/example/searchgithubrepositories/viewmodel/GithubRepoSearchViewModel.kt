package com.example.searchgithubrepositories.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchgithubrepositories.data.GithubRepo
import com.example.searchgithubrepositories.data.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GithubRepoSearchViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    private val _githubRepoList = MutableLiveData<List<GithubRepo>>()
    val githubRepoList: LiveData<List<GithubRepo>> = _githubRepoList

    fun searchGithubRepo(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchRepositories(query)

            withContext(Dispatchers.Main) {
                _githubRepoList.value = result
            }
        }
    }
}
