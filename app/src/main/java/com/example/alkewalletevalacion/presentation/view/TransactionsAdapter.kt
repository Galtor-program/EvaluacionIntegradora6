package com.example.alkewalletevalacion.presentation.view

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.network.response.TransactionResponse

class TransactionsAdapter(private var transactions: List<TransactionResponse>) : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amount: TextView = view.findViewById(R.id.transaccionTxt)
        val date: TextView = view.findViewById(R.id.dateTxT)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.amount.text = transaction.amount.toString()
        holder.date.text = transaction.date

    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun updateData(newTransactions: List<TransactionResponse>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }
}