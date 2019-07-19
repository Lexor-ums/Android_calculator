package com.example.calculator.dagger.modules

import android.app.Application
import android.content.Context
import com.example.calculator.MainApplication
import dagger.Module

@Module
class ApplicationMainModule(private val app : MainApplication){
    fun provideApplication() : Application{
        return app
    }
    fun provideContext() : Context{
        return app
    }
}