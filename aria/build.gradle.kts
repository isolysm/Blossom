plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
}

java.withSourcesJar()

group = "live.shuuyu"
version = "0.0.1"

repositories {
    mavenLocal()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.minecraftforge.net")
    maven("https://jitpack.io")
    maven("https://maven.architectury.dev/")
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    // See: https://maven.architectury.dev/dev/architectury/architectury-loom/
    implementation("dev.architectury.loom:dev.architectury.loom.gradle.plugin:0.12.0.295")

    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    implementation("org.jetbrains.kotlinx:binary-compatibility-validator:0.11.0")
    implementation("gradle.plugin.com.github.jengelman.gradle.plugins:shadow:7.0.0")
    api("com.github.replaymod:preprocessor:48e02ad")

    implementation("org.ow2.asm:asm-commons:9.3")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.4.2")
}

gradlePlugin {
    plugins {

    }
}

publishing {
    repositories {
        maven("https://maven.shuuyu.live/repository/release") {
            name = "Release"
            credentials {
                username = project.findProperty("nexus_user").toString()
                password = project.findProperty("nexus_password").toString()
            }
        }
        maven("https://maven.shuuyu.live/repository/snapshot") {
            name = "Snapshot"
            credentials {
                username = project.findProperty("nexus_user").toString()
                password = project.findProperty("nexus_password").toString()
            }
        }
    }
}