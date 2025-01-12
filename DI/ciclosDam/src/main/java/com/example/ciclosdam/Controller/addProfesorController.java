package com.example.ciclosdam.Controller;

import com.example.ciclosdam.DAO.ProfesorDAO;
import com.example.ciclosdam.model.Profesor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addProfesorController implements Initializable, EventHandler<ActionEvent> {
    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    private ProfesorDAO profesorDAO;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();
        acciones();
    }

    private void acciones() {
        btnAdd.setOnAction(this);
    }

    private void instancias() {
        profesorDAO = new ProfesorDAO();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource()==btnAdd){
            if(txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty() && txtCorreo.getText().isEmpty()){
                System.out.println("Todos los campos son obligatorios");
            }else{
                try {
                    profesorDAO.registrarProfesor(new Profesor(txtNombre.getText(),txtApellido.getText(),txtCorreo.getText()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
