package com.github.example.domain.repository

import com.github.example.data.pojo.RepositoryResponse

/**
 * Created by kostadin.georgiev on 1/7/2020.
 */
interface GithubRepository {
    suspend fun getSquareRepositories(): List<RepositoryResponse>
}