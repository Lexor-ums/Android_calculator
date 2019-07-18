package com.example.calculator.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.dagger.ViewModelKey
import com.example.calculator.ui.MainActivityViewModel
import com.example.calculator.ui.calcfragment.CalcFragmentViewModel
import com.example.calculator.ui.financialfragment.financialconvertionfragment.FinancialConversionFragmentViewModel
import com.example.calculator.ui.financialfragment.financialconvertionfragment.FinancialConvertionFragment
import com.example.calculator.ui.financialfragment.financialexchangefragment.FinancialExchangeFragmentViewModel
import com.example.calculator.ui.financialfragment.financialmainfragment.FinancialMainFragmentViewModel
import com.example.calculator.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(view : MainActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CalcFragmentViewModel::class)
    abstract fun bindCalcFragmentViewModel(view : CalcFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FinancialMainFragmentViewModel::class)
    abstract fun bindFinancialMainFragmentViewModel(view : FinancialMainFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FinancialConversionFragmentViewModel::class)
    abstract fun bindFinancialConversionFragmentViewModel(view : FinancialConversionFragmentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FinancialExchangeFragmentViewModel::class)
    abstract fun bindFinancialExchangeFragmentViewModel(view : FinancialExchangeFragmentViewModel) : ViewModel


    @Binds
    abstract fun bindViewModuleFactory(viewModuleFactory: ViewModelFactory) : ViewModelProvider.Factory
}