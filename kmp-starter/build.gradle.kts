group = "com.example"
version = "1.0-SNAPSHOT"

plugins {
//    kotlin("multiplatform") version libs.versions.kotlin.version.get() apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.desktop) apply false
    kotlin("plugin.serialization") version libs.versions.kotlin.get() apply false
//    id("org.jetbrains.compose") version libs.versions.compose.version.get() apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
