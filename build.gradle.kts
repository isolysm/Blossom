import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.ajoberstar.gradle.git.publish.GitPublishExtension
import dev.shuuyu.Platform

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.dokka")
    id("dev.architectury.loom")
    // id("com.replaymod.preprocess")
    id("org.ajoberstar.git-publish")
    java
    signing
    publishing
    `java-library`
}

val platform = Platform.of(project)
extensions.add("platform", platform)
extra.set("loom.platform", if (platform.isQuilt) "quilt" else if(platform.isFabric) "fabric" else "forge")
java.withSourcesJar()


repositories {
    maven("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.fabricmc.net")
    maven("https://maven.quiltmc.org/repository/release")
    maven("https://maven.minecraftforge.net/")
    maven("https://maven.architectury.dev/")
}

loom {
    runConfigs {
        all { isIdeConfigGenerated = false}
        runConfigs["server"].isIdeConfigGenerated = false
    }
}

val shadowMe: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

val shadowMeMod: Configuration by configurations.creating {
    configurations.modImplementation.get().extendsFrom(this)
}

val lwjglImplementation: Configuration by configurations.creating {
    isTransitive = false
}

dependencies {
    minecraft("com.mojang:minecraft:${platform.mcVersionStr}")
    shadowMe("com.github.LlamaLad7:MixinExtras:0.0.10")

    if (platform.isQuilt) {
        val quiltMappings = when(platform.mcVersion) {
            11900 -> "build.1:v2"
            11802 -> "build.24:v2"
            else -> throw GradleException("Unknown MC version, so cannot determine QM mappings. Platform: ${platform.mcVersionStr}")
        }
        val qslApi = when(platform.mcVersion) {
            11900 -> "2.0.0-beta.2"
            11802 -> "1.1.0-beta.17"
            else -> throw GradleException("Unknown MC version, so cannot determine QSL version. Version: ${platform.mcVersionStr}")
        }

        mappings("org.quiltmc:quilt-mappings:${platform.mcVersionStr}+${quiltMappings}")
    }

    if (platform.isForge) {
        val mcpMappings = when(platform.mcVersion) {
            else -> throw GradleException("Unknown MC version, so cannot determine MCP mappings.")
        }
    }

    if (platform.isFabric) {
        val yarnMappings = when(platform.mcVersion) {
            11900 -> "1.19+build.2"
            11802 -> "1.18.2+build.3"
            11801 -> "1.18.1+build.22"
            11701 -> "1.17.1+build.65"
            else -> throw GradleException("Unknown MC version, so cannot determine Yarn mappings.")
        }
        val fabricApiVersion = when(platform.mcVersion) {
            11900 -> "0.55.3+1.19"
            11802 -> "0.55.1+1.18.2"
            11801 -> "0.46.6+1.18"
            11701 -> "0.46.1+1.17"
            else -> throw GradleException("Unknown MC version so cannot determine the Fabric API version.")
        }

        val fabricApiModules = mutableListOf(
            "api-base",
            "networking-v0",
            "keybindings-v0",
            "resource-loader-v0",
            "lifecycle-events-v1"
        )
        if (platform.mcVersion >= 11600) {
            fabricApiModules.add("key-binding-api-v1")
        }

        mappings("net.fabricmc:yarn:${yarnMappings}:v2")
        shadowMeMod("net.fabricmc:fabric-loader:0.14.7")
        shadowMeMod("net.fabricmc.fabric-api:fabric-api:${fabricApiVersion}")
    }

    lwjglImplementation("org.lwjgl:lwjgl:3.3.1")
    lwjglImplementation("org.lwjgl:lwjgl-opengl:3.3.1")

    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.0")
}

tasks {
    "shadowJar"(ShadowJar::class) {
        configurations = listOf(lwjglImplementation, shadowMe)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    "compileJava"(JavaCompile::class) {

    }
    "compileKotlin"(KotlinCompile::class) {
        kotlinOptions {
            freeCompilerArgs += listOf("-opt-in=kotlin.RequiresOptIn")
        }
    }
}

configure<GitPublishExtension> {
    repoUri.set("https://github.com/isolysm/blossom.git")
    branch.set("gh-pages")

    contents {
        from(project.projectDir.resolve("dokka"))
    }

    commitMessage.set("Update Dokka Docs")
}

publishing {
    repositories {
        maven {
            name = "Releases"
            url = uri("https://maven.shuuyu.live/releases")
            credentials {
                username = null
                password = null
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}
