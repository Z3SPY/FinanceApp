import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;


//Note Transfer this class to Dashboard
public class navItem extends JLabel {

    String navText;
    
    navItem(String text) {

        this.navText = text;
        
        super.setText(this.navText.toUpperCase());
        super.setForeground(Color.WHITE);
        super.setFont(new Font("Arial", Font.BOLD, 16));
        super.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    }
}
