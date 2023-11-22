/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Conexion.ConexionOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;

/**
 *
 * @author cris
 */
public class ClienteDAO {
    
    
    public boolean  agregarCliente(Cliente c){
        boolean  resp=false;
        String RUT=c.getRut();
    
        
        try (Connection cone=ConexionOracle.getCone()){
            
            
            String sql = "INSERT INTO cliente VALUES(?,?)";
          PreparedStatement pst = cone.prepareStatement(sql);
      
            pst.setString(1, c.getRut() );
         pst.setString(2, c.getNombre() );
            int lineas = pst.executeUpdate();
            if (lineas > 0){
                resp = true;
            }    
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
            sql.printStackTrace();
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }
    
    
    public Cliente TraerCLiente(String rut) {
        Cliente p = new Cliente();
        try (Connection cone=ConexionOracle.getCone()){
            String sql = "SELECT RUT,NOMBRE FROM CLIENTE WHERE rut = ?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setString(1, rut);

            ResultSet rs = pst.executeQuery();
            while ( rs.next() ) {
               
                p.setRut(rs.getNString("RUT"));
                p.setNombre( rs.getString("nombre") );
          
            }
                    
        }
          catch (SQLException e) {
            
            System.out.println("ERROR: " + e.getMessage() );
             printStackTrace();
        }
        catch (Exception e) {
            
            System.out.println("ERROR: " + e.getMessage() );
        }
        return p;
    }
    
    
    
    
    public boolean  buscarCLiente(String rut) {
        boolean  resp=false;
        Cliente p = new Cliente();
        try (Connection cone=ConexionOracle.getCone()){
            String sql = "SELECT RUT FROM CLIENTE ";
            PreparedStatement pst = cone.prepareStatement(sql);
            //pst.setString(1, rut);
      
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                if (rs.getString("rut").equals(rut)) {
                resp=true;
            }
              
            }
                  
        } 
        
        catch (Exception e) {
            
            System.out.println("ERROR: " + e.getMessage() );
        }
        return resp;
    }

    private void printStackTrace() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
