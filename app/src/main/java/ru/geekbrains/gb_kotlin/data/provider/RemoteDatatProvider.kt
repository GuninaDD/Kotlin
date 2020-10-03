package ru.geekbrains.gb_kotlin.data.provider

import android.arch.lifecycle.LiveData
import ru.geekbrains.gb_kotlin.data.model.Note
import ru.geekbrains.gb_kotlin.data.model.NoteResult
import ru.geekbrains.gb_kotlin.data.model.User


interface RemoteDataProvider {

    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note) : LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
}
