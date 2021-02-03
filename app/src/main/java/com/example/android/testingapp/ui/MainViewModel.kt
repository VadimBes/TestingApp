package com.example.android.testingapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _navigateToDetail = MutableLiveData<String?>()
    val navigateToDetail:LiveData<String?>
    get() = _navigateToDetail

    fun navigateActionToDetail(id:String){
        _navigateToDetail.value = id
    }
    fun finishNavigate(){
        _navigateToDetail.value = null
    }
}