package com.example.calculator.ui.calcfragment

import android.annotation.SuppressLint
import android.app.ActionBar
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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.databinding.CalcFragmentBinding
import com.example.calculator.operations.Operator
import com.example.calculator.operations.Solver
import kotlinx.android.synthetic.main.calc_fragment.*
import kotlinx.android.synthetic.main.calc_fragment.view.*
import kotlinx.android.synthetic.main.calc_fragment.view.historyView

class CalcFragment : Fragment() {
    private val viewModel by lazy { ViewModelProviders.of(this).get(CalcFragmentViewModel::class.java) }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding =
            DataBindingUtil.inflate<CalcFragmentBinding>(inflater, R.layout.calc_fragment, container, false)
        viewModel.setRecyler(layoutInflater.inflate(R.layout.calc_fragment, container, false).historyView)
        var view = dataBinding.root
        dataBinding.lifecycleOwner = this
        dataBinding.viewmodel =viewModel
        return view
    }
}