import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.file.Paths

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.17")
    }
}

plugins {
    java
    kotlin("jvm") version "1.2.51"
}
apply {
    plugin("org.jetbrains.dokka")
}


group = "com.improve_future"
version = "0.0.2"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile(kotlin("test-junit5"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = Paths.get("docs", "api").toString()
}