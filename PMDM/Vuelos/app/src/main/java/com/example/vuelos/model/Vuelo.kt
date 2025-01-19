package com.example.vuelos.model

import java.util.Date

class Vuelo(
    val origen: String? = null,
    val destino: String? = null,
    val fechaIda: Date? = null,
    val fechaVuelta: Date? = null,
) {
    fun validarVuelo(): Boolean {
        if (this.fechaIda!! > this.fechaVuelta || this.origen.equals(this.destino)) {
            return false
        } else {
            return true
        }
    }

}