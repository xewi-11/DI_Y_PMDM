package com.example.ciclosdam.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            createConnection();
        }

        return connection;
    }

    private void createConnection() {
        String uriConnection = String.format("jdbc:mysql://%s:%s/%s", DBSchema.HOST, DBSchema.PORT, DBSchema.DATABASE_NAME);

        try {
            connection = DriverManager.getConnection(uriConnection,"root","");
            System.out.println("Conexión creada correctamente");
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: ");
        }
    }

}
