package com.example.common.presentation.root

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.common.presentation.comment.CommentContent
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.example.common.presentation.memo.MemoContent
import com.example.common.util.CustomColor
import java.awt.Shape

@Composable
public fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Scaffold (
        modifier = modifier
    ) {
        Box (
            modifier = modifier
                .fillMaxSize()
        ) {
            Children(component = component)
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .width(500.dp)
                    .shadow(10.dp, RoundedCornerShape(50))
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(
                            color = CustomColor.SkyBlue,
                        )
                        .weight(1f)
                        .clickable {
                            component.onCommentClick()
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "CommentComponent",
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(
                            color = CustomColor.SkyBlue,
                        )
                        .weight(1f)
                        .clickable {
                            component.onMemoClick()
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "MemoComponent",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun Children(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier,
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.CommentChild -> CommentContent(component = child.component)
            is RootComponent.Child.MemoChild -> MemoContent(component = child.component)
        }
    }
}