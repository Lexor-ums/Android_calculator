package com.example.calculator.utils.navigation.fragmentrouter

import androidx.fragment.app.Fragment
import com.example.calculator.presentation.calcfragment.CalcFragment
import com.example.calculator.presentation.financialfragment.financialconvertionfragment.FinancialConvertionFragment
import com.example.calculator.presentation.financialfragment.financialexchangefragment.FinancialExchangeFragment
import com.example.calculator.presentation.financialfragment.financialmainfragment.FinancialMainFragment

object Screens {

    const val BUNDLE_KEY = "bundleKey"

    enum class FRAGMENTS(fragmentName: String) {
        CALC_FRAGMENT("CALC_FRAGMENT"),
        FINANCIAL_MAIN_FRAGMENT("FINANCIAL_MAIN_FRAGMENT"),
        FINANCIAL_CONVERSION_FRAGMENT("FINANCIAL_CONVERSION_FRAGMENT"),
        FINANCIAL_EXCHANGE_FRAGMENT("FINANCIAL_EXCHANGE_FRAGMENT")
    }

    fun createFragment(fragment: FRAGMENTS): Fragment = when (fragment) {
        Screens.FRAGMENTS.CALC_FRAGMENT -> CalcFragment()
        Screens.FRAGMENTS.FINANCIAL_MAIN_FRAGMENT -> FinancialMainFragment()
        Screens.FRAGMENTS.FINANCIAL_CONVERSION_FRAGMENT -> FinancialConvertionFragment()
        Screens.FRAGMENTS.FINANCIAL_EXCHANGE_FRAGMENT -> FinancialExchangeFragment()
    }

}