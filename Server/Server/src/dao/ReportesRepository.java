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
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportesRepository {
    ConnectionDB conn = new ConnectionDB();
    /**
     * Llama al Pl para el reporte 2.
     * Muestra los envios que viajaron en un camion dado.
     * @param placa  El numero de placa del camion
     * @return Una lista de JSONObject que representa cada envio encontrado.
     */
     public JSONArray r2_enviosDadoPlacas(String placa){
        JSONArray envios = new JSONArray();
        JSONObject envio;
        try {
            String sql = "select * from R2_EnviosDadoPlacas('"+placa+"');";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                envio= new JSONObject();
                envio.put("id_envio",rs.getInt("id_envio"));
                envio.put("id_almacen",rs.getInt("id_almacen"));
                envio.put("id_tienda",rs.getInt("id_tienda"));
                envio.put("id_viaje",rs.getInt("id_viaje"));
                envio.put("volumen",rs.getDouble("volumen"));
                envio.put("peso_maximo",rs.getDouble("peso_maximo"));   
                envio.put("descripcion",rs.getString("descripcion"));   
                envios.put(envio);
            }
            st.close();
            rs.close();
            return envios;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
