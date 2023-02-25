import java.awt.Font;
import javax.swing.JLabel;

public class navItem extends JLabel {

    String navText;

    navItem(String text) {

        this.navText = text;
        
        super.setText(this.navText.toUpperCase());
        super.setFont(new Font("Arial", Font.BOLD, 20));
    }
}
