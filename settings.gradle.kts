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

include(":forgelegacy")

val blossomVersion = listOf (
    "1.14-fabric",
    "1.14.1-fabric",
    "1.14.2-fabric",
    "1.14.3-fabric",
    "1.14.4-fabric",
    "1.15-fabric",
    "1.15.1-fabric",
    "1.15.2-fabric",
    "1.16-fabric",
    "1.16.1-fabric",
    "1.16.2-fabric",
    "1.16.3-fabric",
    "1.16.4-fabric",
    "1.16.5-fabric",
    "1.17-fabric",
    "1.17.1-fabric",
    "1.18-fabric",
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