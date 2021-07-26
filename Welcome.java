import javax.swing.*;
import java.awt.*;



public class Welcome extends JComponent implements Runnable {

    public void run() {
        /* set up new elements */
        JFrame frame = new JFrame("Pixie");

        // Import png logo
        // Having trouble getting it to show up on screen
        ImageIcon icon = new ImageIcon("pixie_logo.png");
        JLabel label = new JLabel(icon);

        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(350, 100);
        frame.add(label);

        // Allow element to show
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Welcome());
    }
}
