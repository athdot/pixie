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

    //make these fields public because Pixie.java needs to access them
    //creating getters and setters for larger pages would be extremely redundant

    JPanel signInPanel;
    JPanel createAccountPanel;

    //username and password field for sign in option
    public JTextField signInUsernameField;
    public JTextField signInPasswordField;
    public JButton signInConfirmButton;

    //username and password field for create account option
    public JTextField createAccountUsernameField;
    public JTextField createAccountPasswordField;
    public JTextField confirmPasswordField; //only exists for creating new account
    public JButton createAccountConfirmButton;

    public PixieLoginPage() {
        getPanels();
    }

    /**
     * Assign the bare minimum attributes for a panel that you're creating as part of the log in page. Additional items
     * can be added afterwards (e.g., such as a confirm password field for the createAccountPanel).
     */

    private void getPanels() {

        /*
        Create the features on the sign in panel which can be displayed on the login frame
         */
        signInPanel = new JPanel(null); //null for better manipulation with setBounds

        // Buttons and labels with main panel
        signInConfirmButton = new JButton("Sign In");

        JLabel usernameLabel1 = new JLabel("Username");
        JLabel passwordLabel1 = new JLabel("Password");
        usernameLabel1.setBounds(100, 117, 80, 30);
        passwordLabel1.setBounds(100, 147, 80, 30);

        // Text fields for username and password
        signInPasswordField = new JPasswordField(10);
        signInUsernameField = new JTextField(10);

        // panel.setLayout(null) helps with .setBounds
        signInUsernameField.setBounds(180, 125, 100, 20);
        signInPasswordField.setBounds(180, 155, 100, 20);
        signInConfirmButton.setBounds(150, 200, 100, 25);

        // Add all to main panel
        signInPanel.add(usernameLabel1);
        signInPanel.add(passwordLabel1);

        signInPanel.add(signInPasswordField);
        signInPanel.add(signInUsernameField);
        signInPanel.add(signInConfirmButton);

        /*
        Create the features on the create account panel that can be displayed on the login frame
        DEBUG: Single JComponents can only be added to one panel and should not be reused.
         */
        //set layout to null to use setBounds()
        createAccountPanel = new JPanel(null);

        // Buttons and labels with main panel
        //only the create-new-account option has a confirm password field
        createAccountConfirmButton = new JButton("Create Account");

        JLabel usernameLabel2 = new JLabel("Username");
        JLabel passwordLabel2 = new JLabel("Password");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");

        usernameLabel2.setBounds(60, 117, 80, 30);
        passwordLabel2.setBounds(60, 147, 80, 30);
        confirmPasswordLabel.setBounds(60, 177, 175, 30);

        // Text fields for username and password
        createAccountUsernameField = new JTextField(10);
        createAccountPasswordField = new JPasswordField(10);
        confirmPasswordField = new JPasswordField(10);

        // panel.setLayout(null) helps with .setBounds
        createAccountUsernameField.setBounds(180, 125, 100, 20);
        createAccountPasswordField.setBounds(180, 155, 100, 20);
        confirmPasswordField.setBounds(180, 185, 100, 20);
        createAccountConfirmButton.setBounds(140, 230, 135, 25);

        // Add all to main panel
        createAccountPanel.add(usernameLabel2);
        createAccountPanel.add(passwordLabel2);
        createAccountPanel.add(confirmPasswordLabel);

        createAccountPanel.add(createAccountPasswordField);
        createAccountPanel.add(createAccountUsernameField);
        createAccountPanel.add(confirmPasswordField);
        createAccountPanel.add(createAccountConfirmButton);
    }
}
