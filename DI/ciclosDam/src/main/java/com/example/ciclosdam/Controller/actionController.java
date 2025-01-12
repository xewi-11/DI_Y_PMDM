package com.example.ciclosdam.Controller;

import com.example.ciclosdam.DAO.ApiDAO;
import com.example.ciclosdam.HelloApplication;
import com.example.ciclosdam.model.Proyecto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class actionController implements Initializable, EventHandler<ActionEvent> {
    @FXML
    private TableColumn<?, ?> cDescripcion;

    @FXML
    private TableColumn<?, ?> cId;

    @FXML
    private MenuItem menuAddAlumno;

    @FXML
    private MenuItem menuAddProfesor;

    @FXML
    private MenuItem menuProyecto;

    @FXML
    private MenuItem menuUnificar;

    @FXML
    private TableView<Proyecto> tablaProyectos;

    private ObservableList<Proyecto> listaProyecto;
    private ApiDAO proyectoDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instancias();
        acciones();

    }

    private void acciones() {
        menuAddAlumno.setOnAction(this);
        menuAddProfesor.setOnAction(this);
        menuProyecto.setOnAction(this);
        menuUnificar.setOnAction(this);
    }

    private void instancias() {
        proyectoDAO = new ApiDAO();
        listaProyecto= FXCollections.observableArrayList();

        try {
            listaProyecto=proyectoDAO.cargarProyectos();
            proyectoDAO.guardarProyectosEnBaseDeDatos(listaProyecto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tablaProyectos.setItems(listaProyecto);
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==menuAddAlumno){
            FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("addAlumno_view.fxml"));
            try {
               Parent root = loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (actionEvent.getSource()==menuAddProfesor) {
            FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("addProfesor-view.fxml"));
            Parent root= null;
            try {
                root = loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
        } else if (actionEvent.getSource()==menuProyecto) {
            
        } else if (actionEvent.getSource()==menuUnificar) {
            FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("unificar-view.fxml"));
            Parent root= null;
            try {
                root = loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
