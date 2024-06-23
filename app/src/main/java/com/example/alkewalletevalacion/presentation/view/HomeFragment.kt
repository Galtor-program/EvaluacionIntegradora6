package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkewalletevalacion.R

import com.example.alkewalletevalacion.databinding.FragmentHomeBinding
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.TransactionsUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.HomeViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.HomeViewModelFactory
class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authService = RetrofitHelper.getAuthService(requireContext())
        val userInfoUseCase = UserInfoUseCase(authService)
        val accountInfoUseCase = AccountInfoUseCase(authService)
        val transactionsUseCase = TransactionsUseCase(authService)
        val viewModelFactory = HomeViewModelFactory(userInfoUseCase, accountInfoUseCase, transactionsUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        setupRecyclerView()
        setupObservers()

        viewModel.fetchUserInfo()
        viewModel.fetchAccountInfo()
        viewModel.fetchTransactions()
    }

    private fun setupRecyclerView() {
        binding.recyclerUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerUsers.adapter = TransactionsAdapter(emptyList())
    }

    private fun setupObservers() {
        viewModel.transactions.observe(viewLifecycleOwner, Observer { transactions ->
            transactions?.let {
                (binding.recyclerUsers.adapter as TransactionsAdapter).updateData(it)
            }
        })
    }
}