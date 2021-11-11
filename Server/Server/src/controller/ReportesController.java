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
                System.out.println("ENTRO A REPOTE 1");
                JSONArray tiendasR1 = reporte.R1_TiendasPorCamion();
                if (tiendasR1 == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento R2_EnviosDadoPlacas";
                } else {
                    json.put("data", tiendasR1);
                }
                break;
            case 2://Reporte 2. Mostrar los envios que viajaron en el camion dado un numero de placa 
                System.out.println("ENTRO A REPOTE 2");
                System.out.println("con la placa:" + data);
                JSONArray envios = reporte.r2_enviosDadoPlacas(data);
                if (envios == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento R2_EnviosDadoPlacas";
                } else {
                    json.put("data", envios);
                }
                break;
            case 3://Reporte 3. Tiendas que recibieron envios con un peso mayor que 250kg y un volumen mayor que 5000
                System.out.println("ENTRO A REPOTE 3");
                JSONArray tiendas = reporte.r3_tiendasCantidadPeso(data);
                if (tiendas == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento R3_tiendasCantidadPeso";
                } else {
                    json.put("data", tiendas);
                }
                break;
            case 4://Reporte 4.  
                System.out.println("ENTRO A REPOTE 4");
                JSONArray enviosR4 = reporte.r4_enviosPorTienda(data);
                if (enviosR4 == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento R4_enviosPorTienda";
                } else {
                    json.put("data", enviosR4);
                }
                break;
            case 5://Reporte 5.
                 System.out.println("ENTRO A REPOTE 5");
                JSONArray camiones = reporte.r5_camionConMasViajes(data);
                if (camiones == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento R4_enviosPorTienda";
                } else {
                    json.put("data", camiones);
                }
                break;
            case 6://Reporte 6
                System.out.println("ENTRO A REPOTE 6");
                JSONArray tiendasR6 = reporte.r6_tiendasMasPiezas(data);
                if (tiendasR6 == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento r6_tiendasMasPiezas";
                } else {
                    json.put("data", tiendasR6);
                }
                break;
            case 7://Reporte 7
                System.out.println("ENTRO A REPOTE 7");
                JSONArray camionesR7 = reporte.r7_camionesPesoSuperado();
                if (camionesR7 == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento r7_camionesPesoSuperado";
                } else {
                    json.put("data", camionesR7);
                }
                break;
            case 8://Reporte 8
                System.out.println("ENTRO A REPOTE 8");
                JSONArray meses= reporte.r8_mesConMenosViajes();
                if (meses == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento r8_mesConMenosViajes";
                } else {
                    json.put("data", meses);
                }
                break;
            case 9://Reporte 9
                System.out.println("ENTRO A REPOTE 9");
                JSONArray mesesR9= reporte.r9_mesConMasViajes();
                if (mesesR9 == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento r9_mesConMasViajes";
                } else {
                    json.put("data", mesesR9);
                }
                break;
            case 10://Reporte 10
                System.out.println("ENTRO A REPOTE 10");
                JSONArray datos= reporte.R10_ViajesCamionPorFecha(data);
                if (datos == null) {
                    done = false;
                    mensaje = "Ha ocurrido un error con el procedimiento R10_ViajesCamionPorFecha";
                } else {
                    json.put("data", datos);
                }
                break;
        }
        json.put("done", done);
        json.put("mensaje", mensaje);
        return json;
    }

}
