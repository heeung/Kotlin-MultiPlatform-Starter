package com.example.common.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.example.common.presentation.comment.CommentComponent
import com.example.common.presentation.memo.MemoComponent
import kotlinx.serialization.Serializable

public class RootComponent(
    componentContext: ComponentContext
): ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    private val stack =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialStack = { listOf(Config.Comment) },
            childFactory = ::child,
        )

    public val childStack: Value<ChildStack<*, Child>> = stack

    public sealed class Child {
        public class CommentChild(public val component: CommentComponent) : Child()
        public class MemoChild(public val component: MemoComponent) : Child()
    }

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Comment -> Child.CommentChild(CommentComponent(componentContext))
            is Config.Memo -> Child.MemoChild(MemoComponent(componentContext))
        }

    @Serializable
    public sealed interface Config {
        @Serializable
        public data object Comment : Config
        @Serializable
        public data object Memo : Config
    }

    public fun onCommentClick() {
        navigation.bringToFront(Config.Comment)
    }

    public fun onMemoClick() {
        navigation.bringToFront(Config.Memo)
    }
}