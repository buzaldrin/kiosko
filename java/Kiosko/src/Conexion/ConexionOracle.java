/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionOracle {
    
    //1. Vamos a crear un atributo del tipo conexión sql:
  private static Connection cone;
    
    //2. Vamos a crear un método que crea la conexión hacia una base de datos:
    public static void conectar(){
        //2.1 debo conmenzar con un try/catch para controlar posibles errores de conexión:
        try {
            //2.2 creamos variables de conexión como: usuario, contraseña, url de conexión.
            String usuario = "venta";
            String password = "venta";
            //jdbc: java daba base conector
     //       String url = "jdbc:oracle:thin:@localhost:1521:orcl";
     String url = "jdbc:oracle:thin:@192.168.100.62:1521:xe";          
            
            //este comando le permite entender que debe utilizar el .jar agregado
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //realiza la conexión efectiva hacia oracle:
            cone = DriverManager.getConnection(url, usuario, password);
            System.out.println("conetao  a la base de datos");
            
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage() );
        }catch (ClassNotFoundException ex){
             System.out.println("ERROR: " + ex.getMessage() );
        }
    }
    
    //3. constructor de la clase (vacio): instanciará la conexión hacia oracle:
    public ConexionOracle() {
        conectar();
    }
    
    //4. accesador: GET que me entregará la conexión:
    public static Connection getCone() {
        conectar();
        return cone;
    }
    
    
}