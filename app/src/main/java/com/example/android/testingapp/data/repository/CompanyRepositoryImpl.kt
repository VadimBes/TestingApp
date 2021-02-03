package com.example.android.testingapp.data.repository

import androidx.lifecycle.LiveData
import com.example.android.testingapp.data.local.dao.CompanyDAO
import com.example.android.testingapp.data.remote.CompanyNetworkDataSource
import com.example.android.testingapp.data.remote.CompanyNetworkDataSourceImpl
import com.example.android.testingapp.data.response_entity.CompanyDetailResponseItem
import com.example.android.testingapp.data.response_entity.CompanyResponseItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val companyDAO: CompanyDAO,
    private val companyNetworkDataSource: CompanyNetworkDataSource
) : CompanyRepository {

    init {
        companyNetworkDataSource.downloadedCurrentCompany.observeForever { listcompanies ->
            persistFetchedCompanies(listcompanies)
        }
        companyNetworkDataSource.downloadedCompanyDetails.observeForever{
            detailCompany->persistFetchedDetailCompany(detailCompany)
        }
    }

    override suspend fun getCurrentCompanies(): LiveData<List<CompanyResponseItem>> {
        return withContext(Dispatchers.IO) {
            initCompaniesData()
            return@withContext companyDAO.getAllCompany()
        }
    }


    private suspend fun initCompaniesData() {
        companyNetworkDataSource.fetchCurrentCompany()
    }

    private fun persistFetchedCompanies(fetchedCompanies: List<CompanyResponseItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            fetchedCompanies.forEach {
                companyDAO.insert(it)
            }
        }
    }

    override suspend fun getCompanyDetails(id: String): LiveData<CompanyDetailResponseItem> {
        return withContext(Dispatchers.IO) {
            initDetailsCompany(id)
            return@withContext companyDAO.getDetailCompany(id)
        }
    }

    private suspend fun initDetailsCompany(id: String) {
        companyNetworkDataSource.fetchCompanyDetails(id)
    }

    private fun persistFetchedDetailCompany(fetchedDetailCompanies: CompanyDetailResponseItem) =
        CoroutineScope(Dispatchers.IO).launch {
            companyDAO.insertDetail(fetchedDetailCompanies)
        }
}