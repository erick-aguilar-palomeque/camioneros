package controller;

import com.google.gson.Gson;
import dao.EnvioRepository;
import dao.ViajeRepository;
import entities.Envio;
import entities.Viaje;
import org.json.JSONObject;

public class ViajeController {

		ViajeRepository viajeRepository = new ViajeRepository();
		EnvioRepository envioRepository = new EnvioRepository();
		Gson gson = new Gson();

		public JSONObject menuViaje(int opcSubmenu, String data) {

				JSONObject json = new JSONObject();
				boolean done = true;
				String mensaje = "Todo bien";

				switch (opcSubmenu) {
						case 1://ALTA
								System.out.println("ALTA DE VIAJES");
								System.out.println(data);
								Viaje viajeInsertado = viajeRepository.insert(getViaje(data));
								String viajeInsertadoString = gson.toJson(viajeInsertado);
								if (viajeInsertado == null) {
										System.out.println("fallando en viaje insertado");
										done = false;
										mensaje = "Ha ocurrido un error al insertar el viaje";
								} else {
										json.put("data", viajeInsertadoString);
								}
								break;
						case 4:
								System.out.println("ALTA DE ENVIOS");
								System.out.println(data);
								Envio envioInsertado = envioRepository.insert(getEnvio(data));
								String envioInsertadoString = gson.toJson(envioInsertado);
								if (envioInsertado == null) {
										System.out.println("fallando en envio insertado");
										done = false;
										mensaje = "Ha ocurrido un error al insertar el envio";
								} else {
										json.put("data", envioInsertadoString);
								}
								break;
				}
				json.put("done", done);
				json.put("mensaje", mensaje);
				return json;

		}

		public Viaje getViaje(String cadena) {
				return gson.fromJson(cadena, Viaje.class);
		}
		
		public Envio getEnvio(String cadena) {
				return gson.fromJson(cadena, Envio.class);
		}
		
}
