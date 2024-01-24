package com.example.common.util

import okio.Path.Companion.toOkioPath
import java.io.File

//public actual fun generateImageLoader(): ImageLoader {
//    return ImageLoader {
//        components {
//            setupDefaultComponents()
//        }
//        interceptor {
//            // cache 100 success image result, without bitmap
//            defaultImageResultMemoryCache()
//            memoryCacheConfig {
//                maxSizeBytes(32 * 1024 * 1024) // 32MB
//            }
//            diskCacheConfig {
//                directory(getCacheDir().toOkioPath().resolve("image_cache"))
//                maxSizeBytes(512L * 1024 * 1024) // 512MB
//            }
//        }
//    }
//}
//
//private fun getCacheDir() = File(System.getenv("AppData"), "kmp-starter/cache") // window only