package com.bilalov.testtaskfrombilalov.view

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.bilalov.testtaskfrombilalov.R
import com.bilalov.testtaskfrombilalov.data.Note
import com.bilalov.testtaskfrombilalov.navigation.Screen
import com.bilalov.testtaskfrombilalov.utils.TYPE_ROOM
import com.bilalov.testtaskfrombilalov.viewModel.MainViewModel

@Composable
fun DefaultPreview(
    navController: NavHostController,
    context: Application,
    viewModel: MainViewModel
) {
    var counter:Int = 0

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "This is the parent in node(1st graph)"
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,modifier = Modifier.background(
            Color.Red
        )) {
            Image(painterResource(id = R.drawable.ic_baseline_circle_24), contentDescription = "root",
                //modifier = Modifier.fillMaxWidth()
            )

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                //verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    viewModel.initDatabase(TYPE_ROOM) {
                        counter++
                        viewModel.addNote(note = Note(name = "Local", position = "{$counter}L")) {
                            navController.navigate(
                                Screen.SecondView
                                    .withArgs(
                                        "login"
                                    )
                            )
                        }
                    }
                }) {

                }
                Button(onClick = {
                    viewModel.initDatabase(TYPE_ROOM) {
                        counter++
                        viewModel.deleteNote(note = Note(id = 1, "Local", "Test")) {

                        }
                    }
                }) {

                }
           }
        }
        }

}