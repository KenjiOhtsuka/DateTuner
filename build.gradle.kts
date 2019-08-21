/*
 * DateTuner
 * Copyright (C) 2019  Kenji Otsuka
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
    kotlin("jvm") version "1.3.41"
}
apply {
    plugin("org.jetbrains.dokka")
    plugin("maven")
}


group = "com.improve_future"
version = "0.0.8"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
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
