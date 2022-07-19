plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.10.1"
    id("org.jetbrains.dokka") version "1.7.10"
    id("org.ajoberstar.git-publish") version "4.1.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {

}

rootProject.apply {
    from(rootProject.file("buildConfigurations/publishing.gradle.kts"))
    from(rootProject.file("buildConfigurations/versioning.gradle.kts"))
}