package com.example.spacex.adapter

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.spacex.DetailActivity
import com.example.spacex.R
import com.example.spacex.model.Fairings
import com.example.spacex.model.Links
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

class adapterReclycled(val context: Context): Adapter<adapterReclycled.MyHolder>(),
    Serializable {
private  var lista: ArrayList<Fairings>
init {

    lista = arrayListOf()
    carga()
}
    inner class MyHolder(item: View): ViewHolder(item){
        val imagen: ImageView =item.findViewById(R.id.imagen)
        val textNombre:TextView=item.findViewById(R.id.textoNombre)
        val boton:Button=item.findViewById(R.id.botonDetalles)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterReclycled.MyHolder {
        val vista = LayoutInflater.from(context).inflate(R.layout.item_reclycled,parent,false)
        return MyHolder(vista)
    }

    override fun onBindViewHolder(holder: adapterReclycled.MyHolder, position: Int) {
        val mision=lista[position]
        Glide.with(context).load(mision.links.patch.small).into(holder.imagen)
        holder.textNombre.text=mision.name
        holder.boton.setOnClickListener { when(it.id){
            R.id.botonDetalles->{
                val intent = Intent(context, DetailActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("mision", mision)
                intent.putExtra("datos",bundle)
                context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
        }
         }

    }

    override fun getItemCount(): Int {
        return lista.size
    }
    fun addMision(fairings:Fairings){
        lista.add(fairings)
        notifyItemInserted(lista.size-1)
    }
    fun cargarlistaFiltrada(listaFiltrada: MutableList<Fairings>){

            lista.clear()
            lista.addAll(listaFiltrada)
            notifyDataSetChanged()

    } fun cargarlista(listaFiltrada: ArrayList<Fairings>){
        Log.v("hesalidoo","tomaaaaa")
            lista.clear()
            lista.addAll(listaFiltrada)
            notifyDataSetChanged()

    }
    fun limpiarLista(){
        lista.clear()
        notifyDataSetChanged()
    }
    fun carga(){
        val url: String ="https://api.spacexdata.com/v5/launches"
        val peticion: JsonArrayRequest = JsonArrayRequest(url,
            {procesarRespuesta(it)},
            { Log.v("test", "error")})
        Volley.newRequestQueue(context).add(peticion)
    }
    private fun procesarRespuesta(respuesta: JSONArray) {
        for (i in 0 until respuesta.length()){
            val misiones: JSONObject = respuesta.getJSONObject(i)
            //Log.v("json", personajes.toString())
            val gson = Gson()
            val links=gson.fromJson(misiones.getJSONObject("links").toString(), Links::class.java)
            val name=misiones.get("name")
            val success=misiones.get("success")
            val details=misiones.get("details")

            var fairings=Fairings(name.toString(),success.toString(),details.toString(),links)
            lista.add(fairings)
        }

    }
}