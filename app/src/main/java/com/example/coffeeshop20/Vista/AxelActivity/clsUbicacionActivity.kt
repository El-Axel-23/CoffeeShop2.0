package com.example.coffeeshop20.Vista.AxelActivity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshop20.Modelo.AxelModels.Cafeteria
import com.example.coffeeshop20.Presentador.AxelPresenter.clsUbicacionPresenter
import com.example.coffeeshop20.R

class clsUbicacionActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var tvNombre: TextView
    private lateinit var tvDireccion: TextView
    private lateinit var tvHorario: TextView
    private lateinit var presenter: clsUbicacionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicacion)

        progressBar = findViewById(R.id.progressBar)
        tvError = findViewById(R.id.tvError)
        tvNombre = findViewById(R.id.tvNombreCafeteria)
        tvDireccion = findViewById(R.id.tvDireccion)
        tvHorario = findViewById(R.id.tvHorario)

        findViewById<ImageView>(R.id.btnAtras).setOnClickListener {
            finish()
        }

        presenter = clsUbicacionPresenter(this)
        presenter.cargarCafeteria()
    }

    fun mostrarCargando() {
        progressBar.visibility = View.VISIBLE
        tvError.visibility = View.GONE
    }

    fun ocultarCargando() {
        progressBar.visibility = View.GONE
    }

    fun mostrarCafeteria(cafeteria: Cafeteria, horario: String) {
        tvNombre.text = cafeteria.nombre
        tvDireccion.text = cafeteria.direccion
        tvHorario.text = horario
    }

    fun mostrarError(mensaje: String) {
        tvError.text = mensaje
        tvError.visibility = View.VISIBLE
    }
}