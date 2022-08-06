package com.bilalov.testtaskfrombilalov.navigation

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bilalov.testtaskfrombilalov.view.ChildsPreview
import com.bilalov.testtaskfrombilalov.view.DefaultPreview
import com.bilalov.testtaskfrombilalov.viewModel.MainViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavHostController,
    context: Application?,
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Main.screenName) {
        composable(route = Screen.Main.screenName) {
            if (context != null) {
                DefaultPreview(
                    navController = navController,
                    context = context,
                    viewModel = viewModel
                )
            }

        }

        composable(route = Screen.InfoView.screenName){
           // InfoView()
        }

        composable(route = Screen.SecondView.screenName + "/{login}",
            arguments = listOf(
                navArgument(name = route.toString()) {
                    type = NavType.StringType
                    defaultValue = "Unknown"
                    nullable = true
                }
            )) { entry ->
            ChildsPreview(
                login = entry.arguments?.getString("login").toString(),
                viewModel = viewModel,
                navHostController = navController
            )
        }

    }
}



