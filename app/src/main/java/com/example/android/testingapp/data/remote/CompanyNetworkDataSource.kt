package com.example.android.testingapp.data.remote

import androidx.lifecycle.LiveData
import com.example.android.testingapp.data.response_entity.CompanyResponseItem

interface CompanyNetworkDataSource {
    val downloadedCurrentCompany:LiveData<List<CompanyResponseItem>>

    suspend fun fetchCurrentCompany()
}