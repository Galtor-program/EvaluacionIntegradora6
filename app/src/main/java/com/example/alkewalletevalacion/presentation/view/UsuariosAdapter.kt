package com.example.alkewalletevalacion.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletevalacion.data.network.response.UserListResponse
import com.example.alkewalletevalacion.databinding.UserItemBinding

class UsuariosAdapter(private var users: List<UserListResponse>) : RecyclerView.Adapter<UsuariosAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserListResponse) {
            //TODO arreglar el bindeo de usuarios
            binding.nombreUser.text = user.firstName
            binding.transaccionTxt.text = user.points.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun updateUsers(newUsers: List<UserListResponse>) {
        users = newUsers
        notifyDataSetChanged()
    }

}