plugins {
//    kotlin("multiplatform")
//    id("org.jetbrains.compose")
//    id("com.android.library")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.desktop)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
}

group = "com.example"
version = "1.0-SNAPSHOT"

//@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
//                api(libs.compose.bom)
                api(compose.foundation)
                api(compose.material3)
                api(compose.runtime)
                api(compose.ui)
//                api(libs.compose.materialIconsExtended)
                api(libs.kotlinInject.runtime)

                api(libs.koin.core)

                api(libs.napier)

                api(libs.bundles.ktor)
                api(libs.kotlinx.serialization.json)

                api(libs.decompose)
                api(libs.decompose.extention)

                api(libs.bundles.reaktive)

                api(libs.imageLoader)
//                api(libs.imageLoader.extention.moko)
//                api(libs.imageLoader.extention.blur)

                api(libs.multiplatform.settings)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.core)

                api(libs.ktor.okHttp)

                api(libs.koin.core)
                api(libs.koin.android)

//                api(libs.napier.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.desktop.common)
                api(compose.preview)

                api(libs.ktor.okHttp)

                api(libs.koin.core)
                api(libs.koin.jvm)

                api(libs.reaktive.utils)
//                api(libs.ktor.negotiation)
//                api(libs.napier.jvm)
//                api(libs.imageLoader.extention.jvm)
            }
        }

        val desktopTest by getting
    }

    explicitApi()
}

android {
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}