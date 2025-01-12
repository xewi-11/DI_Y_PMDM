package com.example.ciclosdam.DAO;

import com.example.ciclosdam.DataBase.DBConnection;
import com.example.ciclosdam.DataBase.DBSchema;
import com.example.ciclosdam.model.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfesorDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProfesorDAO() {
        connection = new DBConnection().getConnection();
    }

    public void registrarProfesor(Profesor profesor) throws SQLException {
        String query = "INSERT INTO %s (%s,%s,%s)" + "VALUES (?,?,?)";
        String queryFormateada = String.format(query, DBSchema.TAB_PROFESOR, DBSchema.COL_NAME_PROFESOR, DBSchema.COL_SNAME_PROFESOR, DBSchema.COL_CORREO_PROFESOR);
        preparedStatement = connection.prepareStatement(queryFormateada);
        preparedStatement.setString(1, profesor.getNombre());
        preparedStatement.setString(2, profesor.getApellido());
        preparedStatement.setString(3, profesor.getCorreo());
        preparedStatement.execute();
    }

    public ObservableList<Profesor> seleccionarTodos() throws SQLException {
        //Select * from usuario
        String query = "SELECT * FROM %s";
        String queryFormateada = String.format(query, DBSchema.TAB_PROFESOR);
        preparedStatement = connection.prepareStatement(queryFormateada);
        resultSet = preparedStatement.executeQuery();
        ObservableList<Profesor> lista = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String nombre = resultSet.getString(DBSchema.COL_NAME_PROFESOR);
            String apellido = resultSet.getString(DBSchema.COL_SNAME_PROFESOR);
            String correo = resultSet.getString(DBSchema.COL_CORREO_PROFESOR);
            lista.add(new Profesor(nombre,apellido,correo));
        }
        return lista;
    }
}
