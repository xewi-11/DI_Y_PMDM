package com.example.vuelos.dialogs

import androidx.appcompat.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.example.vuelos.R

class InfoDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())

        builder.setTitle("Informacion")
        builder.setIcon(R.drawable.cerrar)
        builder.setMessage("Las Ciudades son las mismas o las fechas estan mal")
        builder.setPositiveButton("OK") { _, _ ->

        }
        builder.setNegativeButton("NO") { _, _ ->

        }
        builder.setNeutralButton("Cancel") { _, _ ->

        }
        return builder.create()
    }
}