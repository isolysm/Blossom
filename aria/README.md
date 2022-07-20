# Aria
The overall build configuration for what modern mods should use.

**Note: This doesn't support FG2 or FG3 era of Forge. This means that any attempt to build a mod with Aria will most likely not work.**

# Installation 

**build.gradle.kts**
```kotlin
plugins {
    //Apply all of your plugins here
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("com.github.johnrengelman.shadow")
    id("live.shuuyu.aria")
}

dependencies {
    // Add whatever dependencies you want to add here
    // Both the mappings and the minecraft versions are taken care of internally
    // And yes, they are hard coded, but it is updated much more frequently.
    
    // Anything that will apply to all platforms
    
    if (platform.isModernForge) {
        // Any specific dependencies you want to add specifically for Forge.
    }
    
    if (platform.isFabric) {
        // Any specific dependencies for Fabric.
    }
    
    if (platform.isQuilt) {
        // Any specific dependencies for Quilt (I don't know why I did this)
    }
}


```

**root.gradle.kts**
```kotlin
plugins {
    // If you want to use Kotlin inside your projects, you can add the kotlin JVM.
    // In this instance, we will use the kotlin JVM
    kotlin("jvm") version "1.7.10" apply false
    kotlin("plugin.serialization") version "1.7.10" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    // You need this here for preprocessor
    id("live.shuuyu.aria.preprocessor") version "0.0.1" 
}

// In order to build for multiple minecraft versions, we use preprocessor
// This means you have to do more work than you should've
preprocessor {
    // It should follow the pattern of the folder, mcVersion, mc mappings.
    val fabric11900 = createNode("1.19-fabric", 11900, "yarn")
    val quilt11900 = createNode("1.19-quilt", 11900, "mp")
    
    fabric11900.link(quilt11900)
}
```

**settings.gradle.kts**
```kotlin
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven("https://jitpack.io")
        maven("https://maven.shuuyu.live/release")
        // For those that want bleeding edge stuff
        maven("https://maven.shuuyu.live/snapshots")
        maven("https://maven.architectury.dev")
        maven("https://maven.fabricmc.net")
        maven("https://maven.minecraftforge.net")
        flatDir {
            dirs = setOf(file("../../libs"))
        }
    }
}

rootProject.name = "template"
rootProject.buildFileName = "root.gradle.kts"

listOf(
    "1.19-fabric",
    "1.19-quilt"
).forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../../build.gradle.kts"
    }
}
```