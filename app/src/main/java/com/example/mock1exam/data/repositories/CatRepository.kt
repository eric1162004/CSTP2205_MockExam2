package com.example.mock1exam.data.repositories

import android.util.Log
import com.example.mock1exam.data.Resource
import com.example.mock1exam.data.entities.Cat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class CatRepository {
    private val _collection = FirebaseFirestore.getInstance().collection("cats")
    private val TAG = "Debug"

    @ExperimentalCoroutinesApi
    fun getAll() = callbackFlow {
        val snapshotListener = _collection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                val response = if (error == null) {
                    val cats = mutableListOf<Cat>()

                    snapshot?.let { snapshot ->
                        snapshot.documents.mapTo(cats) { cat ->
                            cat.toObject<Cat>()!!.apply { id = cat.id }
                        }
                    }

                    Resource.Success(cats)

                } else {
                    Resource.Error("Failed to load posts", error)
                }

                this.trySend(response)
            }

        awaitClose { snapshotListener.remove() }
    }

    fun getById(id: String, onResponse: (Resource<*>) -> Unit){
        _collection.document(id)
            .get()
            .addOnSuccessListener { cat ->
                onResponse(Resource.Success(cat.toObject<Cat>()))
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun create(cat: Cat, onResponse: (Resource<*>) -> Unit) {
        val newDocRef = _collection.document()

        newDocRef.set(cat.apply { id = newDocRef.id })
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written  $documentReference")
                onResponse(Resource.Success(documentReference))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding post", e)
                onResponse(Resource.Error("Error adding post", e))
            }
    }

    fun update(cat: Cat, onResponse: (Resource<*>) -> Unit) {
        _collection.document(cat.id)
            .set(cat)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
                onResponse(Resource.Success(cat.id))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating post", e)
                onResponse(Resource.Error("Error updating post", e))
            }
    }

    fun delete(cat: Cat, onResponse: (Resource<*>) -> Unit) {
        _collection.document(cat.id)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
                onResponse(Resource.Success(cat.id))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error deleting document", e)
                onResponse(Resource.Error("Error deleting post", e))
            }
    }

}