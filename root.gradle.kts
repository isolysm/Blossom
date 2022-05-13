plugins {
    kotlin("jvm") version "1.6.21" apply false
    kotlin("plugin.serialization") version "1.6.21" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    // id("dev.architectury.loom") version "0.11.0-SNAPSHOT" apply false
    // id("com.replaymod.preprocess") version "738ea1a"
    `preprocessor-module`
    id("io.github.juuxel.loom-quiltflower") version "1.7.1" apply false
}

version = determineVersion()
val latestVersion = file("version.txt").readLines().first()
configurations.register("compileClasspath")

preprocess {
    val fabric11802 = createNode("1.18.2-fabric", 11802, "yarn")
    val fabric11801 = createNode("1.18.1-fabric", 11801, "yarn")
    val fabric11800 = createNode("1.18-fabric", 11800, "yarn")

    val fabric11701 = createNode("1.17.1-fabric", 11701, "yarn")
    val fabric11700 = createNode("1.17-fabric", 11700, "yarn")

    val fabric11605 = createNode("1.16.5-fabric", 11605, "yarn")
    val fabric11604 = createNode("1.16.4-fabric", 11604, "yarn")
    val fabric11603 = createNode("1.16.3-fabric", 11603, "yarn")
    val fabric11602 = createNode("1.16.2-fabric", 11602, "yarn")
    val fabric11601 = createNode("1.16.1-fabric", 11601, "yarn")
    val fabric11600 = createNode("1.16-fabric", 11600, "yarn")

    val fabric11502 = createNode("1.15.2-fabric", 11502, "yarn")
    val fabric11501 = createNode("1.15.1-fabric", 11501, "yarn")
    val fabric11500 = createNode("1.15-fabric", 11500, "yarn")

    val fabric11404 = createNode("1.14.4-fabric", 11404, "yarn")
    val fabric11403 = createNode("1.14.3-fabric", 11403, "yarn")
    val fabric11402 = createNode("1.14.2-fabric", 11402, "yarn")
    val fabric11401 = createNode("1.14.1-fabric", 11401, "yarn")
    val fabric11400 = createNode("1.14-fabric", 11400, "yarn")

    fabric11802.link(fabric11801)
    fabric11800.link(fabric11701, file("versions/1.18-1.17.1-mappings.txt"))
    fabric11701.link(fabric11700)
    fabric11700.link(fabric11605, file("versions/1.17-1.16.5-mappings.txt"))
    fabric11605.link(fabric11604)
    fabric11604.link(fabric11603)
    fabric11603.link(fabric11602)
    fabric11602.link(fabric11601)
    fabric11601.link(fabric11600)
    fabric11600.link(fabric11502, file("versions/1.16-1.15.2-mappings.txt"))
    fabric11502.link(fabric11501)
    fabric11501.link(fabric11500)
    fabric11500.link(fabric11404, file("versions/1.15-1.14.4-mappings.txt"))
    fabric11404.link(fabric11403)
    fabric11403.link(fabric11402)
    fabric11402.link(fabric11401)
    fabric11401.link(fabric11400)
}

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