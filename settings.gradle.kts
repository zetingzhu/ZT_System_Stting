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
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "ZT_System_Stting"
include(":app")
include(":systemsettingtrae")
include(":systemsettinggpt")
include(":systemsettinglingma")
include(":zt_lanhu")
include(":zt_task_act_and_frag")
include(":zt_taskv1")
include(":zt_taskv2")
include(":zt_navigation")
include(":zt_navigation2")
