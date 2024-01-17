package com.example.common.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

public actual open class BaseViewModel {
    public actual val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
}