package com.example.android.testingapp.di

import android.content.Context
import androidx.room.Room
import com.example.android.testingapp.data.remote.CompanyApi
import com.example.android.testingapp.data.local.CompanyDataBase
import com.example.android.testingapp.other.Constants.BASE_URL
import com.example.android.testingapp.other.Constants.DATABASE_NAME
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideCompanyDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,CompanyDataBase::class.java,DATABASE_NAME)

    @Singleton
    @Provides
    fun provideCompanyDao(
        dataBase: CompanyDataBase
    ) = dataBase.companyDao()


    @Singleton
    @Provides
    fun provideCompanyApi(): CompanyApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(CompanyApi::class.java)
    }
}