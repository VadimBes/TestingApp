package com.example.android.testingapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.android.testingapp.data.repository.CompanyRepository
import com.example.android.testingapp.other.lazyDeferred
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    repository: CompanyRepository
):ViewModel() {

    val companies by lazyDeferred {
        repository.getCurrentCompanies()
    }
}