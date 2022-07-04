plugins {
    kotlin("plugin.serialization") version "1.6.21" apply false
    id("io.github.juuxel.loom-quiltflower") version "1.7.3" apply false
    id("gg.essential.multi-version.root")
    id("gg.essential.multi-version.api-validation")
}

preprocess {
    val fabric11900 = createNode("1.19-fabric", 11900, "yarn")
    val fabric11802 = createNode("1.18.2-fabric", 11802, "yarn")

    fabric11900.link(fabric11802)
}

apiValidation {
    ignoredProjects.addAll(subprojects.map { it.name })
    ignoredPackages.add("com.example")
    nonPublicMarkers.add("org.jetbrains.annotations.ApiStatus\$Internal")
}