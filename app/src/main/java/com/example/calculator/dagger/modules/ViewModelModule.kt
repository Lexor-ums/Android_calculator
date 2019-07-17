package com.example.calculator.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.calculator.dagger.ViewModelKey
import com.example.calculator.ui.MainActivityViewModel
import com.example.calculator.ui.calcfragment.CalcFragmentViewModel
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
    abstract fun bindViewModuleFactory(viewModuleFactory: ViewModelFactory) : ViewModelProvider.Factory
}