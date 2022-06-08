pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net")
        maven("https://maven.quiltmc.org/repository/release")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.architectury.dev/")
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

val blossomVersion = listOf (
    "1.18.1-fabric",
    "1.18.2-fabric"
)

blossomVersion.forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../../build.gradle.kts"
    }
}