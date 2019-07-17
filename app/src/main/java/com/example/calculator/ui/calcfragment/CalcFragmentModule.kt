package com.example.calculator.ui.calcfragment

import androidx.lifecycle.ViewModelProvider
import com.example.calculator.models.HistoryModelItem
import com.example.calculator.utils.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CalcFragmentModule{

    @Provides
    fun provideHistoryViewAdapter(history : MutableList<HistoryModelItem>) : HistoryViewAdapter{
        return HistoryViewAdapter(history)
    }
}