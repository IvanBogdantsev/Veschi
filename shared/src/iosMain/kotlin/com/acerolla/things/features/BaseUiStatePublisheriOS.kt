package com.acerolla.things.features

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BaseUiStatePublisheriOS {

    private val dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
    internal val scope = CoroutineScope(SupervisorJob() + dispatcher)

    fun onDestroy() {
        scope.cancel()
    }
}