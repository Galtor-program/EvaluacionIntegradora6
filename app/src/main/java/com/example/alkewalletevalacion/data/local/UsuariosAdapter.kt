package com.example.alkewalletevalacion.data.local

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletevalacion.data.model.Usuarios
import com.example.alkewalletevalacion.databinding.UserItemBinding


/**
 * Adaptador para el RecyclerView que mostrara
 * la lista lista de objetos Usuarios
 */
class UsuariosAdapter(var usuarios: List<Usuarios>) : RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder>() {
    /**
     * Creador de nuevas instancias del Contenedor
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return UsuarioViewHolder(binding)
    }

    /**
     * Aca pasamos el objeto Usuario como parametro
     * este metodo actualiza las vistas dentro del contenedor con los datos
     * del usuario
     */
    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.bind(usuario)
    }

    /**
     * Número Total de la lista de Usuarios.
     */
    override fun getItemCount(): Int {
        return usuarios.size
    }

    /**
     * Contenedor de elementos de la lista en el RecycleView
     * UserItemBinding representa el user_item.xml
     */
    inner class UsuarioViewHolder(private val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Función para actualizar las vistas dentro del contenedor
         * recibe el objeto usuario que contiene los datos del usuario para
         * mostrarlos en el contenedor.
         */
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