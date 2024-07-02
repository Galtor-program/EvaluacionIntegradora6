package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.data.network.retrofit.RetrofitHelper
import com.example.alkewalletevalacion.databinding.FragmentPageLogBinding
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.PageLogViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class PageLogFragment : Fragment() {
    private lateinit var viewModel: PageLogViewModel
    private lateinit var binding: FragmentPageLogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Preparar la instancia para la autenticación del usuario
        val authUseCase = AuthUseCase(RetrofitHelper.getAuthService(requireContext()))

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this, PageLogViewModel.Factory(requireActivity().application, authUseCase)).get(PageLogViewModel::class.java)


       viewModel.navigateToSignUp.observe(viewLifecycleOwner, Observer {
         findNavController().navigate(R.id.action_pageLogFragment_to_signupFragment)
        })

        // Observer para la navegación hacia la pantalla de Home
        viewModel.navigationToHome.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_pageLogFragment_to_homeFragment)
        })

        // Click listener del botón de inicio de sesión
        binding.buttonLogin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.passTXT.text.toString()

            Log.d("PageLogFragment", "Email: $email, Password: $password")

            // Llamada al método para iniciar sesión
            viewModel.onLoginClick(email, password)
        }

        // Click listener para ir a la pantalla de registro
        //binding.crearCuentaTxt.setOnClickListener {
          //  viewModel.onSignUpClicked()
        }
    }


