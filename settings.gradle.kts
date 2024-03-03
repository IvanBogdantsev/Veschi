val MAPBOX_DOWNLOADS_TOKEN: String by settings

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
        maven {
            url = uri("https://jitpack.io")
        }
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password = MAPBOX_DOWNLOADS_TOKEN
            }
        }
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
include(":data:networking:authorization-api")
include(":data:networking:authorization-api:api")
include(":data:networking:authorization-api:impl")
include(":feature-modules:authorization:domain:impl")
include(":data:datastore")
include(":data:datastore:api")
include(":data:datastore:impl")
include(":feature-modules:things")
include(":feature-modules:things:data")
include(":feature-modules:things:ui-android")
include(":feature-modules:things:domain")
include(":feature-modules:things:domain:impl")
include(":feature-modules:profile")
include(":feature-modules:profile:data")
include(":feature-modules:profile:ui-android")
include(":feature-modules:profile:domain")
include(":feature-modules:profile:domain:impl")
include(":feature-modules:add-thing")
include(":feature-modules:add-thing:data")
include(":feature-modules:add-thing:domain")
include(":feature-modules:add-thing:domain:api")
include(":feature-modules:add-thing:domain:impl")
include(":feature-modules:add-thing:ui-android")
include(":feature-modules:things:domain:api")
include(":feature-modules:profile:domain:api")
include(":data:networking:things-api")
include(":data:networking:things-api:api")
include(":data:networking:things-api:impl")
include(":data:networking:add-thing-api")
include(":data:networking:add-thing-api:api")
include(":data:networking:add-thing-api:impl")
include(":data:networking:profile-api")
include(":data:networking:profile-api:api")
include(":data:networking:profile-api:impl")
