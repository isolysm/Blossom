package dev.shuuyu

import org.gradle.api.tasks.TaskProvider
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun KotlinCompile.setJvmDefault(mode: String) {
    kotlinOptions {
        val key = "-Xjvm-default="
        freeCompilerArgs = freeCompilerArgs.filterNot { it.startsWith(key) } + listOf(key + mode)
    }
}

fun TaskProvider<KotlinCompile>.setJvmDefault(mode: String) = configure { setJvmDefault(mode) }
