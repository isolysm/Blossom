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
        maven("https://repo.essential.gg/repository/maven-public")
    }
    plugins {
        id("gg.essential.defaults") version "0.1.10"
        id("gg.essential.multi-version.root") version "0.1.10"
        id("gg.essential.multi-version.api-validation") version "0.1.10"
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.replaymod.preprocess") {
                    useModule("com.github.replaymod:preprocessor:${requested.version}")
            }
            if (requested.id.id == "org.jetbrains.dokka") {
                useModule("org.jetbrains.dokka:dokka-gradle-plugin:${requested.version}")
            }
        }
    }
}

rootProject.name = "Blossom"
rootProject.buildFileName = "root.gradle.kts"

include(":platform")
project(":platform").apply {
    projectDir = file("versions/")
    buildFileName = "root.gradle.kts"
}

val blossomVersion = listOf (
    "1.18.2-fabric",
    "1.19-fabric"
)

blossomVersion.forEach { version ->
    include(":platform:$version")
    project(":platform:$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../build.gradle.kts"
    }
}

include(":buildConfigurations")

if (JavaVersion.current() > JavaVersion.VERSION_17) {
    throw GradleException ("You need a Java Version (Minimum is 17) in order to compile.")
}