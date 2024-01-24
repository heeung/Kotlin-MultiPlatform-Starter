package com.example.common.util

import android.os.Build.VERSION.SDK_INT
import coil3.ComponentRegistry
import coil3.decode.DecodeResult
import coil3.decode.DecodeUtils
import coil3.decode.Decoder

internal actual fun ComponentRegistry.Builder.addPlatformComponents() {
    // GIFs
//    if (SDK_INT >= 28) {
//        add(ImageDecoderDecoder.Factory())
//    } else {
//        add(GifDecoder.Factory())
//    }
//    // Video frames
//    add(VideoFrameDecoder.Factory())
}