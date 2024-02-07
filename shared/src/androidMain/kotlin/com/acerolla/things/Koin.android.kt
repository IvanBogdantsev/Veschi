package com.acerolla.things

import com.acerolla.impl.dataStore
import org.koin.dsl.module

actual fun platformModule() = module {

    single { dataStore(get()) }

}