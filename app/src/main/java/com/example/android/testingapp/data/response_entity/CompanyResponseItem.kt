package com.example.android.testingapp.data.response_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyResponseItem(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val img: String,
    val name: String
)