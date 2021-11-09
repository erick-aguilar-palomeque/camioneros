package controllers;

import com.google.gson.Gson;
import config.Peticion;
import entities.Envio;
import entities.Viaje;
import java.util.Scanner;
import org.json.JSONObject;

public class ViajeController {

		Peticion peticion = new Peticion();
		Gson gson = new Gson();

		public void insertar(int opcMenu, int opcSubmenu, String usuario) {

				Scanner input = new Scanner(System.in);
				try {
						Viaje viaje = new Viaje();
						System.out.print("\tID CAMION: ");
						viaje.setIdCamion(Integer.parseInt(input.nextLine()));
						viaje.setUsuarioAlta(usuario);
						viaje.setUsuarioModificacion(usuario);
						String viajeString = gson.toJson(viaje);
						String respuesta = peticion.pedir(opcMenu, opcSubmenu, viajeString);
						JSONObject respuestaJSON = new JSONObject(respuesta);
						if (respuestaJSON.getBoolean("done")) {
								Viaje viajeInsertado = gson.fromJson(respuestaJSON.getString("data"), Viaje.class);
								System.out.println("Viaje insertado correctamente con el ID: " + viajeInsertado.getIdViaje());
								int opc = 1;
								do {
										System.out.print("ESCRIBA y/n PARA FINALIZAR EL AGREGADO DE ENVIOS");
										String letra = input.nextLine();
										if (letra.equals("y")) {
												System.out.println("\t===== ANANIENDO ENVIO A VIAJE" + viajeInsertado.getIdViaje() + " =====");
												Envio envio = new Envio();
												System.out.print("\tID ALMACEN : ");
												envio.setIdAlmacen(Integer.parseInt(input.nextLine()));
												System.out.print("\tID TIENDA : ");
												envio.setIdTienda(Integer.parseInt(input.nextLine()));
												envio.setIdViaje(viajeInsertado.getIdViaje());
												System.out.print("\tVOLUMEN: ");
												envio.setVolumen(Integer.parseInt(input.nextLine()));
												System.out.print("\tPESO: ");
												envio.setPesoMaximo(Integer.parseInt(input.nextLine()));
												envio.setUsuarioAlta(usuario);
												envio.setUsuarioModificacion(usuario);
												String envioString = gson.toJson(envio);
												String respuestaEnvio = peticion.pedir(opcMenu, 4, envioString);
												JSONObject respuestaJSONEnvio = new JSONObject(respuestaEnvio);
												if (respuestaJSONEnvio.getBoolean("done")) {
														System.out.println("\tENVIO AGREGADO CORRECTAMENTE...");
												} else {
														System.out.println("\tESTE ENVIO NO SE PUDO AGREGAR AL VIAJE");
												}
										} else {
													opc = 0;
										}

								} while (opc != 0);

						} else {
								System.out.println(respuestaJSON.getString("mensaje"));
						}
				} catch (Exception e) {
						e.printStackTrace();
						System.out.println("HA OCURRIDO UN ERROR");
				}

		}

}
