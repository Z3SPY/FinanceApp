import javax.swing.JFrame;

public class profileFrame extends JFrame {
    int height = 400, width = 600; 

    profileFrame() {
        this.setTitle("Profile Information");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize((int)width, (int)height);
        this.setLocationRelativeTo(null);
        this.setLayout(null);


        // Last Call
        this.setVisible(true);
    }
}
