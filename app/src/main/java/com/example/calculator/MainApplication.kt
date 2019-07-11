package com.example.calculator

import android.app.Application
import com.example.calculator.dagger.components.DaggerApplicationMainComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector{

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationMainComponent.create()
            .injectApplication(this)
    }
    override fun androidInjector(): AndroidInjector<Any> =  androidInjector

}