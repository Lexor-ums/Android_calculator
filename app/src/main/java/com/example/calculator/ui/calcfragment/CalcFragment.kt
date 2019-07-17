package com.example.calculator.ui.calcfragment

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.AppComponentFactory
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.core.BaseFragment
import com.example.calculator.databinding.CalcFragmentBinding
import com.example.calculator.operations.Operator
import com.example.calculator.operations.Solver
import com.example.calculator.utils.ViewModelFactory
import kotlinx.android.synthetic.main.calc_fragment.*
import kotlinx.android.synthetic.main.calc_fragment.view.*
import kotlinx.android.synthetic.main.calc_fragment.view.historyView
import javax.inject.Inject

class CalcFragment : BaseFragment<CalcFragmentViewModel, CalcFragmentBinding>() {
    override fun getViewModel(): Class<CalcFragmentViewModel> {
        return CalcFragmentViewModel::class.java
    }

    override fun getLayoutRes(): Int {
        return R.layout.calc_fragment
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding?.lifecycleOwner = this
        dataBinding?.viewmodel = viewModel
        return dataBinding?.root
    }
}