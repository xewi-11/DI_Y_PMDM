package com.example.ciclosdam.Controller;

import com.example.ciclosdam.DAO.AlumnoDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class addAlumnoController implements Initializable,, EventHandler<ActionEvent> {

    @FXML
    private Button btnAdd;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;

    private AlumnoDAO alumnoDAO;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      instancias();
      acciones();
    }

    private void acciones() {
        btnAdd.setOnAction(this);
    }

    private void instancias() {
        alumnoDAO=new AlumnoDAO();

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==btnAdd){
            if(txtNombre.getText().isEmpty() && txtApellido.getText().isEmpty()){
                System.out.println("Todos los campos son obligatorios");
            }else{
                
            }
        }
    }
}
