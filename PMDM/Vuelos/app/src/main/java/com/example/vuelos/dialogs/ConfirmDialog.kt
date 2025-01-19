package com.example.vuelos.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmDialog: DialogFragment() {
    private lateinit var listener: OnDialogoConfirmacionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnDialogoConfirmacionListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())

        builder.setTitle("Confirmacion")
        builder.setMessage("Estas seguro que quieres continuar")

        builder.setPositiveButton("Si") { _, _ ->
            // Accion a realizar si se pulsa el boton Si
            listener.onConfirmacionSelected()
        }

        builder.setNeutralButton("Cancel") { _, _ ->
            // Accion a realizar si se pulsa el boton Cancelar
        }

        return builder.create()
    }
    interface OnDialogoConfirmacionListener {
        fun onConfirmacionSelected()
    }
}