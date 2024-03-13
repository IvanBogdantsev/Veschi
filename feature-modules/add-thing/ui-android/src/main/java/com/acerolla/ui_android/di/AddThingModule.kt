package com.acerolla.ui_android.di

import com.acerolla.ui_android.AddThingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun addThingModule() = module {

    single { AddThingViewModel(get()) }
}