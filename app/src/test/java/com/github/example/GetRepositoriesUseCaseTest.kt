package com.github.example

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.example.data.pojo.RepositoryResponse
import com.github.example.data.pojo.ResultWrapper
import com.github.example.domain.repository.GithubRepository
import com.github.example.domain.usecase.GetRepositoriesUseCase
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by kostadin.georgiev on 1/7/2020.
 */
class GetRepositoriesUseCaseTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var githubRepository: GithubRepository
    private lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getRepositoriesUseCase = GetRepositoriesUseCase(githubRepository)
    }

    @Test
    fun getGithubRepositoriesSuccessfully() {
        runBlocking {
            val list = ArrayList<RepositoryResponse>()
            whenever(githubRepository.getSquareRepositories()).thenReturn(list)
            val result = getRepositoriesUseCase.invoke()

            assertNotNull(result)
            assertTrue(result is ResultWrapper.Success)
        }
    }
}