/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import config.ConnectionDB;
import entities.Almacen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desig
 */
public class AlmacenRepository {
     ConnectionDB conn = new ConnectionDB();
    public Almacen insert(Almacen almacen){
        try{
            String query = "INSERT INTO almacen(\n" +
            "nombre, \n" +
            "direccion, \n" +
            "fecha_alta, \n" +
            "usuario_alta, \n" +
            "fecha_modificacion, \n" +
            "usuario_modificacion, \n" +
            "clave_estado)\n" +
            "VALUES ("
                + "'" + almacen.getNombre() +"', "
                + "'" + almacen.getDireccion()+"', "
                + "now(), "
                + "'" + almacen.getUsuarioAlta() +"', "
                + "now(), "
                + "'" + almacen.getUsuarioModificacion()+"', "
                + "'AC'"
                + ")returning id_almacen;";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            almacen.setIdAlmacen(rs.getInt("id_almacen"));
            st.close();
            rs.close();
            return almacen;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public List<Almacen> findAll(){
        List<Almacen> result = new ArrayList();
        try {
            String sql = "select * from almacen where clave_estado = 'AC'";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            String aidi;
            while (rs.next()) {
                Almacen almacen = new Almacen();
                almacen.setIdAlmacen(rs.getInt("id_almacen"));
                almacen.setNombre(rs.getString("nombre"));
                almacen.setDireccion(rs.getString("direccion"));
                almacen.setUsuarioAlta(rs.getString("usuario_alta"));
                almacen.setFechaAlta(rs.getString("fecha_alta"));
                almacen.setFechaModificacion(rs.getString("usuario_modificacion"));
                almacen.setFechaModificacion(rs.getString("fecha_modificacion"));
                almacen.setFechaBaja(rs.getString("usuario_baja"));
                almacen.setFechaBaja(rs.getString("fecha_baja"));
                almacen.setClaveEstado(rs.getString("clave_estado"));
                result.add(almacen);
            }
            st.close();
            rs.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Almacen update(Almacen almacen){
        try{
            String query = "UPDATE almacen\n" +
            "SET \n" +
            "nombre='"+almacen.getNombre()+"',\n" +
            "direccion='"+almacen.getDireccion()+"',\n" +
            "fecha_modificacion= now(), \n" +
            "usuario_modificacion='"+almacen.getUsuarioModificacion()+"'\n" +
            "WHERE id_almacen = "+almacen.getIdAlmacen()+";";
            System.out.println(query);
            Statement st = conn.obtenerConexion().createStatement();
            int filasAfectadas  = st.executeUpdate(query);
            if(filasAfectadas == 0){
                return null;
            }
            st.close();
            return findById("" + almacen.getIdAlmacen());
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public boolean delete(Almacen almacen){
        try{
            String query = "UPDATE almacen\n" +
            "SET \n" +
            "clave_estado='BA',\n" +
            "usuario_baja='"+almacen.getUsuarioBaja()+"', \n" +
            "fecha_baja= now()\n" +
            "WHERE id_almacen = "+almacen.getIdAlmacen()+";";
            Statement st = conn.obtenerConexion().createStatement();
            int filasAfectadas  = st.executeUpdate(query);
            if(filasAfectadas == 0){
                return false;
            }
            st.close();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    public Almacen findById(String id){
        try {
            String sql = "select * from almacen where id_almacen = "+id+" and clave_estado = 'AC'";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            Almacen almacen = new Almacen();
            almacen.setIdAlmacen(rs.getInt("id_almacen"));
            almacen.setNombre(rs.getString("nombre"));
            almacen.setDireccion(rs.getString("direccion"));
            almacen.setUsuarioAlta(rs.getString("usuario_alta"));
            almacen.setFechaAlta(rs.getString("fecha_alta"));
            almacen.setFechaModificacion(rs.getString("usuario_modificacion"));
            almacen.setFechaModificacion(rs.getString("fecha_modificacion"));
            almacen.setFechaBaja(rs.getString("usuario_baja"));
            almacen.setFechaBaja(rs.getString("fecha_baja"));
            almacen.setClaveEstado(rs.getString("clave_estado"));
            st.close();
            rs.close();
            return almacen;
        } catch (SQLException ex) {
            Logger.getLogger(AlmacenRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}

    

