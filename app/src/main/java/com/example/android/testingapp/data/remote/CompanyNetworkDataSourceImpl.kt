package com.example.android.testingapp.data.remote

import android.util.Log
import android.util.MalformedJsonException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.testingapp.data.response_entity.CompanyDetailResponseItem
import com.example.android.testingapp.data.response_entity.CompanyResponseItem
import com.example.android.testingapp.other.NoConnectivityException
import javax.inject.Inject

class CompanyNetworkDataSourceImpl @Inject constructor(
    private val companyApi: CompanyApi
) : CompanyNetworkDataSource {
    private val _downloadedCurrentCompany = MutableLiveData<List<CompanyResponseItem>>()
    override val downloadedCurrentCompany: LiveData<List<CompanyResponseItem>>
        get() = _downloadedCurrentCompany

    override suspend fun fetchCurrentCompany() {
        try {
            val fetchedCurrentCompany = companyApi.getCompanyList().await()
            _downloadedCurrentCompany.postValue(fetchedCurrentCompany)
        }catch (e:NoConnectivityException){
            Log.e("Connectivity","No internet connection")
        }
    }
    private val _downloadedCompanyDetails = MutableLiveData<CompanyDetailResponseItem>()
    override val downloadedCompanyDetails: LiveData<CompanyDetailResponseItem>
        get() = _downloadedCompanyDetails

    override suspend fun fetchCompanyDetails(id:String) {
        try {
            val fetchedDetailCompany = companyApi.getCompanybyId(id).await()
            _downloadedCompanyDetails.postValue(fetchedDetailCompany.first())
        }catch (e:Exception){
            when(e){
                is NoConnectivityException->Log.e("Connectivity","No internet connection")
                is MalformedJsonException->            Log.e("TAG","Malformed")
            }
        }
    }
}