package controllers;

import com.google.gson.Gson;
import config.Peticion;
import entities.Camion;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportesController {

    Peticion peticion = new Peticion();

    public void r2_enviosDadoPlacas(int opcMenu, int opcSubmenu) {
        Scanner input = new Scanner(System.in);
        //Solicitar la placa del camion a consultar
        System.out.print("\tIngrese la placa del camion:");
        String data = input.nextLine();

        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray envios = new JSONArray(json.getJSONArray("data").toString());
            System.out.println("\t\t" + envios.length() + " ENVIOS ENCONTRADOS:");
            for (Object item : envios) {
                JSONObject envio = (JSONObject) item;
                System.out.println("\t-------------------------------------");
                System.out.println("\t\tId Envio:" + envio.getInt("id_envio"));
                System.out.println("\t\tId Almacen:" + envio.getInt("id_almacen"));
                System.out.println("\t\tId Tienda:" + envio.getInt("id_tienda"));
                System.out.println("\t\tId viaje:" + envio.getInt("id_viaje"));
                System.out.println("\t\tCantidad (Volumen):" + envio.getDouble("volumen"));
                System.out.println("\t\tPeso:" + envio.getDouble("peso_maximo"));
                System.out.println("\t\tEstado:" + envio.getString("descripcion"));
            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r1_tiendasPorCamion(int opcMenu, int opcSubmenu) {
        String data = "";
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray tiendas = new JSONArray(json.getJSONArray("data").toString());
            //System.out.println("\t\t" + tiendas.length() + " TIENDAS ENCONTRADAS:");
            int idAux=0;
            for (Object item : tiendas) {
                
                JSONObject tienda = (JSONObject) item;
                if(tienda.getInt("id_camion")!=idAux){
                    System.out.println("\t---------------------------------------");
                    System.out.println("\tCAMION:");
                    System.out.println("\t  ID:"+tienda.getInt("id_camion"));
                    System.out.println("\t  Placas:"+tienda.getString("placas"));
                    System.out.println("\t  TIENDAS:");
                    idAux=tienda.getInt("id_camion");
                }
                System.out.println("\t\t ---------------------------------------");
                 System.out.println("\t\t Id tienda:"+tienda.getInt("id_tienda"));
                 System.out.println("\t\t Nombre de tienda:"+tienda.getString("nombre"));
                 System.out.println("\t\t Direccion:"+tienda.getString("direccion"));
                
            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r3_tiendasCantidadPeso(int opcMenu, int opcSubmenu) {
        Scanner input = new Scanner(System.in);
        String data = "";
        System.out.print("\t\tIngrese el peso:");
        String peso = input.nextLine();
        System.out.print("\t\tIngrese el volumen:");
        String volumen= input.nextLine();
        data=peso +";"+volumen;
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray tiendas = new JSONArray(json.getJSONArray("data").toString());
            System.out.println("\t\t" + tiendas.length() + " TIENDAS ENCONTRADAS:");
            for (Object item : tiendas) {
                JSONObject tienda = (JSONObject) item;
                System.out.println("\t-------------------------------------");
                System.out.println("\t\tNombre de tienda:"+tienda.getString("nombre"));

            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r4_enviosPorTienda(int opcMenu, int opcSubmenu) {
         Scanner input = new Scanner(System.in);
        String data = "";
        System.out.print("\t\tIngrese el ID de la tienda:");
        data = input.nextLine();
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray envios = new JSONArray(json.getJSONArray("data").toString());
            System.out.println("\t\t" + envios.length() + " ENVIOS ENCONTRADOS:");
            for (Object item : envios) {
                JSONObject envio = (JSONObject) item;
                System.out.println("\t-------------------------------------");
                System.out.println("\t\tId del envio:"+envio.getInt("id_envio"));
                System.out.println("\t\tVolumen del envio:"+envio.getFloat("volumen"));
                System.out.println("\t\tPeso maximo:"+envio.getFloat("peso_maximo"));
                System.out.println("\t\t\tVIAJE: Id:"+envio.getInt("id_viaje"));
                System.out.println("\t\t\tCAMION:");
                System.out.println("\t\t\t Id camion:"+envio.getInt("id_camion"));
                System.out.println("\t\t\t Placas:"+envio.getString("placa"));

            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r5_camionConMasViajes(int opcMenu, int opcSubmenu) {
        Scanner input = new Scanner(System.in);
        String data = "";
        System.out.print("\t\tIngrese el ID de la tienda:");
        data = input.nextLine();
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            
            JSONArray camiones = new JSONArray(json.getJSONArray("data").toString());
            System.out.println("\t\t" + camiones.length() + " CAMIONES ENCONTRADOS:");
            for (Object item : camiones) {
                System.out.println("\t-------------------------------------");
                JSONObject camion = (JSONObject) item;
                System.out.println("\t\tID:"+camion.getInt("id_camion"));
                System.out.println("\t\tPlacas:"+camion.getString("placas"));
                System.out.println("\t\tVolumen:"+camion.getFloat("volumen"));
                System.out.println("\t\tPeso maximo:"+camion.getFloat("peso_maximo"));
                System.out.println("\t\tHa viajado a esa tienda:"+camion.getInt("veces")+" veces");
               
            }
            System.out.println("\t---------------------------------------");
            

        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r6_tiendasMasPiezas(int opcMenu, int opcSubmenu) {
         Scanner input = new Scanner(System.in);
        String data = "";
        System.out.print("\t\tIngrese la cantidad de piezas(volumen):");
        data = input.nextLine();
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray tiendas = new JSONArray(json.getJSONArray("data").toString());
            System.out.println("\t\t" + tiendas.length() + " TIENDAS ENCONTRADAS:");
            for (Object item : tiendas) {
                System.out.println("\t-------------------------------------");
                JSONObject tienda = (JSONObject) item;
                System.out.println("\t\tID:"+tienda.getInt("id_tienda"));
                System.out.println("\t\tNombre:"+tienda.getString("nombre"));
                System.out.println("\t\tDireccion:"+tienda.getString("direccion"));
                System.out.println("\t\tCantidad de envios:"+tienda.getInt("num_envios"));

            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r7_camionesPesoSuperado(int opcMenu, int opcSubmenu) {
        String data = "";
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray camiones = new JSONArray(json.getJSONArray("data").toString());
            System.out.println("\t\t" + camiones.length() + " CAMIONES ENCONTRADOS:");
            for (Object item : camiones) {
                JSONObject camion = (JSONObject) item;
                System.out.println("\t-------------------------------------");
                System.out.println("\t\tId:"+camion.getInt("id_camion"));
                System.out.println("\t\tPlacas:"+camion.getString("placas"));
                System.out.println("\t\tPeso maximo:"+camion.getFloat("peso_maximo"));
                System.out.println("\t\tVeces superado:"+camion.getFloat("veces"));

            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r8_mesConMenosViajes(int opcMenu, int opcSubmenu) {
        String data = "";
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
             //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray meses = new JSONArray(json.getJSONArray("data").toString());
            for (Object item : meses) {
                JSONObject mes = (JSONObject) item;
                System.out.println("\t-------------------------------------");
                System.out.println("\t\tMes:"+mes.getString("mes"));
                System.out.println("\t\tViajes realizados:"+mes.getInt("noviajes"));
            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }

    public void r9_mesConMasViajes(int opcMenu, int opcSubmenu) {
        String data = "";
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);

        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray meses = new JSONArray(json.getJSONArray("data").toString());
            for (Object item : meses) {
                JSONObject mes = (JSONObject) item;
                System.out.println("\t-------------------------------------");
                System.out.println("\t\tMes:"+mes.getString("mes"));
                System.out.println("\t\tViajes realizados:"+mes.getInt("noviajes"));
            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }

    }
    
      public void r10_viajesCamionPorFecha(int opcMenu, int opcSubmenu) {
        Scanner input = new Scanner(System.in);
        String data = "";
        String fecha_inicio;
        String fecha_fin;
        System.out.print("\t\tIngrese la fecha inicial:");
        fecha_inicio = input.nextLine();
        System.out.print("\t\tIngrese la fecha fina:");
        fecha_fin = input.nextLine();
        data=fecha_inicio +";"+fecha_fin;
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);
        if (json.getBoolean("done")) {
            //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
            JSONArray datos = new JSONArray(json.getJSONArray("data").toString());
            int idAux=0;
            int idViajeAux=0;
            for (Object item : datos) {
                JSONObject dato = (JSONObject) item;
                if(dato.getInt("id_camion")!=idAux){
                     System.out.println("\t\t---------------------------------");
                    System.out.println("\t\tCAMION ID:"+dato.getInt("id_camion"));
                    idAux=dato.getInt("id_camion");
                    
                }
                if(idViajeAux!=dato.getInt("id_viaje")){
                    System.out.println("\t\t\t---------------------------------");
                    System.out.println("\t\t\tVIAJE ID:"+dato.getInt("id_viaje"));
                    idViajeAux=dato.getInt("id_viaje");
                }
                 System.out.println("\t\t\t\t---------------------------------------");
                 System.out.println("\t\t\t\tENVIO ID:"+dato.getInt("id_envio"));
                 System.out.println("\t\t\t\tEnviado a la tienda:"+dato.getInt("id_tienda"));
                 System.out.println("\t\t\t\tCon la cantidad:"+dato.getFloat("volumen"));              
            }
            System.out.println("\t---------------------------------------");
        } else {
            System.out.println(json.getString("mensaje"));
        }
    }

}
