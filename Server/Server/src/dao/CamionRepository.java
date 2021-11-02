/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.ConnectionDB;
import java.util.List;
import entities.Camion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author erick
 */
public class CamionRepository {
    ConnectionDB conn = new ConnectionDB();
    public Camion insert(Camion camion){
        try{
            String query = "INSERT INTO camion(\n" +
            "placas, \n" +
            "volumen, \n" +
            "peso_maximo, \n" +
            "fecha_alta, \n" +
            "usuario_alta, \n" +
            "fecha_modificacion, \n" +
            "usuario_modificacion, \n" +
            "clave_estado)\n" +
            "VALUES ("
                + "'" + camion.getPlacas() +"', "
                + "" + camion.getVolumen() +", "
                + "" + camion.getPesoMaximo()+", "
                + "now(), "
                + "'" + camion.getUsuarioAlta() +"', "
                + "now(), "
                + "'" + camion.getUsuarioModificacion()+"', "
                + "'AC'"
                + ")returning id_camion;";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            camion.setIdCamion(rs.getInt("id_camion"));
            st.close();
            rs.close();
            return camion;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public List<Camion> findAll(){
        List<Camion> result = new ArrayList();
        try {
            String sql = "select * from camion where clave_estado = 'AC'";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            String aidi;
            while (rs.next()) {
                Camion camion = new Camion();
                camion.setIdCamion(rs.getInt("id_camion"));
                camion.setPlacas(rs.getString("placas"));
                camion.setVolumen(rs.getDouble("volumen"));
                camion.setPesoMaximo(rs.getDouble("peso_maximo"));
                camion.setUsuarioAlta(rs.getString("usuario_alta"));
                camion.setFechaAlta(rs.getString("fecha_alta"));
                camion.setFechaModificacion(rs.getString("usuario_modificacion"));
                camion.setFechaModificacion(rs.getString("fecha_modificacion"));
                camion.setFechaBaja(rs.getString("usuario_baja"));
                camion.setFechaBaja(rs.getString("fecha_baja"));
                camion.setClaveEstado(rs.getString("clave_estado"));
                result.add(camion);
            }
            st.close();
            rs.close();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public Camion update(Camion camion){
        try{
            String query = "UPDATE camion\n" +
            "SET \n" +
            "placas='"+camion.getPlacas()+"', \n" +
            "volumen="+camion.getVolumen()+", \n" +
            "peso_maximo="+camion.getPesoMaximo()+", \n" +
            "fecha_modificacion= now(), \n" +
            "usuario_modificacion='"+camion.getUsuarioModificacion()+"'\n" +
            "WHERE id_camion = "+camion.getIdCamion()+";";
            System.out.println(query);
            Statement st = conn.obtenerConexion().createStatement();
            int filasAfectadas  = st.executeUpdate(query);
            if(filasAfectadas == 0){
                return null;
            }
            st.close();
            return findById("" + camion.getIdCamion());
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public boolean delete(Camion camion){
        try{
            String query = "UPDATE camion\n" +
            "SET \n" +
            "clave_estado='BA',\n" +
            "usuario_baja='"+camion.getUsuarioBaja()+"', \n" +
            "fecha_baja= now()\n" +
            "WHERE id_camion = "+camion.getIdCamion()+";";
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
    public Camion findById(String id){
        try {
            String sql = "select * from camion where id_camion = "+id+" and clave_estado = 'AC'";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            Camion camion = new Camion();
            camion.setIdCamion(rs.getInt("id_camion"));
            camion.setPlacas(rs.getString("placas"));
            camion.setVolumen(rs.getDouble("volumen"));
            camion.setPesoMaximo(rs.getDouble("peso_maximo"));
            camion.setUsuarioAlta(rs.getString("usuario_alta"));
            camion.setFechaAlta(rs.getString("fecha_alta"));
            camion.setFechaModificacion(rs.getString("usuario_modificacion"));
            camion.setFechaModificacion(rs.getString("fecha_modificacion"));
            camion.setFechaBaja(rs.getString("usuario_baja"));
            camion.setFechaBaja(rs.getString("fecha_baja"));
            camion.setClaveEstado(rs.getString("clave_estado"));
            st.close();
            rs.close();
            return camion;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
