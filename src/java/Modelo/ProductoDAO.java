/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author santi
 */
public class ProductoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Producto buscar(int id) {
        Producto pro = new Producto(); // instaciamos para almacenar aca la busqueda del producto
        String sql = "select * from producto where idproducto=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(rs.getInt(1));
                pro.setNom(rs.getString(2));
                pro.setPre(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));

            }
        } catch (Exception e) {
        }

        return pro;
    }
    
    public Producto listarId(int id){
        Producto pr=new Producto();
        String sql="select * from producto where IdProducto="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                pr.setId(rs.getInt(1));
                pr.setNom(rs.getString(2));               
                pr.setPre(rs.getDouble(3));
                pr.setStock(rs.getInt(4));
                pr.setEstado(rs.getString(5));  
            }
        } catch (SQLException e) {
        }
        return pr;
    }
    
    public int actualizarstock( int id , int stock) {
     String sql="update producto set Stock=? where idproducto=?";
     try{
         con=cn.Conexion();
         ps=con.prepareStatement(sql);
         ps.setInt(1, stock);
         ps.setInt(2, id);
         ps.executeUpdate();
     }
     catch(SQLException e){
     }
        return r;
    }
    

}
