package com.example.common.presentation.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.data.entity.Comment
import com.example.common.util.CustomColor
import io.github.aakira.napier.Napier

@Composable
internal fun CommentContent(
    component: CommentComponent,
    modifier: Modifier = Modifier
) {
    Napier.d { "onCreate Content" }
    val commentListState by component.commentListState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CustomColor.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(commentListState) {
            if (loading) {
                Loader()
            }
            if (commentList.isNotEmpty()) {
                CommentListContent(commentList)
            }
            error?.let {
                ErrorMessage(it)
            }
        }
//        CommentListContent(commentListState.commentList)
    }
}

@Composable
internal fun CommentListContent(
    commentList: List<Comment>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(commentList) {comment ->
            CommentItemContent(comment)
        }
    }
}

@Composable
internal fun CommentItemContent(
    comment: Comment
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        Text(
            text = comment.body
        )
    }
}

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
}


@Composable
private fun ErrorMessage(
    message: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
        )
    }
}