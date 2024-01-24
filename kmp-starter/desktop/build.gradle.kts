import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
//    kotlin("multiplatform")
//    id("org.jetbrains.compose")
//    kotlin("jvm")
//    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.desktop)
    alias(libs.plugins.ksp)
}

group = "com.example"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(17)
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(libs.compose.ui.util)
                implementation(compose.desktop.currentOs)
                implementation(libs.skiko.window)
                implementation(libs.slf4j)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        javaHome = System.getenv("JAVA_HOME")
        mainClass = "MainKt"
        nativeDistributions {
            modules("java.sql", "java.instrument", "java.compiler", "jdk.unsupported")
            targetFormats(TargetFormat.Exe)
            packageName = "DesktopApp"
            packageVersion = "1.0.0"
        }
        jvmArgs += listOf("-Xmx2G")
    }
}