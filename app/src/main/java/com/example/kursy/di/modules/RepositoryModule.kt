package com.example.kursy.di.modules

import com.example.kursy.mocks.CurrencyTableRepositoryMock
import com.example.kursy.repository.CurrencyTableRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RepositoryModule {

   @Provides
    fun providesRepository(retrofit: Retrofit): CurrencyTableRepository = retrofit.create(
        CurrencyTableRepository::class.java)

    //@Provides
    //fun providesRepository(retrofit: Retrofit): CurrencyTableRepository = CurrencyTableRepositoryMock()
}