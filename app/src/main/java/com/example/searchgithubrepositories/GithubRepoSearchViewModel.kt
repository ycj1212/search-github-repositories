package com.example.searchgithubrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GithubRepoSearchViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    private val _githubRepoList = MutableLiveData<List<GithubRepo>>()
    val githubRepoList: LiveData<List<GithubRepo>> = _githubRepoList
}
