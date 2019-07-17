package com.example.calculator.ui

import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun provideMainActivityViewModel() : MainActivityViewModel{
        return MainActivityViewModel()
    }
}