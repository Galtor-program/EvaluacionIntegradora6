package com.example.alkewalletevalacion.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.network.response.UserListResponse

class UserAdapter(private var users: List<UserListResponse>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder del adaptador
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTextView: TextView = itemView.findViewById(R.id.nombreUser)
        // Otros views según sea necesario
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.userNameTextView.text = "${user.firstName} ${user.lastName}"
        // Configura otros datos según sea necesario
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // Método para actualizar la lista de usuarios en el adaptador
    fun updateUsers(newUsers: List<UserListResponse>) {
        users = newUsers
        notifyDataSetChanged()
    }
}