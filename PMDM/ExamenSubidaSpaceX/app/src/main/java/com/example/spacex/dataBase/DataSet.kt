package com.example.spacex.dataBase

import com.example.spacex.model.Usuario

class DataSet {
    companion object{
        var listaUsuarios:ArrayList<Usuario> =arrayListOf(
            Usuario("javier","1234")
        )
    }
}