plugins {
    kotlin("jvm") version "1.7.0" apply false
    kotlin("plugin.serialization") version "1.7.0" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("org.jetbrains.dokka") version "1.7.0" apply false
    id("org.ajoberstar.git-publish") version "4.1.0" apply false
    id("io.github.juuxel.loom-quiltflower") version "1.7.3" apply false
    id("gg.essential.multi-version.root")
}

preprocess {
    val fabric11900 = createNode("1.19", 11900, "yarn")
    val fabric11802 = createNode("1.18.2", 11802, "yarn")

    fabric11900.link(fabric11802)
}


