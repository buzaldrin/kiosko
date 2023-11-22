/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.VentaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import modelo.Productos.Producto;

/**
 *
 * @author cris
 */
public class Venta {
    private int id;
    private Date fecha;
    private int total;
    private String rut_cliente;
    private ArrayList<Producto>productos;

    public Venta( Date fecha,String rut_cliente) {
        this.fecha = fecha;
        this.rut_cliente=rut_cliente;
        this.productos=new ArrayList<>();
        crearID();
    }

    public int getId() {
        return id;
    }

    
    public void agregarProducto(Producto p){
            
            productos.add(p);
   
        
     
        
    }
    
    public void setearStock(int cantidad,int posicion){
        productos.get(posicion).setStock(cantidad);
    }
    
    
    public Object[] existe(int id){
        Object[] lista=new Object[2];
        boolean resp;
        int i=0;
        if (!productos.isEmpty()) {
             while (i<cantProducto()&&!this.productos.get(i).idIgual(id)) {            
            i++;
        }
        if (i<cantProducto()) {
            lista[0]=true;
            lista[1]=i;
        }else{
            lista[0]=false;
        }
        
        }else{
            lista[0]=false;
       
        }
 
       
        return lista;
    }

    
    public void eliminarProducto(int posi){
        productos.remove(posi);
    }
            
            
            
    
    
    
    public int cantProducto(){
        return productos.size();
    }
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    
    
    
    
    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

  
    
    private void crearID(){
          int rando;
        Random rmd=new Random();
        rando=rmd.nextInt(9500000);
        this.id=rando;
        System.out.println(rando);
    }
    public String pasarFecha(){

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        String fechaConvertida = formato.format(this.fecha);

        return fechaConvertida;
    }
    
    public  void aumentarStock(){
        int id,cantidad;
        for (Producto producto : productos) {
            id=producto.getId();
            cantidad=producto.getStock();
            VentaDAO.aumentarStock(id, cantidad);
        }
    }
    
      public  void aumentarStockIndividual(int id,int posicion){

          
                int cantidad=productos.get(posicion).getStock();
                VentaDAO.aumentarStock(id, cantidad);    
        
    }
    /*public  void aumentarStockIndividual(int id){

          
                 int cantidad=productos.get(id).getStock();
                VentaDAO.aumentarStock(id, cantidad);    
        
    }*/
    
    
}
