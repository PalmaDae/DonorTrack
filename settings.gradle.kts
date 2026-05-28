pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }


        maven {
            url = uri("https://maven.yandex.cloud/nexus/content/repositories/releases/")
        }
    }
}

rootProject.name = "DonorTrack"
include(":app")
include(":domain")
include(":data")
include(":feature-auth")
include(":feature-main")
include(":feature-common")
include(":feature-add-donation")
include(":feature-map")
include(":feature-calendar")
include(":feature-scanner")
