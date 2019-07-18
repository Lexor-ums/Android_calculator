package com.example.calculator.dagger.builders

import com.example.calculator.ui.MainActivity
import com.example.calculator.ui.MainActivityModule
import com.example.calculator.ui.calcfragment.CalcFragmentProvider
import com.example.calculator.ui.financialfragment.financialconvertionfragment.FinancialConversionFragmentProvider
import com.example.calculator.ui.financialfragment.financialexchangefragment.FinancialExchangeFragmentProvider
import com.example.calculator.ui.financialfragment.financialmainfragment.FinancialMainFragmnetProvider
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