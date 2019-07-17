package com.example.calculator.dagger.builders

import com.example.calculator.ui.MainActivity
import com.example.calculator.ui.MainActivityModule
import com.example.calculator.ui.calcfragment.CalcFragment
import com.example.calculator.ui.calcfragment.CalcFragmentModule
import com.example.calculator.ui.calcfragment.CalcFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBuilder{


    @ContributesAndroidInjector(modules = [MainActivityModule::class,
    CalcFragmentProvider::class
    ])
    abstract fun contributeMainActivity() : MainActivity
}