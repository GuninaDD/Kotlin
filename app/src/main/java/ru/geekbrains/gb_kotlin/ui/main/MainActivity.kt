package ru.geekbrains.gb_kotlin.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.gb_kotlin.R
import ru.geekbrains.gb_kotlin.data.model.Note
import ru.geekbrains.gb_kotlin.ui.base.BaseActivity
import ru.geekbrains.gb_kotlin.ui.base.BaseViewModel
import ru.geekbrains.gb_kotlin.ui.note.NoteActivity

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: BaseViewModel<List<Note>?, MainViewState> by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override val layoutRes = R.layout.activity_main

    lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        rv_notes.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter { note ->
            NoteActivity.start(this, note.id)
        }
        rv_notes.adapter = adapter
        fab.setOnClickListener {
            NoteActivity.start(this)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }

}
