/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import controller.CamionController;
import controller.TiendaController;
import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class Server {

    public static void main(String[] args) {
        // TODO code application logic here
        new Server().recibe();
    }


    void recibe() {

        try {
            while (true) {
                System.out.println("esperando conexion...");
                ServerSocket serv = new ServerSocket(35557); //throws IOException//numero de puerto
                Socket cliente = serv.accept();//devuelve el socket del cliente que realizo la peticion de coneccion
                serv.close();
                new Hilo(cliente).start();
            }
        } catch (Exception e) {
        }
    }
}


class Hilo extends Thread {
    Socket cliente;

    public Hilo(Socket cliente_) {
        cliente = cliente_;
    }

    public void run() {
        Gson gson = new Gson();
        try {
            DataInputStream readmessage = new DataInputStream(cliente.getInputStream());
            DataOutputStream writemessage = new DataOutputStream(cliente.getOutputStream());
            int opcMenu;
            int opcSubmenu;
            System.out.println("\t NUEVA CONEXION => ip: " + cliente.getInetAddress());
            opcMenu = readmessage.readInt();
            opcSubmenu = readmessage.readInt();
            String data = readmessage.readUTF();
            
            String mensaje = "Todo bien";
            boolean done = true;
            JSONObject json = new JSONObject();
            
            switch(opcMenu){
                case 1://ALMACENES
                    
                    break;
                case 2:
                    json = new TiendaController().menuTienda(opcSubmenu, data);
                    break;
                case 3://CAMIONES
                    json = new CamionController().menuCamion(opcSubmenu, data);
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
            }
            
            writemessage.writeUTF(json.toString());
            readmessage.close();
            writemessage.close();
            Thread.sleep(100000);
            cliente.close();
        } catch (Exception e) {
        }
    }
    
}
