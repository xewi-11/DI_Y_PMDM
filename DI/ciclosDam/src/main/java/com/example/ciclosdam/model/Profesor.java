package com.example.ciclosdam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profesor {

    private int id;
    private String nombre;
    private String correo;
    private ArrayList<Proyecto> listaDeProyectosAsociados;

}
