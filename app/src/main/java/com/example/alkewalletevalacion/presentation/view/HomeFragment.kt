package com.example.alkewalletevalacion.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.local.UsuariosAdapter
import com.example.alkewalletevalacion.data.local.UsuariosDataSet
import com.example.alkewalletevalacion.databinding.FragmentHomeBinding
import com.example.alkewalletevalacion.domain.usecases.UsuariosListUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.HomeViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.HomeViewModelFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var usuariosAdapter: UsuariosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Creacion de la instancia del viewModel
         */
        val usuariosDataSet = UsuariosDataSet()
        val usuariosListUseCase = UsuariosListUseCase(usuariosDataSet)
        val viewModelFactory = HomeViewModelFactory(usuariosListUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        /**
         * Se configura el adaptador
         */
        usuariosAdapter = UsuariosAdapter(emptyList())
        binding.recyclerUsers.apply {
            adapter = usuariosAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.usuariosList.observe(viewLifecycleOwner, Observer { usuarios ->
            usuariosAdapter.usuarios = usuarios
            usuariosAdapter.notifyDataSetChanged()
        })

        /**
         * aca pedimos la lista de usuarios
         */
        viewModel.fetchUsuariosList()

        /**
         * aca navegamos hacia el fragmento del perfil
         */
        binding.alPerfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }

}