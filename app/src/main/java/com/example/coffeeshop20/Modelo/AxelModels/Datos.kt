package com.example.coffeeshop20.Modelo.AxelModels

data class Usuario(
    val id_usuario: Int,
    val nombre_completo: String,
    val correo: String,
    val telefono: String
)

data class Cafeteria(
    val nombre: String,
    val direccion: String,
    val latitud: Double,
    val longitud: Double,
    val telefono: String,
    val correo: String,
    val dias_atencion: String,
    val hora_apertura: String,
    val hora_cierre: String
)

data class PreguntaFrecuente(
    val id_pregunta: Int,
    val pregunta: String,
    val respuesta: String
)

data class Direccion(
    val id_direccion: Int,
    val alias: String,
    val direccion: String,
    val referencias: String?,
    val es_principal: Int
)
