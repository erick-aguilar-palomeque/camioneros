package dao;

import config.ConnectionDB;
import entities.Envio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvioRepository {

		ConnectionDB conn = new ConnectionDB();

		public Envio insert(Envio envio) {

				try {
						String query = "INSERT INTO public.envio(\n"
										+ "	id_almacen, \n"
										+ "	id_tienda, \n"
										+ "	id_viaje, \n"
										+ "	volumen, \n"
										+ "	peso_maximo, \n"
										+ "	fecha_alta, \n"
										+ "	usuario_alta, \n"
										+ "	fecha_modificacion, \n"
										+ "	usuario_modificacion, \n"
										+ "	clave_estado)\n"
										+ "	VALUES (\n"
										+ "			" + envio.getIdAlmacen() + ", \n"
										+ "			" + envio.getIdTienda() + ", \n"
										+ "			" + envio.getIdViaje() + ", \n"
										+ "			" + envio.getVolumen() + ", \n"
										+ "			" + envio.getPesoMaximo() + ", \n"
										+ "now(), "
										+ "'" + envio.getUsuarioAlta() + "', "
										+ "now(), "
										+ "'" + envio.getUsuarioModificacion() + "', "
										+ "'EC'"
										+ ")returning id_envio;";
						Statement st = conn.obtenerConexion().createStatement();
						ResultSet rs = st.executeQuery(query);
						rs.next();
						envio.setIdEnvio(rs.getInt("id_envio"));
						st.close();
						rs.close();
						return envio;
				} catch (SQLException ex) {
						Logger.getLogger(EnvioRepository.class.getName()).log(Level.SEVERE, null, ex);
						return null;
				}
		}

}
