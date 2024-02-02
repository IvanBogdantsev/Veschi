enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Things"
include(":androidApp")
include(":shared")
include(":core")
include(":core:android-design-system")
include(":data")
include(":data:networking")
include(":data:database")
include(":android-navigation")
include(":data:networking:networking-utils")
include(":core:common")
include(":feature-modules")
include(":feature-modules:authorization")
include(":feature-modules:authorization:data")
include(":feature-modules:authorization:ui-android")
include(":feature-modules:authorization:domain")
include(":feature-modules:authorization:domain:api")
include(":feature-modules:authorization:domain:implementation")
