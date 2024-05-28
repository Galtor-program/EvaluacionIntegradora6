package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.databinding.FragmentSignupBinding
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.SignUpViewModel
import com.example.alkewalletevalacion.presentation.viewmodel.SignUpViewModelFactory


class SignupFragment : Fragment() {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Preparamos la instancia del AuthUseCase
         */
        val authUseCase = AuthUseCase()
        val signUpViewModelFactory = SignUpViewModelFactory(authUseCase)
        /**
         * Iniciamos el ViewModel utilizando el Factory
         */
        viewModel = ViewModelProvider(this, signUpViewModelFactory)[SignUpViewModel::class.java]
        /**
         * Registro del usuario
         */
        binding.buttonRegistrar.setOnClickListener {
            val nombre = binding.ingresarNombreTxt.text.toString()
            val apellido = binding.ingresarApellidoTxt.text.toString()
            val email = binding.ingresarEmailTxt.text.toString()
            val password = binding.ingresarPassTxt.text.toString()
            val confirmPassword = binding.reingresaPassTxt.text.toString()
            /**
             * Acá registramos un nuevo usuario
             */
            viewModel.registerUser(requireContext(), nombre, apellido, email, password, confirmPassword)
        }
        /**
         * Boton para ir hacia la pagina de Logueo ya que indicamos que contamos con la cuenta
         */

        binding.haciaElLoginTxt.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_pageLogFragment)
        }

        /**
         * Boton para ir a la pagina de Logueo despues de crear la cuenta.
          */
        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_signupFragment_to_pageLogFragment)
        })
    }
}