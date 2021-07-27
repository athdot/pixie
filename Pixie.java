import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pixie extends JComponent implements Runnable{
    Client client;

    JTextField userField;
    JTextField passwordField;
    JButton loginButton;
    JButton createButton;
    JButton signInButton;
    Pixie go;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createButton) {
                JFrame frame = new JFrame();
                Panel panel = new Panel();
                JLabel confirm = new JLabel("Confirm Password ");
                confirm.setBounds(40, 157, 175, 30);
                panel.add(confirm);
                frame.add(panel);

            }
            if (e.getSource() == signInButton) {
                String userCode = "login[" + userField.getText().toLowerCase() +
                        "," + passwordField.getText() + "]";
                String evaluate = client.streamReader(userCode);

                // If invalid, show error message
                if (evaluate.equals("false")) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }
    };

    // The Log In page
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

        // Allows flexibility when adjusting components, texts, etc.
        panel.setLayout(null);

        // Side panel (on west) design
        sidePanel.setLayout(new FlowLayout(4, 4, 4));
        sidePanel.setBackground(new Color(94, 156, 156));
        frame.add(sidePanel, BorderLayout.WEST);

        // Grid layout within side panel
        grid.setLayout(new GridLayout(4, 1, 5, 5));
        grid.setBackground(new Color(94, 156, 156));

        // Log in and create account buttons
        ImageIcon icon = new ImageIcon("icons8-create-24.png");
        ImageIcon icon2 = new ImageIcon("icons8-account-24.png");
        createButton = new JButton(icon);
        loginButton = new JButton(icon2);

        // Add buttons to grid
        grid.add(loginButton);
        grid.add(createButton);

        // Add grid to west panel
        sidePanel.add(grid);

        // Buttons and labels with main panel
        signInButton = new JButton("Sign In");
        JLabel username = new JLabel("Username ");
        JLabel password = new JLabel("Password ");
        username.setBounds(100, 117, 80, 30);
        password.setBounds(100, 147, 80, 30);

        // Text fields for username and password
        passwordField = new JPasswordField(10);
        userField = new JTextField(10);

        // panel.setLayout(null) helps with .setBounds
        userField.setBounds(180, 125, 100, 20);
        passwordField.setBounds(180, 155, 100, 20);
        signInButton.setBounds(150, 200, 100, 25);

        signInButton.addActionListener(actionListener);


        // Add all to main panel
        panel.add(username);
        panel.add(password);

        panel.add(passwordField);
        panel.add(userField);
        panel.add(signInButton);

        // Allow elements to show
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // Run class
        SwingUtilities.invokeLater(new Pixie());
    }
}
