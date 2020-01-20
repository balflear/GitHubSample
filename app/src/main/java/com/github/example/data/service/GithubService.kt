package com.github.example.data.service

import com.github.example.data.pojo.RepositoryResponse
import retrofit2.http.GET

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */
interface GithubService {

    @GET("orgs/square/repos")
    suspend fun getSquareRepos(): List<RepositoryResponse>
}