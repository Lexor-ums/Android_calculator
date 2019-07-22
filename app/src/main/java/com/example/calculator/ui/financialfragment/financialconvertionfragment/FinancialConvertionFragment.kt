package com.example.calculator.ui.financialfragment.financialconvertionfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.calculator.R
import com.example.calculator.core.BaseFragment
import com.example.calculator.databinding.FinancialCalcFragmentBinding
import kotlinx.android.synthetic.main.financial_calc_fragment.*

class FinancialConvertionFragment : BaseFragment<FinancialConversionFragmentViewModel, FinancialCalcFragmentBinding>() {
    private var targetCurrentPos = 1
    private var sourceCurrentPos = 0
    override fun getViewModel(): Class<FinancialConversionFragmentViewModel> {
        return FinancialConversionFragmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.financial_calc_fragment
    }

    override fun onResume() {
        super.onResume()
        setupSpinners()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding?.lifecycleOwner = this
        dataBinding?.viewmodel = viewModel
        viewModel?.targetCashChangeEvent?.observe(this, androidx.lifecycle.Observer {

        })
        return dataBinding?.root
    }

    companion object {
        fun getInstance(): Fragment {
            return FinancialConvertionFragment()
        }
    }

    private fun setupSpinners() {
        ArrayAdapter.createFromResource(
            this.context,
            R.array.money_symbols,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSource.adapter = adapter
            spinnerTarget.adapter = adapter
        }
        spinnerSource.setSelection(0)
        spinnerTarget.setSelection(1)
        spinnerTarget.setOnTouchListener { _, _ ->
            targetCurrentPos = spinnerTarget.selectedItemPosition
            false
        }
        spinnerSource.setOnTouchListener { _, _ ->
            sourceCurrentPos = spinnerSource.selectedItemPosition
            false
        }
        spinnerTarget.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                println("selected target $position")
                if(spinnerSource.selectedItemPosition == position)
                    spinnerSource.setSelection(targetCurrentPos)
                viewModel?.getPrice(-1 , position)
            }
        }
        spinnerSource.onItemSelectedListener  = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                println("selected source $position")
                if(spinnerTarget.selectedItemPosition == position)
                    spinnerTarget.setSelection(sourceCurrentPos)
                viewModel?.getPrice(position, -1)
            }
        }
    }
}