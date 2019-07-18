package com.example.calculator.ui.financialfragment.financialconvertionfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.calculator.R
import com.example.calculator.core.BaseFragment
import com.example.calculator.databinding.FinancialCalcFragmentBinding

class FinancialConvertionFragment : BaseFragment<FinancialConversionFragmentViewModel, FinancialCalcFragmentBinding>() {
    override fun getViewModel(): Class<FinancialConversionFragmentViewModel> {
        return FinancialConversionFragmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.financial_calc_fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding?.lifecycleOwner = this
        dataBinding?.viewmodel = viewModel
        return dataBinding?.root
    }

    companion object{
        fun getInstance(): Fragment{
            return FinancialConvertionFragment()
        }
    }

}