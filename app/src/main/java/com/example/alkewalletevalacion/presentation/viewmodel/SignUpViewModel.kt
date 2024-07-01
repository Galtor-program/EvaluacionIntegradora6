package com.example.alkewalletevalacion.presentation.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alkewalletevalacion.data.network.response.NewAccountRequest
import com.example.alkewalletevalacion.data.network.response.NewUserRequest
import com.example.alkewalletevalacion.domain.usecases.RegisterUserUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SignUpViewModel(private val registerUserUseCase: RegisterUserUseCase) : ViewModel() {

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> get() = _navigateToLogin

    private var accessToken: String? = null // Almacena el token de autorización

    fun setAccessToken(token: String) {
        accessToken = token
    }

    fun registerUser(context: Context, firstName: String, lastName: String, email: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        val newUserRequest = NewUserRequest(
            first_name = firstName,
            last_name = lastName,
            email = email,
            password = password,
            roleId = 1, // Asegúrate de usar roleId correcto
            points = 50
        )

        viewModelScope.launch {
            try {
                // Registrar al usuario
                val newUserResponse = registerUserUseCase.execute(newUserRequest)

                // Crear la cuenta asociada al usuario
                val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                val newAccountRequest = NewAccountRequest(
                    creationDate = date,
                    money = 150000,
                    isBlocked = false,
                    userId = newUserResponse.id
                )

                // Añadir el token de autorización a la solicitud
                accessToken?.let { token ->
                    val result = registerUserUseCase.createAccountWithToken(newAccountRequest, token)
                    if (result) {
                        // Éxito al crear la cuenta
                        _navigateToLogin.postValue(true)
                    } else {
                        Toast.makeText(context, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
                    }
                } ?: run {
                    Toast.makeText(context, "Token de autorización no disponible", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Error al registrar usuario: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}