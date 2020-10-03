package ru.geekbrains.gb_kotlin.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import ru.geekbrains.gb_kotlin.R


class LogoutDialog() : DialogFragment() {

    companion object {
        val TAG = LogoutDialog::class.java.name + " TAG"

        fun createInstance(onLogout: (() -> Unit)) = LogoutDialog().apply {
            this.onLogout = onLogout
        }
    }

    var onLogout: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?) = AlertDialog.Builder(context)
        .setTitle(R.string.logout_title)
        .setMessage(R.string.logout_message)
        .setPositiveButton(R.string.logout_ok) { dialog, which -> onLogout?.invoke() }
        .setNegativeButton(R.string.logout_cancel) { dialog, which -> dismiss() }
        .create()
}
