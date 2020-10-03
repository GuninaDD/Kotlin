package ru.geekbrains.gb_kotlin.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import ru.geekbrains.gb_kotlin.R
import ru.geekbrains.gb_kotlin.data.model.Note
import ru.geekbrains.gb_kotlin.ui.base.BaseActivity
import ru.geekbrains.gb_kotlin.ui.base.BaseViewModel
import ru.geekbrains.gb_kotlin.ui.note.NoteActivity
import ru.geekbrains.gb_kotlin.ui.splash.SplashActivity

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {


    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = MenuInflater(this).inflate(R.menu.main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout -> showLogoutDialog().let { true }
        else -> false
    }

    fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG) ?: LogoutDialog.createInstance {
            onLogout()
        }.show(supportFragmentManager, LogoutDialog.TAG)
    }

    fun onLogout() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
    }

}