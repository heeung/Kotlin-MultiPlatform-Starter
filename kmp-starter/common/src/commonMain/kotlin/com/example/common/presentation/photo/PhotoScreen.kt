package com.example.common.presentation.photo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.data.entity.Photo
import com.example.common.util.CustomColor
import io.github.aakira.napier.Napier
import java.io.File

private const val TAG = "PhotoScreen"
@Composable
internal fun PhotoScreen(
    component: PhotoComponent,
    modifier: Modifier = Modifier
) {
    Napier.d(tag = TAG) { "onCreate" }
    val photoListState by component.photoListState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CustomColor.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        with(photoListState) {
            Napier.d(tag = TAG) { "불러오기 시작합니다." }
            if (loading) {
                Loader()
            }
            if (photoList.isNotEmpty()) {
                Napier.d(tag = TAG) { "불러오기 완료." }
                PhotoListContent(photoList, component)
            }
            error?.let {
                ErrorMessage(it)
            }
        }
    }
}

@Composable
internal fun PhotoListContent(
    photoList: List<Photo>,
    component: PhotoComponent,
) {
    Box {
        val listState = rememberLazyGridState()

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(6),
            state = listState
        ) {
            items(photoList) {photo ->
                PhotoItemContent(photo, component)
                Divider()
            }
        }

//        VerticalScrollbar(
//            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
//            adapter = rememberScrollbarAdapter(scrollState = listState)
//        )
    }
}

@Composable
internal fun PhotoItemContent(
    photo: Photo,
    component: PhotoComponent,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
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