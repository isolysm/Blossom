package dev.shuuyu

/*
Taken and modified from Essential Gradle Toolkit, made by EssentialGG, licensed under the GPL-3.0 license

Modified: Platform specific targets in order to support Quilt (Yeah I know, very extra and very stupid of me)

Taken from: https://github.com/EssentialGG/essential-gradle-toolkit/blob/master/src/main/kotlin/gg/essential/gradle/multiversion/Platform.kt
 */

import org.gradle.api.GradleException
import org.gradle.api.JavaVersion
import org.gradle.api.Project

data class Platform (
    val mcMajor: Int,
    val mcMinor: Int,
    val mcPatch: Int,
    val loader: Loader
) {
    val mcVersion = mcMajor * 10000 + mcMinor * 100 + mcPatch
    val mcVersionStr = listOf(mcMajor, mcMinor, mcPatch).dropLastWhile { it == 0 }.joinToString(".")
    val loaderStr = loader.toString().toLowerCase()

    /*
    In reality, I could've added legacy forge (I think arch 0.12 has it idk),
    but I want to try legacy fabric support. Idk, probably won't work.
     */
    val isForge = loader == Loader.Forge
    val isLegacyFabric = loader == Loader.Fabric && mcVersion < 11400
    val isFabric = loader == Loader.Fabric
    val isQuilt = loader == Loader.Quilt

    val getJavaVersion = when {
        mcVersion >= 11800 -> JavaVersion.VERSION_17
        mcVersion >= 11700 -> JavaVersion.VERSION_16
        else -> JavaVersion.VERSION_1_8
    }

    enum class Loader {
        Forge,
        Fabric,
        Quilt,
    }

    companion object {
        fun of(project: Project): Platform {
            val loader = guessLoader(project)
            val mcVersionStr = guessMcVersion(project)
            val (major, minor, patch) = mcVersionStr.split('.').map { it.toInt() } + listOf(0)
            return Platform(major, minor, patch, loader)
        }

        private fun guessMcVersion(project: Project): String {
            // Try configured minecraft.version value first
            project.findProperty("minecraft.version")
                ?.let { return it.toString() }

            // If that's not set, try to infer it from the project name
            Regex("""(\d+)\.(\d+)(\.(\d+))?""").find(project.name)
                ?.let { return it.value }

            throw GradleException(
                "Failed to infer Minecraft version for project \"${project.path}\".\n" +
                    "Either set \"minecraft.version\" in its \"gradle.properties\"," +
                    "or change the project name to include the version."
            )
        }

        private fun guessLoader(project: Project): Loader {
            // Try configured loom.platform value first
            val loomPlatform = project.findProperty("loom.platform")?.toString()
            when (loomPlatform?.toLowerCase()) {
                "quilt" -> return Loader.Quilt
                "fabric" -> return Loader.Fabric
                "forge" -> return Loader.Forge
                null -> {}
                else -> throw GradleException("Unknown loom.platform value: \"$loomPlatform\"")
            }

            // If that's not set, try to infer it from the project name
            when {
                "quilt" in project.name -> return Loader.Quilt
                "fabric" in project.name -> return Loader.Fabric
                "forge" in project.name -> return Loader.Forge
                else -> {}
            }

            throw GradleException("Failed to infer mod loader for project \"${project.path}\".\n" +
                "Either set \"loom.platform\" in its \"gradle.properties\"," +
                "or change the project name to include the platform.\n" +
                "Valid values: ${Loader.values().joinToString { it.name.toLowerCase() }}")
        }
    }
}

