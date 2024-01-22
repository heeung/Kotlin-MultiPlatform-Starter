package com.example.common.data.local.sqldelight

import app.cash.sqldelight.db.SqlDriver
import com.example.memo.db.MemoDataBase

public expect class DriverFactory {
    public fun createDriver(): SqlDriver
}

public fun createDatabase(sqlDriver: SqlDriver): MemoDataBase {
    return MemoDataBase(
        driver = sqlDriver
        // 만들어진 Enum, Data Class 등등 쓰고싶으면 adapter 넣어주기
    )
}