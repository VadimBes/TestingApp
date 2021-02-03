package com.example.android.testingapp.data.remote

import com.example.android.testingapp.data.response_entity.CompanyDetailResponseItem
import com.example.android.testingapp.data.response_entity.CompanyResponseItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface CompanyApi {

    @GET("test.php")
    fun getCompanyList(): Deferred<List<CompanyResponseItem>>

    @GET("test.php")
    fun getCompanybyId(
        @Query("id") id: String
    ):Deferred<List<CompanyDetailResponseItem>>

}