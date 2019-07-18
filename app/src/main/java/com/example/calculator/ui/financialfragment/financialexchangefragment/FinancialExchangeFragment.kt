package com.example.calculator.ui.financialfragment.financialexchangefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculator.R
import com.example.calculator.core.BaseFragment
import com.example.calculator.databinding.FinancialExchangeRateFragmentBinding
import retrofit2.Retrofit
import javax.inject.Inject

class FinancialExchangeFragment :
    BaseFragment<FinancialExchangeFragmentViewModel, FinancialExchangeRateFragmentBinding>() {
    @Inject
    lateinit var retrofit: Retrofit
    override fun getViewModel(): Class<FinancialExchangeFragmentViewModel> {
        return FinancialExchangeFragmentViewModel::class.java
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