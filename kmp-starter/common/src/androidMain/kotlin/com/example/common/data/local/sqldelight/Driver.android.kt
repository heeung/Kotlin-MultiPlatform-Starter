package com.example.common.data.local.sqldelight

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.memo.db.MemoDataBase

public actual class DriverFactory(private val context: Context) {
    public actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MemoDataBase.Schema, context, "test.db")
    }
}