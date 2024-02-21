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
            baseName = "data"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(projects.featureModules.addThing.domain.api)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.acerolla.data"
    compileSdk = AppConfiguration.compileSdk
    defaultConfig {
        minSdk = AppConfiguration.minSdk
    }
}