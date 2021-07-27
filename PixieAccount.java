import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PixieAccount extends JComponent implements Runnable {
    JTextField userField;
    JTextField passwordField;
    JTextField confirmField;
    JButton loginButton;
    JButton createButton;
    JButton newCreate;

    PixieAccount go;

    private Client client;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                go.switchToLogIn();
            }
            if (e.getSource() == newCreate) {
                go.create();
            }
        }
    };

    // Checks the validity of username and password in login page
    public void create() {
        String userCode = "createAccount[" + userField.getText().toLowerCase() + "," + passwordField.getText() + "]";
        String evaluate = client.streamReader(userCode);

        // If username is taken, show error message
        if (evaluate.equals("false")) {
            JOptionPane.showMessageDialog(null, "Username is taken.", "",
                    JOptionPane.ERROR_MESSAGE);
        // If valid, show Welcome page (having trouble adding Welcome class
        // once button is clicked)
        } else if (evaluate.equals("true") && passwordField.equals(confirmField)){
        }

    }

    // Expects the screen to switch from the create account page to login page
    // once the "Log In" button is clicked
    // (Having trouble doing this)
    public void switchToLogIn() {
    }

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
