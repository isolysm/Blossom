plugins {
    `kotlin-dsl`
    groovy
}

java.withSourcesJar()

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.minecraftforge.net")
    maven("https://jitpack.io")
    maven("https://maven.architectury.dev/")
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    api("dev.architectury.loom:dev.architectury.loom.gradle.plugin:0.11.0.263")
    implementation("dev.architectury.architectury-pack200:dev.architectury.architectury-pack200.gradle.plugin:0.1.3")

    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    implementation("org.jetbrains.kotlinx:binary-compatibility-validator:0.10.0")
    implementation("com.guardsquare:proguard-gradle:7.2.1") {
        exclude(group = "org.jetbrains.kotlin")
    }
    api("com.github.replaymod:preprocessor:738ea1a")
    implementation("org.ow2.asm:asm-commons:9.3")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.4.2")
}