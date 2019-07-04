package com.example.calculator.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.calculator.R
import kotlinx.android.synthetic.main.result_fragment_layout.view.*

class ResultFragment : Fragment() {
    lateinit var historyTextView: TextView
    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.result_fragment_layout, container, false)
        historyTextView = view.historyField
        historyTextView.text = savedInstanceState?.getString("history")
        historyTextView.movementMethod = ScrollingMovementMethod()
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("history", historyTextView.text.toString())
    }

    fun onClear() {
        historyTextView.text = ""
    }

    /**
     * добавляет в последнюю строку поля с историей операций
     * значение к символу выполняемой операции
     * @param value числовое занчение
     * @return обновленное содержимое текстового поля
     */
    private fun replaceHistoryText(value: Number) {
        var content = historyTextView.text.split("\n").toMutableList()
        if (content.size > historyTextView.maxLines)
            content = content.slice(
                IntRange(
                    content.lastIndex - historyTextView.maxLines,
                    content.lastIndex
                )
            ).toMutableList()
        val newOperationText = content[content.lastIndex] + value.toString()
        content.removeAt(content.lastIndex)
        content.add(newOperationText)
        historyTextView.text = ""
        content.forEach { str ->
            historyTextView.append(str + "\n")
        }
    }

    fun showOperationResult(value: Number, result: Number, operationType: String) {
        replaceHistoryText(value)
        historyTextView.append("= $result")
        historyTextView.append("\n" + operationType)
    }

    fun calculationsComplited(value: Number) {
        replaceHistoryText(value)
        historyTextView.append("--------------------")


    }

    fun calculationsStarted(value: Number, operationType: String) {
        historyTextView.append("\n" + value.toString())
        historyTextView.append("\n" + operationType)
    }
}