package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.network.response.TransferRequest
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import com.example.alkewalletevalacion.databinding.FragmentSendingMoneyBinding
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.TransferUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class SendingMoneyFragment : Fragment() {
    private lateinit var viewModel: SendingMoneyViewModel
    private lateinit var binding: FragmentSendingMoneyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendingMoneyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authService = RetrofitHelper.getAuthService(requireContext())
        val transferUseCase = TransferUseCase(authService)
        val userListUseCase = UserListUseCase(authService)
        val accountInfoUseCase = AccountInfoUseCase(authService)
        val viewModelFactory =
            SendingMoneyViewModelFactory(transferUseCase, userListUseCase, accountInfoUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SendingMoneyViewModel::class.java)

        setupSpinner()
        setupListeners()

        viewModel.usuariosList.observe(viewLifecycleOwner, Observer { userList ->
            userList?.let {
                val userNames = it.map { user -> "${user.firstName} ${user.lastName}" }
                val adapter =
                    ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerUsers.adapter = adapter
            }
        })

        viewModel.fetchUsuariosList()
        viewModel.fetchAccountInfo()
    }

    private fun setupSpinner() {
        binding.spinnerUsers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Acción cuando se selecciona un elemento
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Acción cuando no se selecciona nada
            }
        }
    }

    private fun setupListeners() {
        binding.enviarTansferencia.setOnClickListener {
            val amountText = binding.cantidadDinero.text.toString()
            val amount = amountText.toIntOrNull()
            val concept = binding.notaTransferencia.text.toString()
            val selectedUserIndex = binding.spinnerUsers.selectedItemPosition
            val selectedUser = viewModel.usuariosList.value?.getOrNull(selectedUserIndex)

            if (amount == null || amountText.isBlank()) {
                Log.e("SendingMoneyFragment", "Amount is invalid")
                return@setOnClickListener
            }

            if (selectedUser == null) {
                Log.e("SendingMoneyFragment", "Selected user is null or invalid")
                return@setOnClickListener
            }

            val accountId = viewModel.accountInfo.value?.firstOrNull()?.id
            if (accountId == null) {
                Log.e("SendingMoneyFragment", "Account id is null or invalid")
                return@setOnClickListener
            }

            val toAccountId = selectedUser.accountId ?: run {
                Log.e("SendingMoneyFragment", "Selected user account id is null")
                return@setOnClickListener
            }

            val transferRequest = TransferRequest(
                amount = amount,
                concept = concept,
                date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()),
                type = "payment",
                accountId = accountId,
                userId = selectedUser.id,
                to_account_id = toAccountId
            )

            viewModel.makeTransfer(transferRequest)

            // Navegar de regreso al HomeFragment
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }
}