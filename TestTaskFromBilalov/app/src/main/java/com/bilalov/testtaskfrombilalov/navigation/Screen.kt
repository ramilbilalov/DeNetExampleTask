package com.bilalov.testtaskfrombilalov.navigation

import androidx.compose.runtime.MutableState

sealed class Screen(val screenName: String) {

    object Main : Screen("main")
    object SecondView : Screen("second")
    object InfoView : Screen("infoView")



    fun withArgs(vararg args: Int): String {
        return buildString {
            append(screenName)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}