package com.example.calculator.ui.calcfragment
import androidx.databinding.BaseObservable
import com.example.calculator.data.models.HistoryModelItem

class HistoryViewItemModel(private val item : HistoryModelItem) : BaseObservable() {
    /**
     * текстовое представление произщведенной операции
     * @param isFinished - признак завершения серии вычислений
     */
    fun getOperation(isFinished : Boolean)  :String{

        return if (!isFinished){
            "${item.operation} ${item.rightVal}"
        }
        else{
            "${item.operation} ${item.rightVal} \n ----------------------------------------------------------------"
        }
    }

    /**
     * предоставляет конечный результат
     */
    fun getFullExpression() : String{
        return "= ${item.result}"
    }
}