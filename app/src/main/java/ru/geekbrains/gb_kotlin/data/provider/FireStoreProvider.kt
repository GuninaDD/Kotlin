package ru.geekbrains.gb_kotlin.data.provider

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ru.geekbrains.gb_kotlin.data.errors.NoAuthException
import ru.geekbrains.gb_kotlin.data.model.Note
import ru.geekbrains.gb_kotlin.data.model.NoteResult
import ru.geekbrains.gb_kotlin.data.model.User


class FirestoreDataProvider : RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USER_COLLECTION = "users"

    }

    private val store by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }

    private val currentUser
        get() = auth.currentUser

    val userNotesCollection
        get() = currentUser?.let { store.collection(USER_COLLECTION).document(it.uid).collection(NOTES_COLLECTION) } ?: throw NoAuthException()

    override fun getCurrentUser(): LiveData<User?> = MutableLiveData<User?>().apply {
        value = currentUser?.let { User(it.displayName ?: "", it.email ?: "") }
    }

    override fun subscribeToAllNotes(): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        userNotesCollection.addSnapshotListener { snapshot, e ->
            e?.let {
                value = NoteResult.Error(e)
            } ?: let {
                snapshot?.let {
                    val notes = snapshot.documents.map { doc ->
                        doc.toObject(Note::class.java)
                    }
                    value = NoteResult.Success(notes)
                }
            }
        }
    }

    override fun getNoteById(id: String): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        userNotesCollection.document(id).get()
            .addOnSuccessListener { snapshot ->
                value = NoteResult.Success(snapshot.toObject(Note::class.java))
            }.addOnFailureListener {
                value = NoteResult.Error(it)
            }
    }

    override fun saveNote(note: Note): LiveData<NoteResult> = MutableLiveData<NoteResult>().apply {
        userNotesCollection.document(note.id).set(note)
            .addOnSuccessListener { snapshot ->
                value = NoteResult.Success(note)
            }.addOnFailureListener {
                value = NoteResult.Error(it)
            }
    }
}