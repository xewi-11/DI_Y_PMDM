package com.example.ciclosdam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alumno {
    private String nombre;
    private String apellido;
    private String DNI;
    private int idProyecto;
    // Constructor, getters y setters
}
