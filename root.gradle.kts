plugins {
    kotlin("jvm") version "1.6.10" apply false
    kotlin("plugin.serialization") version "1.6.10" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("fabric-loom") version "0.11-SNAPSHOT" apply false

    id("com.replaymod.preprocess") version "0ab22d2"
}

group = "xyz.myosyn"
version = "1.0.0"

configurations.register("compileClasspath")

preprocess {
    val mc11802 = createNode("1.18.2", 11802, "yarn")
    val mc11801 = createNode("1.18.1", 11801, "yarn")

    //Legacy version mappings (Forge)
    // val mc11202 = createNode("1.12.2", 11202, "srg")
    // val mc10809 = createNode("1.8.9", 10809, "srg")

    mc11802.link(mc11801)
    //mc11202.link(mc10809)
}