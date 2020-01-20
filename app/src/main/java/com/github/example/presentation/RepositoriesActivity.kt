package com.github.example.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.example.App
import com.github.example.R
import com.github.example.data.pojo.RepositoryResponse
import com.github.example.data.pojo.ResultWrapper
import com.github.example.presentation.adapters.RepositoryAdapter
import com.github.example.presentation.di.ViewModelFactoryProvider
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class RepositoriesActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactoryProvider

    private lateinit var repositoriesAdapter: RepositoryAdapter
    private lateinit var viewModel: RepositoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).appComponent.inject(this)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(RepositoriesViewModel::class.java)

        subscribeLiveData()
        initRecyclerView()
        initRefreshListener()
    }

    private fun initRefreshListener() {
        refreshLayout.setOnRefreshListener {
            // Clear current items in the list
            repositoriesAdapter.updateValues(ArrayList())
            viewModel.fetchSquareRepositories()
        }
    }

    private fun initRecyclerView() {
        repositoriesAdapter = RepositoryAdapter(this)
        rvRepositoriesList.adapter = repositoriesAdapter
        rvRepositoriesList.itemAnimator = DefaultItemAnimator()
        rvRepositoriesList.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribeLiveData() {
        viewModel.getSquareRepositoriesData().observe(this, Observer {
            when (it) {
                is ResultWrapper.Loading -> {
                    refreshLayout.isRefreshing = true
                }

                is ResultWrapper.Success -> {
                    refreshLayout.isRefreshing = false
                    repositoriesAdapter.updateValues(it.value as ArrayList<RepositoryResponse>)
                }
                is ResultWrapper.NetworkError -> {
                    refreshLayout.isRefreshing = false
                    showToast(getString(R.string.network_error))
                }
                is ResultWrapper.GenericError -> {
                    refreshLayout.isRefreshing = false
                    it.error?.let { errorMsg ->
                        showToast(errorMsg)
                        return@Observer
                    }
                    showToast(getString(R.string.server_error))
                }
            }
        })
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}
