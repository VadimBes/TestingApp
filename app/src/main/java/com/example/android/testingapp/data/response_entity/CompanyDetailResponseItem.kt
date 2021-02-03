package com.example.android.testingapp.data.response_entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyDetailResponseItem(
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val img: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val phone: String,
    val www: String
)