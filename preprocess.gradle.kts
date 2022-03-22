plugins {
    id("com.replaymod.preprocess")
}

project.extensions.create("preprocess", RootPreprocessExtension::class)
configure<RootPreprocessExtension> {
    val mc11802 = createNode("1.18.2", 11802, "yarn")
    val mc11801 = createNode("1.18.1", 11801, "yarn")

    val mc11202 = createNode("1.8.9", 11202, "srg")
    val mc10809 = createNode("1.8.9", 10809, "srg")

    mc11802.link(mc11801)

    mc11202.link(mc10809)
}


