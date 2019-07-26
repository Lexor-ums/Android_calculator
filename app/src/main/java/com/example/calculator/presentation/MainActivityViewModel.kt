package com.example.calculator.presentation

import androidx.lifecycle.ViewModel
import com.example.calculator.R
import com.example.calculator.utils.navigation.fragmentrouter.FragmentRouter
import com.example.calculator.utils.navigation.fragmentrouter.Screens
import kotlinx.android.synthetic.main.content_main_layout.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor() : ViewModel() {
    private var fragmentRouter: FragmentRouter = FragmentRouter()
    fun setFragmentRouter(fragmentRouter: FragmentRouter){
        this.fragmentRouter = fragmentRouter
        this.fragmentRouter.navigateTo(Screens.FRAGMENTS.CALC_FRAGMENT)
    }

    fun switchFragment(id: Int) {
        when (id) {
            R.id.nav_financial ->
                fragmentRouter.replace(Screens.FRAGMENTS.FINANCIAL_MAIN_FRAGMENT)
            R.id.nav_simple ->
                fragmentRouter.replace(Screens.FRAGMENTS.CALC_FRAGMENT)
        }
    }

}