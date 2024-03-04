import com.acerolla.buildSrc.AppConfiguration

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
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
            baseName = "api"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.data.networking.thingsApi.api)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.serialization.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.logger.kermit)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.acerolla.api"
    compileSdk = AppConfiguration.compileSdk
    defaultConfig {
        minSdk = AppConfiguration.minSdk
    }
}