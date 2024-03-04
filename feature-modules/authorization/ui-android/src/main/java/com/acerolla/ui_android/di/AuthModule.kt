package com.acerolla.ui_android.di

import com.acerolla.add_thing_api.AuthStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.ui_android.AuthViewModel
import com.acerolla.ui_android.UiState
import com.acerolla.ui_android.mappers.AuthStateUiMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun authModule() = module {

    single<BaseMapper<AuthStore.State, UiState>> { AuthStateUiMapper() }
    viewModel { AuthViewModel(get(), get()) }
}