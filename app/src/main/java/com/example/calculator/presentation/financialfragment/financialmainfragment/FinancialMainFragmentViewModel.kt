package com.example.calculator.presentation.financialfragment.financialmainfragment

import androidx.lifecycle.ViewModel
import com.example.calculator.R
import com.example.calculator.utils.navigation.fragmentrouter.FragmentRouter
import com.example.calculator.utils.navigation.fragmentrouter.Screens
import javax.inject.Inject

class FinancialMainFragmentViewModel @Inject constructor(): ViewModel(){
    private var fragmentRouter: FragmentRouter = FragmentRouter()
    fun setFragmentRouter(fragmentRouter: FragmentRouter){
        this.fragmentRouter = fragmentRouter
    }

    fun switchFragment(position: Int) {
        when (position) {
            0 -> fragmentRouter.replace(Screens.FRAGMENTS.FINANCIAL_CONVERSION_FRAGMENT)
            1 -> fragmentRouter.replace(Screens.FRAGMENTS.FINANCIAL_EXCHANGE_FRAGMENT)
        }
    }
}