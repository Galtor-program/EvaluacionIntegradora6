package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.network.response.User
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import com.example.alkewalletevalacion.databinding.FragmentSendingMoneyBinding
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModelFactory

class SendingMoneyFragment : Fragment() {

    private lateinit var viewModel: SendingMoneyViewModel
    private lateinit var spinnerAdapter: ArrayAdapter<User>
    private var _binding: FragmentSendingMoneyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendingMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authService = RetrofitHelper.getAuthService(requireContext())
        val viewModelFactory = SendingMoneyViewModelFactory(authService)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SendingMoneyViewModel::class.java)

        viewModel.users.observe(viewLifecycleOwner, Observer { users ->
            users?.let {
                spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = spinnerAdapter
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.fetchUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}