includeBuild("build-logic")

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
    }
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id) {
                "com.replaymod.preprocess" -> {
                    useModule("com.github.replaymod:preprocessor:${requested.version}")
                }
            }
        }
    }
}

rootProject.name = "Blossom"
rootProject.buildFileName = "root.gradle.kts"

listOf (
    // Legacy versions that we might not even add whatsoever. (These are mainly for forge)
    // "1.8.9",
    // "1.12.2",
    "1.18.1-fabric",
    "1.18.2-fabric"
).forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../../build.gradle.kts"
    }
}