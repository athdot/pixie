import javax.swing.*;
import java.awt.*;



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

        ImageIcon loading = new ImageIcon("ajax-loader.gif");
        JLabel label2 = new JLabel(loading);



        frame.setBackground(Color.white);

        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);
        frame.add(label);

        frame.pack();

        // Allow element to show
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Welcome());
    }
}
