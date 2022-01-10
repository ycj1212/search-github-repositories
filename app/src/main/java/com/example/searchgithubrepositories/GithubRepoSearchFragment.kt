package com.example.searchgithubrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchgithubrepositories.databinding.FragmentGithubRepoSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubRepoSearchFragment : Fragment() {
    private lateinit var binding: FragmentGithubRepoSearchBinding
    private lateinit var searchView: SearchView

    private val adapter = GithubRepoAdapter()
    private val viewModel: GithubRepoSearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGithubRepoSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGithubRepositories.adapter = adapter

        searchView =
            binding.tbSearchGithubRepositories.menu.findItem(R.id.search).actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                query ?: return false
                viewModel.searchGithubRepo(query)
                searchView.onActionViewCollapsed()
                return true
            }
        })

        viewModel.githubRepoList.observe(viewLifecycleOwner) { githubRepoList ->
            adapter.submitList(githubRepoList)
        }
    }
}
