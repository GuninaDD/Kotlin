package ru.geekbrains.gb_kotlin.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ru.geekbrains.gb_kotlin.data.model.Note
import ru.geekbrains.gb_kotlin.data.provider.FirestoreDataProvider
import ru.geekbrains.gb_kotlin.data.provider.RemoteDataProvider
import java.util.*

object NotesRepository {
    val remoteProvider: RemoteDataProvider = FirestoreDataProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
}