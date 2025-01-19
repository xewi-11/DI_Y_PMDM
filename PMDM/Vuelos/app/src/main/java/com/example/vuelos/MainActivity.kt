package com.example.vuelos

import android.app.DatePickerDialog

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vuelos.adapter.VuelosAdapter
import com.example.vuelos.databinding.ActivityMainBinding
import com.example.vuelos.db.DataBase
import com.example.vuelos.dialogs.ConfirmDialog
import com.example.vuelos.dialogs.DateDialog
import com.example.vuelos.dialogs.InfoDialog
import com.example.vuelos.model.Vuelo

import java.time.format.DateTimeFormatter
import java.util.Date


class MainActivity : AppCompatActivity(),OnClickListener,
    DatePickerDialog.OnDateSetListener,
        ConfirmDialog.OnDialogoConfirmacionListener
{
    private lateinit var fecha1: Date
    var fecha1Selec: Boolean = false
    var fecha2Selec: Boolean = false
    private lateinit var fecha2: Date
    private lateinit var vuelo: Vuelo
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/mm/yyyy")



    private  val ciudades = arrayOf(
    // América del Norte
    "New York",
    "Los Angeles",
    "Chicago",
    "Toronto",
    "Vancouver",
    "Mexico City",
    "Houston",
    "Phoenix",
    "Miami",
    "Montreal",

    // América del Sur
    "São Paulo",
    "Buenos Aires",
    "Rio de Janeiro",
    "Lima",
    "Bogotá",
    "Santiago",
    "Caracas",
    "Quito",
    "La Paz",
    "Montevideo",

    // Europa
    "London",
    "Paris",
    "Berlin",
    "Madrid",
    "Rome",
    "Amsterdam",
    "Vienna",
    "Prague",
    "Budapest",
    "Warsaw",

    // Asia
    "Tokyo",
    "Beijing",
    "Shanghai",
    "Seoul",
    "Singapore",
    "Bangkok",
    "Delhi",
    "Mumbai",
    "Hong Kong",
    "Kuala Lumpur",

    // África
    "Cairo",
    "Lagos",
    "Nairobi",
    "Johannesburg",
    "Casablanca",
    "Addis Ababa",
    "Accra",
    "Dakar",
    "Algiers",
    "Kampala",

    // Oceanía
    "Sydney",
    "Melbourne",
    "Auckland",
    "Brisbane",
    "Perth",
    "Honolulu",
    "Wellington",
    "Suva",
    "Canberra",
    "Port Moresby"
)

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: VuelosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ciudad1
        instancias()
        acciones()
        adapter = VuelosAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun acciones() {
        binding.fechaIda.setOnClickListener(this)
        binding.fechaVuelta.setOnClickListener(this)
        binding.confirmar.setOnClickListener(this)
    }

    private fun instancias() {
        val adaptadorCiudades = ArrayAdapter(this, android.R.layout.simple_list_item_1, ciudades)
        adaptadorCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.ciudad1.setAdapter(adaptadorCiudades)
        val adaptadorCiudades2 = ArrayAdapter(this, android.R.layout.simple_list_item_1, ciudades)
        adaptadorCiudades2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.ciudad2.setAdapter(adaptadorCiudades2)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            binding.fechaIda.id -> {
                fecha1Selec = true
                val dialogo = DateDialog()
                dialogo.show(supportFragmentManager,null)
            }
            binding.fechaVuelta.id -> {
                fecha2Selec = true
                val dialogo = DateDialog()
                dialogo.show(supportFragmentManager,null)
            }
            binding.confirmar.id -> {
                 vuelo = Vuelo(binding.ciudad1.selectedItem.toString(),
                    binding.ciudad2.selectedItem.toString(),fecha1,fecha2)
                var si = vuelo.validarVuelo()
                if(si){
                    var dialogo = ConfirmDialog()
                    dialogo.show(supportFragmentManager,null)
                }else{
                    var dialogo = InfoDialog()
                    dialogo.show(supportFragmentManager,null)
                }

            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        if(fecha1Selec){
            Log.v("fechas","Fecha 1 Año." + year.toString())
            fecha1 = Date(year-1900,month,dayOfMonth)
            Log.v("fechas","Fecha 1 Año." + fecha1.year.toString())
            Log.v("fechas","Fecha 1 " + fecha1.toLocaleString())
            Log.v("fechas","Fecha 1 " + fecha1.toGMTString())
            binding.fechaIda.text = fecha1.toLocaleString().substring(0,fecha1.toLocaleString().length - 7)

            fecha1Selec = false
        }
        if (fecha2Selec){
            fecha2 = Date(year-1900,month,dayOfMonth)
            binding.fechaVuelta.text = fecha2.toLocaleString().substring(0,fecha2.toLocaleString().length - 7)
            fecha2Selec = false
        }
    }

    override fun onConfirmacionSelected() {
        DataBase.listaVuelos.add(vuelo)
        adapter.notifyDataSetChanged()
    }
}