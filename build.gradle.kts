import org.jetbrains.kotlin.codegen.inline.addReturnsUnitMarker
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    // id("io.github.juuxel.loom-quiltflower")
}

// Primarily for convenience, but a bit extra if you ask me
rootProject.apply {
    from(rootProject.file("gradle/publishing.gradle.kts"))
    from(rootProject.file("gradle/root.gradle.kts"))
}

group = "dev.shuuyu"
version = "1.0.0"

val mcMajor: Int by extra
val mcMinor: Int by extra
val mcPatch: Int by extra
val loader: Loader
    // This is pseudo, and idk why I needed this in the first place lol
    get() {
        TODO()
    }
val mcVersion = mcMajor * 10000 + mcMinor * 100 + mcPatch
val mcVersionStr = listOf(mcMajor, mcMinor, mcPatch).dropLastWhile { it == 0 }.joinToString(".")
val loaderStr = loader.toString().toLowerCase()

val isQuilt = loader == Loader.QUILT
val isFabric = loader == Loader.FABRIC
// ONLY SUPPORTS MODERN FORGE, NOT LEGACY FORGE
val isForge = loader == Loader.FORGE

enum class Loader {
    QUILT,
    FABRIC,
    FORGE
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

val setTargetJavaVersion = when {
    mcVersion >= 11800 -> JavaVersion.VERSION_17
    else -> throw GradleException("Unknown Minecraft Version, so cannot determine Java Verison.")
}

tasks {
    "compileKotlin"(KotlinCompile::class) {
        kotlinOptions {

        }
    }

    "compileJava"(JavaCompile::class) {
        sourceCompatibility = setTargetJavaVersion.toString()
        targetCompatibility = setTargetJavaVersion.toString()
    }
}

