package com.example.calculator.ui.financialfragment.financialexchangefragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FinancialExchangeFragmentProvider {
    @ContributesAndroidInjector
    abstract fun contributeFinancialExchangeFragment() : FinancialExchangeFragment
}