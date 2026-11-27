package com.example.coffeeshop20.Presentador.AxelPresenter

import com.example.coffeeshop20.Modelo.AxelModels.RespuestaApi
import com.example.coffeeshop20.Modelo.AxelModels.Usuario
import com.example.coffeeshop20.Redes.RetrofitClient
import com.example.coffeeshop20.Vista.AxelActivity.clsPerfilActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class clsPerfilPresenter(private val view: clsPerfilActivity) {

    fun cargarPerfil() {
        view.mostrarCargando()

        RetrofitClient.api.obtenerPerfil().enqueue(object : Callback<RespuestaApi<Usuario>> {

            override fun onResponse(call: Call<RespuestaApi<Usuario>>, response: Response<RespuestaApi<Usuario>>) {
                view.ocultarCargando()
                if (response.isSuccessful) {
                    procesarRespuesta(response.body())
                } else {
                    view.mostrarError("Error del servidor: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RespuestaApi<Usuario>>, t: Throwable) {
                view.ocultarCargando()
                view.mostrarError("Sin conexión. Revisa tu internet")
            }
        })
    }

    fun procesarRespuesta(respuesta: RespuestaApi<Usuario>?) {
        if (respuesta == null) {
            view.mostrarError("No se recibió respuesta")
        } else if (respuesta.status != "success") {
            view.mostrarError(respuesta.message)
        } else if (respuesta.data == null) {
            view.mostrarError("No se encontró el usuario")
        } else {
            view.mostrarPerfil(respuesta.data, obtenerIniciales(respuesta.data.nombre_completo))
        }
    }

    companion object {
        fun obtenerIniciales(nombre: String): String {
            val partes = nombre.trim().split(" ").filter { it.isNotEmpty() }
            if (partes.size >= 2) {
                return (partes[0].take(1) + partes[partes.size - 1].take(1)).uppercase()
            }
            return nombre.take(2).uppercase()
        }
    }
}