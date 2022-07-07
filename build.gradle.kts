import gg.essential.gradle.util.RelocationTransform.Companion.registerRelocationAttribute
import gg.essential.gradle.util.setJvmDefault

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.10.1"
    id("org.jetbrains.dokka") version "1.6.21" apply false
    id("gg.essential.defaults.repo")
    id("gg.essential.defaults.java")
    id("org.ajoberstar.git-publish") version "4.1.0"
}

tasks.compileKotlin.setJvmDefault("all-compatibility")

val internal by configurations.creating {
    val relocated = registerRelocationAttribute("internal-relocate") {

    }
}

dependencies {

}

rootProject.apply {
    from(rootProject.file("buildConfigurations/publishing.gradle.kts"))
    from(rootProject.file("buildConfigurations/versioning.gradle.kts"))
}