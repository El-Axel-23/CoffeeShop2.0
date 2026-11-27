package com.example.coffeeshop20.Presentador.AxelPresenter

import com.example.coffeeshop20.Modelo.AxelModels.Cafeteria
import com.example.coffeeshop20.Modelo.AxelModels.RespuestaApi
import com.example.coffeeshop20.Redes.RetrofitClient
import com.example.coffeeshop20.Vista.AxelActivity.clsUbicacionActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class clsUbicacionPresenter(private val view: clsUbicacionActivity) {

    fun cargarCafeteria() {
        view.mostrarCargando()

        RetrofitClient.api.obtenerCafeteria().enqueue(object : Callback<RespuestaApi<List<Cafeteria>>> {

            override fun onResponse(call: Call<RespuestaApi<List<Cafeteria>>>, response: Response<RespuestaApi<List<Cafeteria>>>) {
                view.ocultarCargando()
                if (response.isSuccessful) {
                    procesarRespuesta(response.body())
                } else {
                    view.mostrarError("Error del servidor: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RespuestaApi<List<Cafeteria>>>, t: Throwable) {
                view.ocultarCargando()
                view.mostrarError("Sin conexión. Revisa tu internet")
            }
        })
    }

    fun procesarRespuesta(respuesta: RespuestaApi<List<Cafeteria>>?) {
        if (respuesta == null) {
            view.mostrarError("No se recibió respuesta")
        } else if (respuesta.status != "success") {
            view.mostrarError(respuesta.message)
        } else if (respuesta.data == null || respuesta.data.isEmpty()) {
            view.mostrarError("No hay información de la cafetería")
        } else {
            val cafeteria = respuesta.data[0]
            view.mostrarCafeteria(cafeteria, armarHorario(cafeteria))
        }
    }

    companion object {
        fun armarHorario(c: Cafeteria): String {
            return c.dias_atencion + ": " + c.hora_apertura.take(5) + " a " + c.hora_cierre.take(5)
        }
    }
}