package com.example.searchgithubrepositories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.searchgithubrepositories.databinding.ActivityGithubRepoSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubRepoSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGithubRepoSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubRepoSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
