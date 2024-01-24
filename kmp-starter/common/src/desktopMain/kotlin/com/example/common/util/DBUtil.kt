package com.example.common.util

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.memo.db.MemoDataBase
import com.example.memo.db.MemoDataBaseQueries
import io.github.aakira.napier.Napier
import org.sqlite.SQLiteException
import java.io.File

public actual object DBUtil {
    public actual var DB: MemoDataBase? = null
    public actual val memoQueries: MemoDataBaseQueries
        get() = DB!!.memoDataBaseQueries

    public fun initDB() {
        val databasePath = File(System.getProperty("java.io.tmpdir"), "test.db")
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:${databasePath.absolutePath}")
//        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:")
        try {
            MemoDataBase.Schema.create(driver)
        } catch (e: SQLiteException) {
            Napier.d { "이미 생성된 DB입니다. 기존 DB를 유지합니다." }
        }
        DB = MemoDataBase(driver)
    }
}