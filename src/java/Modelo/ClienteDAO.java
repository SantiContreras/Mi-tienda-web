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

    public Cliente buscar(String dni) {
        Cliente cli = new Cliente();
        String sql = "select * from cliente where Dni=" + dni;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cli.setId(rs.getInt(1));
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEs(rs.getString(5));
            }

        } catch (SQLException e) {
        }
        return cli;
    }

    //Operaciones 
    public List listar() {
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
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

    public int Agregar(Cliente cli) {
        String sql = "insert into cliente(Dni , Nombres ,Direccion ,Estado) values (?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDir());
            ps.setString(4, cli.getEs());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public void Eliminar(int id) {
        String sql = "delete from cliente where Idcliente=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public int Actualizar(Cliente cli) {
        String sql = "Update cliente set Dni=? , Nombres=? ,Direccion=? , Estado=? , where Idcliente=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDir());
            ps.setString(4, cli.getEs());
            ps.setInt(5, cli.getId());
            ps.executeUpdate();

        } catch (Exception e) {
        }
        return r;
    }
    
     public Cliente listarId(int id){
        Cliente cli=new Cliente();
        String sql="select * from cliente where IdCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEs(rs.getString(5));              
            }
        } catch (Exception e) {
        }
        return cli;
    }
}
