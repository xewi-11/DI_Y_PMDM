package com.example.vuelos.db

import com.example.vuelos.model.Vuelo
import java.util.Date

class DataBase() {
    companion object{
        var listaVuelos: ArrayList<Vuelo> = arrayListOf(
            Vuelo("Madrid","Barcelona", Date(2025-1900,4,21),Date(2025-1900,5,21))
        )
    }
}