package com.example.common.base

import kotlinx.coroutines.CoroutineScope

public expect open class BaseViewModel() {
    public val scope: CoroutineScope
}