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
    
     public void r4_enviosDadoPlacas(int opcMenu, int opcSubmenu) {
         Scanner input = new Scanner(System.in);
         //Solicitar la placa del camion a consultar
         System.out.print("\tIngrese la placa del camion:");
         String data= input.nextLine();
         
        String respuesta = peticion.pedir(opcMenu, opcSubmenu, data);
        JSONObject json = new JSONObject(respuesta);
        
        if(json.getBoolean("done")){
           //Se obtiene la lista de envios devuelta en el atributo data de la respuesta del servidor
           JSONArray envios = new JSONArray(json.getJSONArray("data").toString());
            System.out.println("\t\t"+envios.length()+ " ENVIOS ENCONTRADOS:");
            for(Object item : envios){
                JSONObject envio = (JSONObject) item; 
                System.out.println("\t-------------------------------------");
                System.out.println("\t\tId Envio:"+envio.getInt("id_envio"));
                System.out.println("\t\tId Almacen:"+envio.getInt("id_almacen"));
                System.out.println("\t\tId Tienda:"+envio.getInt("id_tienda"));
                System.out.println("\t\tId viaje:"+envio.getInt("id_viaje"));
                System.out.println("\t\tCantidad (Volumen):"+envio.getDouble("volumen"));
                System.out.println("\t\tPeso:"+envio.getDouble("peso_maximo"));
                System.out.println("\t\tEstado:"+envio.getString("descripcion"));
            }
             System.out.println("\t---------------------------------------");
        }else{
            System.out.println(json.getString("mensaje"));
        }
        
    }
    
}

