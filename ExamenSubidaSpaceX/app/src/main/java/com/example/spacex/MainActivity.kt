package com.example.spacex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener

import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.spacex.dataBase.DataSet
import com.example.spacex.databinding.ActivityMainBinding
import com.example.spacex.model.Usuario
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        acciones()
    }

    private fun acciones() {
       binding.botonLogin.setOnClickListener(this)
    }

    private fun instancias() {
        Glide.with(applicationContext).load("https://media.gettyimages.com/id/1243751894/es/foto/cape-canaveral-florida-in-this-handout-photo-provided-by-nasa-a-spacex-falcon-9-rocket.jpg?s=612x612&w=gi&k=20&c=ts7JOrvjQNAb1OHY5ongNKsiUWhRDdHHUm7K9JSjSVQ=").into(binding.imageView)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            binding.botonLogin.id->{
                if(binding.textNombre.text.isEmpty() || binding.textContraseA.text.isEmpty()){
                    Snackbar.make(v,"Todos los campos deben estar rellenos",Snackbar.LENGTH_LONG).show()
                }else
                {
                    Log.v("prueba","entra dentro")
                    if(DataSet.listaUsuarios.contains(Usuario(binding.textNombre.text.toString(),binding.textContraseA.text.toString()))){
                        //pasar pantalla
                        Log.v("prueba","contiene usuario")
                        val intent = Intent(applicationContext, ReclycledActivity2::class.java)
                        startActivity(intent)
                    }else{
                        Log.v("prueba","no contiene usuario")
                        DataSet.listaUsuarios.add(Usuario(binding.textNombre.text.toString(),binding.textContraseA.text.toString()))
                        val intent = Intent(applicationContext, ReclycledActivity2::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }


}