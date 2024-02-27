import com.acerolla.buildSrc.AppConfiguration

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0")
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            api("dev.icerock.moko:resources:0.23.0")
            implementation(projects.data.networking.networkingUtils)
            implementation(projects.data.networking.authorizationApi.api)
            implementation(projects.data.networking.authorizationApi.impl)
            implementation(projects.data.networking.thingsApi.api)
            implementation(projects.data.networking.thingsApi.impl)
            implementation(projects.data.datastore.api)
            implementation(projects.data.datastore.impl)
            implementation(projects.featureModules.authorization.data)
            implementation(projects.featureModules.authorization.domain.api)
            implementation(projects.featureModules.authorization.domain.impl)
            implementation(projects.featureModules.things.data)
            implementation(projects.featureModules.things.domain.api)
            implementation(projects.featureModules.things.domain.impl)
            implementation(projects.core.common)
            implementation(libs.logger.kermit)
            implementation(libs.ktor.core)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.serialization.json)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.datetime)
            implementation(libs.content.negotiation)
            implementation(libs.androidx.datastore.core)
            api(libs.koin.core)

            implementation(libs.mvi.core)
            implementation(libs.mvi.main)
            implementation(libs.mvi.logging)
            implementation(libs.mvi.coroutines)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.acerolla.things"
    compileSdk = AppConfiguration.compileSdk
    defaultConfig {
        minSdk = AppConfiguration.minSdk
    }
    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.acerolla.shared_resources"
    multiplatformResourcesClassName = "SharedResources"
}