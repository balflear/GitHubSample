package com.github.example.domain.usecase

import com.github.example.data.pojo.RepositoryResponse
import com.github.example.data.pojo.ResultWrapper
import com.github.example.domain.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * Created by kostadin.georgiev on 1/7/2020.
 */
class GetRepositoriesUseCase @Inject constructor(private val repository: GithubRepository) :
    BaseUseCase() {
    suspend fun invoke(): ResultWrapper<List<RepositoryResponse>> {
        return doApiCall(Dispatchers.IO) { repository.getSquareRepositories() }
    }
}