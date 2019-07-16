package com.example.calculator.dagger.modules

import android.content.Context
import com.example.calculator.MainApplication
import dagger.Module

@Module
class ApplicationMainModule{
    fun provideContext(app : MainApplication) : Context{
        return app
    }
}