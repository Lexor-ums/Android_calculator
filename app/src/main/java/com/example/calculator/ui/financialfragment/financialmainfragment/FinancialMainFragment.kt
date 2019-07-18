package com.example.calculator.ui.financialfragment.financialmainfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.R
import com.example.calculator.core.BaseFragment
import com.example.calculator.databinding.FinancialMainFragmentBinding
import com.example.calculator.ui.financialfragment.financialconvertionfragment.FinancialConvertionFragment
import com.example.calculator.ui.financialfragment.financialexchangefragment.FinancialExchangeFragment
import com.example.calculator.utils.FragmentUtils
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.financial_main_fragment.*

class FinancialMainFragment : BaseFragment<FinancialMainFragmentViewModel, FinancialMainFragmentBinding>() {


    override fun getViewModel(): Class<FinancialMainFragmentViewModel> {
        return FinancialMainFragmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.financial_main_fragment
    }

    override fun onResume() {
        super.onResume()
        val financialTabLayout : TabLayout = tabLayout
        financialTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                println("Tab selected ${tab.position}")
                when (tab.position) {
                    0 -> FragmentUtils.replaceFragment(
                        activity as AppCompatActivity,
                        FinancialConvertionFragment.getInstance(),
                        R.id.financialMain,
                        false
                    )
                    1 -> FragmentUtils.replaceFragment(
                        activity as AppCompatActivity,
                        FinancialExchangeFragment.getInstance(),
                        R.id.financialMain,
                        false
                    )
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
        return dataBinding?.root
    }

    companion object {
        fun getInstance(): FinancialMainFragment {
            return FinancialMainFragment()
        }
    }
}