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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santi
 */
public class ClienteDAO {
    Conexion cn = new Conexion();
    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    int r;
    
    public Cliente buscar(String dni){
          Cliente cli = new Cliente();  
          String sql = "select * from cliente where Dni="+dni;
          try{
              con = cn.Conexion();
              ps=con.prepareStatement(sql);
              rs=ps.executeQuery();
              while(rs.next()){
                  cli.setId(rs.getInt(1));
                  cli.setDni(rs.getString(2));
                  cli.setNom(rs.getString(3));
                  cli.setDir(rs.getString(4));
                  cli.setEs(rs.getString(5));
              }
              
              
              
          }
          catch(SQLException e){
                      }
        return cli;
    }
    
    //Operaciones 
    
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Cliente cl=new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEs(rs.getString(5));               
                lista.add(cl);
            }
        } catch (Exception e) {
        }
        return lista;
    }
}
