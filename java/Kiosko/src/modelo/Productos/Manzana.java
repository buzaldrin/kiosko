/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Productos;

/**
 *
 * @author cris
 */
public class Manzana extends Producto{
    private int peso;
    private String color;

    

    public Manzana(int peso, String color,  int precio, String nombre,int tipo,int stock) {
        super( precio, nombre,tipo,stock);
        setPeso(peso);
        setColor(color);
    }

    public Manzana() {
    }
    
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        if (peso>-1) {
         
        this.peso = peso;   
        }else{
            throw  new IllegalArgumentException("Ingrese un peso positivo");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color.trim().length()>0) {
            this.color = color;
        }else{
            throw  new NullPointerException("Ingrese un color");
        }
        
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
}
