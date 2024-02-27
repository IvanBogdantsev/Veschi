package com.acerolla.ui_android.di

import com.acerolla.api.ThingsStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.ui_android.ThingsViewModel
import com.acerolla.ui_android.ThingsUiState
import com.acerolla.ui_android.mappers.ThingsUiStateMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun thingsModule() = module {

    single<BaseMapper<ThingsStore.State, ThingsUiState>> { ThingsUiStateMapper() }
    viewModel { ThingsViewModel(get(), get()) }
}