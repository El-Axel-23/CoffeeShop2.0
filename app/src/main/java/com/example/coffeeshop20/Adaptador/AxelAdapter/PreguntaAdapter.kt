package com.example.coffeeshop20.Adaptador.AxelAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop20.Modelo.AxelModels.PreguntaFrecuente
import com.example.coffeeshop20.R

class PreguntaAdapter(private val lista: List<PreguntaFrecuente>) :
    RecyclerView.Adapter<PreguntaAdapter.ViewHolder>() {

    private val abiertas = mutableSetOf<Int>()

    class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        val tvPregunta: TextView = vista.findViewById(R.id.tvPregunta)
        val tvRespuesta: TextView = vista.findViewById(R.id.tvRespuesta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pregunta, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.tvPregunta.text = item.pregunta
        holder.tvRespuesta.text = item.respuesta

        if (abiertas.contains(position)) {
            holder.tvRespuesta.visibility = View.VISIBLE
        } else {
            holder.tvRespuesta.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            if (abiertas.contains(position)) {
                abiertas.remove(position)
            } else {
                abiertas.add(position)
            }
            notifyItemChanged(position)
        }
    }
    override fun getItemCount(): Int = lista.size
}
