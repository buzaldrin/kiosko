/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Productos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author cris
 */
public class Yogurt extends Producto{
    private Date fecha_vencimiento;
    private boolean grasas;

    public Yogurt() {
    }

    public Yogurt(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Yogurt(Date fecha_vencimiento, int id, int stock) {
        super(id, stock);
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Yogurt(Date fecha_vencimiento,boolean  grasas,String marca, int precio, String nombre, int stock,int tipo) throws Exception {
        super(marca, precio, nombre, stock,tipo);
        setFecha(fecha_vencimiento);
        this.grasas=grasas;
    }

    public Yogurt(Date fecha_vencimiento, int id, String marca, int precio, String nombre) {
        super(id, marca, precio, nombre);
        this.fecha_vencimiento = fecha_vencimiento;
    }


public Date setFecha(Date fecha)  {
    
        Date fechaHoy=new Date();
      if (fecha.after(fechaHoy)) {
        this.fecha_vencimiento=fecha;
    }else{
          
          throw new IllegalArgumentException("INGRESE UNA FECHA DESPUES DE HOY");
      }
           return fecha;
    
}

    public boolean isGrasas() {
        return grasas;
    }             
    /*public static boolean isValidDate(Date fecha) {
  
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        
        try {
            formato.parse(fecha);
            
            return true;
        } catch (ParseException e) {
            return false;
        }
    }*/

    public void setGrasas(boolean grasas) {
        this.grasas = grasas;
    }
    
    
     public static boolean isAfterToday(LocalDate fecha) {
        // Crea la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Compara las fechas
        return fecha.isAfter(fechaActual);
    }

   
    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
    
}
