package com.example.android.testingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.testingapp.data.local.dao.CompanyDAO
import com.example.android.testingapp.data.response_entity.CompanyDetailResponseItem
import com.example.android.testingapp.data.response_entity.CompanyResponseItem

@Database(
    entities = [CompanyDetailResponseItem::class, CompanyResponseItem::class],
    version = 1
)
abstract class CompanyDataBase : RoomDatabase() {

    abstract fun companyDao():CompanyDAO
}