package com.example.calculator.dagger.builders

import com.example.calculator.presentation.MainActivity
import com.example.calculator.presentation.MainActivityModule
import com.example.calculator.presentation.calcfragment.CalcFragmentProvider
import com.example.calculator.presentation.financialfragment.financialconvertionfragment.FinancialConversionFragmentProvider
import com.example.calculator.presentation.financialfragment.financialexchangefragment.FinancialExchangeFragmentProvider
import com.example.calculator.presentation.financialfragment.financialmainfragment.FinancialMainFragmnetProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBuilder {


    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            CalcFragmentProvider::class,
            FinancialMainFragmnetProvider::class,
            FinancialConversionFragmentProvider::class,
            FinancialExchangeFragmentProvider::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}