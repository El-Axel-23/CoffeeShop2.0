package com.example.coffeeshop20.Vista.AxelActivity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop20.Adaptador.AxelAdapter.PreguntaAdapter
import com.example.coffeeshop20.Modelo.AxelModels.PreguntaFrecuente
import com.example.coffeeshop20.Presentador.AxelPresenter.clsAyudaPresenter
import com.example.coffeeshop20.R

class clsAyudaActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView
    private lateinit var rvPreguntas: RecyclerView
    private lateinit var presenter: clsAyudaPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)

        progressBar = findViewById(R.id.progressBar)
        tvError = findViewById(R.id.tvError)
        rvPreguntas = findViewById(R.id.rvPreguntas)
        rvPreguntas.layoutManager = LinearLayoutManager(this)

        findViewById<ImageView>(R.id.btnAtras).setOnClickListener { finish() }

        presenter = clsAyudaPresenter(this)
        presenter.cargarPreguntas()
    }

    fun mostrarCargando() {
        progressBar.visibility = View.VISIBLE
        tvError.visibility = View.GONE
        rvPreguntas.visibility = View.GONE
    }

    fun ocultarCargando() {
        progressBar.visibility = View.GONE
    }

    fun mostrarPreguntas(lista: List<PreguntaFrecuente>) {
        rvPreguntas.visibility = View.VISIBLE
        rvPreguntas.adapter = PreguntaAdapter(lista)
    }

    fun mostrarError(mensaje: String) {
        tvError.text = mensaje
        tvError.visibility = View.VISIBLE
        rvPreguntas.visibility = View.GONE
    }
}