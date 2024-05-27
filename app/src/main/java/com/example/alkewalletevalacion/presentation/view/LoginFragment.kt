package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.databinding.FragmentLoginBinding
import com.example.alkewalletevalacion.presentation.viewmodel.LoginViewModel


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding  get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * ViewModelProvider obtiene instancia del LoginViewModel
         */
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        /**
         * Observa si se debe navegar al SignUp, cuando este emite un evento
         * se ejecuta el codigo de navegacion hacia sigupFragment2
         */
        viewModel.navigateToSignUp.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment2)
        })
        /**
         * Observa si se debe navegar al LoginPage, cuando este emite un evento
         * se ejecuta el codigo de navegacion hacia pageLogFragment
         */
        viewModel.navigateToPageLog.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_loginFragment_to_pageLogFragment)
        })

        /**
         * Maneja el evento del click en CrearCuentaNueva
         * para entrar al fragmento de registro de nuevo usuario
         */
        binding.CrearCuentaNueva.setOnClickListener {
            viewModel.onSingUpClicked()
        }
        /**
         * Maneja el evento del click en onAlreadHAveAccountClicked
         * para entrar al login ya que contamos con una cuenta.
         */
        binding.yaTienesCuenta.setOnClickListener {
            viewModel.onAlreadyHaveAccountClicked()
        }
    }

}