package entities;

import java.io.Serializable;

/**
 *
 * @author erick
 */
public class PeticionObject{
    private int opcMenu;
    private int opcSubmenu;

    public PeticionObject() {
    }

    public PeticionObject(int opcMenu, int opcSubmenu) {
        this.opcMenu = opcMenu;
        this.opcSubmenu = opcSubmenu;
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
    
}
