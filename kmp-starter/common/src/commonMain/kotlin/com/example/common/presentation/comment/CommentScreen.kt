package com.example.common.presentation.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.data.entity.Comment
import com.example.common.util.CustomColor
import com.example.common.util.VerticalScrollbar
import com.example.common.util.rememberScrollbarAdapter
import io.github.aakira.napier.Napier

private const val TAG = "CommentScreen"
@Composable
internal fun CommentScreen(
    component: CommentComponent,
    modifier: Modifier = Modifier
) {
    Napier.d(tag = TAG) { "onCreate" }
    val commentListState by component.commentListState.collectAsState()

    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(CustomColor.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            with(commentListState) {
                Napier.d(tag = TAG) { "불러오기 시작합니다." }
                if (loading) {
                    Loader()
                }
                if (commentList.isNotEmpty()) {
                    Napier.d(tag = TAG) { "불러오기 완료." }
                    CommentListContent(commentList)
                }
                error?.let {
                    ErrorMessage(it)
                }
            }
    //        CommentListContent(commentListState.commentList)
        }
        Box(
            modifier = Modifier
                .background(CustomColor.Transparent)
                .wrapContentSize()
                .padding(20.dp)
                .align(Alignment.TopEnd)
        ) {
            IconButton(
                modifier = Modifier
                    .background(
                        color = CustomColor.DefaultButtonColor,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .size(width = 50.dp, height = 50.dp),
                onClick = component::onClickRefreshButton,
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "refresh button"
                )
            }
        }
    }
}

@Composable
internal fun CommentListContent(
    commentList: List<Comment>
) {
    Box {
        val listState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState
        ) {
            items(commentList) {comment ->
                CommentItemContent(comment)
                Divider()
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollState = listState)
        )
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
            text = comment.email,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = comment.body
            )
        }
    }
}

@Composable
private fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(80.dp),
            color = CustomColor.SkyBlue,
            trackColor = CustomColor.Gray,
            strokeWidth = 10.dp
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