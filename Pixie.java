import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pixie extends JComponent implements Runnable {
    JPanel sidePanel;
    JPanel mainPanel2;
    JButton loginButton;
    JButton createButton;

    public Pixie() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }
        });
    }

        public void run() {
            /* set up new elements */
            JFrame frame = new JFrame("Pixie");
            JPanel panel = new JPanel();
            JPanel sidePanel = new JPanel();
            JPanel grid = new JPanel();

            frame.setSize(500, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocation(450, 200);
            frame.add(panel);

            // Main container
            panel.setLayout(null);

            // Side panel (on west) design
            sidePanel.setLayout(new FlowLayout(4, 4, 4));
            sidePanel.setBackground(new Color(94, 156, 156));
            frame.add(sidePanel, BorderLayout.WEST);

            // Grid layout within side panel
            grid.setLayout(new GridLayout(4, 1, 5, 5));
            grid.setBackground(new Color(94, 156, 156));

            // Log in and create account buttons
            JButton login = new JButton("Log In");
            JButton create = new JButton("Create Account");

            // Add buttons to grid
            grid.add(login);
            grid.add(create);

            // Add grid to west panel
            sidePanel.add(grid);

            // Buttons and labels with main panel
            JButton signIn = new JButton("Sign In");
            JLabel username = new JLabel("Username ");
            JLabel password = new JLabel("Password ");
            username.setBounds(80, 117, 80, 30);
            password.setBounds(80, 147, 80, 30);

            // Text fields for username and password
            JPasswordField passwordField = new JPasswordField(10);
            JTextField userField = new JTextField(10);
            userField.setBounds(160, 125, 100, 20);
            passwordField.setBounds(160, 155, 100, 20);
            signIn.setBounds(130, 200, 100, 25);

            // Add all to main panel
            panel.add(username);
            panel.add(password);

            panel.add(passwordField);
            panel.add(userField);
            panel.add(signIn);

            // Allow elements to show
            frame.setVisible(true);
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Pixie());
    }


}
