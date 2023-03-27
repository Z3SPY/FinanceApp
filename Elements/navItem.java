package Elements;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;


//Note Transfer this class to Dashboard
public class navItem {

    String navText;
    String navIcon;
    String selectedIcon;
    MenuType type;
    
    public MenuType getType() {
        return type;
    }

    public String getNavIcon() {
        return navIcon;
    }

    public String getNavText() {
        return navText;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public void setNavIcon(String navIcon) {
        this.navIcon = navIcon;
    }

    public void setNavText(String navText) {
        this.navText = navText;
    }

    public navItem(String text, String icon, MenuType type) {

        this.navText = text.toUpperCase();
        this.navIcon = icon;
        this.type = type;
        
    }

    public enum MenuType {
        MENU,
        EMPTY
    }
}
