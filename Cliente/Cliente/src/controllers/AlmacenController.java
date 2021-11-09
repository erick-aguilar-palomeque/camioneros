/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import config.Peticion;
import entities.Almacen;
import entities.Camion;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author desig
 */
public class AlmacenController {

    Peticion peticion = new Peticion();
    Gson gson = new Gson();

    public void consultar(int opcMenu, int opcSubmenu) {
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, "");
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            /*CONVERTIR STRING A LISTA*/
            Almacen[] almacenes = gson.fromJson(json.get("data").toString(), Almacen[].class);
            /*IMPRIMIR*/
            System.out.println("\t " + almacenes.length + " almacenes encontrados");
            System.out.println("\t-----------------------------------");
            for (Almacen almacen : almacenes) {
                System.out.println("\tID: " + almacen.getIdAlmacen());
                System.out.println("\tNOMBRE: " + almacen.getNombre());
                System.out.println("\tDIRECCION: " + almacen.getDireccion());
                System.out.println("\tUSUARIO CREACION: " + almacen.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + almacen.getFechaAlta() + "\n");
            }
            System.out.println("\t-----------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }
     public Almacen getAlmacenFromString(String cadena){
        Gson gson = new Gson();
        return gson.fromJson(cadena, Almacen.class);        
    }




    public void insertar(int opcMenu, int opcSubmenu, String usuario) {
        Scanner input = new Scanner(System.in);
        try {
            Almacen almacen = new Almacen();
            System.out.print("\tNOMBRE: ");
            almacen.setNombre(input.nextLine());
            System.out.print("\tDIRECCION: ");
            almacen.setDireccion(input.nextLine());
            almacen.setUsuarioAlta(usuario);
            almacen.setUsuarioModificacion(usuario);
            String almacenString = gson.toJson(almacen);
            String respuesta = peticion.pedir(opcMenu, opcSubmenu, almacenString);
            JSONObject respuestaJSON = new JSONObject(respuesta);
            if (respuestaJSON.getBoolean("done")) {
                Almacen almacenInsertado = gson.fromJson(respuestaJSON.getString("data"), Almacen.class);
                System.out.println(" insertado correctamente con el ID: " + almacenInsertado.getIdAlmacen());
            } else {
                System.out.println(respuestaJSON.getString("mensaje"));
            }
        } catch (Exception e) {
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }

    public void eliminar(int opcMenu, int opcSubmenu, String usuario) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("\tID: ");
            int idAlmacen = Integer.parseInt(input.nextLine());
            Almacen almacenAEliminar = getAlmacenById(idAlmacen);
            if (almacenAEliminar == null) {
                System.out.println("NO SE HA ENCONTRADO EL ALMACEN");
            } else {
                System.out.println("\tALMACEN ENCONTRADO");
                System.out.println("\t-----------------------------------");
                System.out.println("\tID: " + almacenAEliminar.getIdAlmacen());
                System.out.println("\tNOMBRE: " + almacenAEliminar.getNombre());
                System.out.println("\tDIRECCION: " + almacenAEliminar.getDireccion());
                System.out.println("\tUSUARIO CREACION: " + almacenAEliminar.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + almacenAEliminar.getFechaAlta());
                System.out.println("\t-----------------------------------");
                System.out.print("\t¿Esta seguro que desea eliminar el almacen? (SI/NO)");
                String confirmacion = input.nextLine();
                if (confirmacion.equals("SI")) {
                    almacenAEliminar.setUsuarioBaja(usuario);
                    /*ELIMINANDO CAMION*/
                    String resultadoEdicion = peticion.pedir(opcMenu, opcSubmenu, gson.toJson(almacenAEliminar));
                    System.out.println(new JSONObject(resultadoEdicion).getString("mensaje"));
                }
            }
        } catch (Exception e) {
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }


 public Almacen getAlmacenById(int id){
        String respuesta = peticion.pedir(1, 5, "" + id);
        JSONObject json = new JSONObject(respuesta);
        if(json.getBoolean("done")){
            return getAlmacenFromString(json.getString("data"));
        }
        return null;
          
}public void modificar(int opcMenu, int opcSubmenu, String usuario){
        Scanner input = new Scanner(System.in);
        try{
            System.out.print("\tID: ");
            int idAlmacen = Integer.parseInt(input.nextLine());
            Almacen almacenAModificar = getAlmacenById(idAlmacen);
            if(almacenAModificar == null){
                System.out.println("NO SE HA ENCONTRADO EL ALMACEN");
            }
            else{
                System.out.println("\tCAMION ENCONTRADO");
                System.out.println("\t-----------------------------------");
                System.out.println("\tID: " + almacenAModificar.getIdAlmacen());
                System.out.println("\tNOMBRE: " + almacenAModificar.getNombre());
                System.out.println("\tDIRECCION: " + almacenAModificar.getDireccion());
                System.out.println("\tUSUARIO CREACION: " + almacenAModificar.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + almacenAModificar.getFechaAlta());
                System.out.println("\t-----------------------------------");
                System.out.println("\tINGRESE LOS NUEVOS VALORES");
                
                /*PIDIENDO DATOS DEL NUEVO ALMACEN*/
                Camion nuevoALmacen = new Camion();
                input.reset();
                System.out.print("\tNOMBRE: ");
                almacenAModificar.setNombre(input.nextLine());
                System.out.print("\tDIRECCION: ");
                almacenAModificar.setDireccion(input.nextLine());
               
                almacenAModificar.setUsuarioAlta(usuario);
                almacenAModificar.setUsuarioModificacion(usuario);
                String camionString = gson.toJson(almacenAModificar);
                
                /*ACTUALIZANDO ALMACEN*/
                String resultadoEdicion = peticion.pedir(opcMenu, opcSubmenu, camionString);
                System.out.println(new JSONObject(resultadoEdicion).getString("mensaje"));
            }
        }
        catch(Exception e){
            System.out.println("HA OCURRIDO UN ERROR");
       }
}}