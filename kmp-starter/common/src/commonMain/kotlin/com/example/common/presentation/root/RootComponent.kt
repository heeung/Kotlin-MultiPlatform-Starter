package com.example.common.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.subscribe
import com.example.common.presentation.comment.CommentComponent
import com.example.common.presentation.login.LoginComponent
import com.example.common.presentation.memo.MemoComponent
import com.example.common.presentation.photo.PhotoComponent
import com.example.common.util.PreferencesUtil
import io.github.aakira.napier.Napier
import kotlinx.serialization.Serializable

private const val TAG = "RootComponent"
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
        public class LoginChild(public val component: LoginComponent) : Child()
        public class PhotoChild(public val component: PhotoComponent) : Child()
    }

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            is Config.Comment -> Child.CommentChild(CommentComponent(componentContext))
            is Config.Memo -> Child.MemoChild(MemoComponent(componentContext))
            is Config.Login -> Child.LoginChild(LoginComponent(componentContext, PreferencesUtil.settingsRepository!!))
            is Config.Photo -> Child.PhotoChild(PhotoComponent(componentContext))
        }

    @Serializable
    public sealed interface Config {
        @Serializable
        public data object Comment : Config
        @Serializable
        public data object Memo : Config
        @Serializable
        public data object Login : Config
        @Serializable
        public data object Photo : Config
    }

    public fun onCommentClick() {
//        navigation.bringToFront(Config.Comment)
        navigation.replaceCurrent(Config.Comment)
    }

    public fun onMemoClick() {
//        navigation.bringToFront(Config.Memo)
        navigation.replaceCurrent(Config.Memo)
    }

    public fun onLoginClick() {
//        navigation.bringToFront(Config.Login)
        navigation.replaceCurrent(Config.Login)
    }

    public fun onPhotoClick() {
//        navigation.bringToFront(Config.Photo)
        navigation.replaceCurrent(Config.Photo)
    }
}