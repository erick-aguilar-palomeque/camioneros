/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import com.google.gson.Gson;
import dao.AlmacenRepository;
import entities.Almacen;
import entities.Camion;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author desig
 */
public class AlmacenesController {
    AlmacenRepository almacenesRepository = new AlmacenRepository();
    Gson gson = new Gson();
    
     public JSONObject menuAlmacen(int opcSubmenu, String data) {
        JSONObject json = new JSONObject();
        boolean done = true;
        String mensaje = "Todo bien";
        
        switch (opcSubmenu) {
            case 1://ALTA
                System.out.println("ALTA DE ALMACEN");
                System.out.println(data);
                Almacen AlmacenInsertado = almacenesRepository.insert(getAlmacen(data));
                String AlmacenControllerInsertadoString = gson.toJson(AlmacenInsertado);
                if (AlmacenInsertado == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al insertar";
                } else {
                    json.put("data", AlmacenControllerInsertadoString);
                }
                break;
            case 2://BAJA
                System.out.println("BAJA DE ALMACEN");
                if (almacenesRepository.delete(getAlmacen(data))) {
                    done = true;
                    mensaje = "Almacen eliminado con exito";
                } else {
                    done = false;
                    mensaje = "Ha ocurrido un error al eliminar el camion";
                }
                break;
            case 3://EDICION
                System.out.println("edicion de camiones");
                Almacen almacenAEditar = gson.fromJson(data, Almacen.class);
                Almacen almacenEditado = almacenesRepository.update(almacenAEditar);
                if (almacenEditado == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al editar el almacen";
                } else {
                    mensaje = "El almacen ha sido editado correctamente";
                    json.put("data", gson.toJson(almacenEditado));
                }
                break;
            case 4://CONSULTA
                System.out.println("consulta de almacenes");
                List<Almacen> almacen = almacenesRepository.findAll();
                if (almacen == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar los almacenes";
                } else {
                    json.put("data", almacen);
                }
                break;
            case 5://FIND ONE
                System.out.println("Buscando almacen");
                Almacen almacenEncontrado = almacenesRepository.findById(data);
                if (almacenEncontrado == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error al consultar ";
                } else {
                    json.put("data", gson.toJson(almacenEncontrado));
                }
        }
        json.put("done", done);
        json.put("mensaje", mensaje);
        return json;
    }

 public Almacen getAlmacen(String cadena) {
        return gson.fromJson(cadena, Almacen.class);
    }
}

  
