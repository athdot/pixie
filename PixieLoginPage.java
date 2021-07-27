import javax.swing.*;
import java.awt.*;

/**
 * PixieLoginPage - PJ05
 * Contains panel layouts for login JFrame interface
 *
 * @author Group 8
 * @version July 27, 2021
 */

public class PixieLoginPage extends JComponent {

    //make these fields public because Pixie.java needs to reference them

    public JPanel signInPanel; //panel setup for user sign in
    public JPanel createAccountPanel; //panel setup for creating new account

    public JTextField usernameField; //text field to enter username
    public JTextField passwordField; //text field to enter password
    public JTextField confirmPasswordField; //only exists for creating new account

    public JButton signInButton; //button to go to sign in page
    public JButton createAccountButton; //button to go to create account page
    public JButton confirmButton; //button confirming sign in or account creation

//    // Checks the validity of username and password in login page
//    public void create() {
//        String userCode = "createAccount[" + usernameField.getText().toLowerCase() + "," + passwordField.getText() + "]";
//        String evaluate = client.streamReader(userCode);
//
//        // If username is taken, show error message
//        if (evaluate.equals("false")) {
//            JOptionPane.showMessageDialog(null, "Username is taken.", "Username taken",
//                    JOptionPane.ERROR_MESSAGE);
//        // If valid, show Welcome page (having trouble adding Welcome class
//        // once button is clicked)
//        }
//    }

    public PixieLoginPage() {
        getPanels();
    }

    /**
     * Assign the bare minimum attributes for a panel that you're creating as part of the log in page. Additional items
     * can be added afterwards (e.g., such as a confirm password field for the createAccountPanel).
     */
    private void getPanels() {

        //Local variables to be applied to both the sign in panel and create account panel
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel invalidLabel = new JLabel("Hello");

        // panel.setLayout(null) helps with .setBounds
        usernameLabel.setBounds(80, 97, 80, 30);
        passwordLabel.setBounds(80, 127, 80, 30);
        invalidLabel.setBounds(80, 157, 175, 30);

        //initialize username and password text fields
        passwordField = new JPasswordField(10);
        passwordField.setBounds(200, 135, 135, 20);
        usernameField = new JTextField(10);
        usernameField.setBounds(200, 105, 135, 20);

        // Log in and create account buttons
        createAccountButton = new JButton("Create Account");
        signInButton = new JButton("Sign In");

        // Grid layout within side panel
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(4, 1, 5, 5));
        grid.setBackground(new Color(94, 156, 156));

        // Add buttons to grid
        grid.add(signInButton);
        grid.add(createAccountButton);

        // Side panel (on west) design
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new FlowLayout(4, 4, 4));
        sidePanel.setBackground(new Color(94, 156, 156));
        sidePanel.add(grid);

        /*
        Create the features on the sign in panel which can be displayed on the loginFrame
         */
        signInPanel = new JPanel();
        signInPanel.setLayout(null); //null layout allows flexibility when adjusting components, texts, etc.
        signInPanel.add(sidePanel, BorderLayout.WEST);

        signInPanel.add(usernameLabel);
        signInPanel.add(passwordLabel);

        signInPanel.add(passwordField);
        signInPanel.add(usernameField);

        /*
        Create the features on the "create account" panel that can be displayed on the login page
         */
        createAccountPanel = new JPanel();
        createAccountPanel.setLayout(null);
        createAccountPanel.add(sidePanel, BorderLayout.WEST);

        createAccountPanel.add(usernameLabel);
        createAccountPanel.add(passwordLabel);

        createAccountPanel.add(passwordField);
        createAccountPanel.add(usernameField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password ");
        confirmPasswordLabel.setBounds(80, 157, 175, 30);
        createAccountPanel.add(confirmPasswordLabel);
    }
}
