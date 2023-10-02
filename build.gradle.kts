group = "com.vngrp"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.9.20-Beta2"
    application
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")

    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("$group.AdventOfCodeKt")
}