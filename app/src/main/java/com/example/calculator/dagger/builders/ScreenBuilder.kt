package com.example.calculator.dagger.builders

import com.example.calculator.ui.calcfragment.CalcFragment
import com.example.calculator.ui.calcfragment.CalcFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBuilder{

    @ContributesAndroidInjector(modules = [CalcFragmentModule::class])
    abstract fun contributeCalcFragment() : CalcFragment

    abstract fun contributeMainActivity()
}