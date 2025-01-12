package com.example.ciclosdam.DAO;

import com.example.ciclosdam.DataBase.DBConnection;
import com.example.ciclosdam.DataBase.DBSchema;
import com.example.ciclosdam.Model.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public AlumnoDAO() {
        connection = new DBConnection().getConnection();
    }

    public void registrrarAlumno(Alumno alumno) throws SQLException {
        String query = "INSERT INTO %s (%s,%s,%s,%s)" + "VALUES (?,?,?,?,?)";
        String queryFormateada = String.format(query, DBSchema.TAB_ALUMNNO, DBSchema.COL_NAME_ALUMNO, DBSchema.COL_SNAME_ALUMNO, DBSchema.COL_DNI_ALUMNO, DBSchema.COL_FK_ID_PROYECTO_ALUMNO);
        preparedStatement = connection.prepareStatement(queryFormateada);
        preparedStatement.setString(1, alumno.getNombre());
        preparedStatement.setString(2, alumno.getApellido());
        preparedStatement.setString(3, alumno.getDni());
        preparedStatement.setInt(4, alumno.getIdProyecto());
        preparedStatement.execute();
    }

    public ObservableList<Alumno> seleccionarTodos() throws SQLException {
        String query = "SELECT * FROM %s";
        String queryFormateada = String.format(query, DBSchema.TAB_ALUMNNO);
        preparedStatement = connection.prepareStatement(queryFormateada);
        resultSet = preparedStatement.executeQuery();
        ObservableList<Alumno> lista = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String nombre = resultSet.getString(DBSchema.COL_NAME_ALUMNO);
            String apellido = resultSet.getString(DBSchema.COL_SNAME_PROFESOR);
            String dni = resultSet.getString(DBSchema.COL_DNI_ALUMNO);
            int id_proyecto = resultSet.getInt(DBSchema.COL_FK_ID_PROYECTO_ALUMNO);
            lista.add(new Alumno(nombre, apellido, dni, id_proyecto));
        }
        return lista;

    }
}
