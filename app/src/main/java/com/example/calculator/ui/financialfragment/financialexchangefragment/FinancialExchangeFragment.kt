package com.example.calculator.ui.financialfragment.financialexchangefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculator.R
import com.example.calculator.core.BaseFragment
import com.example.calculator.databinding.FinancialExchangeRateFragmentBinding


class FinancialExchangeFragment :
    BaseFragment<FinancialExchangeFragmentViewModel, FinancialExchangeRateFragmentBinding>() {
    override fun getViewModel(): Class<FinancialExchangeFragmentViewModel> {
        return FinancialExchangeFragmentViewModel::class.java
    }

    override fun onPause() {
        super.onPause()
        viewModel?.storeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.restoreData()
    }
    override fun getLayoutRes(): Int {
        return R.layout.financial_exchange_rate_fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding?.lifecycleOwner = this
        dataBinding?.viewmodel = viewModel
        return dataBinding?.root
    }

    companion object{
        fun getInstance() : Fragment {
            return FinancialExchangeFragment()
        }
    }
}