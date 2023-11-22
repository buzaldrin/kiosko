/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Conexion.ConexionOracle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Detalle_venta;
import modelo.Venta;

/**
 *
 * @author cris
 */
public class DetalleVentaDAO {

    public DetalleVentaDAO() {
    }
    
    
    
    
        public static boolean  eliminarDetalle(int idProducto,int idVenta){
        boolean  resp=false;
        
    try (Connection cone=ConexionOracle.getCone()){
        String sql = "delete from detalle_venta\n" +
"where id_producto=? and id_venta=?";

    
        
             PreparedStatement pstmt = cone.prepareStatement(sql) ;
          
            pstmt.setInt(1,idProducto);
            pstmt.setInt(2, idVenta);
     
            
            System.out.println("antes de ejecutar el update");
            int filasInsertadas = pstmt.executeUpdate();
            System.out.println("depues");
            if (filasInsertadas > 0) {
                System.out.println("eliminacion exitosa exitosa.");
                resp=true;
            } else {
                System.out.println("No se insertaron filas.");
            }
        } catch (SQLException   e) {
            System.err.println("Error en la Eliminacion: " + e.getMessage());
        }
        return resp;
    }
    
    public boolean  agregarDetalle(Detalle_venta v){
        boolean  resp=false;
        
    try (Connection cone=ConexionOracle.getCone()){
        String sql = "INSERT INTO detalle_venta ( cantidad, id_producto, id_venta) VALUES ( ?,  ?,?)";

        int cantidad = v.getCantidad();
        int idProducto = v.getIdProducto(); 
        int idVenta = v.getVentaID(); 
        
             PreparedStatement pstmt = cone.prepareStatement(sql) ;
          
            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, idProducto);
            pstmt.setInt(3, idVenta);
            
            System.out.println("antes de ejecutar el update");
            int filasInsertadas = pstmt.executeUpdate();
            System.out.println("depues");
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa.");
                resp=true;
            } else {
                System.out.println("No se insertaron filas.");
            }
        } catch (SQLException   e) {
            System.err.println("Error en la inserción: " + e.getMessage());
        }
        return resp;
    }
}
