pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://repo.essential.gg/repository/maven-public")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net")
        maven("https://maven.quiltmc.org/repository/release")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.architectury.dev/")
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.jetbrains.dokka") {
                useModule("org.jetbrains.dokka:dokka-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "Blossom"

include(":buildConfigurations")
include(":aria")

include(":sakura")
project(":sakura").apply {
    projectDir = file("sakura/versions")
    buildFileName = "root.gradle.kts"
}

include(":blossom")
project(":blossom").apply {
    projectDir = file("blossom/versions")
    buildFileName = "root.gradle.kts"
}
