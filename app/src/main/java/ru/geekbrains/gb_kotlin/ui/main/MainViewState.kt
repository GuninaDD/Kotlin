package ru.geekbrains.gb_kotlin.ui.main

import ru.geekbrains.gb_kotlin.data.model.Note
import ru.geekbrains.gb_kotlin.ui.base.BaseViewState

class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)