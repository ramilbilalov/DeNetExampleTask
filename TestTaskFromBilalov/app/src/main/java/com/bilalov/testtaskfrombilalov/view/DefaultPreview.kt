package com.bilalov.testtaskfrombilalov.view

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
    val counter = viewModel.countLevel.observeAsState().value

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
            Color.DarkGray
        )) {
            Image(painterResource(id = R.drawable.ic_baseline_circle_24), contentDescription = "root",
                modifier = Modifier.size(98.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                //verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column() {
                    Button(onClick = {
                        viewModel.initDatabase(TYPE_ROOM) {
                            if (counter != null)
                                navController.navigate(
                                    Screen.SecondView
                                        .withArgs(
                                            counter
                                        )
                                )
                        }
                    }, modifier = Modifier.background(color = Color.DarkGray)
                    ) {
                        Text(text = "Select")
                    }
                    Button(
                        onClick = {
                            if (counter != null) {
                                viewModel.initDatabase(TYPE_ROOM) {
                                    viewModel.addLevel()
                                    viewModel.addNote(
                                        note = Note(
                                            name = "Local",
                                            position = "left",
                                            countLevel = counter
                                        )
                                    ) {
                                        navController.navigate(
                                            Screen.SecondView
                                                .withArgs(
                                                    counter
                                                )
                                        )
                                    }
                                }
                            }
                        },
                    ) {
                        Text(text = "Create")
                    }
                    Button(onClick = {
                        if (counter != null) {
                            viewModel.initDatabase(TYPE_ROOM) {
                                viewModel.deleteLevel()
                                viewModel.deleteNote(
                                    note = Note(
                                        name = "Local",
                                        position = "left",
                                        countLevel = counter
                                    )
                                ) {

                                }
                            }
                        }
                    }) {
                        Text(text = "Delete")
                    }
                }

                Column() {
                    Button(onClick = {
                        viewModel.initDatabase(TYPE_ROOM) {
                            if (counter != null)
                                navController.navigate(
                                    Screen.SecondView
                                        .withArgs(
                                            counter
                                        )
                                )
                        }
                    }, modifier = Modifier.background(color = Color.DarkGray)
                    ) {
                        Text(text = "Select")
                    }
                    Button(
                        onClick = {
                            if (counter != null) {
                                viewModel.initDatabase(TYPE_ROOM) {
                                    viewModel.addLevel()
                                    viewModel.addNote(
                                        note = Note(
                                            name = "Local",
                                            position = "right",
                                            countLevel = counter
                                        )
                                    ) {
                                        navController.navigate(
                                            Screen.SecondView
                                                .withArgs(
                                                    counter
                                                )
                                        )
                                    }
                                }
                            }
                        },
                    ) {
                        Text(text = "Create")
                    }
                    Button(onClick = {
                        if (counter != null) {
                            viewModel.initDatabase(TYPE_ROOM) {
                                viewModel.deleteLevel()
                                viewModel.deleteNote(
                                    note = Note(
                                        name = "Local",
                                        position = "right",
                                        countLevel = counter
                                    )
                                ) {

                                }
                            }
                        }
                    }) {
                        Text(text = "Delete")
                    }
                }
           }
        }
        }

}