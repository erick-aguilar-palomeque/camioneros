package config;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class Peticion {
    public JSONObject pedir(int opcMenu, int opcSubmenu, JSONObject data){
        Socket cliente;
        try {
            cliente = new Socket("localhost", 35557);
            System.out.println("conectado...");
            DataOutputStream writemessage = new DataOutputStream(cliente.getOutputStream());
            DataInputStream readmessage = new DataInputStream(cliente.getInputStream());
            
            writemessage.writeInt(opcMenu); //Enviando opc de menu
            writemessage.writeInt(opcSubmenu);//Enviando opc del submenu
            writemessage.writeUTF(data.toString());
            System.out.println(" Enviando datos ");
            JSONObject respuesta = new JSONObject(readmessage.readUTF());
//            System.out.println("respuesta => \n" + respuesta);
            writemessage.close();
            readmessage.close();
            cliente.close();
            return respuesta;
        } catch (IOException ex) {
            Logger.getLogger(Peticion.class.getName()).log(Level.SEVERE, null, ex);
            return new JSONObject();
        }
    }

    }
