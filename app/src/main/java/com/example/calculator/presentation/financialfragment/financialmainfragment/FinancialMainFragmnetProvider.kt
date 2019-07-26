package com.example.calculator.presentation.financialfragment.financialmainfragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FinancialMainFragmnetProvider{

    @ContributesAndroidInjector
    abstract fun contributeFinancialMainFragemnt() : FinancialMainFragment
}