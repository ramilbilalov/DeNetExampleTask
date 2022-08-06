package com.bilalov.testtaskfrombilalov.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.bilalov.testtaskfrombilalov.data.Note
import com.bilalov.testtaskfrombilalov.navigation.Screen
import com.bilalov.testtaskfrombilalov.utils.TYPE_ROOM
import com.bilalov.testtaskfrombilalov.viewModel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChildsPreview(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    login: String
){
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

    Scaffold() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Button(onClick = {
                    viewModel.initDatabase(TYPE_ROOM) {
                        viewModel.addNote(note = Note(name = "Local", position = "Test")) {
                            navHostController.navigate(
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
                        viewModel.deleteNote(note = Note(id = 1, "Local", "Test")) {

                        }
                    }
                }) {

                }
            }
        }
        LazyRow {
            items(notes){
                note-> NoteItem(navHostController, note, viewModel)
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
    Text(text = "${note.name} ${note.id}")
}