// VuelosAdapter.kt
package com.example.vuelos.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar // Importación correcta
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.vuelos.R
import com.example.vuelos.db.DataBase
import com.example.vuelos.gson.CiudadGson
import com.example.vuelos.gson.Page
import com.google.gson.Gson
import com.google.gson.GsonBuilder


class VuelosAdapter(
    private val context: Context
) : RecyclerView.Adapter<VuelosAdapter.VueloViewHolder>() {

    // Inicializar la cola de solicitudes de Volley
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)

    // Inicializar Gson
    private val gson: Gson = GsonBuilder().create()

    inner class VueloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.image)
        val origen: TextView = itemView.findViewById(R.id.origen)
        val destino: TextView = itemView.findViewById(R.id.destino)
        val fechaIda: TextView = itemView.findViewById(R.id.fechaIda)
        val fechaVuelta: TextView = itemView.findViewById(R.id.fechaVuelta)
        val toolbar: Toolbar = itemView.findViewById(R.id.toolbar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VueloViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_vuelo, parent, false)
        return VueloViewHolder(view)
    }

    override fun onBindViewHolder(holder: VueloViewHolder, position: Int) {
        val fly = DataBase.listaVuelos[position]
        holder.toolbar.subtitle = "Tu Vuelo el día ${fly.fechaIda!!.toLocaleString()!!.substring(0,fly.fechaIda.toLocaleString().length-7)} con destino a ${fly.destino} "
        holder.origen.text = fly.origen
        holder.destino.text = fly.destino
        holder.fechaIda.text = fly.fechaIda.toLocaleString()!!.substring(0,fly.fechaIda.toLocaleString().length-7)
        holder.fechaVuelta.text = fly.fechaVuelta?.toLocaleString()!!.substring(0,fly.fechaVuelta.toLocaleString().length-7)

        // Cargar la imagen correspondiente a la ciudad de destino
        val ciudadDestino = fly.destino
        if (ciudadDestino != null) {
            cargarImagenCiudad(ciudadDestino) { imageUrl ->
                if (imageUrl != null) {
                    // Cargar la imagen con Glide
                    Glide.with(context)
                        .load(imageUrl)
                        .placeholder(R.drawable.avion) // Imagen placeholder
                        .error(R.drawable.cerrar) // Imagen de error
                        .into(holder.imagen)
                } else {
                    // Cargar una imagen por defecto si no hay thumbnail
                    holder.imagen.setImageResource(R.drawable.avion)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return DataBase.listaVuelos.size
    }

    /**
     * Método para obtener la URL de la imagen de una ciudad y retornar mediante callback
     */
    private fun cargarImagenCiudad(ciudad: String, callback: (String?) -> Unit) {
        val url = "https://commons.wikimedia.org/w/api.php?action=query&titles=$ciudad&prop=pageimages&format=json&pithumbsize=500"

        // Crear la solicitud de cadena con Volley
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                // Parsear la respuesta JSON
                val ciudadGson = parseJson(response)
                val pages = ciudadGson?.query?.pages
                val page: Page? = pages?.values?.firstOrNull()
                val imageUrl = page?.thumbnail?.source

                // Retornar la URL de la imagen mediante callback
                callback(imageUrl)
            },
            { error ->
                // Manejar errores de la solicitud
                Log.e("Conexion", "Error al conectarse a la API: ${error.localizedMessage}")
                // Retornar null mediante callback en caso de error
                callback(null)
            }
        )

        // Añadir la solicitud a la cola
        requestQueue.add(stringRequest)
    }

    /**
     * Método para deserializar el JSON a un objeto CiudadGson
     */
    private fun parseJson(jsonString: String): CiudadGson? {
        return try {
            gson.fromJson(jsonString, CiudadGson::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
