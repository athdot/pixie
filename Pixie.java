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
    JFrame loginFrame; //loginPageFrame
    JFrame applicationFrame; //applicationFrame

    //create an instance of the classes containing all panel layouts
    PixieLoginPage pixieLoginPage = new PixieLoginPage();

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == pixieLoginPage.createAccountButton) {
                JFrame loginFrame = new JFrame();
                Panel panel = new Panel();
                JLabel confirm = new JLabel("Confirm Password ");
                confirm.setBounds(40, 157, 175, 30);
                panel.add(confirm);
                loginFrame.add(panel);
            }

            if (e.getSource() == pixieLoginPage.signInButton) {
                String userCode = "login[" + pixieLoginPage.usernameField.getText().toLowerCase() +
                        "," + pixieLoginPage.passwordField.getText() + "]";
                String evaluate = client.streamReader(userCode);

                // If invalid, show error message
                if (evaluate.equals("false")) {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    /**
     * Call this method when you want to switch from one panel to another on the given frame
     * @param frame - frame you want to display on (loginPageFrame or applicationFrame)
     * @param newPanel - panel you want to display on the frame
     */
    public void switchPanel(JFrame frame, JPanel newPanel) {
        frame.removeAll();
        frame.add(newPanel, BorderLayout.CENTER);

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
        Create the JFrame for the login page and create its basic set up.
        */
        loginFrame = new JFrame("Pixie Login");

        loginFrame.setSize(500, 400);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //do not use JFrame.EXIT_ON_CLOSE
        loginFrame.setLocationRelativeTo(null);

        loginFrame.add(pixieLoginPage.signInPanel, BorderLayout.CENTER); //start off on the sign in panel
        loginFrame.setVisible(true);

        /*
        Create the JFrame for the rest of the app (which will contain the main menu and all sub-menus)
        */
        applicationFrame = new JFrame("Pixie App");
        applicationFrame.setSize(1200, 1000); //we will probably want the actual app to be larger
        applicationFrame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        //run application on event dispatch thread (EDT)
        SwingUtilities.invokeLater(new Pixie());
    }
}
