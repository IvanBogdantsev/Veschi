package com.acerolla.things

import com.acerolla.add_thing_domain_api.AddThingStatePublisher
import com.acerolla.add_thing_domain_api.AddThingStore
import com.acerolla.add_thing_domain_api.AddThingsUseCase
import com.acerolla.add_thing_api.AuthRepository
import com.acerolla.add_thing_api.AuthStatePublisher
import com.acerolla.add_thing_api.AuthStore
import com.acerolla.add_thing_api.StreetObjectsRepository
import com.acerolla.add_thing_api.ThingsStatePublisher
import com.acerolla.add_thing_api.ThingsStore
import com.acerolla.common.mappers.BaseMapper
import com.acerolla.data.AddThingsUseCaseImpl
import com.acerolla.data.AuthRepositoryImpl
import com.acerolla.data.ProfileRepositoryImpl
import com.acerolla.data.StreetObjectsRepositoryImpl
import com.acerolla.impl.AddThingStatePublisherImpl
import com.acerolla.impl.AuthStatePublisherImpl
import com.acerolla.impl.ProfileStatePublisherImpl
import com.acerolla.impl.ThingsStatePublisherImpl
import com.acerolla.impl.dataStore
import com.acerolla.profile_domain_api.ProfileRepository
import com.acerolla.profile_domain_api.ProfileStatePublisher
import com.acerolla.profile_domain_api.ProfileStore
import com.acerolla.things.di.initKoin
import com.acerolla.things.features.addThing.AddThingIOSStatePublisher
import com.acerolla.things.features.addThing.AddThingUiStateIOS
import com.acerolla.things.features.addThing.AddThingUiStateToIOSMapper
import com.acerolla.things.features.authorization.AuthStatePublisheriOS
import com.acerolla.things.features.authorization.AuthUiStateToIosStateMapper
import com.acerolla.things.features.authorization.AuthUiStateiOS
import com.acerolla.things.features.profile.ProfileStatePublisheriOS
import com.acerolla.things.features.profile.ProfileUiStateToiOSMapper
import com.acerolla.things.features.profile.ProfileUiStateiOS
import com.acerolla.things.features.things.ThingsUiStatePublisherIOS
import com.acerolla.things.features.things.ThingsUiStateToiOSMapper
import com.acerolla.things.features.things.ThingsUiStateiOS
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

private const val AUTHORIZATION_SCOPE_NAME = "AUTHORIZATION_SCOPE_NAME"
private const val AUTHORIZATION_SCOPE_ID = "AUTHORIZATION_SCOPE_ID"

private const val ADD_THING_SCOPE_NAME = "ADD_THING_SCOPE_NAME"
private const val ADD_THING_SCOPE_ID = "ADD_THING_SCOPE_ID"

private const val THINGS_SCOPE_NAME = "THINGS_SCOPE_NAME"
private const val THINGS_SCOPE_ID = "THINGS_SCOPE_ID"

private const val PROFILE_SCOPE_NAME = "PROFILE_SCOPE_NAME"
private const val PROFILE_SCOPE_ID = "PROFILE_SCOPE_ID"

fun initKoiniOS(): KoinApplication = initKoin(emptyList())

actual fun platformModule() = module {

    single { dataStore() }

    scope(named(AUTHORIZATION_SCOPE_NAME)) {

        scoped<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }

        scoped<AuthStatePublisher> { AuthStatePublisherImpl(get()) }
        scoped<BaseMapper<AuthStore.State, AuthUiStateiOS>> { AuthUiStateToIosStateMapper() }
        scoped { AuthStatePublisheriOS(get(), get()) }
    }

    scope(named(ADD_THING_SCOPE_NAME)) {

        scoped<AddThingsUseCase> { AddThingsUseCaseImpl(get()) }

        scoped<AddThingStatePublisher> { AddThingStatePublisherImpl(get()) }
        scoped<BaseMapper<AddThingStore.State, AddThingUiStateIOS>> { AddThingUiStateToIOSMapper() }
        scoped { AddThingIOSStatePublisher(get(), get()) }
    }

    scope(named(THINGS_SCOPE_NAME)) {

        scoped<StreetObjectsRepository> { StreetObjectsRepositoryImpl(get(), get(), get()) }

        scoped<ThingsStatePublisher> { ThingsStatePublisherImpl(get()) }
        scoped<BaseMapper<ThingsStore.State, ThingsUiStateiOS>> { ThingsUiStateToiOSMapper() }
        scoped { ThingsUiStatePublisherIOS(get(), get()) }
    }

    scope(named(PROFILE_SCOPE_NAME)) {

        scoped<ProfileRepository> { ProfileRepositoryImpl() }

        scoped<ProfileStatePublisher> { ProfileStatePublisherImpl(get()) }
        scoped<BaseMapper<ProfileStore.State, ProfileUiStateiOS>> { ProfileUiStateToiOSMapper() }
        scoped { ProfileStatePublisheriOS(get(), get()) }
    }
}

fun Koin.openKoinScope(scopeConstId: String, scopeConstName: String): Scope {
    return this.createScope(scopeConstId, named(scopeConstName))
}

fun Koin.getScope(scopeConst: String): Scope {
    return this.getScope(scopeConst)
}

fun Koin.closeScope(scopeConst: String) {
    this.getScope(scopeConst).close()
}

fun Koin.get(objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    return get(kClazz)
}

fun Koin.getFromScope(scopeConst: String, objCClass: ObjCClass): Any {
    val kClazz = getOriginalKotlinClass(objCClass)!!
    val scope = getScope(scopeConst)
    return scope.get(kClazz)
}