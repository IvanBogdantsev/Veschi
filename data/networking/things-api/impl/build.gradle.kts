import com.acerolla.buildSrc.AppConfiguration

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
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
        framework {
            baseName = "impl"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.data.networking.thingsApi.api)
            implementation(projects.data.networking.networkingUtils)
            implementation(projects.core.common)
            implementation(libs.ktor.core)
            implementation(libs.ktor.logging)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.serialization.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.datetime)
            implementation(libs.content.negotiation)
            implementation(libs.logger.kermit)
            api(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.acerolla.impl"
    compileSdk = AppConfiguration.compileSdk
    defaultConfig {
        minSdk = AppConfiguration.minSdk
    }
}