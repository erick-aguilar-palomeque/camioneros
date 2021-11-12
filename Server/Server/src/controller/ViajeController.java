package controller;

import com.google.gson.Gson;
import dao.EnvioRepository;
import dao.ViajeRepository;
import entities.Entrega;
import entities.Envio;
import entities.Viaje;
import java.util.List;
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
            case 2://find one
                System.out.println("FIND ONE VIAJE");
                System.out.println(data);
                Viaje viaje = viajeRepository.findById(data);
                if (viaje == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar el viaje";
                } else {
                    json.put("data", gson.toJson(viaje));
                }
                break;
            case 3: //FIND ENVIOS
                System.out.println("FIND ENVIOS DE VIAJE");
                List<Envio> envios = viajeRepository.findEnvios(data);
                System.out.println(envios.size() + " envios");
                if (envios == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar los envios";
                } else {
                    json.put("data", gson.toJson(envios));
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
            case 5: //ENTREGAR ENVIO
                System.out.println("ENTREGAR ENVIO");
                Entrega entrega = gson.fromJson(data, Entrega.class);
                boolean entregado = viajeRepository.entregarEnvio(entrega);
                done = entregado;
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
