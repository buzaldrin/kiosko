/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Productos;

/**
 *
 * @author cris
 */
public class Bebida extends Producto{
    
    private boolean  azucar;
    private boolean lata;

    public Bebida() {
    }


    public Bebida( boolean azucar,boolean lata, String marca, int precio, String nombre, int stock,int tipo) throws Exception {
        super(marca, precio, nombre, stock,tipo);
        this.azucar = azucar;
        this.lata=lata;
    }

    public boolean isLata() {
        return lata;
    }




   

    public boolean isAzucar() {
        return azucar;
    }

    public void setAzucar(boolean azucar) {
        this.azucar = azucar;
    }
    
    
    
}
