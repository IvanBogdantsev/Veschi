package com.acerolla.things.android

import android.app.Application
import android.content.Context
import com.acerolla.things.di.initKoin
import com.acerolla.ui_android.di.authModule
import com.acerolla.ui_android.di.thingsModule
import org.koin.dsl.module

class VeschiApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(
            module {
                single<Context> { this@VeschiApp }
            } + authModule() + thingsModule(),
        )
    }
}