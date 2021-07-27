import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Swaps between log in page and create account page
 * Confirms validity of username and password
 * Opens "welcome" page once the username and password is confirmed
 *
 * <p>Purdue University -- CS18000 -- Summer 2021 -- Project 5 -- Group 8</p>
 *
 */

public class Pixie extends JComponent implements Runnable{
    Client client;

    JTextField userField;
    JTextField passwordField;
    JTextField confirmField;
    JButton loginButton;
    JButton createButton;
    JButton signInButton;
    JButton newCreate;
    Pixie go;

    JFrame frame = new JFrame("Pixie");
    JFrame frame2 = new JFrame("Pixie");

    Container container = frame.getContentPane();

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create account button
            // Button works
            if (e.getSource() == createButton) {
                switchToCreate(panel2);
            }

            // Log In button
            // Button works
            if (e.getSource() == loginButton) {
                switchToLog(panel1);
            }

            // Button doesn't work yet
            if (e.getSource() == signInButton) {
                acceptLogIn();
            }

            // Button doesn't work yet
            if (e.getSource() == newCreate) {
                acceptCreate();
            }

        }
    };

    // Validates sign in
    public void acceptLogIn() {
        String userCode = "login[" + userField.getText().toLowerCase() +
                "," + passwordField.getText() + "]";
        String evaluate = client.streamReader(userCode);

        // If invalid, show error message
        if (evaluate.equals("false")) {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Error",
                    JOptionPane.ERROR_MESSAGE);
        // If valid, display welcome frame    
        } else {
            welcome();
        }
    }

    // Validates create account
    public void acceptCreate() {
        String userCode = "createAccount[" + userField.getText().toLowerCase() +
                "," + passwordField.getText() + "]";
        String evaluate = client.streamReader(userCode);

        // If invalid, show error message
        if (evaluate.equals("false")) {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Error",
                    JOptionPane.ERROR_MESSAGE);
        // If valid, display welcome frame    
        } else {
            welcome();
        }
    }

    // The welcome page layout
    // Different frame
    public void welcome() {
        frame2.setSize(400, 450);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocation(20, 0);

        frame2.setBackground(new Color(255, 255, 255));

        // Import png logo
        ImageIcon icon = new ImageIcon("pixie_logo.png");
        JLabel label = new JLabel(icon);

        // Loading gif 
        ImageIcon loading = new ImageIcon("ajax-loader.gif");
        JLabel label2 = new JLabel(loading);
        label2.setBounds(660, 800, 40, 40);

        // Make sure loading gif overlaps logo
        frame2.add(label2);
        frame2.add(label);

        frame2.pack();

        // Allow elements to show
        frame2.setVisible(true);
    }

    // Switches to create account page
    public void switchToCreate(JPanel newPanel) {
        // Remove all old elements in container
        container.removeAll();
        // Add new elements
        container.add(newPanel);

        JPanel sidePanel = new JPanel();
        JPanel grid = new JPanel();

        // Allows flexibility when adjusting components, texts, etc.
        newPanel.setLayout(null);

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
        newCreate = new JButton("Create Account");
        JLabel username = new JLabel("Username ");
        JLabel password = new JLabel("Password ");
        JLabel confirm = new JLabel("Confirm Password ");
        JLabel invalid = new JLabel("Hello");

        // panel.setLayout(null) helps with .setBounds
        username.setBounds(80, 97, 80, 30);
        password.setBounds(80, 127, 80, 30);
        confirm.setBounds(80, 157, 175, 30);
        invalid.setBounds(80, 157, 175, 30);

        // Text fields for username and password
        passwordField = new JPasswordField(10);
        confirmField = new JPasswordField(10);
        userField = new JTextField(10);

        userField.setBounds(200, 105, 135, 20);
        passwordField.setBounds(200, 135, 135, 20);
        confirmField.setBounds(200, 165, 135, 20);
        newCreate.setBounds(140, 200, 135, 25);

        // Add all to container
        newPanel.add(username);
        newPanel.add(password);
        newPanel.add(newCreate);
        newPanel.add(confirm);

        newPanel.add(confirmField);
        newPanel.add(passwordField);
        newPanel.add(userField);

        // Add button mechanics
        createButton.addActionListener(actionListener);
        loginButton.addActionListener(actionListener);
        newCreate.addActionListener(actionListener);


        //DEBUGGED: use repaint() and revalidate() to refresh and recalculate layout
        frame.repaint();
        frame.revalidate();
    }

    // Switches back to log in page
    public void switchToLog(JPanel firstPanel) {
        // Removes all elements in container
        container.removeAll();
        // Add log in page
        container.add(firstPanel);

        JPanel sidePanel = new JPanel();
        JPanel grid = new JPanel();

        panel1.setLayout(null);

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
        createButton.addActionListener(actionListener);
        loginButton.addActionListener(actionListener);

        // Add all to main panel
        firstPanel.add(username);
        firstPanel.add(password);

        firstPanel.add(passwordField);
        firstPanel.add(userField);
        firstPanel.add(signInButton);

        //DEBUGGED: use repaint() and revalidate() to refresh and recalculate layout
        frame.repaint();
        frame.revalidate();
    }

    // The OG log in page
    public void run() {
        /* set up new elements */
        JPanel sidePanel = new JPanel();
        JPanel grid = new JPanel();

        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(450, 200);
        frame.add(panel1);

        // Allows flexibility when adjusting components, texts, etc.
        panel1.setLayout(null);
        
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
        createButton.addActionListener(actionListener);
        loginButton.addActionListener(actionListener);

        // Add all to main panel
        panel1.add(username);
        panel1.add(password);

        panel1.add(passwordField);
        panel1.add(userField);
        panel1.add(signInButton);

        // Allow elements to show
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // Run class
        SwingUtilities.invokeLater(new Pixie());
    }
}
