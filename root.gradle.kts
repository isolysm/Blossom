plugins {
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.serialization") version "1.7.0" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("dev.architectury.loom") version "0.11.0-SNAPSHOT" apply false
    id("com.replaymod.preprocess") version "48e02ad"
    // id("io.github.juuxel.loom-quiltflower") version "1.7.1" apply false
}

val latestVersion = file("version.txt").readLines().first()

preprocess {
    val fabric11802 = createNode("1.18.2", 11802, "yarn")
    val fabric11801 = createNode("1.18.1", 11801, "yarn")

    fabric11802.link(fabric11801)
}

/*
fun Project.versionFromBuildIdAndBranch(): String {
    val branch = branch().replace('/', '-')
    var version = buildId() ?: return "$branch-SNAPSHOT"
    if (branch != "master") {
        version += "+$branch"
    }
    return version
}

fun Project.buildId(): String? = project.properties["BUILD_ID"]?.toString()

fun Project.branch(): String = project.properties["branch"]?.toString() ?: try {
    val stdout = java.io.ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--abbrev-ref", "HEAD")
        standardOutput = stdout
    }
    stdout.toString().trim()
} catch (e: Throwable) {
    "unknown"
}

 */