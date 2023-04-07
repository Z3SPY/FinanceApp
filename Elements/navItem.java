package Elements;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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

    public ImageIcon getNavIcon() {
        return new ImageIcon(new ImageIcon(navIcon).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
    }

    public ImageIcon getSelectedIcon() {
        System.out.println(selectedIcon);
        return new ImageIcon(new ImageIcon(selectedIcon).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
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

    public navItem(String text, String icon, String select , MenuType type) {

        this.navText = text.toUpperCase();
        this.navIcon = icon;
        this.type = type;
        this.selectedIcon = select;
        
    }

    public enum MenuType {
        MENU,
        EMPTY
    }
}
