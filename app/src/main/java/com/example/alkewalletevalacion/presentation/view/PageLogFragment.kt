package com.example.alkewalletevalacion.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alkewalletevalacion.R
import com.example.alkewalletevalacion.databinding.FragmentPageLogBinding
import com.example.alkewalletevalacion.domain.usecases.LoginCase
import com.example.alkewalletevalacion.presentation.viewmodel.PageLogViewModel

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
        viewModel = ViewModelProvider(this)[PageLogViewModel::class.java]
        viewModel.navigateToSignUp.observe(viewLifecycleOwner, Observer{
            findNavController().navigate(R.id.action_pageLogFragment_to_signupFragment)
        })


        binding.crearCuentaTxt.setOnClickListener{
            viewModel.onSignUpClicked()
        }

        binding.buttonLogin.setOnClickListener{
            viewModel.onLoginClick()
        }


        }


    }
