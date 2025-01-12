package com.example.ciclosdam.DAO;

import com.example.ciclosdam.DataBase.DBConnection;
import com.example.ciclosdam.DataBase.DBSchema;
import com.example.ciclosdam.model.Alumno;
import com.example.ciclosdam.model.Profesor;
import com.example.ciclosdam.model.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatosDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DatosDAO() {
        connection = new DBConnection().getConnection();
    }
    public void unificarDatos(Alumno alumno, Profesor profesor, Proyecto proyecto) throws SQLException {
        String query = "INSERT INTO %s (%s,%s,%s)" + "VALUES (?,?,?,?)";
        String queryFormateada = String.format(query, DBSchema.TAB_DATOS, DBSchema.COL_ALUMNO_DATOS, DBSchema.COL_PROFESOR_DATOS, DBSchema.COL_FK_ID_PROYECTO_DATOS);
        preparedStatement = connection.prepareStatement(queryFormateada);
        preparedStatement.setString(1, alumno.getNombre());
        preparedStatement.setString(2, profesor.getNombre());
        preparedStatement.setInt(3, proyecto.getId());
        preparedStatement.execute();
    }
}
