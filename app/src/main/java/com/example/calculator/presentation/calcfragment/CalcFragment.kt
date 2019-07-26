package com.example.calculator.presentation.calcfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator.R
import com.example.calculator.presentation.base.BaseFragment
import com.example.calculator.databinding.CalcFragmentBinding


class CalcFragment : BaseFragment<CalcFragmentViewModel, CalcFragmentBinding>() {
    override fun getViewModel(): Class<CalcFragmentViewModel> {
        return CalcFragmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.calc_fragment
    }

    override fun onResume() {
        super.onResume()
        dataBinding?.historyView?.let { viewModel?.setRecylcer(it) }
    }
    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding?.lifecycleOwner = this
        dataBinding?.viewmodel = viewModel
        return dataBinding?.root
    }
}