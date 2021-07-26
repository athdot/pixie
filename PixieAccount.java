import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PixieAccount extends JComponent implements Runnable {
    public void run() {
        /* set up new elements */
        JFrame frame = new JFrame("Create Account");
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
        JButton newCreate = new JButton("Create Account");
        JLabel username = new JLabel("Username ");
        JLabel password = new JLabel("Password ");
        JLabel confirm = new JLabel("Confirm Password ");
        JLabel invalid = new JLabel("Hello");

        username.setBounds(40, 97, 80, 30);
        password.setBounds(40, 127, 80, 30);
        confirm.setBounds(40, 157, 175, 30);
        invalid.setBounds(40, 157, 175, 30);



        // Text fields for username and password
        JPasswordField passwordField = new JPasswordField(10);
        JPasswordField confirmField = new JPasswordField(10);
        JTextField userField = new JTextField(10);
        userField.setBounds(170, 105, 135, 20);
        passwordField.setBounds(170, 135, 135, 20);
        confirmField.setBounds(170, 165, 135, 20);
        newCreate.setBounds(110, 200, 135, 25);

        // Add all to main panel
        panel.add(username);
        panel.add(password);
        panel.add(newCreate);
        panel.add(confirm);

        panel.add(confirmField);
        panel.add(passwordField);
        panel.add(userField);


        // Allow elements to show
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new PixieAccount());
    }


}
