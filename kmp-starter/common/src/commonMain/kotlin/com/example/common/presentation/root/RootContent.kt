package com.example.common.presentation.root

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.Direction
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.isEnter
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.example.common.presentation.comment.CommentScreen
import com.example.common.presentation.memo.MemoScreen
import com.example.common.util.CustomColor

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
                            color = CustomColor.DefaultButtonColor,
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
                            color = CustomColor.DefaultButtonColor,
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
//        animation = stackAnimation(fade())
        animation = tabAnimation()
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.CommentChild -> CommentScreen(component = child.component)
            is RootComponent.Child.MemoChild -> MemoScreen(component = child.component)
        }
    }
}

@Composable
private fun tabAnimation(): StackAnimation<Any, RootComponent.Child> =
    stackAnimation { child, otherChild, direction ->
        val index = child.instance.index
        val otherIndex = otherChild.instance.index
        val anim = slide()
        if ((index > otherIndex) == direction.isEnter) anim else anim.flipSide()
    }

private val RootComponent.Child.index: Int
    get() =
        when (this) {
            is RootComponent.Child.CommentChild -> 0
            is RootComponent.Child.MemoChild -> 1
        }

private fun StackAnimator.flipSide(): StackAnimator =
    StackAnimator { direction, isInitial, onFinished, content ->
        invoke(
            direction = direction.flipSide(),
            isInitial = isInitial,
            onFinished = onFinished,
            content = content,
        )
    }

private fun Direction.flipSide(): Direction =
    when (this) {
        Direction.ENTER_FRONT -> Direction.ENTER_BACK
        Direction.EXIT_FRONT -> Direction.EXIT_BACK
        Direction.ENTER_BACK -> Direction.ENTER_FRONT
        Direction.EXIT_BACK -> Direction.EXIT_FRONT
    }