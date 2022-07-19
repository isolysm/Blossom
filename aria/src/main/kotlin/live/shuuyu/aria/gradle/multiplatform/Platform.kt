package live.shuuyu.aria.gradle.multiplatform

import org.gradle.kotlin.dsl.accessors.projectSchemaResourcePath

data class Platform(
    val mcMajor: Int,
    val mcMinor: Int,
    val mcPatch: Int,
    val loader: Loader,
) {
    val mcVersion = mcMajor * 10000 + mcMinor * 100 + mcPatch

    val isFabric = loader == Loader.Fabric
    val isModernForge = loader == Loader.Forge
    val isQuilt = loader == Loader.Quilt && projectSchemaResourcePath.contains("quilt.mod.json")
    enum class Loader {
        Forge,
        Fabric,
        Quilt,
    }
}
