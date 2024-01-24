plugins {
//    kotlin("multiplatform")
//    id("org.jetbrains.compose")
//    id("com.android.library")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.desktop)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqlDelght)
    kotlin("plugin.serialization")
    id("kotlin-parcelize")
}

group = "com.example"
version = "1.0-SNAPSHOT"

sqldelight {
    databases {
        create("MemoDataBase") {
            packageName.set("com.example.memo.db")
        }
        linkSqlite.set(true)
    }
}

//@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
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

//                api(libs.imageLoader)
//                api(libs.imageLoader.extention.moko)
//                api(libs.imageLoader.extention.blur)

                api(libs.multiplatform.settings)

                api(libs.sqlDelight.runtime)
                api(libs.sqlDelight.adapter)

//                api(libs.coil.core)
                api(libs.coil)
//                api(libs.coil.core)
                api(libs.coil.svg)
//                api(libs.coil.gif)
//                api(libs.coil.video)
                api(libs.coil.core.network)
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

                api(libs.sqlDelight.android)
//                api(libs.napier.android)
//                api(libs.coil.core.android)
                api(libs.coil.android)
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

                api(libs.sqlDelight.jvm)
//                api(libs.sqlDelight.jvm.runtime)
                api(libs.jdbc)

//                api(libs.imageLoader.desktop)

//                api(libs.imageLoader.extention.jvm)
//                api(libs.sqlDelight.native)
//                api(libs.ktor.negotiation)
//                api(libs.napier.jvm)
//                api(libs.imageLoader.extention.jvm)
                api(libs.coil.core.jvm)
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}