plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    mavenCentral()
    google()
    gradlePluginPortal()
    maven("https://jitpack.io")
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.ow2.asm:asm-commons:9.3")
    implementation("com.guardsquare:proguard-gradle:7.2.0")
}