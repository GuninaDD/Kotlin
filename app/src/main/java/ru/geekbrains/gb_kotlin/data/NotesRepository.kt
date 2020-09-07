package ru.geekbrains.gb_kotlin.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.geekbrains.gb_kotlin.data.model.Note
import java.util.*

object NotesRepository {

    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes = mutableListOf(
        Note(
            UUID.randomUUID().toString(),
            "Просто заметка",
            "Просто текст заметки",
             Note.Color.BLUE
        )
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note:Note){
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note){
        for (i in notes.indices){
            if(notes[i] == note){
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }
}