package com.example.common.util

import com.example.memo.db.Memo
import com.example.memo.db.MemoDataBase
import com.example.memo.db.MemoDataBaseQueries

public expect object DBUtil {
    public var DB: MemoDataBase?
    public val memoQueries: MemoDataBaseQueries
}

public fun MemoDataBaseQueries.getMemoList(): List<Memo> {
    return this.selectAll().executeAsList()
}

public fun MemoDataBaseQueries.insertMemo(
    comment: String,
    isDone: Boolean
) {
    this.insertMemo(comment, if (isDone) 1 else 0)
}