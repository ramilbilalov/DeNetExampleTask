package com.bilalov.testtaskfrombilalov.database.room.repository

import androidx.lifecycle.LiveData
import com.bilalov.testtaskfrombilalov.data.Note
import com.bilalov.testtaskfrombilalov.database.DatabaseRepository
import com.bilalov.testtaskfrombilalov.database.room.dao.NoteRoomDao

class RoomRepository(private val noteRoomDao: NoteRoomDao) : DatabaseRepository {
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
        onSuccess()
    }
}