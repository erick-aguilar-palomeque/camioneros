package controller;

import com.google.gson.Gson;
import dao.ReportesRepository;
import entities.Camion;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReportesController {
    ReportesRepository reporte = new ReportesRepository();
    
      public JSONObject menuReportes(int opcSubmenu, String data) {
        JSONObject json = new JSONObject();
        boolean done = true;
        String mensaje = "Reporte generado con exito";
        
        switch (opcSubmenu) {
            case 1://Reporte 1
               
                break;
            case 2://Reporte 2. Mostrar los envios que viajaron en el camion dado un numero de placa 
                System.out.println("ENTRO A REPOTE 2");
                System.out.println("con la placa:"+data);
                JSONArray envios = reporte.r2_enviosDadoPlacas(data);
                if (envios==null){
                    done=false;
                    mensaje="Ha ocurrido un error con el procedimiento R2_EnviosDadoPlacas";
                }else{
                    json.put("data",envios);
                }
            case 3://Reporte 3.
                
                break;
            case 4://Reporte 4 
                
                break;
            case 5://Reporte 5.
                
                break;
        }
        json.put("done", done);
        json.put("mensaje", mensaje);
        return json;
    }
    
}
