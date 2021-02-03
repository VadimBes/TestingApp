package com.example.android.testingapp.data.repository

import androidx.lifecycle.LiveData
import com.example.android.testingapp.data.response_entity.CompanyDetailResponseItem
import com.example.android.testingapp.data.response_entity.CompanyResponseItem

interface CompanyRepository {
    suspend fun getCurrentCompanies():LiveData<List<CompanyResponseItem>>
    suspend fun getCompanyDetails(id:String):LiveData<CompanyDetailResponseItem>
}