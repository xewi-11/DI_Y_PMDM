package com.example.spacex

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.spacex.adapter.adapterReclycled
import com.example.spacex.databinding.ActivityMainBinding
import com.example.spacex.databinding.ActivityReclycled2Binding
import com.example.spacex.model.Fairings
import com.example.spacex.model.Links
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class ReclycledActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityReclycled2Binding
    private lateinit var adapterReclycled: adapterReclycled
    private lateinit var lista:ArrayList<Fairings>

    val url: String ="https://api.spacexdata.com/v5/launches"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityReclycled2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()
        val peticion: JsonArrayRequest= JsonArrayRequest(url,
            {procesarRespuesta(it)},
            { Log.v("test", "error")})
        Volley.newRequestQueue(applicationContext).add(peticion)
        acciones()

    }

    private fun acciones() {
        val listaFinal:ArrayList<Fairings> =lista
        binding.toolbar.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.menu_exitos->{
                    println("exito")
                    val listaf:MutableList<Fairings>
                     listaf=listaFinal.filter { if(it.success.equals("true")){ return@filter true }else return@filter false}.toMutableList()
                     adapterReclycled.cargarlistaFiltrada(listaf)
                }
                R.id.menu_fallos->{
                    println("fallo")
                    val listaf:MutableList<Fairings>
                    listaf= listaFinal.filter { if(it.success.equals("false")){ return@filter true }else return@filter false}.toMutableList()
                    adapterReclycled.cargarlistaFiltrada(listaf)
                }
                R.id.menu_todos->{
                    println("todos")
                    adapterReclycled.limpiarLista()
                   adapterReclycled.cargarlista(listaFinal)
                }


            }
            return@setOnMenuItemClickListener true
        }
    }

    private fun instancias() {
        lista= arrayListOf()
        adapterReclycled= adapterReclycled(applicationContext)
        binding.recycled.adapter=adapterReclycled
        binding.recycled.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.toolbar.inflateMenu(R.menu.menu_reclyced)

    }

    private fun procesarRespuesta(respuesta: JSONArray) {
        for (i in 0 until respuesta.length()){
            val misiones: JSONObject = respuesta.getJSONObject(i)
            //Log.v("json", personajes.toString())
            val gson = Gson()
            val links=gson.fromJson(misiones.getJSONObject("links").toString(),Links::class.java)
            val name=misiones.get("name")
            val success=misiones.get("success")
            val details=misiones.get("details")

           var fairings=Fairings(name.toString(),success.toString(),details.toString(),links)
            lista.add(fairings)
        }
        adapterReclycled.cargarlista(lista)
    }
}