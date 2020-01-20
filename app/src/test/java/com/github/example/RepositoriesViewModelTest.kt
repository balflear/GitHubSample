package com.github.example

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.github.example.data.pojo.RepositoryResponse
import com.github.example.data.pojo.ResultWrapper
import com.github.example.domain.usecase.GetRepositoriesUseCase
import com.github.example.presentation.RepositoriesViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.MockitoAnnotations

/**
 * Created by kostadin.georgiev on 1/7/2020.
 */
class RepositoriesViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineTestRule = CouroutineTestRule()

    private lateinit var repositoriesViewModel: RepositoriesViewModel

    @Mock
    private lateinit var getRepositoriesUseCase: GetRepositoriesUseCase

    @Mock
    private lateinit var liveDataObserver: Observer<ResultWrapper<List<RepositoryResponse>>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        repositoriesViewModel = RepositoriesViewModel(getRepositoriesUseCase).apply {
            getSquareRepositoriesData().observeForever(liveDataObserver)
        }
    }


    @Test
    fun getRepositoriesSuccessfully() {
        runBlocking {
            // Given
            val list = ArrayList<RepositoryResponse>()
            val result =
                ResultWrapper.Success<List<RepositoryResponse>>(list)
            whenever(getRepositoriesUseCase.invoke()).thenReturn(result)

            // When
            repositoriesViewModel.fetchSquareRepositories()

            // then
            val executionOrder = inOrder(liveDataObserver)
            executionOrder.verify(liveDataObserver).onChanged(ResultWrapper.Loading)
            executionOrder.verify(liveDataObserver)
                .onChanged(ArgumentMatchers.eq(result))
        }
    }

    @Test
    fun getRepositoriesGenericError() {
        runBlocking {
            // Given
            val result =
                ResultWrapper.GenericError()
            whenever(getRepositoriesUseCase.invoke()).thenReturn(result)

            // When
            repositoriesViewModel.fetchSquareRepositories()

            // then
            val executionOrder = inOrder(liveDataObserver)
            executionOrder.verify(liveDataObserver).onChanged(ResultWrapper.Loading)
            executionOrder.verify(liveDataObserver).onChanged(ArgumentMatchers.eq(result))
        }
    }

    @Test
    fun getRepositoriesNetworkError() {
        runBlocking {
            // Given
            val result =
                ResultWrapper.NetworkError
            whenever(getRepositoriesUseCase.invoke()).thenReturn(result)

            // When
            repositoriesViewModel.fetchSquareRepositories()

            // then
            val executionOrder = inOrder(liveDataObserver)
            executionOrder.verify(liveDataObserver).onChanged(ResultWrapper.Loading)
            executionOrder.verify(liveDataObserver).onChanged(ArgumentMatchers.eq(result))
        }
    }
}