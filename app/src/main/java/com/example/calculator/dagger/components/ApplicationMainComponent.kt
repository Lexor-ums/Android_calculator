package com.example.calculator.dagger.components

import com.example.calculator.MainApplication
import com.example.calculator.dagger.builders.ScreenBuilder
import com.example.calculator.dagger.modules.ApplicationMainModule
import com.example.calculator.dagger.modules.NetworkModule
import com.example.calculator.dagger.modules.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ApplicationMainModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    ScreenBuilder::class])

interface ApplicationMainComponent {
    fun injectApplication(app: MainApplication)
}