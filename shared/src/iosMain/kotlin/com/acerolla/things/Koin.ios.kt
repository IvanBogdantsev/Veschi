package com.acerolla.things

import com.acerolla.impl.dataStore
import com.acerolla.things.di.initKoin
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

private const val AUTHORIZATION_SCOPE_NAME = "TRAININGS_DIARY_SCOPE_NAME"
private const val AUTHORIZATION_SCOPE_ID = "TRAININGS_DIARY_SCOPE_ID"

fun initKoiniOS(): KoinApplication = initKoin(emptyList())

actual fun platformModule() = module {

    single { dataStore() }

    scope(named(AUTHORIZATION_SCOPE_NAME)) {

//        scoped<TrainingsDiaryRepository> { TrainingsDiaryRepositoryImpl(get()) }
//
//        scoped<TrainingsDiaryStatePublisher> { TrainingsDiaryStatePublisherImpl(get()) }
//        scoped<BaseMapper<TrainingsDiaryStore.State, TrainingsDiaryUiStateiOS>> { TrainingsDiaryUiStateToIosStateMapper() }
//        scoped { TrainingsDiaryStatePublisheriOS(get(), get()) }
//
//        scoped<FetchTrainingsUseCase> { FetchTrainingsUseCaseImpl(get(), get()) }
//        scoped { TrainingsDiaryUseCaseiOS(get()) }
    }
}

fun Koin.openKoinScope(): Scope {
    return this.createScope(AUTHORIZATION_SCOPE_ID, named(AUTHORIZATION_SCOPE_NAME))
}

fun Koin.getScope(): Scope {
    return this.getScope(AUTHORIZATION_SCOPE_ID)
}

fun Koin.closeScope() {
    this.getScope().close()
}

fun Koin.get(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz)
}

fun Koin.getFromScope(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    val scope = getScope(AUTHORIZATION_SCOPE_ID)
    return scope.get(kClazz)
}