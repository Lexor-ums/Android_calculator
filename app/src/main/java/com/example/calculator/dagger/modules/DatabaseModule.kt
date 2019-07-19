package com.example.calculator.dagger.modules

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.calculator.data.database.CurrencyDao
import com.example.calculator.data.database.CurrencyDatabase
import com.example.calculator.data.database.CurrencyRepositoryImpl
import com.example.calculator.data.database.CurrencyRepositoryInterface
import com.example.calculator.utils.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideCurrencyDataBase(application: Application): CurrencyDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            CurrencyDatabase::class.java, DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrencyDAO(currencyDatabase: CurrencyDatabase): CurrencyDao = currencyDatabase.currencyDAO()

    @Provides
    @Singleton
    fun provideCurrencyRepository(currencyDatabase: CurrencyDatabase): CurrencyRepositoryInterface =
        CurrencyRepositoryImpl(currencyDatabase.currencyDAO())

}