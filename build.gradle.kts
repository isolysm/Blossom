import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.ajoberstar.gradle.git.publish.GitPublishExtension
import dev.shuuyu.Platform
import net.fabricmc.loom.configuration.ide.IdeConfiguration

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.dokka")
    // id("dev.architectury.loom")
    // id("com.replaymod.preprocess")
    id("org.ajoberstar.git-publish")
    java
}

rootProject.apply {
    from(rootProject.file("buildConfigurations/publishing.gradle.kts"))
    from(rootProject.file("buildConfigurations/versions.gradle.kts"))
}