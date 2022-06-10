import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.ajoberstar.gradle.git.publish.GitPublishExtension

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.dokka")
    id("org.ajoberstar.git-publish")
    java
    signing
    publishing
    `java-library`
}

repositories {
    maven("https://jitpack.io")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://maven.fabricmc.net")
    maven("https://maven.quiltmc.org/repository/release")
    maven("https://maven.minecraftforge.net/")
    maven("https://maven.architectury.dev/")
}

val shadowMe: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

val lwjglImplementation: Configuration by configurations.creating {
    isTransitive = false
}

dependencies {
    lwjglImplementation("org.lwjgl:lwjgl:3.3.1")
    lwjglImplementation("org.lwjgl:lwjgl-opengl:3.3.1")

    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.6.21")
}

tasks {
    "shadowJar"(ShadowJar::class) {
        configurations = listOf(lwjglImplementation, shadowMe)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    "compileKotlin"(KotlinCompile::class) {
        kotlinOptions {
            freeCompilerArgs = listOf(

            )
        }
    }
    "compileJava"(JavaCompile::class) {

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
    publications {
        register<MavenPublication>("Blossom") {
            groupId = "dev.shuuyu"

            from(components["java"])
            artifact(tasks["dokkaJar"])
        }
    }
    repositories {
        // This was free so I did this lol
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/isolysm/Blossom")
            credentials {
                username = (project.findProperty("gpr.user") ?: System.getenv("USERNAME")).toString()
                password = (project.findProperty("gpr.password") ?: System.getenv("PASSWORD")).toString()
            }
        }

        // On hold because Jfrog is being an ass right now
        /*
        val mavenUser = project.findProperty("maven_user")
        val mavenPassword = project.findProperty("maven_password")
        if (mavenUser != null && mavenPassword != null) {
            maven("") {
                name = "release"
                credentials {
                    username = mavenUser.toString()
                    password = mavenPassword.toString()
                }
            }
        }

         */
    }
}