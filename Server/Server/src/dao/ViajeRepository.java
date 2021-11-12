package dao;

import config.ConnectionDB;
import entities.Entrega;
import entities.Envio;
import entities.Viaje;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViajeRepository {

    ConnectionDB conn = new ConnectionDB();

    public Viaje insert(Viaje viaje) {

        try {
            String query = "INSERT INTO public.viaje( \n"
                    + "	id_camion, \n"
                    + "	fecha_alta, \n"
                    + "	usuario_alta, \n"
                    + "	fecha_modificacion, \n"
                    + "	usuario_modificacion, \n"
                    + "	clave_estado)\n"
                    + "	VALUES (" + viaje.getIdCamion() + ", "
                    + "now(), "
                    + "'" + viaje.getUsuarioAlta() + "', "
                    + "now(), "
                    + "'" + viaje.getUsuarioModificacion() + "', "
                    + "'AC'"
                    + ")returning id_viaje;";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            viaje.setIdViaje(rs.getInt("id_viaje"));
            st.close();
            rs.close();
            return viaje;
        } catch (SQLException ex) {
            Logger.getLogger(ViajeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public Viaje findById(String id){
        try {
            String sql = "select * from viaje where id_viaje = "+id+" and clave_estado = 'AC'";
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            Viaje viaje = new Viaje();
            viaje.setIdViaje(rs.getInt("id_viaje"));
            viaje.setIdCamion(rs.getInt("id_camion"));
            viaje.setUsuarioAlta(rs.getString("usuario_alta"));
            viaje.setFechaAlta(rs.getString("fecha_alta"));
            viaje.setFechaModificacion(rs.getString("usuario_modificacion"));
            viaje.setFechaModificacion(rs.getString("fecha_modificacion"));
            viaje.setFechaBaja(rs.getString("usuario_baja"));
            viaje.setFechaBaja(rs.getString("fecha_baja"));
            viaje.setClaveEstado(rs.getString("clave_estado"));
            st.close();
            rs.close();
            return viaje;
        } catch (SQLException ex) {
            Logger.getLogger(CamionRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Envio> findEnvios(String id) {
        try{
            String sql = "select * from envio where id_viaje = "+id;
            System.out.println(sql);
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Envio> envios = new ArrayList();
            while(rs.next()){
                Envio envio = new Envio();
                envio.setIdEnvio(rs.getInt("id_envio"));
                envio.setIdAlmacen(rs.getInt("id_almacen"));
                envio.setIdTienda(rs.getInt("id_tienda"));
                envio.setIdViaje(rs.getInt("id_viaje"));
                envio.setVolumen(rs.getFloat("volumen"));
                envio.setPesoMaximo(rs.getFloat("peso_maximo"));
                envio.setUsuarioAlta(rs.getString("usuario_alta"));
                envio.setFechaAlta(rs.getString("fecha_alta"));
                envio.setFechaModificacion(rs.getString("usuario_modificacion"));
                envio.setFechaModificacion(rs.getString("fecha_modificacion"));
                envio.setFechaBaja(rs.getString("usuario_baja"));
                envio.setFechaBaja(rs.getString("fecha_baja"));
                envio.setClaveEstado(rs.getString("clave_estado"));
                envios.add(envio);
            }
            st.close();
            rs.close();
            return envios;
        }catch(Exception e){
            System.out.println("FALLO ALGO");
            return null;
        }
    }

    public boolean entregarEnvio(Entrega entrega) {
        boolean respuesta = false;
        try{
            String query = "insert into entrega("
                    + "id_envio,"
                    + "fecha_entrega,"
                    + "fecha_alta,"
                    + "usuario_alta,"
                    + "fecha_modificacion,"
                    + "usuario_modificacion,"
                    + "clave_estado"
                    + ")"
                    + "values"
                    + "("
                    + entrega.getIdEnvio() + ","
                    + "now(),"
                    + "now(),"
                    + "'"+ entrega.getUsuarioAlta() + "' ,"
                    + "now(),"
                    + "'" + entrega.getUsuarioModificacion()+ "' ,"
                    + "'" +entrega.getClaveEstado()+ "'"
                    + ") returning id_entrega;";
            System.out.println(query);
            Statement st = conn.obtenerConexion().createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            st.close();
            rs.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;            
        }
    }
}
