package com.example.calculator.ui.calcfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.databinding.HistoryItemBinding
import com.example.calculator.data.models.HistoryModelItem
import javax.inject.Inject

class HistoryViewAdapter @Inject constructor(private var history : MutableList<HistoryModelItem> ): RecyclerView.Adapter<HistoryViewAdapter.HistoryViewHolder>(){

    override fun getItemCount(): Int {
        return history.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyModelItem  = history[position]
        holder.setViewModel(HistoryViewItemModel(historyModelItem))
    }
    fun addItem(item : HistoryModelItem){
        history.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    fun clear(){
        history.clear()
        notifyDataSetChanged()
    }
    class HistoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private var binding : HistoryItemBinding? = null
        init {
            bind()
        }

        /**
         * привязка модели ко view
         */
        private fun bind(){
            binding = androidx.databinding.DataBindingUtil.bind(itemView)
        }

        /**
         * установка модели данных
         */
        fun setViewModel(viewModel : HistoryViewItemModel){
            binding?.viewModel = viewModel
        }
    }
}