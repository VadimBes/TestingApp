package com.example.android.testingapp.ui.fragments

import androidx.lifecycle.ViewModel
import com.example.android.testingapp.data.repository.CompanyRepository
import com.example.android.testingapp.other.lazyDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(
    val id:String,
    val repository: CompanyRepository
):ViewModel() {

    val detailCompany by lazyDeferred {
        repository.getCompanyDetails(id)
    }

}