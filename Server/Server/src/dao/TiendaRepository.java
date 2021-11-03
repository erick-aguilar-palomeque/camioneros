/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConnectionDB;
import entities.Tienda;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author titof
 */
public class TiendaRepository {

    ConnectionDB conn = new ConnectionDB();

    public Tienda insert(Tienda tienda) {
        try {
            String query = "INSERT INTO tienda(\n"
                    + "nombre, \n"
                    + "direccion, \n"
                    + "fecha_alta, \n"
                    + "usuario_alta, \n"
                    + "fecha_modificacion, \n"
                    + "usuario_modificacion, \n"
                    + "clave_estado)\n"
                    + "VALUES ("
                    + "'" + tienda.getNombre() + "', "
                    + "'" + tienda.getDireccion() + "', "
                    + "now(), "
                    + "'" + tienda.getUsuarioAlta() + "', "
                    + "now(), "
                    + "'" + tienda.getUsuarioModificacion() + "', "
                    + "'AC'"
                    + ")returning id_tienda;";
            System.out.println("QUERY: " + query);
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            tienda.setIdTienda(rs.getInt("id_tienda"));
            st.close();
            rs.close();
            return tienda;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean delete(Tienda tienda) {
        try {
            String query = "UPDATE tienda\n"
                    + "SET \n"
                    + "clave_estado='BA',\n"
                    + "usuario_baja='" + tienda.getUsuarioBaja() + "', \n"
                    + "fecha_baja= now()\n"
                    + "WHERE id_tienda = " + tienda.getIdTienda() + ";";
            Statement st = conn.obtenerConexion().createStatement();
            int filasAfectadas = st.executeUpdate(query);
            if (filasAfectadas == 0) {
                return false;
            }
            st.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Tienda update(Tienda tienda) {
        try {
            String query = "UPDATE tienda\n"
                    + "SET \n"
                    + "nombre='" + tienda.getNombre()+ "', \n"
                    + "direccion='" + tienda.getDireccion()+ "', \n"
                    + "fecha_modificacion= now(), \n"
                    + "usuario_modificacion='" + tienda.getUsuarioModificacion() + "'\n"
                    + "WHERE id_tienda = " + tienda.getIdTienda()+ ";";
            System.out.println(query);
            Statement st = conn.obtenerConexion().createStatement();
            int filasAfectadas = st.executeUpdate(query);
            if (filasAfectadas == 0) {
                return null;
            }
            st.close();
            return findById("" + tienda.getIdTienda());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Tienda findById(String id) {
        try {
            String sql = "select * from tienda where id_tienda = " + id + " and clave_estado = 'AC'";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            Tienda tienda = new Tienda();
            tienda.setIdTienda(rs.getInt("id_tienda"));
            tienda.setNombre(rs.getString("nombre"));
            tienda.setDireccion(rs.getString("direccion"));
            tienda.setUsuarioAlta(rs.getString("usuario_alta"));
            tienda.setFechaAlta(rs.getString("fecha_alta"));
            tienda.setFechaModificacion(rs.getString("usuario_modificacion"));
            tienda.setFechaModificacion(rs.getString("fecha_modificacion"));
            tienda.setFechaBaja(rs.getString("usuario_baja"));
            tienda.setFechaBaja(rs.getString("fecha_baja"));
            tienda.setClaveEstado(rs.getString("clave_estado"));
            st.close();
            rs.close();
            return tienda;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public List<Tienda> findAll(){
        List<Tienda> result = new ArrayList();
        try {
            String sql = "select * from tienda where clave_estado = 'AC'";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            String aidi;
            while (rs.next()) {
                Tienda tienda = new Tienda();
                tienda.setIdTienda(rs.getInt("id_tienda"));
                tienda.setNombre(rs.getString("nombre"));
                tienda.setDireccion(rs.getString("direccion"));
                tienda.setUsuarioAlta(rs.getString("usuario_alta"));
                tienda.setFechaAlta(rs.getString("fecha_alta"));
                tienda.setFechaModificacion(rs.getString("usuario_modificacion"));
                tienda.setFechaModificacion(rs.getString("fecha_modificacion"));
                tienda.setFechaBaja(rs.getString("usuario_baja"));
                tienda.setFechaBaja(rs.getString("fecha_baja"));
                tienda.setClaveEstado(rs.getString("clave_estado"));
                result.add(tienda);
            }
            st.close();
            rs.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
