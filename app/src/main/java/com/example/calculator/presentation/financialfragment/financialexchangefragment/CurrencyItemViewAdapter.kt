package com.example.calculator.presentation.financialfragment.financialexchangefragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.R
import com.example.calculator.databinding.CurrencyItemBinding
import com.example.calculator.presentation.models.CurrencyModelItem
import javax.inject.Inject

class CurrencyItemViewAdapter @Inject constructor(private var currencies: List<CurrencyModelItem>) :
    RecyclerView.Adapter<CurrencyItemViewAdapter.CurrencyItemViewHolder>() {

    override fun getItemCount(): Int {
        return currencies.size
    }

    override fun onBindViewHolder(holder: CurrencyItemViewHolder, position: Int) {
        val currencyItemModelItem = currencies[position]
        holder.setViewModel(CurrencyItemViewModel(currencyItemModelItem))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return CurrencyItemViewHolder(view)
    }

    fun updateData(){
        notifyDataSetChanged()
    }
    class CurrencyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: CurrencyItemBinding? = null

        init {
            bind()
        }

        fun bind() {
            binding = androidx.databinding.DataBindingUtil.bind(itemView)
        }

        fun setViewModel(viewModel: CurrencyItemViewModel) {
            binding?.viewmodel = viewModel
        }
    }
}