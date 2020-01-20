package com.github.example.data.repository

import com.github.example.data.pojo.RepositoryResponse
import com.github.example.data.service.GithubService
import com.github.example.domain.repository.GithubRepository
import javax.inject.Inject

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */
class GithubRepositoryImpl : GithubRepository {
    private var githubService: GithubService

    @Inject
    constructor(githubService: GithubService) {
        this.githubService = githubService
    }

    override suspend fun getSquareRepositories(): List<RepositoryResponse> {
        return githubService.getSquareRepos()
    }
}