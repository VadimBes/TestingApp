package com.example.android.testingapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.testingapp.data.repository.CompanyRepository
import com.example.android.testingapp.ui.fragments.DetailViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val id:String, private val repository: CompanyRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(id,repository) as T
    }
}