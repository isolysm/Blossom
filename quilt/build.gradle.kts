plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    // id("dev.architectury.loom")
    // id("io.github.juuxel.loom-quiltflower")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://maven.quiltmc.org/repository/release")
}

dependencies {
    implementation(project(":common")) // Requires the common directory for everything lol
}