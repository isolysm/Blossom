plugins {
    kotlin("jvm") version "1.7.0" apply false
    kotlin("plugin.serialization") version "1.7.0" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("dev.architectury.loom") version "0.12.0-SNAPSHOT" apply false
    id("com.replaymod.preprocess") version "48e02ad"
    id("org.jetbrains.dokka") version "1.7.0" apply false
    id("org.ajoberstar.git-publish") version "4.1.0" apply false
    id("io.github.juuxel.loom-quiltflower") version "1.7.1" apply false
    id("dev.architectury.architectury-pack200") version "0.1.3" apply false // Required for the legacy branch
}

preprocess {
    val fabric11900 = createNode("1.19", 11900, "yarn")
    val fabric11802 = createNode("1.18.2", 11802, "yarn")
    val fabric11801 = createNode("1.18.1", 11801, "yarn")

    fabric11900.link(fabric11802)
    fabric11802.link(fabric11801)
}

