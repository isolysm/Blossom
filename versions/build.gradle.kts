import gg.essential.gradle.util.*

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.dokka")
    id("gg.essential.multi-version")
    id("gg.essential.defaults.repo")
    id("gg.essential.defaults.java")
    id("gg.essential.defaults.loom")
    `maven-publish`
}

group = "live.shuuyu"

java.withSourcesJar()
tasks.compileKotlin.setJvmDefault(if (platform.mcVersion >= 11400) "all" else "all-compatibility")
loom.noServerRunConfigs()

val common by configurations.creating
configurations.compileClasspath { extendsFrom(common) }
configurations.runtimeClasspath { extendsFrom(common) }

val shadowMe: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

val shadowMeMod: Configuration by configurations.creating {
    configurations.modImplementation.get().extendsFrom(this)
}

val lwjglWindowsNatives = "natives-windows"
val lwjglMacOSNatives = "natives-macos"
val lwjglLinuxNatives = "natives-linux"

dependencies {
    common(project(":"))
    implementation(platform("org.lwjgl:lwjgl-bom:3.3.1"))
    implementation("org.lwjgl", "lwjgl-nanovg", classifier = lwjglWindowsNatives)
    implementation("org.lwjgl", "lwjgl-tinyfd", classifier = lwjglWindowsNatives)
    implementation("org.lwjgl", "lwjgl-nanovg", classifier = lwjglLinuxNatives)
    implementation("org.lwjgl", "lwjgl-nanovg", classifier = lwjglMacOSNatives)

    if (platform.isLegacyForge) {

    }

    if (platform.isForge) {

    }

    if (platform.isFabric) {
        val fabricApiVersion = when(platform.mcVersion) {
            11404 -> "0.28.5+1.14"
            11502 -> "0.28.5+1.15"
            11701 -> "0.38.1+1.17"
            11802 -> "0.46.4+1.18"
            11900 -> "0.46.4+1.18"
            else -> throw GradleException("Unknown mcVersion so cannot determine FAPI version. Platform mcVersion: ${platform.mcVersion}")
        }
        val fabricApiModules = mutableListOf(
            "api-base",
            "keybindings-v0",
            "resource-loader-v0",
            "lifecycle-events-v1",
        )
        if (platform.mcVersion >= 11600) {
            fabricApiModules.add("key-binding-api-v1")
        }
        fabricApiModules.forEach { module ->
            modRuntime(modCompileOnly(fabricApi.module("fabric-$module", fabricApiVersion))!!)
        }
    }
}

tasks.dokkaHtml {
    moduleName.set("Blossom $name")
}