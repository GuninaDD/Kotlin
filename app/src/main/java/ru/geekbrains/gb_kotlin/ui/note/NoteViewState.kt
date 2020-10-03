package ru.geekbrains.gb_kotlin.ui.note

import ru.geekbrains.gb_kotlin.data.model.Note
import ru.geekbrains.gb_kotlin.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) :
    BaseViewState<Note?>(note, error)