plugins {
    kotlin("jvm") version "1.7.0" apply false
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.10.1"
    id("org.jetbrains.dokka") version "1.6.21" apply false
    id("gg.essential.defaults.loom")
    id("org.ajoberstar.git-publish") version "4.1.0"
    `maven-publish`
    signing
    java
}

java.withSourcesJar()

rootProject.apply {
    from(rootProject.file("buildConfigurations/publishing.gradle.kts"))
    from(rootProject.file("buildConfigurations/versioning.gradle.kts"))
}