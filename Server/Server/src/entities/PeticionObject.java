package entities;

import java.io.Serializable;
import org.json.JSONObject;

/**
 *
 * @author erick
 */
public class PeticionObject implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int opcMenu;
    private int opcSubmenu;
    private String data;

    public PeticionObject(int opcMenu, int opcSubmenu, String data) {
        this.opcMenu = opcMenu;
        this.opcSubmenu = opcSubmenu;
        this.data = data;
    }

    public PeticionObject() {
    }

    public int getOpcMenu() {
        return opcMenu;
    }

    public void setOpcMenu(int opcMenu) {
        this.opcMenu = opcMenu;
    }

    public int getOpcSubmenu() {
        return opcSubmenu;
    }

    public void setOpcSubmenu(int opcSubmenu) {
        this.opcSubmenu = opcSubmenu;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    
}
