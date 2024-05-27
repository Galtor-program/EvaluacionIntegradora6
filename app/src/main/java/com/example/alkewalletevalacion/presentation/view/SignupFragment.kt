package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.databinding.FragmentSignupBinding
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase


class SignupFragment : Fragment() {

    private lateinit var authUseCase: AuthUseCase
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authUseCase = AuthUseCase()

        binding.buttonRegistrar.setOnClickListener {
            val nombre = binding.ingresarNombreTxt.text.toString()
            val apellido = binding.ingresarApellidoTxt.text.toString()
            val email = binding.ingresarEmailTxt.text.toString()
            val password = binding.ingresarPassTxt.text.toString()
            val confirmPassword = binding.reingresaPassTxt.text.toString()

            if (authUseCase.registerUser(
                    requireContext(),
                    nombre,
                    apellido,
                    email,
                    password,
                    confirmPassword
                )
            ) {

                findNavController().navigate(R.id.action_signupFragment_to_pageLogFragment)
            }
        }

        binding.haciaElLoginTxt.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_pageLogFragment)
        }
    }
}