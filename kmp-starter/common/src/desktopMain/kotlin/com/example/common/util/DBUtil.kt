package com.example.common.util

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.memo.db.MemoDataBase
import com.example.memo.db.MemoDataBaseQueries

public actual object DBUtil {
    public actual var DB: MemoDataBase? = null
    public actual val memoQueries: MemoDataBaseQueries
        get() = DB!!.memoDataBaseQueries

    public fun initDB() {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        MemoDataBase.Schema.create(driver)
        DB = MemoDataBase(driver)
//        DB = MemoDataBase(NativeSqliteDriver(MemoDataBase.Schema, "test.db"))
    }
}