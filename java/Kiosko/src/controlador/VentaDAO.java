/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Conexion.ConexionOracle;
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Productos.Producto;
import modelo.Productos.Yogurt;
import modelo.Venta;

/**
 *
 * @author cris
 */
public class VentaDAO {
     public static boolean  agregarVenta(Venta v){
        boolean  resp=false;
        
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "INSERT INTO venta (id_venta,fecha,cliente_rut) VALUES(?,?,?)";
            PreparedStatement pst1 = cone.prepareStatement(sql1);
      
             pst1.setInt(1, v.getId());
             pst1.setString(2, v.pasarFecha());
             pst1.setString(3, v.getRut_cliente());
            int lineas = pst1.executeUpdate();
            
            if (lineas > 0){
               resp = true;
                
            }else{
                System.out.println("aqui corta");
                
            }    
              
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
            sql.printStackTrace();
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }
     
     
    public static boolean actualizarPrecio(int id) {
    boolean resp = false;

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst1 = cone.prepareStatement("update venta set total = ? where id_venta = ?")) {

        // Calcula el monto total de la venta
        int montoTotal = 0;
        PreparedStatement pst2 = cone.prepareStatement("select sum(dv.cantidad * p.precio) as monto from detalle_venta dv"
                + " inner join producto p on dv.id_producto = p.id_producto"
                + " inner join venta v on v.id_venta = dv.id_venta"
                + " where v.id_venta = ?");
        pst2.setInt(1, id);
        ResultSet rs = pst2.executeQuery();
        if (rs.next()) {
            montoTotal = rs.getInt("monto");
        }
        rs.close();
        pst2.close();

        // Actualiza el precio de la venta
        pst1.setInt(1, montoTotal);
        pst1.setInt(2, id);
        int lineas = pst1.executeUpdate();

        if (lineas > 0) {
            resp = true;
        }
    } catch (SQLException sql) {
        System.out.println("Ocurrió un error al actualizar el precio de la venta: " + sql.getMessage());
    } catch (Exception e) {
        System.out.println("Ocurrió un error al actualizar el precio de la venta: " + e.getMessage());
    }

