/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import modelo.Productos.Producto;
import sun.security.krb5.internal.crypto.Aes256;

/**
 *
 * @author cris
 */
public class Detalle_venta {
    private int id;
    private int cantidad;
    private int idProducto;
    private int ventaID;
    private int detalleID;

    public Detalle_venta( int cantidad,int idProducto,int ventaID) {
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this. ventaID= ventaID;
        this.detalleID=detalleID;
    }

    public int getDetalleID() {
        return detalleID;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /*public Venta getVenta() {
        return venta;
    }*/

    
  /*public int darIdVenta(){
 
    return venta.getId();
      }*/
    

    public int getVentaID() {
        return ventaID;
    }

    public void setIdVenta(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}  

