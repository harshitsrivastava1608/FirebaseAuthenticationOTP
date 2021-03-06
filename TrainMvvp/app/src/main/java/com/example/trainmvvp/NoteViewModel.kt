package com.example.trainmvvp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application):AndroidViewModel(application) {
    val allNotes:LiveData<List<Note>>
    init {
        val dao=NoteDatabase.getDatabase(application).getNotesDao()
        val repository=NoteRepository(dao)
        allNotes=repository.allNotes
    }
}