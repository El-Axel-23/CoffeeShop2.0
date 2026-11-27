package com.example.coffeeshop20.Presentador.AxelPresenter

import com.example.coffeeshop20.Modelo.AxelModels.PreguntaFrecuente
import com.example.coffeeshop20.Modelo.AxelModels.RespuestaApi
import com.example.coffeeshop20.Redes.RetrofitClient
import com.example.coffeeshop20.Vista.AxelActivity.clsAyudaActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class clsAyudaPresenter(private val view: clsAyudaActivity) {

    fun cargarPreguntas() {
        view.mostrarCargando()

        RetrofitClient.api.obtenerPreguntas().enqueue(object : Callback<RespuestaApi<List<PreguntaFrecuente>>> {

            override fun onResponse(call: Call<RespuestaApi<List<PreguntaFrecuente>>>, response: Response<RespuestaApi<List<PreguntaFrecuente>>>) {
                view.ocultarCargando()
                if (response.isSuccessful) {
                    procesarRespuesta(response.body())
                } else {
                    view.mostrarError("Error del servidor: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RespuestaApi<List<PreguntaFrecuente>>>, t: Throwable) {
                view.ocultarCargando()
                view.mostrarError("Sin conexión. Revisa tu internet")
            }
        })
    }

    fun procesarRespuesta(respuesta: RespuestaApi<List<PreguntaFrecuente>>?) {
        if (respuesta == null) {
            view.mostrarError("No se recibió respuesta")
        } else if (respuesta.status != "success") {
            view.mostrarError(respuesta.message)
        } else if (respuesta.data == null || respuesta.data.isEmpty()) {
            view.mostrarError("No hay preguntas disponibles")
        } else {
            view.mostrarPreguntas(respuesta.data)
        }
    }
}