/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Conexion.ConexionOracle;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Productos.Bebida;
import modelo.Productos.Manzana;
import modelo.Productos.Producto;
import modelo.Productos.Yogurt;

/**
 *
 * @author cris
 */
public class ProductoDAO {
     public static boolean  agregarBebida(Bebida b){
        boolean  resp=false;
        
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "INSERT INTO producto (marca,precio,nombre,id_tipopr,stock,azucar,lata) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
      
             pst1.setString(1, b.getMarca() );
    
             pst1.setInt(2, b.getPrecio());
 
             
             pst1.setString(3, b.getNombre());
   
             pst1.setInt(4, b.getTipoProducto());
  
                pst1.setInt(5, b.getStock());
                          
            pst1.setString(6, b.isAzucar()?"si":"no");
                
                pst1.setString(7, b.isLata()?"si":"no");
            int lineas = pst1.executeUpdate();
            
            if (lineas > 0){
        resp=true;
                
            }else{
                System.out.println("aqui corta");
            }    
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return resp;
    }
 public static int seq() {
     int id=0;
        try (Connection cone=ConexionOracle.getCone()){
            String sql ="SELECT * FROM PRODUCTO";
            CallableStatement sta=cone.prepareCall(sql);
            ResultSet rs=sta.executeQuery();
            
            while(rs.next()){
      
            id=rs.getInt("id_producto");
 

            }
        } catch (Exception e) {
            
        }
        return id;
}

 
 
 
  public static int stock(int id) {
     int stock=1;
        try (Connection cone=ConexionOracle.getCone()){
            String sql ="SELECT stock FROM PRODUCTO where id_producto=?";
            CallableStatement sta=cone.prepareCall(sql);
            sta.setInt(1, id);
            ResultSet rs=sta.executeQuery();
   
             
            if (rs.next()) {
              stock=rs.getInt("stock");
  
            }

         
 

            
        } catch (Exception e) {
            
        }
        
        return stock;
}
 
 
 
 
 
     
     
     
     public static boolean  agregarYogurt(Yogurt b){
        boolean  resp=false;
        
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "INSERT INTO producto (marca,precio,nombre,stock,id_tipopr,fch_vnc,grasas) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        
            String fechaParseada=formato.format(b.getFecha_vencimiento()) ;
            
             pst1.setString(1, b.getMarca() );
             pst1.setInt(2, b.getPrecio());
             pst1.setString(3, b.getNombre());
             pst1.setInt(4, b.getStock());
             pst1.setInt(5, b.getTipoProducto());
             pst1.setString(6, fechaParseada);
             pst1.setString(7, b.isGrasas()?"si":"no");
                
            int lineas = pst1.executeUpdate();
            
            if (lineas > 0){
         
                    resp = true;
                
                
            }else{
                System.out.println("aqui corta");
            }    
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return resp;
    }
     
     
      public static boolean  agregarManzana(Producto b){
        boolean  resp=false;
        
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "INSERT INTO producto (precio,nombre,stock,id_tipopr, color, peso) VALUES(?,?,?,?,?,?)";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
      

                pst1.setInt(1, b.getPrecio());
                pst1.setString(2, b.getNombre());
                pst1.setInt(3, b.getStock());
                pst1.setInt(4, b.getTipoProducto());
                pst1.setString(5, b.getColor());
                pst1.setInt(6, b.getPeso());
             
            int lineas = pst1.executeUpdate();
            
            if (lineas > 0){
            resp = true;
                
            }else{
                System.out.println("aqui corta");
            }    
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return resp;
    }
     
    
     public static Producto  buscarProducto(int id){
        boolean  resp=false;
        Producto producto=new Producto();
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "select id_producto, precio,nombre,stock,id_tipopr,marca  from producto where id_producto=?";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
            pst1.setInt(1, id);
      
             
           ResultSet rs=pst1.executeQuery();
            while (rs.next()) {                
                
                producto.setPrecio(rs.getInt("precio"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setId(rs.getInt("id_producto"));
                producto.setTipoProducto(rs.getInt("id_tipopr"));
                if (rs.getString("marca")!=null) {
                       producto.setMarca(rs.getString("marca"));
                }else{
                    producto.setMarca("no aplica");
                }
             
                
            }
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return producto;
    }
     
     
         
     public static Producto  buscarBebida(int id){
        boolean  resp=false;
        Producto producto=new Producto();
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "select \n" +
"marca,precio,nombre,\n" +
"stock,\n" +
"lata,\n" +
"azucar\n" +
"from producto where Id_Producto=?";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
            pst1.setInt(1, id);
      
             
           ResultSet rs=pst1.executeQuery();
            while (rs.next()) {                
                producto.setPrecio(rs.getInt("precio"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setMarca(rs.getString("marca"));
                if (rs.getString("lata").equals("si")) {
                 producto.setLata(true); 

                }else{
                          producto.setLata(false); 
                }
                 if (rs.getString("azucar").equals("si")) {
                           producto.setAzucar(true); 
                 }else{
                         producto.setAzucar(false); 
                 }
                
              
             
                
            }
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return producto;
    }
     
     public static Producto  buscarYogurt(int id){
        boolean  resp=false;
        Yogurt producto=new Yogurt();
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "select \n" +
"precio,nombre,stock,fch_vnc,grasas,Id_Producto\n" +
"from producto where Id_Producto=?";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
            pst1.setInt(1, id);
      
            SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy"); 
           ResultSet rs=pst1.executeQuery();
            while (rs.next()) {                
                producto.setPrecio(rs.getInt("precio"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setMarca("no aplica");
                producto.setFecha_vencimiento(formato.parse(rs.getString("fch_vnc")));
                producto.setColor(rs.getString("grasas"));
                

            }
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return producto;
    }
     
     
     public static Producto  buscarManzana(int id){
        boolean  resp=false;
        Manzana producto=new Manzana();
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "select \n" +
"precio,nombre,stock,peso,color,Id_Producto\n" +
"from producto where Id_Producto=?";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
            pst1.setInt(1, id);
      
             
           ResultSet rs=pst1.executeQuery();
            while (rs.next()) {                
                producto.setPrecio(rs.getInt("precio"));
                producto.setNombre(rs.getString("nombre"));
                producto.setStock(rs.getInt("stock"));
                producto.setMarca("no aplica");
                producto.setPeso(rs.getInt("peso"));
                producto.setColor(rs.getString("color"));
                

            }
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return producto;
    }
     
     
     
     
        public static boolean buscar(int id) throws SQLException {
    boolean existe = false;

    try (Connection conn = ConexionOracle.getCone();
         PreparedStatement pst = conn.prepareStatement("SELECT 1 FROM producto WHERE id_producto = ?")) {
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        existe = rs.next();
    }

    return existe;
}
  public static Object[] buscarNombre(String nombre) {

        Object[]resp=new Object[2];
        resp[0]=false;
        resp[1]="";
        Cliente p = new Cliente();
        try (Connection cone=ConexionOracle.getCone()){
            String sql = "select nombre from producto \n" +
"where nombre=?";
            PreparedStatement pst = cone.prepareStatement(sql);
            pst.setString(1, nombre);
      
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                if (rs.getString("nombre").toLowerCase().equals(nombre)) {
              
                resp[0]=true;
                resp[1]=rs.getString("nombre");
            }
              
            }
                  
        } 
        
        catch (Exception e) {
            
            System.out.println("ERROR: " + e.getMessage() );
        }
        return resp;
    }
          
     
         
         
    public static boolean modificarManzana(Manzana b, String id) {
    boolean resp = false;

    try (Connection cone = ConexionOracle.getCone()) {
        String sql1 = "update Producto set nombre=?,precio=?,stock=?,peso=?,Id_Tipopr=?,color=?where Id_Producto=?";
        PreparedStatement pst1 = cone.prepareStatement(sql1);

        pst1.setString(1, b.getNombre());
        pst1.setInt(2, b.getPrecio());
        pst1.setInt(3, b.getStock());
        pst1.setInt(4, b.getPeso());


        int idEntero = Integer.parseInt(id);
        pst1.setInt(5, b.getTipoProducto());
        pst1.setString(6, b.getColor()  );
        pst1.setInt(7, idEntero);

        int lineas = pst1.executeUpdate();

        if (lineas > 0) {
            resp = true;
        } else {
            System.out.println("No se pudo modificar el producto");
        }
    } catch (SQLException sql) {
        System.out.println("Error al modificar el producto: " + sql.getMessage());
    } catch (Exception e) {
        System.out.println("Error al modificar el producto: " + e.getMessage());
    }

    return resp;
}
    
    
         
    public static boolean modificarBebida(Bebida b, String id) {
    boolean resp = false;

    try (Connection cone = ConexionOracle.getCone()) {
        String sql1 = "update Producto set marca=?, nombre=?,precio=?,stock=?,Id_Tipopr=?,azucar=?,lata=?where Id_Producto=?";
        PreparedStatement pst1 = cone.prepareStatement(sql1);
        
        pst1.setString(1, b.getMarca());
        pst1.setString(2, b.getNombre());
        pst1.setInt(3, b.getPrecio());
        pst1.setInt(4, b.getStock());
        int idEntero = Integer.parseInt(id);
        pst1.setInt(5, b.getTipoProducto());
        pst1.setString(6, b.isAzucar()?"si":"no");
        pst1.setString(7, b.isLata()?"si":"no");
        pst1.setInt(8, idEntero);

        int lineas = pst1.executeUpdate();

        if (lineas > 0) {
            resp = true;
        } else {
            System.out.println("No se pudo modificar el producto");
        }
    } catch (SQLException sql) {
        System.out.println("Error al modificar el producto: " + sql.getMessage());
    } catch (Exception e) {
        System.out.println("Error al modificar el producto: " + e.getMessage());
    }

    return resp;
}
    
    public static boolean modificarYogurt(Yogurt b, String id) {
    boolean resp = false;

    try (Connection cone = ConexionOracle.getCone()) {
        String sql1 = "update Producto set marca=?, nombre=?,precio=?,stock=?,Id_Tipopr=?,grasas=?,fch_vnc=?where Id_Producto=?";
        PreparedStatement pst1 = cone.prepareStatement(sql1);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        
        String fechaParseada=formato.format(b.getFecha_vencimiento()) ;
        pst1.setString(1, b.getMarca());
        pst1.setString(2, b.getNombre());
        pst1.setInt(3, b.getPrecio());
        pst1.setInt(4, b.getStock());
        int idEntero = Integer.parseInt(id);
        pst1.setInt(5, b.getTipoProducto());
        pst1.setString(6, b.isGrasas()?"si":"no");
        pst1.setString(7, fechaParseada);
        pst1.setInt(8, idEntero);

        int lineas = pst1.executeUpdate();

        if (lineas > 0) {
            resp = true;
        } else {
            System.out.println("No se pudo modificar el producto");
        }
    } catch (SQLException sql) {
        System.out.println("Error al modificar el producto: " + sql.getMessage());
    } catch (Exception e) {
        System.out.println("Error al modificar el producto: " + e.getMessage());
    }

    return resp;
}
    
    
    
    
    
    
        public static boolean reseto(int idtop, String id) {
    boolean resp = false;

    try (Connection cone = ConexionOracle.getCone()) {
        /*String sql1 = "update Producto set marca=?, nombre=?,precio=?,stock=?,Id_Tipopr=?,grasas=?,fch_vnc=?,peso=?,color=?,azucar=?,lata=? where Id_Producto=?";
        PreparedStatement pst1 = cone.prepareStatement(sql1);
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        
        String fechaParseada=formato.format(new Date()) ;
        pst1.setString(1, "*");
        pst1.setString(2,"*");
        pst1.setInt(3, 0);
        pst1.setInt(4, 0);
        int idEntero = Integer.parseInt(id);
        pst1.setInt(5, idtop);
        pst1.setString(6, null);
        pst1.setString(7, null);
        pst1.setInt(8, 0);
        pst1.setString(9, null);
        pst1.setString(10, null);
        pst1.setString(11, null);
        pst1.setInt(12, idEntero);*/
        
        String sql1 = "update Producto set marca='*', nombre='*',precio=0,stock=0,Id_Tipopr=?,grasas=null,fch_vnc=null,peso=null,color=null,azucar=null,lata=null where Id_Producto=?";
        int idEntero = Integer.parseInt(id);
        PreparedStatement pst1 = cone.prepareStatement(sql1);
        pst1.setInt(1, idtop);
        pst1.setInt(2, idEntero);
        int lineas = pst1.executeUpdate();

        if (lineas > 0) {
            resp = true;
        } else {
            System.out.println("No se pudo modificar el producto");
        }
    } catch (SQLException sql) {
        System.out.println("Error al modificar el producto: " + sql.getMessage());
    } catch (Exception e) {
        System.out.println("Error al modificar el producto: " + e.getMessage());
    }

    return resp;
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     public static boolean  eliminar(int id){
        boolean  resp=false;
        
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "delete from Producto\n" +
"where Id_Producto=? ";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
      
pst1.setInt(1,id);
             
            int lineas = pst1.executeUpdate();
            
            if (lineas > 0){
            resp = true;
                
            }else{
                System.out.println("aqui corta");
            }    
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return resp;
    }
     
    
     
     
     
    /*public List<Producto>listarProductos(){
        List<Producto>productos=new ArrayList<>();
        try (Connection cone=ConexionOracle.getCone()){
            String sql ="SELECT * FROM PRODUCTO";
            CallableStatement sta=cone.prepareCall(sql);
            ResultSet rs=sta.executeQuery();
            while(rs.next()){
            System.out.println("hola");
            int id=rs.getInt("id_producto");
            String marca=rs.getString("marca");
            int precio=rs.getInt("precio");
            String nombre=rs.getString("nombre");
           
Producto p=new Producto(id, marca, precio, nombre);
productos.add(p);
            }
        } catch (Exception e) {
            
        }
        return productos;
    } */
     
     
     
     public List<Producto>listarProductos(){
        List<Producto>productos=new ArrayList<>();
        try (Connection cone=ConexionOracle.getCone()){
            String sql ="select \n" +
"P.id_producto id,\n" +
"P.marca,\n" +
"to_char(P.precio,'$999G999G999') PRECIO,\n" +
"P.nombre,\n" +
"tp.descripcion TIPO,\n" +
"s.cantidad STOCK\n" +
"\n" +
"from producto P\n" +
"INNER JOIN tipo_producto TP\n" +
"ON tp.id_tipopr=p.id_tipopr\n" +
"INNER JOIN stock S\n" +
"ON s.id_stock = p.id_stock";
            CallableStatement sta=cone.prepareCall(sql);
            ResultSet rs=sta.executeQuery();
            while(rs.next()){
            int id=rs.getInt("id");
            String marca=rs.getString("P.marca");
            int precio=rs.getInt("precio");
            String nombre=rs.getString("P.nombre");
            String tipo=rs.getString("tipo");
            int stock=rs.getInt("stock");
           
Producto p=new Producto(id, marca, precio, nombre);
productos.add(p);
            }
        } catch (Exception e) {
            
        }
        return productos;
    }
     
     
     
     
     
     public static ArrayList<Object> listarReporte() {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("select \n" +
"to_char(sum( P.precio),'$999G999') AS PRECIO,\n" +
"sum(P.stock) stock,\n" +
"Tp.descripcion tipo\n" +
"from producto p\n" +
"INNER JOIN Tipo_Producto tp\n" +
"ON Tp.Id_Tipopr = p.id_Tipopr\n" +
"GROUP by Tp.descripcion")) {


        ResultSet rs = pst.executeQuery();
       
        ResultSetMetaData rsmd = rs.getMetaData();

        // Recorre los resultados de la consulta y agrega cada fila a la lista de información
        while (rs.next()) {
            Object[] objetos = new Object[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                objetos[i] = rs.getObject(i+1);
            }
            info.add(objetos);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return info;
}    
     
     
     
     
}
