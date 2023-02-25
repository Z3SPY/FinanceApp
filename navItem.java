<<<<<<< HEAD
import java.awt.Font;
import javax.swing.JLabel;

public class navItem extends JLabel {

    String navText;

=======
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;


//Note Transfer this class to Dashboard
public class navItem extends JLabel {

    String navText;
    
>>>>>>> da9356ff0f01a7b72997e021facb05a21cf48d53
    navItem(String text) {

        this.navText = text;
        
        super.setText(this.navText.toUpperCase());
<<<<<<< HEAD
        super.setFont(new Font("Arial", Font.BOLD, 20));
=======
        super.setForeground(Color.WHITE);
        super.setFont(new Font("Arial", Font.BOLD, 16));
        super.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
>>>>>>> da9356ff0f01a7b72997e021facb05a21cf48d53
    }
}
