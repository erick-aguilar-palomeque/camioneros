/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import config.Peticion;
import controllers.AlmacenController;
import controllers.CamionController;
import java.util.Scanner;
import org.json.JSONObject;
public class Cliente {
    final String USUARIO = "USUARIO1";
    public static void main(String[] args) {
        // TODO code application logic here
        new Cliente().conecta_cliente();
    }


    void conecta_cliente() {
        Scanner input = new Scanner(System.in);
        Peticion peticion = new Peticion();
        String respuesta = null;
        try {
            int opcMenu, opcSubmenu;
            do {
                imprimirMenu();
                opcMenu = input.nextInt();
                AlmacenController almacenController = new AlmacenController();
                switch(opcMenu){
                    case 1:
                        do{
                            imprimirMenuCRUD("ALMACENES");
                            opcSubmenu = input.nextInt();
                            switch(opcSubmenu){
                                case 1: 
                                    System.out.println("\n\nHAZ ESCOGIDO ALTA DE ALMACENES");
                                    almacenController.insertar(opcMenu, opcSubmenu, USUARIO);
                                    break;
                                case 2: System.out.println("Haz escogido baja de almacenes"); 
                                    almacenController.eliminar(opcMenu, opcSubmenu, USUARIO);
                                break;
                               case 3: System.out.println("Haz escogido edicion de almacenes"); 
                                    almacenController.modificar(opcMenu, opcSubmenu, USUARIO);
                                break;
                                
                                case 4:  
                                    System.out.println("\n\nHAZ ESCOGIDO CONSULTA DE ALMACENES");
                                    almacenController.consultar(opcMenu, opcSubmenu);
                                break;
                                case 5: System.out.println("..."); break;
                            }
                        }while(opcSubmenu != 5);                        
                      break;
//                    case 2:
//                        do{
//                            imprimirMenuCRUD("TIENDAS");
//                            opcSubmenu = input.nextInt();
//                            switch(opcSubmenu){
//                                case 1: System.out.println("Haz escogido alta de tiendas"); 
//                                    peticion.pedir(opcMenu, opcSubmenu, new JSONObject().put("message", "hola servidor"));
//                                break;
//                                case 2: System.out.println("Haz escogido baja de tiendas"); 
//                                    peticion.pedir(opcMenu, opcSubmenu, new JSONObject().put("message", "hola servidor"));
//                                break;
//                                case 3: System.out.println("Haz escogido edicion de tiendas"); 
//                                    peticion.pedir(opcMenu, opcSubmenu, new JSONObject().put("message", "hola servidor"));
//                                break;
//                                case 4: System.out.println("..."); break;
//                            }
//                        }while(opcSubmenu != 4);                        
//                        break;
                    case 3:
                        CamionController camionController = new CamionController();
                        do{
                            imprimirMenuCRUD("CAMIONES");
                            opcSubmenu = input.nextInt();
                            switch(opcSubmenu){
                                case 1: System.out.println("Haz escogido alta de camiones"); 
                                    camionController.insertar(opcMenu, opcSubmenu, USUARIO);
                                break;
                                case 2: System.out.println("Haz escogido baja de camiones"); 
                                    camionController.eliminar(opcMenu, opcSubmenu, USUARIO);
                                break;
                                case 3: System.out.println("Haz escogido edicion de camiones"); 
                                    camionController.modificar(opcMenu, opcSubmenu, USUARIO);
                                break;
                                case 4: 
                                    System.out.println("\n\nHAZ ESCOGIDO CONSULTA DE CAMIONES"); 
                                    camionController.consultar(opcMenu, opcSubmenu);
                                break;
                                case 5: System.out.println("..."); break;
                            }
                        }while(opcSubmenu != 5);                        
                        break;
//                    case 4:
//                        do{
//                            imprimirMenuCRUD("VIAJES");
//                            opcSubmenu = input.nextInt();
//                            switch(opcSubmenu){
//                                case 1: System.out.println("Haz escogido alta de viajes"); 
//                                    peticion.pedir(opcMenu, opcSubmenu, new JSONObject().put("message", "hola servidor"));
//                                break;
//                                case 2: System.out.println("Haz escogido baja de viajes"); 
//                                    peticion.pedir(opcMenu, opcSubmenu, new JSONObject().put("message", "hola servidor"));
//                                break;
//                                case 3: System.out.println("Haz escogido edicion de viajes"); 
//                                    peticion.pedir(opcMenu, opcSubmenu, new JSONObject().put("message", "hola servidor"));
//                                break;
//                                case 4: System.out.println("..."); break;
//                            }
//                        }while(opcSubmenu != 4);                        
//                        break;
//                    case 5:
//                        do{
//                            imprimirMenuReportes();
//                            opcSubmenu = input.nextInt();
//                            switch(opcSubmenu){
//                                case 11: System.out.println("..."); break;
//                                default: 
//                                    System.out.println("Haz escogido el reporte: " + opcSubmenu); 
//                                    peticion.pedir(opcMenu, opcSubmenu, new JSONObject().put("message", "hola servidor"));
//                            }
//                        }while(opcSubmenu != 11);                        
//                        break;
                    case 6:System.out.println("===SALIR===");break;
                }

            }while(opcMenu != 7);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void imprimirMenu(){
        System.out.println("=========== Menu principal ===========");
        System.out.println("[1]. ALMACENES");
        System.out.println("[2]. TIENDAS");
        System.out.println("[3]. CAMIONES");
        System.out.println("[4]. VIAJES");
        System.out.println("[5]. REPORTES");
        System.out.println("[6]. SALIR");
        System.out.print("Elige una opción: ");
    }
    void imprimirMenuCRUD(String titulo){
        System.out.println("\t=========== " + titulo + " ===========");
        System.out.println("\t[1]. ALTA");
        System.out.println("\t[2]. BAJA");
        System.out.println("\t[3]. MODIFICAR");
        System.out.println("\t[4]. CONSULTA");
        System.out.println("\t[5]. VOLVER");
        System.out.print("\tElige una opción: ");
    }
    void imprimirMenuReportes(){
        System.out.println("\t=========== Reportes ===========");
        System.out.println("\t[1]. REPORTE 1");
        System.out.println("\t[2]. REPORTE 2");
        System.out.println("\t[3]. REPORTE 3");
        System.out.println("\t[4]. REPORTE 4");
        System.out.println("\t[5]. REPORTE 5");
        System.out.println("\t[6]. REPORTE 6");
        System.out.println("\t[7]. REPORTE 7");
        System.out.println("\t[8]. REPORTE 8");
        System.out.println("\t[9]. REPORTE 9");
        System.out.println("\t[10]. REPORTE 10");
        System.out.println("\t[11]. VOLVER");
        System.out.print("\tElige una opción: ");
    }
}
