package com.example.alkewalletevalacion.presentation.view

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import com.example.alkewalletevalacion.databinding.FragmentSendingMoneyBinding
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModelFactory

class SendingMoneyFragment : Fragment() {

    private var _binding: FragmentSendingMoneyBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SendingMoneyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendingMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        val authService = RetrofitHelper.getAuthService(requireContext())
        val userListUseCase = UserListUseCase(authService)
        viewModel = ViewModelProvider(this, SendingMoneyViewModelFactory(userListUseCase)).get(SendingMoneyViewModel::class.java)

        viewModel.usuariosList.observe(viewLifecycleOwner, { usuarios ->
            val userNames = usuarios.map { "${it.firstName} ${it.lastName}" }
            val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, userNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        })


        viewModel.fetchUsuariosList()
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}