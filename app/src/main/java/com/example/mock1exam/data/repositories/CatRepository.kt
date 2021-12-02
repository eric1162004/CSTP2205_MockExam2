package com.example.mock1exam.data.repositories

import android.util.Log
import com.example.foodvillage2205.model.entities.Post
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
    fun getCats() = callbackFlow {
        val snapshotListener = _collection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                val response = if (error == null) {
                    val cats = mutableListOf<Cat>()

                    snapshot?.let { snapshotPosts ->
                        snapshotPosts.documents.mapTo(cats) { cat ->
                            val snapshotId = cat.id

                            cat.toObject<Cat>()!!.apply { id = snapshotId }
                        }
                    }

                    Resource.Success(cats)

                } else {
                    Resource.Error("Failed to load posts", error)
                }

                this.trySend(response).isSuccess
            }

        awaitClose { snapshotListener.remove() }
    }

    fun createCat(cat: Cat, onResponse: (Resource<*>) -> Unit) {
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

}