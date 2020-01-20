package com.github.example.presentation.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by kostadin.georgiev on 1/6/2020.
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)