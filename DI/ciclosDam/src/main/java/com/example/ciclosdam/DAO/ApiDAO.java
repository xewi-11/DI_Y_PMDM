package com.example.ciclosdam.DAO;

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

public class ApiDAO {
    String urlProyectos="https://run.mocky.io/v3/d907a4e8-d037-4e09-b1bd-39c83758c73c";

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
}
