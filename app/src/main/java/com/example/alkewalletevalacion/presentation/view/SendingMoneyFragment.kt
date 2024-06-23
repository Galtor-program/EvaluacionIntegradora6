package com.example.alkewalletevalacion.presentation.view

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
        val viewModelFactory = SendingMoneyViewModelFactory(transferUseCase, userListUseCase, accountInfoUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SendingMoneyViewModel::class.java)

        setupSpinner()
        setupListeners()

        viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->
            userList?.let {
                val userNames = it.map { user -> "${user.firstName} ${user.lastName}" }
                val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, userNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerUsers.adapter = adapter
            }
        })

        viewModel.fetchUserList()
        viewModel.fetchAccountInfo()
    }

    private fun setupSpinner() {
        binding.spinnerUsers.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Acción cuando se selecciona un elemento
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Acción cuando no se selecciona nada
            }
        }
    }

    private fun setupListeners() {
        binding.enviarTansferencia.setOnClickListener {
            val amount = binding.cantidadDinero.text.toString().toInt()
            val concept = binding.notaTransferencia.text.toString()
            val selectedUserIndex = binding.spinnerUsers.selectedItemPosition
            val selectedUser = viewModel.userList.value?.get(selectedUserIndex)
            val accountId = viewModel.accountInfo.value?.firstOrNull()?.id ?: return@setOnClickListener
            val userId = viewModel.accountInfo.value?.firstOrNull()?.userId ?: return@setOnClickListener

            val toAccountId = selectedUser?.id ?: return@setOnClickListener

            val transferRequest = TransferRequest(
                amount = amount,
                concept = concept,
                date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()),
                type = "payment",
                accountId = accountId,
                userId = userId,
                to_account_id = toAccountId
            )

            viewModel.depositOrTransfer(toAccountId, transferRequest)
        }
    }
}