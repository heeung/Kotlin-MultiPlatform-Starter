package com.example.common.data.local.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.memo.db.MemoDataBase

public actual class DriverFactory {
    public actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        MemoDataBase.Schema.create(driver)
        return driver
    }
}