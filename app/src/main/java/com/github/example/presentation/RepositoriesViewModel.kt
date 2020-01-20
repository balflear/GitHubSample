package com.github.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.example.data.pojo.RepositoryResponse
import com.github.example.data.pojo.ResultWrapper
import com.github.example.domain.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */
class RepositoriesViewModel @Inject constructor(private val getRepositoriesUseCase: GetRepositoriesUseCase) :
    ViewModel() {

    private val squareRepositoriesLiveData: MutableLiveData<ResultWrapper<List<RepositoryResponse>>> =
        MutableLiveData()

    init {
        // Fetch data once on view-model creation
        fetchSquareRepositories()
    }

    fun fetchSquareRepositories() {
        squareRepositoriesLiveData.postValue(ResultWrapper.Loading)

        viewModelScope.launch {
            val response = getRepositoriesUseCase.invoke()
            squareRepositoriesLiveData.postValue(response)
        }
    }

    fun getSquareRepositoriesData(): LiveData<ResultWrapper<List<RepositoryResponse>>> =
        squareRepositoriesLiveData
}