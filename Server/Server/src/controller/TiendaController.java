/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import dao.TiendaRepository;
import entities.Tienda;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Carlos Lázaro
 */
public class TiendaController {
       TiendaRepository tiendaRepository = new TiendaRepository();
    Gson gson = new Gson();    
    public JSONObject menuTienda(int opcSubmenu, String data) {
        JSONObject json = new JSONObject();
        boolean done = true;
        String mensaje = "Todo bien";
        
        switch (opcSubmenu) {
            case 1://ALTA
                System.out.println("ALTA DE TIENDAS");
                System.out.println(data);
                System.out.println("OPC SUBMENU: " + opcSubmenu);
                System.out.println("DATA: " + data);
                Tienda tiendaInsertado = tiendaRepository.insert(getTienda(data));
                String tiendaInsetadaString = gson.toJson(tiendaInsertado);
                if (tiendaInsetadaString == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al insertar la tienda";
                } else {
                    json.put("data", tiendaInsetadaString);
                }
                break;
            case 2://BAJA
                System.out.println("BAJA DE CAMIONES");
                if (tiendaRepository.delete(getTienda(data))) {
                    done = true;
                    mensaje = "Tienda eliminada con exito";
                } else {
                    done = false;
                    mensaje = "Ha ocurrido un error al eliminar la Tienda";
                }
                break;
            case 3://EDICION
                System.out.println("edicion de camiones");
                Tienda tiendaAEditar = gson.fromJson(data, Tienda.class);
                Tienda tiendaEditado = tiendaRepository.update(tiendaAEditar);
                if (tiendaEditado == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al editar el camion";
                } else {
                    mensaje = "El camion ha sido editado correctamente";
                    json.put("data", gson.toJson(tiendaEditado));
                }
                break;
            case 4://CONSULTA
                System.out.println("consulta de tiendas");
                List<Tienda> tiendecitas = tiendaRepository.findAll();
                if (tiendecitas == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar las tiendas";
                } else {
                    json.put("data", tiendecitas);
                }
                break;
            case 5://FIND ONE
                System.out.println("Buscando camion");
                Tienda tiendaEncontrada = tiendaRepository.findById(data);
                if (tiendaEncontrada == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar los camiones";
                } else {
                    json.put("data", gson.toJson(tiendaEncontrada));
                }
                break;
        }
        json.put("done", done);
        json.put("mensaje", mensaje);
        return json;
    }

    public Tienda getTienda(String cadena) {
        return gson.fromJson(cadena, Tienda.class);
    }
}
