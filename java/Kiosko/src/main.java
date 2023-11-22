

import controlador.ClienteDAO;
import controlador.DetalleVentaDAO;
import controlador.ProductoDAO;
import controlador.VentaDAO;
import java.awt.List;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;
import modelo.Detalle_venta;
import modelo.Productos.Bebida;
import modelo.Productos.Producto;
import modelo.Productos.Yogurt;
import modelo.Venta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cris
 */
public class main {
    public static void main(String[] args) {
   
        ArrayList<Object>Object=VentaDAO.listarProductos();
        for (Object object : Object) {
            System.out.println(object);
        }


//System.out.println(productos);
       
        
       
        
    }
}
