/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cris
 */
public class Cliente {
    private String rut;
    private String nombre;;

    public Cliente() {
    }

    public Cliente(String rut, String nombre) {
        setRut(rut);
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    /*public void setRut(String rut) {
        String redex="[0-9]{2,3}.[0-9]{3}-[0-9kK] {1}";
        if (rut.matches(redex)) {
            if (CommonFn.validaRut(rut)) {
             
               this.rut = rut;   
            }
        }else{
            
            throw new IllegalArgumentException("El rut debe estar en formato xx.xxx.xxx.x");
        }
     
    }*/
    
       public void setRut(String rut) {

            if (CommonFn.validaRut(rut)) {
             
               this.rut = rut;   
            }else {
                throw new IllegalArgumentException("RUT invalido\n deber ser xxxxxxxx-x");
            }
        
     
    }
    
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     

    
}
