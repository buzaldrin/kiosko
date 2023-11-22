/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Productos;

import controlador.VentaDAO;
import java.awt.List;
import java.util.Date;

/**
 *
 * @author cris
 */
public  class Producto {
    protected int id;
    protected  String marca;
    protected int precio;
    protected String nombre;
    protected int stock;
    private int tipoProducto;
    private boolean  azucar;
    private boolean lata;
    private int peso;
    private String color;
    private Date fecha_vencimiento;
    private boolean grasas;

  
    

    public Producto() {
    }

    public Producto(int id, int stock) {
        this.id = id;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public Producto( String marca, int precio, String nombre,int stock,int  tipoProducto) throws Exception {
        //this.id = id;
     setMarca(marca);
        setPrecio(precio);
        setNombre(nombre);
        setStock(stock);
        this.tipoProducto=tipoProducto;
    }
    
//constructor de la bebidad
    public Producto(String marca, int precio, String nombre, int stock, int tipoProducto, boolean azucar, boolean lata) {
        this.marca = marca;
        this.precio = precio;
        this.nombre = nombre;
        this.stock = stock;
        this.tipoProducto = tipoProducto;
        this.azucar = azucar;
        this.lata = lata;
    }
//constructor de la manzana
    public Producto(int stock, int precio, String nombre, int tipoProducto, int peso, String color) throws Exception {
       // setMarca(marca);
        setPrecio(precio);
        setNombre(nombre);
        this.tipoProducto = tipoProducto;
        setPeso(peso);
        setColor(color);
        setStock(stock);
    }
//constructor del yoguty
    public Producto(String marca, int precio, String nombre, int stock, int tipoProducto, Date fecha_vencimiento, boolean grasas) {
        this.marca = marca;
        this.precio = precio;
        this.nombre = nombre;
        this.stock = stock;
        this.tipoProducto = tipoProducto;
        this.fecha_vencimiento = fecha_vencimiento;
        this.grasas = grasas;
    }

    public void setStock(int stock) {
        if (stock>-1) {
                this.stock = stock;
        }else{
            throw  new IllegalArgumentException("INGRESE UNA CANTIDAD MAYOR A -1");
        }
    
    }

    public void setTipoProducto(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public void setAzucar(boolean azucar) {
        this.azucar = azucar;
    }

    public void setLata(boolean lata) {
        this.lata = lata;
    }

    public void setPeso(int peso) throws Exception {
        if (peso>-1) {
             this.peso = peso;
        }else{
            throw new Exception("debe ingresar un numero positivo");
        }
       
    }

    public void setColor(String color) throws Exception {
        if (color.trim().length()>0) {
           this.color = color; 
        }else{
            throw new Exception("La logitud del color debe ser mayor a 0 ");
        }
        
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public void setGrasas(boolean grasas) {
        this.grasas = grasas;
    }

    
    
      public int getTipoProducto() {
        return tipoProducto;
    }
    
    public boolean isAzucar() {
        return azucar;
    }

    public boolean isLata() {
        return lata;
    }

    public int getPeso() {
        return peso;
    }

    public String getColor() {
        return color;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public boolean isGrasas() {
        return grasas;
    }
    
    
    
public Producto(int id, String marca, int precio, String nombre) {
        this.id = id;
        this.marca = marca;
        this.precio = precio;
        this.nombre = nombre;
    }

    public Producto(int precio, String nombre, int stock, int tipoProducto) {
        setPrecio(precio);
        this.nombre = nombre;
        setStock(stock);
        this.tipoProducto = tipoProducto;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) throws Exception {
        if (marca.trim().length()>0) {
           this.marca = marca; 
        }else{
            throw new Exception("ingrese una marca");
        }
        
        
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        if (precio>-1) {
            this.precio = precio;
        }else if(precio<0){
            throw new IllegalArgumentException("Ingrese un numero mayor a -1");
        }else if (precio==Integer.MAX_VALUE) {

            throw new IllegalArgumentException("Ingrese un numero menor a "+Integer.MAX_VALUE);
        }
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws Exception {
        if (nombre.trim().length()>0) {
             this.nombre = nombre;
        }else{
            throw new Exception("ingrese un nombre");
        }
       
    }

    
    public static void aumentarStockIndivual(int id,int cantidad){
//        int id,cantidad;
       
            VentaDAO.aumentarStock(id, cantidad);
        
    }
    
    public boolean idIgual(int id){
        return this.id==id;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", marca=" + marca + ", precio=" + precio + ", nombre=" + nombre + '}';
    }
  
}