    return resp;
}

     
     public static boolean aumentarStock(int id,int cantidad){
             boolean resp = false;
             
    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst1 = cone.prepareStatement("update producto set stock=stock+? where id_producto=?")) {

        // Obtiene el ID de la existencia del producto
        /*int id_stock = 0;
        PreparedStatement pst2 = cone.prepareStatement("select stock.id_stock "
                + "from producto inner join stock on stock.id_stock=producto.id_stock"
                + "where producto.id_stock=?");
        pst2.setInt(1, id);
        ResultSet rs = pst2.executeQuery();
        if (rs.next()) {
            id_stock = rs.getInt("stock.id_stock");
        }
        rs.close();
        pst2.close();*/
  
        // Actualiza la cantidad de la existencia
        pst1.setInt(1, cantidad);
        pst1.setInt(2, id);
         //ResultSet rs = pst1.executeQuery();
        int lineas = pst1.executeUpdate();

        if (lineas > 0) {
            resp = true;
        }
    } catch (SQLException sql) {
        System.out.println("Ocurrió un error al actualizar la cantidad de la existencia: " + sql.getMessage());
    } catch (Exception e) {
        System.out.println("Ocurrió un error al actualizar la cantidad de la existencia: " + e.getMessage());
    }

    return resp;
     }
    
     
     
     
     
     
  public static boolean descontarStock(int id, int cantidad) {
    boolean resp = false;

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst1 = cone.prepareStatement("update producto set stock=stock-? where id_producto=?")) {
//PreparedStatement pst1 = cone.prepareCall("{call sp_aumentar_stock(?,?)}"))

        // Obtiene el ID de la existencia del producto
        /*int id_stock = 0;
        PreparedStatement pst2 = cone.prepareStatement("select stock.id_stock "
                + "from producto inner join stock on stock.id_stock=producto.id_stock"
                + "where producto.id_stock=?");
        pst2.setInt(1, id);
        ResultSet rs = pst2.executeQuery();
        if (rs.next()) {
            id_stock = rs.getInt("stock.id_stock");
        }
        rs.close();
        pst2.close();*/
  
        // Actualiza la cantidad de la existencia
        pst1.setInt(1, cantidad);
        pst1.setInt(2, id);
         //ResultSet rs = pst1.executeQuery();
        int lineas = pst1.executeUpdate();

        if (lineas > 0) {
            resp = true;
        }
    } catch (SQLException sql) {
        System.out.println("Ocurrió un error al actualizar la cantidad de la existencia: " + sql.getMessage());
    } catch (Exception e) {
        System.out.println("Ocurrió un error al actualizar la cantidad de la existencia: " + e.getMessage());
    }

    return resp;
}

    
    
    
    
    
    
    
    
    
     
     
    public static ArrayList<Object> info(int id) {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("select P.id_Producto as id,\n" +
"SUM(dv.cantidad) AS CANTIDAD,\n" +
"\n" +
"                 p.marca, \n" +
"TO_CHAR(p.precio,'$999G999G999') AS PRECIO, \n" +
"p.nombre, \n" +
"TO_CHAR(SUM((dv.cantidad*p.precio)),'$999G999')AS MONTO\n" +
"from detalle_venta dv\n" +
"inner join producto  p\n" +
"on dv.id_producto=p.id_producto\n" +
"inner join venta v\n" +
"on v.id_venta = dv.id_venta\n" +
"WHERE v.id_venta=?\n" +
"GROUP BY \n" +
"p.marca,TO_CHAR(p.precio,'$999G999G999')\n" +
",p.nombre,P.id_Producto")) {

        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        // Obtiene información sobre la estructura de los resultados de la consulta
        ResultSetMetaData rsmd = rs.getMetaData();

        // Recorre los resultados de la consulta y agrega cada fila a la lista de información
        while (rs.next()) {
            Object[] objetos = new Object[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                objetos[i] = rs.getObject(i + 1);
            }
            info.add(objetos);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return info;
}
    
    
    public static String total(int id){
         String total="";
        try (Connection cone=ConexionOracle.getCone()){
            
            String sql="select \n" +
"\n" +
"to_char (v.total,'$999G999G999')AS TOTAL\n" +
"from venta v\n" +
"INNER JOIN cliente C\n" +
"ON v.cliente_rut =RUT\n" +
"WHERE v.id_venta=?";
          PreparedStatement pst=cone.prepareCall(sql);
            pst.setInt(1, id);
            ResultSet rs=pst.executeQuery();
            rs.next();
            total=rs.getString("total");
            
        } catch (Exception e) {
        }
        return total;
    }
    
        public static int totalINT(int id){
         int total=0;
        try (Connection cone=ConexionOracle.getCone()){
            
            String sql="select \n" +
"v.total AS TOTAL\n" +
"from venta v\n" +
"INNER JOIN cliente C\n" +
"ON v.cliente_rut =RUT\n" +
"WHERE v.id_venta=?";
          PreparedStatement pst=cone.prepareCall(sql);
            pst.setInt(1, id);
            ResultSet rs=pst.executeQuery();
            rs.next();
            total=rs.getInt("total");
            
        } catch (Exception e) {
        }
        return total;
    }
    
        
             public static String ganancias(){
         String total="";
        try (Connection cone=ConexionOracle.getCone()){
            
            String sql="SELECT \n" +
"to_char(sum(total),'$999G999G999') as total\n" +
"FROM venta";
          PreparedStatement pst=cone.prepareCall(sql);
            ResultSet rs=pst.executeQuery();
            rs.next();
            total=rs.getString("total");
            
        } catch (Exception e) {
        }
        return total;
    }
    
             public static String gananciasCliente(String id){
         String total="";
        try (Connection cone=ConexionOracle.getCone()){
            
            String sql="SELECT \n" +
"to_char(sum(total),'$999G999G999') as total\n" +
"FROM venta\n" +
"where Venta.cliente_Rut=?";
          PreparedStatement pst=cone.prepareCall(sql);
           pst.setString(   1, id);
            ResultSet rs=pst.executeQuery();
            rs.next();
            total=rs.getString("total");
            
        } catch (Exception e) {
        }
        return total;
    }
             
    
         public static ArrayList<Object> ventasCliente(int id) {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("select \n" +
"to_char(v.fecha),\n" +
"to_char (v.total,'$999G999G999')AS TOTAL,\n" +
"c.nombre AS CLIENTE\n" +
"from venta v\n" +
"INNER JOIN cliente C\n" +
"ON v.cliente_rut =RUT\n" +
"WHERE v.id_venta=?")) {

        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        // Obtiene información sobre la estructura de los resultados de la consulta
        ResultSetMetaData rsmd = rs.getMetaData();

        // Recorre los resultados de la consulta y agrega cada fila a la lista de información
        while (rs.next()) {
            Object[] objetos = new Object[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                objetos[i] = rs.getObject(i + 1);
            }
            info.add(objetos);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return info;
}    
    
             
    
    public static ArrayList<Object> info2(int id) {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("select \n" +
"to_char(v.fecha),\n" +
"to_char (v.total,'$999G999G999')AS TOTAL,\n" +
"c.nombre AS CLIENTE\n" +
"from venta v\n" +
"INNER JOIN cliente C\n" +
"ON v.cliente_rut =RUT\n" +
"WHERE v.id_venta=?")) {

        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        // Obtiene información sobre la estructura de los resultados de la consulta
        ResultSetMetaData rsmd = rs.getMetaData();

        // Recorre los resultados de la consulta y agrega cada fila a la lista de información
        while (rs.next()) {
            Object[] objetos = new Object[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                objetos[i] = rs.getObject(i + 1);
            }
            info.add(objetos);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return info;
}

    
    
    public static boolean  eliminacion(int id){
         boolean  resp=false;
        
        try (Connection cone=ConexionOracle.getCone()){
            String sql1 = "delete detalle_venta\n" +
"where id_detalle_venta in(select \n" +
"dv.id_detalle_venta from detalle_venta dv\n" +
"inner join venta v\n" +
"on v.id_venta = dv.id_venta\n" +
"where v.id_venta=?)";
            
            
            PreparedStatement pst1 = cone.prepareStatement(sql1);
      
             pst1.setInt(1,id);

            int lineas = pst1.executeUpdate();
            
            if (lineas > 0){
               resp = true;
                
            }else{
                System.out.println("aqui corta");
                
            }    
              sql1="delete venta where id_venta=?";
           pst1 = cone.prepareStatement(sql1);
                pst1.setInt(1,id);

            lineas = pst1.executeUpdate();
            
            if (lineas > 0){
               resp = true;
                
            }else{
                System.out.println("aqui corta");
                
            }
        }catch (SQLException sql) {
            System.out.println("dio este error "+sql.getMessage());
            sql.printStackTrace();
        }
        
        catch (Exception e) {
            System.out.println("error "+e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }
    
    
    
    
    
    public static ArrayList<Object> listarProductos() {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("select \n" +
"P.id_Producto,\n" +
"nvl(to_char( P.fch_Vnc),'*') as vencimiento,\n" +
"nvl(P.grasas,'*') as grasas,\n" +
"nvl(P.azucar,'*')as azucar,\n" +
"nvl(P.lata,'*') as lata,\n" +
"nvl(P.color,'*') as color,\n" +
"nvl(to_char(P.peso),'*') as peso,\n" +
"to_char(P.precio,'$999G999') AS PRECIO,\n" +
"P.stock,\n" +
"NVL(P.marca,'*') AS MARCA,\n" +
"NVL(P.NOMBRE,'*') DESCRIPCION,\n" +
"Tp.descripcion tipo\n" +
"from producto p\n" +
"INNER JOIN Tipo_Producto tp\n" +
"ON Tp.Id_Tipopr = p.id_Tipopr")) {

   
        ResultSet rs = pst.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        // Recorre los resultados de la consulta y agrega cada fila a la lista de información
        while (rs.next()) {
            Object[] objetos = new Object[12];
            for (int i = 0; i < 12; i++) {
                objetos[i] = rs.getObject(i+1);
            }
            info.add(objetos);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return info;
}
  
    
       public static ArrayList<Object> listarProductosEspecifico(int id) {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("select \n" +
"P.id_Producto,\n" +
"nvl(to_char( P.fch_Vnc),'*') as vencimiento,\n" +
"nvl(P.grasas,'*') as grasas,\n" +
"nvl(P.azucar,'*')as azucar,\n" +
"nvl(P.lata,'*') as lata,\n" +
"nvl(P.color,'*') as color,\n" +
"nvl(to_char(P.peso),'*') as peso,\n" +
"to_char(P.precio,'$999G999') AS PRECIO,\n" +
"P.stock,\n" +
"NVL(P.marca,'*') AS MARCA,\n" +
"NVL(P.NOMBRE,'*') DESCRIPCION,\n" +
"Tp.descripcion tipo\n" +
"from producto p\n" +
"INNER JOIN Tipo_Producto tp\n" +
"ON Tp.Id_Tipopr = p.id_Tipopr\n" +
"where Tp.id_Tipopr=?")) {

        pst.setInt(1,id);
        ResultSet rs = pst.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        // Recorre los resultados de la consulta y agrega cada fila a la lista de información
        while (rs.next()) {
            Object[] objetos = new Object[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                objetos[i] = rs.getObject(i + 1);
            }
            info.add(objetos);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return info;
}
    
    
    
    
    
    
    
    public static ArrayList<Object> listarVentas() {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("SELECT V.id_Venta,\n" +
"V.fecha,\n" +
"V.total,\n" +
"C.rut,\n" +
"C.nombre\n" +
"FROM venta v\n" +
"inner join Cliente c\n" +
"on V.cliente_Rut=c.rut")) {

   
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
    
    
    

    public static ArrayList<Object> listarVentasCliente(String id) {
    ArrayList<Object> info = new ArrayList<>();

    try (Connection cone = ConexionOracle.getCone();
         PreparedStatement pst = cone.prepareStatement("SELECT V.id_Venta,\n" +
"V.fecha,\n" +
"to_char(V.total,'$999G999G999') AS TOTAL,\n" +
"C.rut,\n" +
"C.nombre AS CLIENTE\n" +
"FROM venta v\n" +
"inner join Cliente c\n" +
"on V.cliente_Rut=c.rut\n" +
"where C.rut=?")) {

    pst.setString(1, id);
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
