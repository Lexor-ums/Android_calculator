package com.example.calculator.presentation.calcfragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CalcFragmentProvider {

    @ContributesAndroidInjector(modules = [CalcFragmentModule::class])
    abstract fun contributeCalcFragment() : CalcFragment
}