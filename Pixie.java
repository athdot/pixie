import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Pixie.java - PJ05
 * Apparently our app is branded as "Pixie". Pixes are mythical creatures in folklore and children's stories.
 * This class ties the GUI together by allowing all panels of the application to work together. Panels are created
 * within different classes for organization. Pixie.java creates an instance of all these panels to call them from
 * their external classes. Also listens for all actions across the program.
 *
 * @author Group 8
 * @version July 27, 2021
 */

public class Pixie extends JComponent implements Runnable {

    //each user who has the app open also has a Client object that communicates with the Server class.
    private Client client;

    //The entire app has 2 JFrames. One containing all login pages, another for the rest
    JFrame loginFrame; //loginFrame
    JFrame applicationFrame; //applicationFrame

    //loginFrame baseline features
    JButton createAccountButton;
    JButton signInButton;

    //create an instance of the classes containing all panel layouts
    PixieLoginPage pixieLoginPage = new PixieLoginPage();
    JButton signInConfirmButton = pixieLoginPage.signInConfirmButton;
    JButton createAccountConfirmButton = pixieLoginPage.createAccountConfirmButton;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == createAccountButton) {
                switchPanel(loginFrame, pixieLoginPage.signInPanel, pixieLoginPage.createAccountPanel);
            }
            if (e.getSource() == signInButton) {
                switchPanel(loginFrame, pixieLoginPage.createAccountPanel, pixieLoginPage.signInPanel);
            }
/*            if (e.getSource() == createAccountConfirmButton) {
                String userCode = "createAccount[" + pixieLoginPage.createAccountUsernameField.getText().toLowerCase() +
                        "," + pixieLoginPage.createAccountPasswordField.getText() + "]";
                String evaluate = client.streamReader(userCode);

                // If username is taken, show error message
                if (evaluate.equals("false")) {
                    JOptionPane.showMessageDialog(null, "Username is taken.",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    // If valid, show Welcome page (having trouble adding Welcome class
                    // once button is clicked)
                }*/
/*            if (e.getSource() == signInButton) {
                String userCode = "login[" + pixieLoginPage.signInUsernameField.getText().toLowerCase() +
                        "," + pixieLoginPage.signInPasswordField.getText() + "]";
                String evaluate = client.streamReader(userCode);

                // If invalid, show error message
                if (evaluate.equals("false")) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Invalid",
                            JOptionPane.ERROR_MESSAGE);
                }
            }*/
        }
    };

    /**
     * Call this method when you want to switch from one panel to another on the given frame
     * @param frame - frame you want to display on (loginPageFrame or applicationFrame)
     * @param newPanel - panel you want to display on the frame
     */
    public void switchPanel(JFrame frame, JPanel oldPanel, JPanel newPanel) {
        frame.remove(oldPanel);
        frame.add(newPanel);

        //DEBUGGED: use repaint() and revalidate() to refresh and recalculate layout
        frame.repaint();
        frame.revalidate();
    }

    /**
     * dispose of one frame and start the other (loginPageFrame or applicationFrame)
     * @param oldFrame - frame you want to dispose of
     * @param newFrame - frame you want to show
     */
    public void changeFrame(JFrame oldFrame, JFrame newFrame) {
        oldFrame.dispose();
        newFrame.setVisible(true);
    }

    /**
     * idea: let there be 2 JFrames in total. One JFrame is for the login page, another for the app itself. The frame
     * should be no more than a simple raw frame. Panel layouts are created in their respective classes and then added
     * to the JFrames later on.
     */
    public void run() {
        /*
        Create the JFrame for the login page. Build the side menu bar that contains button options to sign in or
        create a new account. This "side panel" stays, whether we are on the page to sign in or create a new account.
        */
        loginFrame = new JFrame("Pixie Login");
        loginFrame.setSize(500, 400);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //do not use JFrame.EXIT_ON_CLOSE
        loginFrame.setLocationRelativeTo(null);

        //side panel creation (called it "loginFrameMenu")
        JPanel loginFrameMenu = new JPanel();
        loginFrameMenu.setLayout(new FlowLayout(4, 4, 4));
        loginFrameMenu.setBackground(new Color(94, 156, 156));

        //grid implementation within teh side panel/login frame menu
        JPanel loginFrameMenuGrid = new JPanel();
        loginFrameMenuGrid.setLayout(new GridLayout(4, 1, 5, 5));
        loginFrameMenuGrid.setBackground(new Color(94, 156, 156));

        // Log in and create account buttons
        createAccountButton = new JButton("Create Account");
        signInButton = new JButton("Sign In");

        // Add buttons to grid
        loginFrameMenuGrid.add(signInButton);
        loginFrameMenuGrid.add(createAccountButton);

        // Add grid to west panel
        loginFrameMenu.add(loginFrameMenuGrid);

        //start off on the sign in panel
        loginFrame.add(loginFrameMenu, BorderLayout.WEST);
        loginFrame.add(pixieLoginPage.signInPanel);
        loginFrame.setVisible(true);

        /*
        Create the JFrame for the rest of the app (which will contain the main menu and all sub-menus)
        */
        applicationFrame = new JFrame("Pixie App");
        applicationFrame.setSize(1200, 1000); //we will probably want the actual app to be larger
        applicationFrame.setLocationRelativeTo(null);

        //todo: transfer buttons from other classes that design panels and add action listeners to them below
        signInButton.addActionListener(actionListener);
        createAccountButton.addActionListener(actionListener);

        signInConfirmButton.addActionListener(actionListener);
        createAccountConfirmButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        //run application on event dispatch thread (EDT)
        SwingUtilities.invokeLater(new Pixie());
    }
}
