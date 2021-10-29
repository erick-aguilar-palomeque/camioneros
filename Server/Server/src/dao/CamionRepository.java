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
    public List<Camion> findAll(){
        List<Camion> result = new ArrayList();
        try {
            ConnectionDB conn = new ConnectionDB();
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
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        }
    }
    
}
