package com.bilalov.testtaskfrombilalov.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilalov.testtaskfrombilalov.data.Note
import com.bilalov.testtaskfrombilalov.database.room.AppRoomDatabase
import com.bilalov.testtaskfrombilalov.database.room.repository.RoomRepository
import com.bilalov.testtaskfrombilalov.utils.REPOSITORY
import com.bilalov.testtaskfrombilalov.utils.TYPE_ROOM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val application: Application) : ViewModel() {

    val countLevel: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(1)
    }


    init {

    }

    val context = application

    fun initDatabase(type: String, onSuccess: ()-> Unit) {
        Log.d("checkData", "MainViewModel initDatabase with type: $type")
        when(type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }


    fun readAllNotes() = REPOSITORY.readAll

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun addLevel(){
        countLevel.value = countLevel.value?.plus(1)
    }

    fun deleteLevel(){
        countLevel.value = countLevel.value?.minus(1)
    }

}