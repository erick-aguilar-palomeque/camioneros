package controllers;

import com.google.gson.Gson;
import config.Peticion;
import entities.Almacen;
import entities.Camion;
import entities.Entrega;
import entities.Envio;
import entities.Tienda;
import entities.Viaje;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;

public class ViajeController {

    Peticion peticion = new Peticion();
    Gson gson = new Gson();
    CamionController camionController = new CamionController();
    AlmacenController almacenController = new AlmacenController();
    TiendaController tiendaController = new TiendaController();

    public int insertar(int opcMenu, int opcSubmenu, String usuario) {

        Scanner input = new Scanner(System.in);
        try {
            Viaje viaje = new Viaje();
            System.out.print("\tID CAMION: ");
            viaje.setIdCamion(Integer.parseInt(input.nextLine()));
            viaje.setUsuarioAlta(usuario);
            viaje.setUsuarioModificacion(usuario);
            Camion camion = camionController.getCamionById(viaje.getIdCamion());
            if (camion == null) {
                System.out.println("\tCAMION NO ENCONTRADO, CANCELANDO REGISTRO DE VIAJE");
                return 0;
            }
            String viajeString = gson.toJson(viaje);
            String respuesta = peticion.pedir(opcMenu, opcSubmenu, viajeString);
            JSONObject respuestaJSON = new JSONObject(respuesta);
            List<Envio> enviosRegistrados = new ArrayList();
            if (respuestaJSON.getBoolean("done")) {
                Viaje viajeInsertado = gson.fromJson(respuestaJSON.getString("data"), Viaje.class);
                System.out.println("Viaje insertado correctamente con el ID: " + viajeInsertado.getIdViaje());
                int opc = 1;
                double pesoMaximo = camion.getPesoMaximo();
                double volumenMaximo = camion.getVolumen();
                do {
                    System.out.println("\t PESO DISPONIBLE: " + pesoMaximo);
                    System.out.println("\t VOLUMEN DISPONIBLE: " + volumenMaximo);
                    System.out.print("ESCRIBA y/n PARA FINALIZAR EL AGREGADO DE ENVIOS: ");
                    String letra = input.nextLine();
                    if (letra.equals("y")) {
                        System.out.println("\t===== AÑADIENDO ENVIO A VIAJE" + viajeInsertado.getIdViaje() + " =====");
                        Envio envio = new Envio();

                        System.out.print("\tID ALMACEN : ");
                        envio.setIdAlmacen(Integer.parseInt(input.nextLine()));
                        /*BUSCANDO ALMACEN*/
                        if (almacenController.getAlmacenById(envio.getIdAlmacen()) == null) {
                            System.out.println("\tALMACEN NO ENCONTRADO, CANCELANDO REGISTRO DE ENVIO");
                            return 0;
                        }
                        System.out.print("\tID TIENDA : ");
                        envio.setIdTienda(Integer.parseInt(input.nextLine()));
                        envio.setIdViaje(viajeInsertado.getIdViaje());
                        if (tiendaController.getTiendaById(envio.getIdTienda()) == null) {
                            System.out.println("\tTIENDA NO ENCONTRADA, CANCELANDO REGISTRO DE ENVIO");
                            return 0;
                        }

                        System.out.print("\tVOLUMEN: ");
                        envio.setVolumen(Integer.parseInt(input.nextLine()));
                        System.out.print("\tPESO: ");
                        envio.setPesoMaximo(Integer.parseInt(input.nextLine()));
                        envio.setUsuarioAlta(usuario);
                        envio.setUsuarioModificacion(usuario);

                        /*VALIDAR PESO DE ENVIO CON EL CAMION*/
                        if (!envioExcedePesoCamion(pesoMaximo, volumenMaximo, envio)) {
                            String envioString = gson.toJson(envio);
                            String respuestaEnvio = peticion.pedir(opcMenu, 4, envioString);
                            JSONObject respuestaJSONEnvio = new JSONObject(respuestaEnvio);
                            if (respuestaJSONEnvio.getBoolean("done")) {
                                System.out.println("\tENVIO AGREGADO CORRECTAMENTE...");
                                pesoMaximo -= envio.getPesoMaximo();
                                volumenMaximo -= envio.getVolumen();
                            } else {
                                System.out.println("\tESTE ENVIO NO SE PUDO AGREGAR AL VIAJE");
                            }
                        } else {
                            System.out.println("\tEL ENVIO NO PUEDE SER REGISTRADO YA QUE EL VOLUMEN O PESO EXCEDEN LOS DEL CAMION");
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
        return 0;
    }

    public int consulta(int opcMenu, int opcSubmenu) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("\tID VIAJE: ");
            int idEnvio = Integer.parseInt(input.nextLine());
            Viaje viaje = findById(idEnvio);
            if (viaje == null) {
                System.out.println("\tEL VIAJE QUE ESTA TRATANDO DE CONSULTAR NO EXISTE");
            } else {
                Camion camion = camionController.getCamionById(viaje.getIdCamion());
                Envio[] envios = findEnviosByIdViaje(viaje.getIdViaje());
                System.out.println("\t=== VIAJE ENCONTRADO ===");
                System.out.println("\tID VIAJE: " + viaje.getIdViaje());
                System.out.println("\tCAMION: ID " + camion.getIdCamion() + ", PLACAS " + camion.getPlacas());
                System.out.println("\tFECHA ALTA: " + viaje.getFechaAlta());
                System.out.println("\tUSUARIO ALTA: " + viaje.getUsuarioAlta());
                System.out.println("\tENVIOS");
                System.out.println("\t--------------------------------------");
                int i = 1;
                for (Envio envio : envios) {
                    Almacen almacen = almacenController.getAlmacenById(envio.getIdAlmacen());
                    Tienda tienda = tiendaController.getTiendaById(envio.getIdTienda());
                    System.out.println("\tENVIO " + i + " de " + envios.length);
                    System.out.println("\t\tID ENVIO: " + envio.getIdEnvio());
                    System.out.println("\t\tALMACEN: ID " + almacen.getIdAlmacen() + "(" + almacen.getNombre() + ")");
                    System.out.println("\t\tESTADO: " + envio.getClaveEstado());
                    System.out.println("\t\tTIENDA: ID " + tienda.getIdTienda() + "(" + tienda.getNombre() + ")");
                    System.out.println("\t\tPESO: " + envio.getPesoMaximo());
                    System.out.println("\t\tVOLUMEN: " + envio.getVolumen());
                    System.out.println("");
                    i++;
                }
                System.out.println("\t--------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("HA OCURRIDO UN ERROR");
        }
        return 0;
    }

    public int entregar(int opcMenu, int opcSubmenu, String usuario) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("\tID VIAJE: ");
            int idViaje = Integer.parseInt(input.nextLine());
            Viaje viaje = findById(idViaje);
            if (viaje == null) {
                System.out.println("\tEL VIAJE QUE ESTA TRATANDO DE CONSULTAR NO EXISTE");
                return 0;
            }
            Envio[] envios = findEnviosByIdViaje(viaje.getIdViaje());
            int i = 1;
            for (Envio envio : envios) {
                Almacen almacen = almacenController.getAlmacenById(envio.getIdAlmacen());
                Tienda tienda = tiendaController.getTiendaById(envio.getIdTienda());
                System.out.println("\tENVIO " + i + " de " + envios.length);
                System.out.println("\t\tID ENVIO: " + envio.getIdEnvio());
                System.out.println("\t\tALMACEN: ID " + almacen.getIdAlmacen() + "(" + almacen.getNombre() + ")");
                System.out.println("\t\tESTADO: " + envio.getClaveEstado());
                System.out.println("\t\tTIENDA: ID " + tienda.getIdTienda() + "(" + tienda.getNombre() + ")");
                System.out.println("\t\tPESO: " + envio.getPesoMaximo());
                System.out.println("\t\tVOLUMEN: " + envio.getVolumen());
                System.out.println("");
                i++;
            }
            System.out.print("\t INGRESE ID DEL ENVIO A ENTREGAR: ");
            int idEnvioEntregar = Integer.parseInt(input.nextLine());
            if(existeEnvioEnViaje(idEnvioEntregar, envios)){
                Entrega entrega = new Entrega();
                entrega.setIdEnvio(idEnvioEntregar);
                entrega.setUsuarioAlta(usuario);
                entrega.setUsuarioModificacion(usuario);
                entrega.setClaveEstado("AC");
                String entregaString = gson.toJson(entrega);

                String respuestaEnvio = peticion.pedir(opcMenu, 5, entregaString);
                JSONObject respuestaJSONEnvio = new JSONObject(respuestaEnvio);
                if (respuestaJSONEnvio.getBoolean("done")) {
                    System.out.println("\tENVIO ENTREGADO CORRECTAMENTE...");
                } else {
                    System.out.println("\tESTE ENVIO NO PUDO SER ENTREGADO");
                }
            }
            else{
                System.out.println("\tEL ENVIO INGRESADO NO EXISTE EN EL VIAJE O YA SE ENCUENTRA ENTREGADO");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("HA OCURRIDO UN ERROR");
        }
        return 0;
    }
    public boolean existeEnvioEnViaje(int id, Envio[] envios){
        for(Envio envio : envios){
            if(envio.getIdEnvio() == id && !envio.getClaveEstado().equals("EN")){
                return true;
            }
        }
        return false;        
    }

    private boolean envioExcedePesoCamion(double pesoMaximo, double volumenMaximo, Envio envio) {
        if (((volumenMaximo - envio.getVolumen()) >= 0) && ((pesoMaximo - envio.getPesoMaximo()) >= 0)) {
            return false;
        } else {
            return true;
        }
    }

    public Viaje findById(int idViaje) {
        String respuesta = peticion.pedir(4, 2, "" + idViaje);
        JSONObject json = new JSONObject(respuesta);
        if (json.getBoolean("done")) {
            return gson.fromJson(json.getString("data"), Viaje.class);
        }
        return null;
    }

    public Envio[] findEnviosByIdViaje(int idViaje) {
        String respuesta = peticion.pedir(4, 3, "" + idViaje);
        JSONObject json = new JSONObject(respuesta);
        if (json.getBoolean("done")) {
            Envio[] envios = gson.fromJson(json.get("data").toString(), Envio[].class);
            return envios;
        }
        return null;
    }

}
