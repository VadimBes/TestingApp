package com.example.android.testingapp.data.response_entity

import androidx.room.Entity

@Entity
data class CompanyResponseItem(
    val id: String,
    val img: String,
    val name: String
)