package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.databinding.FragmentPageLogBinding
import com.example.alkewalletevalacion.domain.usecases.AuthUseCase
import com.example.alkewalletevalacion.presentation.viewmodel.PageLogViewModel

/**
 * Pagina del fragmento de Logueo
 * Se utilizaron Logs para ver como viajaban los datos entre los fragmentos.
 */
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
        /**
         * Aca preparamos la instancia para la autenticacion del usuario
         */
        val authUseCase = AuthUseCase()

        /**
         * Inicializamos el Viewmodel
         */
        viewModel = ViewModelProvider(this, PageLogViewModel.Factory(requireActivity().application, authUseCase))[PageLogViewModel::class.java]

        /**
         * Observer para la navegacion hacia la pantalla de registro
         */
        viewModel.navigateToSignUp.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_pageLogFragment_to_signupFragment)
        })
        /**
         * Observer para la navegacion hacia la pantalla de Home
         */
        viewModel.navigationToHome.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_pageLogFragment_to_homeFragment)
        })

        binding.buttonLogin.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.passTXT.text.toString()

            Log.d("PageLogFragment", "Email: $email, Password: $password")
            /**
             * Llamamos al metodo para inciiar sesion.
             */
            viewModel.onLoginClick(email, password)
        }
        /**
         * Si no tenemos cuenta aca podemos ir a crear una.
         */
        binding.crearCuentaTxt.setOnClickListener {
            viewModel.onSignUpClicked()
        }
    }
}