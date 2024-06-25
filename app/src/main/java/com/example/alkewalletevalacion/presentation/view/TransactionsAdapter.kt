package com.example.alkewalletevalacion.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.domain.utils.GlobalUserList

class TransactionAdapter(private var transactions: List<TransactionResponse>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transaccionTxt: TextView = itemView.findViewById(R.id.transaccionTxt)
        val dateTxT: TextView = itemView.findViewById(R.id.dateTxT)
        val nombreUser: TextView = itemView.findViewById(R.id.nombreUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.transaccionTxt.text = transaction.amount.toString()
        holder.dateTxT.text = transaction.date

        // Buscar el nombre del usuario
        val user = GlobalUserList.getUserById(transaction.userId)
        if (user != null) {
            holder.nombreUser.text = "${user.firstName} ${user.lastName}"
        } else {
            holder.nombreUser.text = "Unknown User"
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun updateTransactions(newTransactions: List<TransactionResponse>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }
}