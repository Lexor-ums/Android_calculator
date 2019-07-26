package com.example.calculator.presentation.calcfragment

import com.example.calculator.presentation.models.HistoryModelItem
import dagger.Module
import dagger.Provides

@Module
class CalcFragmentModule{

    @Provides
    fun provideHistoryViewAdapter(history : MutableList<HistoryModelItem>) : HistoryViewAdapter{
        return HistoryViewAdapter(history)
    }
}