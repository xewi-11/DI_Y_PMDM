package com.example.ciclosdam.DAO;

import com.example.ciclosdam.DataBase.DBConnection;
import com.example.ciclosdam.DataBase.DBSchema;
import com.example.ciclosdam.model.Alumno;
import com.example.ciclosdam.model.Proyecto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

public class ApiDAO {
    String urlProyectos="https://run.mocky.io/v3/d907a4e8-d037-4e09-b1bd-39c83758c73c";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ApiDAO() {
        connection = new DBConnection().getConnection();
    }

    public ObservableList<Proyecto> cargarProyectos() throws IOException {
        // Simulación de carga de datos
        ObservableList<Proyecto> proyectos = FXCollections.observableArrayList();
        // Llenar la lista con datos
        URL url = new URL(urlProyectos);
        HttpURLConnection connection= (HttpURLConnection) url.openConnection();
        BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));


        String linea=null;
        StringBuilder content=new StringBuilder();
        while ((linea=reader.readLine()) != null){
            content.append(linea);
        }
        JSONArray jsonArray=new JSONArray(content.toString());
        for(int i=0; i<jsonArray.length();i++) {
            JSONObject proyecto =jsonArray.getJSONObject(i);
            int id =proyecto.getInt("index");
            String descripcion = proyecto.getString("proyect_descriptoin");
            proyectos.add(new Proyecto(id,descripcion));
        }
        return proyectos;
    }
    public void guardarProyectosEnBaseDeDatos(ObservableList<Proyecto> proyectos) throws SQLException {
        String query = "INSERT INTO %s (%s,%s)" + "VALUES (?,?)";
        String queryFormateada = String.format(query, DBSchema.TAB_PROYECTO, DBSchema.COL_ID_PROYECTO, DBSchema.COL_DESCRIPCION);
        preparedStatement = connection.prepareStatement(queryFormateada);
        for(int i = 0; i < proyectos.size(); i++) {
            preparedStatement.setInt(1, proyectos.get(i).getId());
            preparedStatement.setString(2, proyectos.get(i).getDescripcion());
            preparedStatement.execute();
        }
    }

    public ObservableList<Proyecto> seleccionarProyectos() throws SQLException {
        String query = "SELECT * FROM %s";
        String queryFormateada = String.format(query, DBSchema.TAB_PROYECTO);
        preparedStatement = connection.prepareStatement(queryFormateada);
        resultSet = preparedStatement.executeQuery();
        ObservableList<Proyecto> lista = FXCollections.observableArrayList();
        while (resultSet.next()) {
            int  id = resultSet.getInt(DBSchema.COL_NAME_ALUMNO);
            String descripcion = resultSet.getString(DBSchema.COL_SNAME_PROFESOR);
            lista.add(new Proyecto(id, descripcion));
        }
        return lista;

    }
}
