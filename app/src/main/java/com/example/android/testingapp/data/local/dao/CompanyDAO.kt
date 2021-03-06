package com.example.android.testingapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.testingapp.data.response_entity.CompanyDetailResponseItem
import com.example.android.testingapp.data.response_entity.CompanyResponseItem

@Dao
interface CompanyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(companyItem:CompanyResponseItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(companyDetail:CompanyDetailResponseItem)

    @Query("SELECT * FROM CompanyDetailResponseItem WHERE id = :id")
    fun getDetailCompany(id:String):LiveData<CompanyDetailResponseItem>

    @Query("SELECT * FROM CompanyResponseItem")
    fun getAllCompany():LiveData<List<CompanyResponseItem>>


}