package com.hussam.hhsapp.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.hussam.hhsapp.data.model.User

object FirebaseManager {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().getReference("users")

    fun registerWithEmail(user: User, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnSuccessListener { result ->
                val uId = result.user?.uid ?: ""
                val updatedUser = user.copy(id = uId)
                
                database.child(uId).setValue(updatedUser)
                    .addOnSuccessListener { onComplete(true, null) }
                    .addOnFailureListener { e -> onComplete(true, e.message) }
            }
            .addOnFailureListener { e ->
                onComplete(false, e.message)
            }
    }

    fun loginWithEmail(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onComplete(true, null)
            }
            .addOnFailureListener { e ->
                onComplete(false, e.message)
            }
    }
}
