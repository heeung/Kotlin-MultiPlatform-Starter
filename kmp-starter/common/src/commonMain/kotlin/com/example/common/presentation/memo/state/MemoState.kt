package com.example.common.presentation.memo.state

import com.example.common.data.entity.Comment
import com.example.memo.db.Memo

public data class MemoState(
    val memoList: List<Memo> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null
)
