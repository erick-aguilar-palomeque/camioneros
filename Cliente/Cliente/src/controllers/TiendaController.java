/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import config.Peticion;
import entities.Tienda;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Carlos Lázaro
 */
public class TiendaController {

    Peticion peticion = new Peticion();
    Gson gson = new Gson();

    public void insertar(int opcMenu, int opcSubmenu, String usuario) {
        Scanner input = new Scanner(System.in);
        try {
            Tienda tiendas = new Tienda();
            System.out.print("\t NOMBRE: ");
            tiendas.setNombre(input.nextLine());
            System.out.print("\t DIRECCIÓN: ");
            tiendas.setDireccion(input.nextLine());
            tiendas.setUsuarioAlta(usuario);
            tiendas.setUsuarioModificacion(usuario);
            String tiendaString = gson.toJson(tiendas);
            String respuesta = peticion.pedir(opcMenu, opcSubmenu, tiendaString);
            JSONObject respuestaJSON = new JSONObject(respuesta);
            if (respuestaJSON.getBoolean("done")) {
                Tienda tiendaInsertada = gson.fromJson(respuestaJSON.getString("data"), Tienda.class);
                System.out.println("Tienda insertada correctamente con el ID: " + tiendaInsertada.getIdTienda());
            } else {
                System.out.println(respuestaJSON.getString("mensaje"));
            }
        } catch (Exception e) {
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }

    /*Estamos en eliminar tiendas chidas  de la escuela*/
    public void eliminar(int opcMenu, int opcSubmenu, String usuario) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("\tID: ");
            int idTienda = Integer.parseInt(input.nextLine());
            Tienda tiendaAEliminar = getTiendaById(idTienda);
            if (tiendaAEliminar == null) {
                System.out.println("NO SE HA ENCONTRADO LA TIENDA");
            } else {
                System.out.println("\tTIENDA ENCONTRADA");
                System.out.println("\t-----------------------------------");
                System.out.println("\tID: " + tiendaAEliminar.getIdTienda());
                System.out.println("\tNOMBRE TIENDA: " + tiendaAEliminar.getNombre());
                System.out.println("\tDIRECCION: " + tiendaAEliminar.getDireccion());
                System.out.println("\tUSUARIO CREACION: " + tiendaAEliminar.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + tiendaAEliminar.getFechaAlta());
                System.out.println("\t-----------------------------------");
                System.out.print("\t¿Esta seguro que desea eliminar la tienda? (SI/NO)");
                String confirmacion = input.nextLine();
                if (confirmacion.equals("SI")) {
                    tiendaAEliminar.setUsuarioBaja(usuario);
                    /*ELIMINANDO TIENDA*/
                    String resultadoEdicion = peticion.pedir(opcMenu, opcSubmenu, gson.toJson(tiendaAEliminar));
                    System.out.println(new JSONObject(resultadoEdicion).getString("mensaje"));
                }
            }
        } catch (Exception e) {
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }

    /*INICIO DE EDITAR CAMIONCITOS*/
    public void modificar(int opcMenu, int opcSubmenu, String usuario) {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("\tID: ");
            int idTienda = Integer.parseInt(input.nextLine());
            Tienda tiendaAModificar = getTiendaById(idTienda);
            if (tiendaAModificar == null) {
                System.out.println("NO SE HA ENCONTRADO LA TIENDA");
            } else {
                System.out.println("\tTIENDA ENCONTRADA");
                System.out.println("\t-----------------------------------");
                System.out.println("\tID: " + tiendaAModificar.getIdTienda());
                System.out.println("\tNOMBRE TIENDA: " + tiendaAModificar.getNombre());
                System.out.println("\tDIRECCIÓN: " + tiendaAModificar.getDireccion());
                System.out.println("\tUSUARIO CREACION: " + tiendaAModificar.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + tiendaAModificar.getFechaAlta());
                System.out.println("\t-----------------------------------");
                System.out.println("\tINGRESE LOS NUEVOS VALORES");

                /*PIDIENDO DATOS DEL NUEVO CAMION*/
                input.reset();
                System.out.print("\tNOMBRE: ");
                tiendaAModificar.setNombre(input.nextLine());
                System.out.print("\tDIRECCIÓN: ");
                tiendaAModificar.setDireccion(input.nextLine());
                tiendaAModificar.setUsuarioAlta(usuario);
                tiendaAModificar.setUsuarioModificacion(usuario);
                String tiendaString = gson.toJson(tiendaAModificar);

                /*ACTUALIZANDO CAMION*/
                String resultadoEdicion = peticion.pedir(opcMenu, opcSubmenu, tiendaString);
                System.out.println(new JSONObject(resultadoEdicion).getString("mensaje"));
            }
        } catch (Exception e) {
            System.out.println("HA OCURRIDO UN ERROR");
        }
    }

    public Tienda getTiendaById(int id) {
        String respuesta = peticion.pedir(2, 5, "" + id);
        JSONObject json = new JSONObject(respuesta);
        if (json.getBoolean("done")) {
            return getTiendaFromString(json.getString("data"));
        }
        return null;
    }

    public void consultar(int opcMenu, int opcSubmenu) {
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, "");
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            /*CONVERTIR STRING A LISTA*/
            Tienda[] tiendas = gson.fromJson(json.get("data").toString(), Tienda[].class);
            /*IMPRIMIR*/
            System.out.println("\t " + tiendas.length + " tiendas encontradas");
            System.out.println("\t-----------------------------------");
            for (Tienda tienda : tiendas) {
                System.out.println("\tID: " + tienda.getIdTienda());
                System.out.println("\tNOMBRE TIENDA: " + tienda.getNombre());
                System.out.println("\tDIRECCIÓN: " + tienda.getDireccion());
                System.out.println("\tUSUARIO CREACION: " + tienda.getUsuarioAlta());
                System.out.println("\tFECHA CREACION: " + tienda.getFechaAlta() + "\n");
            }
            System.out.println("\t-----------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public Tienda getTiendaFromString(String cadena) {
        Gson gson = new Gson();
        return gson.fromJson(cadena, Tienda.class);
    }

}
