package com.example.alkewalletevalacion.data.network.retrofit

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${getToken()}") // Asegúrate de obtener el token correctamente
            .build()
        return chain.proceed(request)
    }

    private fun getToken(): String? {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("accessToken", null)
    }
}