package com.bilalov.testtaskfrombilalov.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChildrenPreview(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    countLevel: Int
){
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

    val counter = viewModel.countLevel.observeAsState().value

    Log.e("TTT", "Restart: $countLevel")

    Scaffold() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, modifier = Modifier.background(
                    Color.DarkGray
                )
            ) {
                Image(
                    painterResource(id = R.drawable.ic_baseline_circle_24),
                    contentDescription = "root",
                    modifier = Modifier.size(98.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    //verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column() {
                        Button(
                            onClick = {
                                viewModel.initDatabase(TYPE_ROOM) {
                                    if (counter != null)
                                        navHostController.navigate(
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
                                            navHostController.navigate(
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
                                viewModel.deleteLevel()
                                viewModel.initDatabase(TYPE_ROOM) {
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
                        Button(
                            onClick = {
                                viewModel.initDatabase(TYPE_ROOM) {
                                    if (counter != null)
                                        navHostController.navigate(
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
                                            navHostController.navigate(
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
                                    ){
                                    }
                                }
                            }
                        }) {
                            Text(text = "Delete")
                        }
                    }
                }
            }
            LazyRow {
                items(notes) { note ->
                    NoteItem(navHostController, note, viewModel)
                }
            }
        }
    }
}
@Composable
fun NoteItem(
    navHostController: NavHostController,
    note: Note,
    viewModel: MainViewModel
) {
    Column(verticalArrangement = Arrangement.SpaceAround) {
        Text(text = " ${note.name} ${note.countLevel} ${note.position}")
    }
}