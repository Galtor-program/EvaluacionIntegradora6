package com.example.alkewalletevalacion.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.network.response.TransactionResponse
import com.example.alkewalletevalacion.domain.utils.GlobalUserList
import com.squareup.picasso.Picasso

class TransactionAdapter(private var transactions: List<TransactionResponse>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    // ViewHolder del adaptador
    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transaccionTxt: TextView = itemView.findViewById(R.id.transaccionTxt)
        val dateTxT: TextView = itemView.findViewById(R.id.dateTxT)
        val nombreUser: TextView = itemView.findViewById(R.id.nombreUser)
        val img : ImageView = itemView.findViewById(R.id.avatar1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.transaccionTxt.text = transaction.amount
        holder.dateTxT.text = transaction.date
        Picasso.get()
            .load("https://avatar.iran.liara.run/public")
            .resize(80,80)
            .centerCrop()
            .into(holder.img)

        // Buscar el nombre del usuario
        val user = GlobalUserList.getUserById(transaction.userId)
        if (user != null) {
            holder.nombreUser.text = "${user.firstName}"
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