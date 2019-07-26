package com.example.calculator.presentation.financialfragment.financialmainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.R
import com.example.calculator.presentation.base.BaseFragment
import com.example.calculator.databinding.FinancialMainFragmentBinding
import com.example.calculator.utils.navigation.fragmentrouter.FragmentRouter
import com.example.calculator.utils.navigation.fragmentrouter.Screens
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.financial_main_fragment.*

class FinancialMainFragment : BaseFragment<FinancialMainFragmentViewModel, FinancialMainFragmentBinding>() {
    private var fragmentRouter: FragmentRouter = FragmentRouter()

    override fun getViewModel(): Class<FinancialMainFragmentViewModel> {
        return FinancialMainFragmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.financial_main_fragment
    }

    override fun onResume() {
        super.onResume()
        val financialTabLayout : TabLayout = tabLayout
        fragmentRouter.navigateTo(Screens.FRAGMENTS.FINANCIAL_CONVERSION_FRAGMENT)
        financialTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> fragmentRouter.replace(Screens.FRAGMENTS.FINANCIAL_CONVERSION_FRAGMENT)
                    1 ->fragmentRouter.replace(Screens.FRAGMENTS.FINANCIAL_EXCHANGE_FRAGMENT)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        financialTabLayout.getTabAt(1)?.select()
        financialTabLayout.getTabAt(0)?.select()

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding?.lifecycleOwner = this
        dataBinding?.viewmodel = viewModel
        fragmentRouter.initRouter(fragmentManager!!, R.id.financialMain, ::onFinishActivity)
        return dataBinding?.root
    }

    private fun onFinishActivity(){
    }
}