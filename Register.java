import javax.swing.JFrame;

public class Register extends JFrame {

    float width = 500, height = 700;

    Register() {
        this.setTitle("Registration Page");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        //Always at the bottom

        this.setVisible(true);
    }
}
