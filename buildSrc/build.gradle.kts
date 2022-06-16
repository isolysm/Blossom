plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
}