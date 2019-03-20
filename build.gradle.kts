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
    kotlin("jvm") version "1.3.21"
}
apply {
    plugin("org.jetbrains.dokka")
    plugin("maven")
}


group = "com.improve_future"
version = "0.0.6"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile(kotlin("test-junit5"))
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    testImplementation("org.hamcrest:hamcrest:2.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = Paths.get("docs", "api").toString()
}

tasks.withType<Test> {
    useJUnitPlatform()
}