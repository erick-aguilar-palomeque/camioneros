/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao.CamionRepository;
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
    CamionRepository camionRepository = new CamionRepository();

    public Hilo(Socket cliente_) {
        cliente = cliente_;
    }

    public void run() {
        try {
            DataInputStream readmessage = new DataInputStream(cliente.getInputStream());
            DataOutputStream writemessage = new DataOutputStream(cliente.getOutputStream());
            
            int opcMenu;
            int opcSubmenu;
            System.out.println("\t NUEVA CONEXION => ip: " + cliente.getInetAddress());
            opcMenu = readmessage.readInt();
            opcSubmenu = readmessage.readInt();
            JSONObject data = new JSONObject(readmessage.readUTF());
            
            System.out.println("opcMenu: " + opcMenu);
            System.out.println("opcSubmenu: " + opcSubmenu);
            System.out.println("data: " + data);
            System.out.println("Termino la espera");
            
            JSONObject respuesta = new JSONObject().put("message", "Hola!, estoy a tus ordenes");
            if(opcMenu == 6){
                respuesta = new JSONObject().put("camiones", camionRepository.findAll());
            }
            
            writemessage.writeUTF(respuesta.toString());
            
            readmessage.close();
            writemessage.close();
            Thread.sleep(100000);
            cliente.close();
        } catch (Exception e) {
        }
    }
}
