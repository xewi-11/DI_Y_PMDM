package com.example.ciclosdam.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    private String nombre,apellido,correo;
    private int idProyecto;
}
