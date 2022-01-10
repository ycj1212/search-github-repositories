package com.example.searchgithubrepositories.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.searchgithubrepositories.R
import com.example.searchgithubrepositories.adapter.GithubRepoAdapter
import com.example.searchgithubrepositories.databinding.FragmentGithubRepoSearchBinding
import com.example.searchgithubrepositories.viewmodel.GithubRepoSearchViewModel
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
        binding.rvGithubRepositories.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )

        searchView =
            binding.tbSearchGithubRepositories.menu.findItem(R.id.search).actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = resources.getString(R.string.search_view_query_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean = false

            override fun onQueryTextSubmit(query: String?): Boolean {
                query ?: return false
                viewModel.searchGithubRepo(query)
                searchView.onActionViewCollapsed()
                binding.tbSearchGithubRepositories.subtitle = "$query"
                return true
            }
        })

        viewModel.githubRepoList.observe(viewLifecycleOwner) { githubRepoList ->
            adapter.submitList(githubRepoList)
        }
    }
}
