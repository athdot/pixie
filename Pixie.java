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
    private final Client CLIENT = new Client();
    private String currentUsername;

    //APP HAS 2 JFrames.
    JFrame loginFrame; //login page frame
    JFrame appFrame; //frame for the app part

    //LOGIN FRAME: baseline features -- the login menu
    JButton createAccountButton;
    JButton signInButton;

    //APP FRAME: baseline features -- the main menu
    JButton yourProfileButton;
    JButton createPostButton;
    JButton viewYourPostsButton;
    JButton viewYourCommentsButton;
    JButton viewAllPostsButton;
    JButton logoutButton;

    //LOGIN FRAME: bring the panels and their components created for the login page from PixieLoginPage class
    PixieLoginPage pixieLoginPage = new PixieLoginPage();

    JPanel signInPanel = pixieLoginPage.signInPanel;
    JPanel createAccountPanel = pixieLoginPage.createAccountPanel;

    JTextField signInUsernameField = pixieLoginPage.signInUsernameField;
    JTextField signInPasswordField = pixieLoginPage.signInPasswordField;
    JButton signInConfirmButton = pixieLoginPage.signInConfirmButton;

    JTextField createAccountUsernameField = pixieLoginPage.createAccountUsernameField;
    JTextField createAccountPasswordField = pixieLoginPage.createAccountPasswordField;
    JTextField confirmPasswordField = pixieLoginPage.confirmPasswordField;
    JButton createAccountConfirmButton = pixieLoginPage.createAccountConfirmButton;

    //FOR THE ENTIRE PROGRAM: Action listeners for all components that require action listeners
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // LOGIN FRAME

            //user chooses to navigate to the sign-in page
            if (e.getSource() == signInButton) {
                switchPanel(loginFrame, createAccountPanel, signInPanel);
            }

            //user chooses to navigate to the create account page
            if (e.getSource() == createAccountButton) {
                switchPanel(loginFrame, signInPanel, createAccountPanel);
            }

            //user chooses to sign into the account with provided username and password
            if (e.getSource() == signInConfirmButton) {

                if (signInUsernameField.getText().length() == 0 || signInPasswordField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Password or Username too Short",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String worked = "login[" + signInUsernameField.getText().toLowerCase() + "," +
                        signInPasswordField.getText() + "]";
                worked = CLIENT.streamReader(worked);

                if (worked.equals("false")) {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                } else {
                    currentUsername = signInUsernameField.getText();
                    changeFrame(loginFrame, appFrame);
                }
            }

            //user chooses to create a new account with given username, password, and confirm password
            if (e.getSource() == createAccountConfirmButton) {

                if (createAccountUsernameField.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "No Spaces Should be in the Username",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return; //exit the method, ignore the rest.
                } else if (createAccountUsernameField.getText().contains(",")) {
                    JOptionPane.showMessageDialog(null, "No Commas Should be in the Username",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (!createAccountPasswordField.getText().equals(confirmPasswordField.getText())) {
                    JOptionPane.showMessageDialog(null, "Passwords Must Match",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (createAccountUsernameField.getText().length() == 0 ||
                        createAccountPasswordField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Password or Username too Short",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String worked = "createAccount[" + createAccountUsernameField.getText().toLowerCase() + "," +
                        createAccountPasswordField.getText() + "]";
                worked = CLIENT.streamReader(worked);

                if (worked.equals("true")) {
                    JOptionPane.showMessageDialog(null, "New Account Created",
                            "Account Created", JOptionPane.INFORMATION_MESSAGE);
                    currentUsername = createAccountUsernameField.getText();
                    changeFrame(loginFrame, appFrame);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }
            }

            //APP FRAME

            //...

        }
    };

    //FOR THE ENTIRE PROGRAM: end the client when the user closes out of the app by X'ing out.
    WindowAdapter windowAdapter = new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            CLIENT.end();
        }
    };

    /**
     * Call this method when you want to switch from one panel to another on the given frame
     * @param frame - frame that you are currently on (loginFrame or applicationFrame)
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
     * dispose of one frame and start the other (loginFrame or applicationFrame)
     * @param oldFrame - frame you want to dispose of
     * @param newFrame - frame you want to show
     */
    public void changeFrame(JFrame oldFrame, JFrame newFrame) {
        oldFrame.setVisible(false);
        newFrame.setVisible(true);
    }

    /**
     * idea: let there be 2 JFrames in total. One JFrame is for the login page, another for the app itself.
     * Create the base layout of these frames here (e.g., login page has a side panel containing options to sign in
     * or create a new account.
     */
    public void run() {
        /*
        Create the JFrame for the login page. Build the side menu bar/panel which is shared by all pages of the login
        page. The side menu bar/panel contains button options to sign in or create a new account.
        */

        loginFrame = new JFrame("Pixie Login");
        loginFrame.setSize(500, 400);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //do not use JFrame.EXIT_ON_CLOSE
        loginFrame.addWindowListener(windowAdapter);
        loginFrame.setLocationRelativeTo(null);

        //side panel creation (called it "loginFrameMenu")
        JPanel loginFrameMenu = new JPanel();
        loginFrameMenu.setLayout(new FlowLayout(4, 4, 4));
        loginFrameMenu.setBackground(new Color(0, 160, 160));

        //grid implementation within the side panel/login frame menu
        JPanel loginFrameMenuGrid = new JPanel();
        loginFrameMenuGrid.setLayout(new GridLayout(4, 1, 5, 5));
        loginFrameMenuGrid.setBackground(new Color(0, 160, 160));

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
        loginFrame.add(signInPanel);
        loginFrame.setVisible(true);

        /*
        Create the JFrame for the rest of the app (which will contain the main menu and all sub-menus)
        */

        appFrame = new JFrame("Pixie App");
        appFrame.setSize(1000, 700); //we will probably want the actual app to be larger
        appFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //do not use JFrame.EXIT_ON_CLOSE
        appFrame.addWindowListener(windowAdapter);
        appFrame.setLocationRelativeTo(null);

        //side panel creation
        JPanel appFrameMenu = new JPanel();
        appFrameMenu.setLayout(new FlowLayout(4, 4, 4));
        appFrameMenu.setBackground(new Color(0, 160, 160));

        //
        JPanel appFrameMenuGrid = new JPanel();
        appFrameMenuGrid.setLayout(new GridLayout(6, 1, 5, 5));
        appFrameMenuGrid.setBackground(new Color(0, 160, 160));

        // Log in and create account buttons
        yourProfileButton = new JButton("Your Profile");
        createPostButton = new JButton("Create Post");
        viewYourPostsButton  = new JButton("View Your Posts");
        viewYourCommentsButton = new JButton("View Your Comments");
        viewAllPostsButton = new JButton("View All Posts");
        logoutButton = new JButton("Logout");

        // Add buttons to grid
        appFrameMenuGrid.add(yourProfileButton);
        appFrameMenuGrid.add(createPostButton);
        appFrameMenuGrid.add(viewYourPostsButton);
        appFrameMenuGrid.add(viewYourCommentsButton);
        appFrameMenuGrid.add(viewAllPostsButton);
        appFrameMenuGrid.add(logoutButton);

        // Add grid to west panel
        appFrameMenu.add(appFrameMenuGrid);

        //start off on the sign in panel
        appFrame.add(appFrameMenu, BorderLayout.WEST);
        //appFrame.setVisible(true);

        /*
        All JComponents that need action listeners (e.g., buttons) need to have action listeners instantiated within
        this class. This includes components from other classes that helped create panels for the JFrames. Add their
        action listeners here.
         */

        //LOGIN FRAME

        //user can navigate to sign-in or create-new-account page
        signInButton.addActionListener(actionListener);
        createAccountButton.addActionListener(actionListener);

        signInConfirmButton.addActionListener(actionListener); //confirm button for signing in
        createAccountConfirmButton.addActionListener(actionListener); //confirm button for creating new account

        //APP FRAME

        //user can navigate to 6 different pages using the main menu (similar to Application.java from PJ04)
        yourProfileButton.addActionListener(actionListener);
        createPostButton.addActionListener(actionListener);
        viewYourPostsButton.addActionListener(actionListener);
        viewYourCommentsButton.addActionListener(actionListener);
        viewAllPostsButton.addActionListener(actionListener);
        logoutButton.addActionListener(actionListener);

    }

    public static void main(String[] args) {
        //run application on event dispatch thread (EDT)
        SwingUtilities.invokeLater(new Pixie());
    }
}
