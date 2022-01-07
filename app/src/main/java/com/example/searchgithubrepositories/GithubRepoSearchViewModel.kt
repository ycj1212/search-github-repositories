package com.example.searchgithubrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GithubRepoSearchViewModel : ViewModel() {
    private val _githubRepoList = MutableLiveData<List<GithubRepo>>()
    val githubRepoList: LiveData<List<GithubRepo>> = _githubRepoList
}
