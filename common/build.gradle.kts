plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.essential.gg/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://jitpack.io")
    maven("https://maven.fabricmc.net")
    maven("https://maven.quiltmc.org/repository/release")
    maven("https://maven.minecraftforge.net/")
    maven("https://maven.architectury.dev/")
}

dependencies {

}