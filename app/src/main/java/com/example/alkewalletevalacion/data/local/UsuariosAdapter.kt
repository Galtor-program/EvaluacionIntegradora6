package com.example.alkewalletevalacion.data.local

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletevalacion.data.model.Usuarios
import com.example.alkewalletevalacion.databinding.UserItemBinding

class UsuariosAdapter(var usuarios: List<Usuarios>) : RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return UsuarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.bind(usuario)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    inner class UsuarioViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(usuario: Usuarios) {
            binding.apply {
                nombreUser.text = usuario.nombre
                dateTxT.text =  usuario.fecha
                transaccionTxt.text = usuario.saldo
                imageView.setImageResource(usuario.imagen)
            }
        }
    }
}