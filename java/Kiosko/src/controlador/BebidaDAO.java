/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Conexion.ConexionOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Cliente;
import modelo.Productos.Bebida;

/**
 *
 * @author cris
 */
public class BebidaDAO {
    
      public boolean  agregarBebida(Bebida b){
        boolean  resp=false;
        
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "INSERT INTO producto (marca,precio,nombre) VALUES(?,?,?)";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
      
             pst1.setString(1, b.getMarca() );
             pst1.setInt(2, b.getPrecio());
             pst1.setString(3, b.getNombre());
            int lineas = pst1.executeUpdate();
            
            if (lineas > 0){
                 String sql2 = "INSERT INTO bebida (azucar) VALUES(?)";
                PreparedStatement pst2 = cone.prepareStatement(sql2);
      
                pst2.setString(1, b.isAzucar()?"s":"n");
              int lineas2 = pst2.executeUpdate();
                if (lineas2>0) {
                    resp = true;
                }
                
            }else{
                System.out.println("aqui corta");
            }    
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return resp;
    }
}
