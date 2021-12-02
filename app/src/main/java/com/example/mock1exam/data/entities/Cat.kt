package com.example.mock1exam.data.entities

import com.google.firebase.Timestamp

data class Cat(
    var id: String = "",
    val title: String = "",
    val breed: String = "",
    val gender: String = "boy",
    val age: Int = 0,
    val imageUrl: String = "",
    val imageFileName: String = "",
    val description: String = "",
    val timestamp: Timestamp? = Timestamp.now(),
    val userId: String = ""
)

