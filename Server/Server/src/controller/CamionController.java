package controller;

import com.google.gson.Gson;
import dao.CamionRepository;
import entities.Camion;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author erick
 */
public class CamionController {
    CamionRepository camionRepository = new CamionRepository();
    Gson gson = new Gson();
    
    public JSONObject menuCamion(int opcSubmenu, String data) {
        JSONObject json = new JSONObject();
        boolean done = true;
        String mensaje = "Todo bien";
        
        switch (opcSubmenu) {
            case 1://ALTA
                System.out.println("ALTA DE CAMIONES");
                System.out.println(data);
                Camion camionInsertado = camionRepository.insert(getCamion(data));
                String camionInsertadoString = gson.toJson(camionInsertado);
                if (camionInsertado == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al insertar el camion";
                } else {
                    json.put("data", camionInsertadoString);
                }
                break;
            case 2://BAJA
                System.out.println("BAJA DE CAMIONES");
                if (camionRepository.delete(getCamion(data))) {
                    done = true;
                    mensaje = "Camion eliminado con exito";
                } else {
                    done = false;
                    mensaje = "Ha ocurrido un error al eliminar el camion";
                }
                break;
            case 3://EDICION
                System.out.println("edicion de camiones");
                Camion camionAEditar = gson.fromJson(data, Camion.class);
                Camion camionEditado = camionRepository.update(camionAEditar);
                if (camionEditado == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al editar el camion";
                } else {
                    mensaje = "El camion ha sido editado correctamente";
                    json.put("data", gson.toJson(camionEditado));
                }
                break;
            case 4://CONSULTA
                System.out.println("consulta de camiones");
                List<Camion> camiones = camionRepository.findAll();
                if (camiones == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar los camiones";
                } else {
                    json.put("data", camiones);
                }
                break;
            case 5://FIND ONE
                System.out.println("Buscando camion");
                Camion camionEncontrado = camionRepository.findById(data);
                if (camionEncontrado == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar los camiones";
                } else {
                    json.put("data", gson.toJson(camionEncontrado));
                }
        }
        json.put("done", done);
        json.put("mensaje", mensaje);
        return json;
    }

    public Camion getCamion(String cadena) {
        return gson.fromJson(cadena, Camion.class);
    }
}
