package dao;

import config.ConnectionDB;
import entities.Viaje;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}
