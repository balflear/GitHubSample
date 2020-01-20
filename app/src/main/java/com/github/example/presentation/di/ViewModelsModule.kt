package com.github.example.presentation.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.example.presentation.RepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by kostadin.georgiev on 1/6/2020.
 */
@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindMainViewModel(viewModel: RepositoriesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactoryProvider): ViewModelProvider.Factory
}