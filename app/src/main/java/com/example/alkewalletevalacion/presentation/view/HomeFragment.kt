package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.alkewalletevalacion.databinding.FragmentHomeBinding
import com.example.alkewalletevalacion.domain.usecases.UserInfoUseCase
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import com.example.alkewalletevalacion.domain.usecases.AccountInfoUseCase
import com.example.alkewalletevalacion.domain.usecases.UserListUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.HomeViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.HomeViewModelFactory
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var usuariosAdapter: UsuariosAdapter

    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authService = RetrofitHelper.getAuthService(requireContext())
        val userInfoUseCase = UserInfoUseCase(authService)
        val accountInfoUseCase = AccountInfoUseCase(authService)
        val userListUseCase = UserListUseCase(authService)
        val viewModelFactory = HomeViewModelFactory(userInfoUseCase, accountInfoUseCase, userListUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.userInfo.observe(viewLifecycleOwner, Observer { userInfo ->
            userInfo?.let {
                binding.nombreUsuario.text = "Bienvenido, ${it.firstName}!"
            }
        })

        viewModel.accountInfo.observe(viewLifecycleOwner, Observer { accountInfoList ->
            accountInfoList?.let {
                if (it.isNotEmpty()) {
                    val accountInfo = it[0] // Muestra el primer elemento de la lista, ajusta según necesites
                    binding.cuentaUsuario.text = "${accountInfo.money}"
                    Log.d(TAG, "accountInfo LiveData Observer - AccountResponse List: $accountInfoList")
                }
            }
        })
        viewModel.userList.observe(viewLifecycleOwner, Observer { userList ->
            userList?.let {
                Log.d("HomeFragment", "User List: $it")
                it.forEach { user ->
                    Log.d("HomeFragment", "User: $user")
                }
                usuariosAdapter.updateUsers(it)
            }
        })

        // Configura el adaptador del RecyclerView
        usuariosAdapter = UsuariosAdapter(emptyList())
        binding.recyclerUsers.apply {
            adapter = usuariosAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.fetchUserInfo()
        viewModel.fetchUserList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}