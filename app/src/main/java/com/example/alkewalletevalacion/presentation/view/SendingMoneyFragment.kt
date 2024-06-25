package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.data.network.response.TransactionRequest
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import com.example.alkewalletevalacion.databinding.FragmentSendingMoneyBinding
import com.example.alkewalletevalacion.domain.usecases.CreateTransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.TransactionUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.SendingMoneyViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SendingMoneyFragment : Fragment() {

    private var _binding: FragmentSendingMoneyBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SendingMoneyViewModel
    private lateinit var transactionAdapter: TransactionAdapter // Asegúrate de tener el adaptador de transacciones

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
        val createTransactionUseCase = CreateTransactionUseCase(authService)
        val transactionUseCase = TransactionUseCase(authService)
        viewModel = ViewModelProvider(this, SendingMoneyViewModelFactory(userListUseCase, createTransactionUseCase, transactionUseCase)).get(SendingMoneyViewModel::class.java)

        setupObservers()

        binding.sendingMoney.setOnClickListener {
            handleSendMoney()
        }

        if (savedInstanceState == null) {
            viewModel.fetchUsuariosList()
        }
    }

    private fun setupObservers() {
        viewModel.usuariosList.observe(viewLifecycleOwner, { usuarios ->
            usuarios?.let {
                val userNames = it.map { user -> "${user.firstName} ${user.lastName}" }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, userNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }
        })

        viewModel.transactionResult.observe(viewLifecycleOwner, { success ->
            if (success) {
                Toast.makeText(requireContext(), "Transferencia realizada con éxito", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "Error al realizar la transferencia", Toast.LENGTH_SHORT).show()
            }
        })

        // Observa las transacciones actualizadas
        viewModel.transactions.observe(viewLifecycleOwner, { transactionList ->
            transactionList?.let {
                transactionAdapter.updateTransactions(it)
                Log.d("SendingMoneyFragment", "transactions LiveData Observer - TransactionResponse List: $transactionList")
            }
        })
    }

    private fun handleSendMoney() {
        val selectedUser = viewModel.usuariosList.value?.get(binding.spinner.selectedItemPosition)
        if (selectedUser != null) {
            val amount = binding.dinero.text.toString().toIntOrNull()
            val concept = binding.motivo.text.toString()
            if (amount != null && concept.isNotEmpty()) {
                val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                val transactionRequest = TransactionRequest(
                    amount = amount,
                    concept = concept,
                    date = date,
                    type = "payment",
                    accountId = 2262, // Reemplaza con el ID de la cuenta del usuario logueado
                    userId = 3365, // Reemplaza con el ID del usuario logueado
                    toAccountId = selectedUser.id // ID de la cuenta del usuario seleccionado
                )
                viewModel.createTransaction(transactionRequest)
            } else {
                Toast.makeText(requireContext(), "Por favor, introduce una cantidad y un concepto válidos", Toast.LENGTH_SHORT).show()
            }
        }
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