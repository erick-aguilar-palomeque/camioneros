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

       public JSONArray R1_TiendasPorCamion() {
        JSONArray tiendas = new JSONArray();
        JSONObject tienda;
        try {
            String sql = "select * from R1_TiendasPorCamion();";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tienda = new JSONObject();
                tienda.put("id_camion", rs.getInt("id_camion"));
                tienda.put("placas", rs.getString("placas"));
                tienda.put("id_tienda", rs.getInt("id_tienda"));
                tienda.put("nombre", rs.getString("nombre"));
                tienda.put("direccion", rs.getString("direccion"));
                tiendas.put(tienda);
            }
            st.close();
            rs.close();
            return tiendas;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    /**
     * Llama al Pl para el reporte 2. Muestra los envios que viajaron en un
     * camion dado.
     *
     * @param placa El numero de placa del camion
     * @return Una lista de JSONObject que representa cada envio encontrado.
     */
    public JSONArray r2_enviosDadoPlacas(String placa) {
        JSONArray envios = new JSONArray();
        JSONObject envio;
        try {
            String sql = "select * from R2_EnviosDadoPlacas('" + placa + "');";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                envio = new JSONObject();
                envio.put("id_envio", rs.getInt("id_envio"));
                envio.put("id_almacen", rs.getInt("id_almacen"));
                envio.put("id_tienda", rs.getInt("id_tienda"));
                envio.put("id_viaje", rs.getInt("id_viaje"));
                envio.put("volumen", rs.getDouble("volumen"));
                envio.put("peso_maximo", rs.getDouble("peso_maximo"));
                envio.put("descripcion", rs.getString("descripcion"));
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

    public JSONArray r3_tiendasCantidadPeso(String data) {
        JSONArray tiendas = new JSONArray();
        JSONObject tienda;
        String datos[]=data.split(";");
        try {
            String sql = "select * from R3_TiendasCantidadPeso("+datos[0]+","+datos[1]+");";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tienda = new JSONObject();
                tienda.put("nombre", rs.getString("nombre"));
                tiendas.put(tienda);
            }
            st.close();
            rs.close();
            return tiendas;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public JSONArray r4_enviosPorTienda(String data) {
        JSONArray envios = new JSONArray();
        JSONObject envio;
        try {
            String sql = "select * from R4_enviosPorTienda('"+data+"');";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                envio = new JSONObject();
                envio.put("id_envio", rs.getString("id_envio"));
                envio.put("volumen", rs.getFloat("volumen"));
                envio.put("peso_maximo", rs.getFloat("peso_maximo"));
                envio.put("id_viaje", rs.getInt("id_viaje"));
                envio.put("id_camion", rs.getInt("id_camion"));
                envio.put("placa", rs.getString("placa"));
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
    public JSONArray  r5_camionConMasViajes(String data) {
        JSONArray camiones = new JSONArray();
        JSONObject camion;
        try {
            String sql = "select * from  R5_CamionConMasViajes("+data+");;";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                camion = new JSONObject();
                camion.put("id_camion", rs.getInt("id_camion"));
                camion.put("placas", rs.getString("placas"));
                camion.put("volumen", rs.getFloat("volumen"));
                camion.put("peso_maximo",rs.getFloat("peso_maximo"));
                camion.put("veces",rs.getInt("veces"));
                camiones.put(camion);
            }
            st.close();
            rs.close();
            return camiones;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public JSONArray  r6_tiendasMasPiezas(String data) {
        JSONArray tiendas = new JSONArray();
        JSONObject tienda;
        try {
            String sql = "select * from  R6_TiendasMasPiezas("+data+");";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tienda = new JSONObject();
                tienda.put("id_tienda", rs.getInt("id_tienda"));
                tienda.put("nombre", rs.getString("nombre"));
                tienda.put("direccion", rs.getString("direccion"));
                tienda.put("num_envios", rs.getLong("num_envios"));
                tiendas.put(tienda);
            }
            st.close();
            rs.close();
            return tiendas;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public JSONArray  r7_camionesPesoSuperado() {
        JSONArray camiones = new JSONArray();
        JSONObject camion;
        try {
            String sql = "select * from R7_CamionesPesoSuperado();";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                camion = new JSONObject();
                camion.put("id_camion", rs.getInt("id_camion"));
                camion.put("placas", rs.getString("placas"));
                camion.put("peso_maximo", rs.getFloat("peso_maximo"));
                camion.put("veces", rs.getInt("veces"));
                camiones.put(camion);
            }
            st.close();
            rs.close();
            return camiones;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public JSONArray  r8_mesConMenosViajes() {
        JSONArray meses = new JSONArray();
        JSONObject mes;
        try {
            String sql = "select * from  R8_MesConMenosViajes();";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mes = new JSONObject();
                mes.put("mes", rs.getString("mes"));
                mes.put("noviajes", rs.getInt("noviajes"));
                meses.put(mes);
            }
            st.close();
            rs.close();
            return meses;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public JSONArray  r9_mesConMasViajes() {
        JSONArray meses = new JSONArray();
        JSONObject mes;
        try {
            String sql = "select * from  R9_MesConMasViajes();";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mes = new JSONObject();
                mes.put("mes", rs.getString("mes"));
                mes.put("noviajes", rs.getInt("noviajes"));
                meses.put(mes);
            }
            st.close();
            rs.close();
            return meses;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public JSONArray R10_ViajesCamionPorFecha(String data) {
        String fechas[] = data.split(";");
        JSONArray datos = new JSONArray();
        JSONObject dato;
        try {
            String sql = "select * from  R10_ViajesCamionPorFecha('"+fechas[0]+"', '"+fechas[1]+"');";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dato = new JSONObject();
                dato.put("id_camion", rs.getInt("id_camion"));
                dato.put("id_viaje", rs.getInt("id_viaje"));
                dato.put("id_envio", rs.getInt("id_envio"));
                dato.put("volumen", rs.getFloat("volumen"));
                dato.put("id_tienda", rs.getInt("id_tienda"));
                datos.put(dato);
            }
            st.close();
            rs.close();
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
