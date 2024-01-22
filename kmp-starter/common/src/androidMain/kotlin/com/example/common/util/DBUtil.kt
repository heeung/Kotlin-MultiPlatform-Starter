package com.example.common.util

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.memo.db.MemoDataBase
import com.example.memo.db.MemoDataBaseQueries

public actual object DBUtil {
    public actual var DB: MemoDataBase? = null
    public actual val memoQueries: MemoDataBaseQueries
        get() = DB!!.memoDataBaseQueries

    public fun initDB(context: Context) {
        DB = MemoDataBase(
            AndroidSqliteDriver(
                MemoDataBase.Schema,
                context,
                "test.db"
            )
        )
    }
}