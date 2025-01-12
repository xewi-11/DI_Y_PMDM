package com.example.ciclosdam.Controller;

import com.example.ciclosdam.DAO.AlumnoDAO;
import com.example.ciclosdam.DAO.ApiDAO;
import com.example.ciclosdam.DAO.DatosDAO;
import com.example.ciclosdam.DAO.ProfesorDAO;
import com.example.ciclosdam.model.Alumno;
import com.example.ciclosdam.model.Profesor;
import com.example.ciclosdam.model.Proyecto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class unificarController implements Initializable, EventHandler<ActionEvent> {
    @FXML
    private Button btnUnificar;

    @FXML
    private ComboBox<Alumno> comboAlumno;
    private ObservableList<Alumno> listaAlumnos;
    private AlumnoDAO alumnoDAO;

    @FXML
    private ComboBox<Profesor> comboProfesor;
    private ObservableList<Profesor> listaProfesores;
    private ProfesorDAO profesorDAO;

    @FXML
    private ComboBox<Proyecto> comboProyecto;
    private ObservableList<Proyecto> listaProyectos;
    private ApiDAO proyectoDAO;
    private DatosDAO datosDAO;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       instancias();
    }

    private void instancias()  {
        alumnoDAO = new AlumnoDAO();
        profesorDAO = new ProfesorDAO();
        proyectoDAO = new ApiDAO();
        listaAlumnos= FXCollections.observableArrayList();
        listaProfesores= FXCollections.observableArrayList();
        listaProyectos= FXCollections.observableArrayList();
        try {
            listaAlumnos=alumnoDAO.seleccionarTodos();
            comboAlumno.setItems(listaAlumnos);
            listaProfesores=profesorDAO.seleccionarTodos();
            comboProfesor.setItems(listaProfesores);
            listaProyectos=proyectoDAO.seleccionarProyectos();
            comboProyecto.setItems(listaProyectos);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(ActionEvent event) {
         if(event.getSource()==btnUnificar){
             Alumno alumno = comboAlumno.getValue();
             Profesor profesor = comboProfesor.getValue();
             Proyecto proyecto = comboProyecto.getValue();

             if(alumno!=null && profesor!=null && proyecto!=null){
                 try {
                    datosDAO.unificarDatos(alumno,profesor,proyecto);
                     System.out.println("Datos unificados correctamente");
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
             }else{
                 System.out.println("Debe seleccionar alumnos, profesores y proyectos");
             }
         }
    }
}
