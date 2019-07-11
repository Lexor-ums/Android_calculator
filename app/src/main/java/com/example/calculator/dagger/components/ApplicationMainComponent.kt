package com.example.calculator.dagger.components

import com.example.calculator.MainApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class])
interface ApplicationMainComponent {
    fun injectApplication(app: MainApplication)
}