plugins {
//    id("org.jetbrains.compose")
//    id("com.android.application")
//    kotlin("android")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

android {
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.kmpstarter"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.android.compose)
}

tasks.register("BuildAndRun") {
    doFirst {
        exec {
            workingDir(projectDir.parentFile)
            commandLine("./gradlew", "android:build")
            commandLine("./gradlew", "android:installDebug")
        }
    }
}