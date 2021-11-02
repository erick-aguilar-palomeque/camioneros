package controllers;

import com.google.gson.Gson;
import config.Peticion;
import entities.Camion;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author erick
 */
public class CamionController {
    Peticion peticion = new Peticion();
    Gson gson = new Gson();
    public void insertar(int opcMenu, int opcSubmenu, String usuario) {
        Scanner input = new Scanner(System.in);
        try{
            Camion camion = new Camion();
            System.out.print("\tPLACAS: ");
            camion.setPlacas(input.nextLine());
            System.out.print("\tVOLUMEN: ");
            camion.setVolumen(input.nextDouble());
            System.out.print("\tPESO MAXIMO: ");
            camion.setPesoMaximo(input.nextDouble());
            camion.setUsuarioAlta(usuario);
            camion.setUsuarioModificacion(usuario);
            String camionString = gson.toJson(camion);
            String respuesta = peticion.pedir(opcMenu, opcSubmenu, camionString);
            JSONObject respuestaJSON = new JSONObject(respuesta);
            if(respuestaJSON.getBoolean("done")){
                Camion camionInsertado = gson.fromJson(respuestaJSON.getString("data"), Camion.class);
                System.out.println("Camión insertado correctamente con el ID: " + camionInsertado.getIdCamion());
            }
            else{
                System.out.println(respuestaJSON.getString("mensaje"));
            }
        }
        catch(Exception e){
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }
    public void modificar(int opcMenu, int opcSubmenu, String usuario){
        Scanner input = new Scanner(System.in);
        try{
            System.out.print("\tID: ");
            int idCamion = Integer.parseInt(input.nextLine());
            Camion camionAModificar = getCamionById(idCamion);
            if(camionAModificar == null){
                System.out.println("NO SE HA ENCONTRADO EL CAMION");
            }
            else{
                System.out.println("\tCAMION ENCONTRADO");
                System.out.println("\t-----------------------------------");
                System.out.println("\tID: " + camionAModificar.getIdCamion());
                System.out.println("\tPLACAS: " + camionAModificar.getPlacas());
                System.out.println("\tVOLUMEN: " + camionAModificar.getVolumen());
                System.out.println("\tPESO MAXIMO: " + camionAModificar.getPesoMaximo());
                System.out.println("\tUSUARIO CREACION: " + camionAModificar.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + camionAModificar.getFechaAlta());
                System.out.println("\t-----------------------------------");
                System.out.println("\tINGRESE LOS NUEVOS VALORES");
                
                /*PIDIENDO DATOS DEL NUEVO CAMION*/
                Camion nuevoCamion = new Camion();
                input.reset();
                System.out.print("\tPLACAS: ");
                camionAModificar.setPlacas(input.nextLine());
                System.out.print("\tVOLUMEN: ");
                camionAModificar.setVolumen(input.nextDouble());
                System.out.print("\tPESO MAXIMO: ");
                camionAModificar.setPesoMaximo(input.nextDouble());
                camionAModificar.setUsuarioAlta(usuario);
                camionAModificar.setUsuarioModificacion(usuario);
                String camionString = gson.toJson(camionAModificar);
                
                /*ACTUALIZANDO CAMION*/
                String resultadoEdicion = peticion.pedir(opcMenu, opcSubmenu, camionString);
                System.out.println(new JSONObject(resultadoEdicion).getString("mensaje"));
            }
        }
        catch(Exception e){
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }
    
     public void eliminar(int opcMenu, int opcSubmenu, String usuario){
        Scanner input = new Scanner(System.in);
        try{
            System.out.print("\tID: ");
            int idCamion = Integer.parseInt(input.nextLine());
            Camion camionAEliminar = getCamionById(idCamion);
            if(camionAEliminar == null){
                System.out.println("NO SE HA ENCONTRADO EL CAMION");
            }
            else{
                System.out.println("\tCAMION ENCONTRADO");
                System.out.println("\t-----------------------------------");
                System.out.println("\tID: " + camionAEliminar.getIdCamion());
                System.out.println("\tPLACAS: " + camionAEliminar.getPlacas());
                System.out.println("\tVOLUMEN: " + camionAEliminar.getVolumen());
                System.out.println("\tPESO MAXIMO: " + camionAEliminar.getPesoMaximo());
                System.out.println("\tUSUARIO CREACION: " + camionAEliminar.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + camionAEliminar.getFechaAlta());
                System.out.println("\t-----------------------------------");
                System.out.print("\t¿Esta seguro que desea eliminar el camion? (SI/NO)");
                String confirmacion = input.nextLine();
                if(confirmacion.equals("SI")){
                    camionAEliminar.setUsuarioBaja(usuario);
                    /*ELIMINANDO CAMION*/
                    String resultadoEdicion = peticion.pedir(opcMenu, opcSubmenu, gson.toJson(camionAEliminar));
                    System.out.println(new JSONObject(resultadoEdicion).getString("mensaje"));
                }
            }
        }
        catch(Exception e){
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }
    public Camion getCamionById(int id){
        String respuesta = peticion.pedir(3, 5, "" + id);
        JSONObject json = new JSONObject(respuesta);
        if(json.getBoolean("done")){
            return getCamionFromString(json.getString("data"));
        }
        return null;
    }

    public void consultar(int opcMenu, int opcSubmenu) {
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, "");
        JSONObject json = new JSONObject(respuesta);
        
        if(json.getBoolean("done")){
            /*CONVERTIR STRING A LISTA*/
            Camion[] camiones = gson.fromJson(json.get("data").toString(), Camion[].class);
            /*IMPRIMIR*/
            System.out.println("\t " + camiones.length + " camiones encontrados");
            System.out.println("\t-----------------------------------");
            for (Camion camion : camiones) {
                System.out.println("\tID: " + camion.getIdCamion());
                System.out.println("\tPLACAS: " + camion.getPlacas());
                System.out.println("\tVOLUMEN: " + camion.getVolumen());
                System.out.println("\tPESO MAXIMO: " + camion.getPesoMaximo());
                System.out.println("\tUSUARIO CREACION: " + camion.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + camion.getFechaAlta() + "\n");
            }
            System.out.println("\t-----------------------------------");
        }else{
            System.out.println(json.getString("mensaje"));
        }
        
    }
    public Camion getCamionFromString(String cadena){
        Gson gson = new Gson();
        return gson.fromJson(cadena, Camion.class);        
    }

}
