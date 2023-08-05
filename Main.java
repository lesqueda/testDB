// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;

        try {
         //coneccion a la base de datos MYSQL

         String url = "jdbc:mysql://localhost:3306/universidad";
         String user = "root";
         String password = "";
         connection = DriverManager.getConnection(url, user, password);

         //Crear la tabla si esta no existe

         String createTableQuery = "CREATE TABLE IF NOT EXISTS persona2 (id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(24), apellido VARCHAR(24))";
         connection.createStatement().execute(createTableQuery);

         //Insertar Datos

         String insertQuery = "INSERT INTO persona2 (nombre, apellido) VALUES ('juan', 'perez')" ;
         PreparedStatement insertStatement =  connection.prepareStatement(insertQuery);
         insertStatement.executeUpdate();

         //Consultar los datos
         String selectQuery = "SELECT * FROM persona2";
         ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

         while(resultSet.next()){

             int id = resultSet.getInt("id");
             String nombre = resultSet.getString("nombre");
             String apellido = resultSet.getString("apellido");
            // int edad = resultSet.getInt("edad");

             Persona persona = new Persona(id, nombre, apellido);
             System.out.println("ID: " + persona.getId() + "Nombre: " + persona.getNombre() + "Apellido: " + persona.getApellido());
         }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try{
                if (connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

    }
}