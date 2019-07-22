package com.example.calculator.ui.calcfragment

import com.example.calculator.data.models.HistoryModelItem
import dagger.Module
import dagger.Provides

@Module
class CalcFragmentModule{

    @Provides
    fun provideHistoryViewAdapter(history : MutableList<HistoryModelItem>) : HistoryViewAdapter{
        return HistoryViewAdapter(history)
    }
}