package com.example.ciclosdam.DataBase;


public interface DBSchema {
    String HOST = "localhost";
    String PORT = "3307";
    String DATABASE_NAME="DAM";
    String TAB_ALUMNNO = "Alumno";
    String COL_DNI_ALUMNO = "DNI";
    String COL_NAME_ALUMNO = "nombre";
    String COL_SNAME_ALUMNO = "apellido";
    String COL_FK_ID_PROYECTO_ALUMNO = "id_proyecto";
    String TAB_PROFESOR = "profesores";
    String COL_NAME_PROFESOR = "nombre";
    String COL_SNAME_PROFESOR = "apellido";
    String COL_CORREO_PROFESOR = "correo";
    String COL_FK_ID_PROYECTO_PROFESOR = "id_proyecto";
}
