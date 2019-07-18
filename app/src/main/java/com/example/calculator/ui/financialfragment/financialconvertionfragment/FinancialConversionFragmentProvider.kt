package com.example.calculator.ui.financialfragment.financialconvertionfragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FinancialConversionFragmentProvider {
    @ContributesAndroidInjector
    abstract fun contributeFinancialConversionFragent() : FinancialConvertionFragment
}