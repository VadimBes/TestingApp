package com.example.android.testingapp.data.repository

import androidx.lifecycle.LiveData
import com.example.android.testingapp.data.response_entity.CompanyResponseItem

interface CompanyRepository {
    suspend fun getCurrentCompanies():LiveData<List<CompanyResponseItem>>
}