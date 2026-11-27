package com.example.coffeeshop20.Modelo.AxelModels

data class RespuestaApi<T>(
    val status: String,
    val message: String,
    val data: T?
)
