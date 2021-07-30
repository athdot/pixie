import javax.swing.*;
import java.awt.*;

/**
 * PixieYourProfile - PJ05
 * Contains all panel setups for "Your Profile" page from the main menu options
 *
 * @author Group 8
 * @version July 28, 2021
 */

public class PixieYourProfile extends JComponent {

    //PANELS created for "Your Profile" page
    public JPanel changeBioPanel;
    public JPanel changeUsernamePanel;
    public JPanel changePasswordPanel;
    public JPanel yourProfilePanel;

    public JPanel blankPanel = new JPanel(null);

    //CHANGE BIO - components of the change bio page
    public JTextField changeBioField;
    public JButton confirmChangeBioButton;

    //CHANGE USERNAME - components of the change username page
    public JTextField changeUsernameField;
    public JButton confirmChangeUsernameButton;

    //CHANGE PASSWORD - components of the change password page
    public JTextField oldPasswordField;
    public JTextField newPasswordField;
    public JButton confirmChangePasswordButton;

    //YOUR PROFILE - text components of the user's profile
    public JLabel yourProfileUsernameLabel;
    public JLabel yourProfileBioLabel;

    public PixieYourProfile() {
        //when creating an instance of PixieYourProfile, also set up the associated pages
        changeBioPage();
        changeUsernamePage();
        changePasswordPage();
        yourProfilePage();

        blankContentPage();
    }

    //?
    private void blankContentPage() {
        blankPanel.setBackground(Color.WHITE);
    }
    /**
     * The "Change Bio" panel - includes a title "Enter Your New Bio:" along with a JTextArea to house the new bio
     * information. And a "Save Bio" button.
     */
    public void changeBioPage() {
        changeBioPanel = new JPanel(null); //keep layout as null: Works well with setBounds()

        JLabel instructions = new JLabel("Enter Your New Bio:");
        instructions.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 12));
        instructions.setBounds(100, 150, 300, 25);

        changeBioField = new JTextField();
        changeBioField.setBounds(100, 200, 250, 25);

        confirmChangeBioButton = new JButton("Save Bio");
        confirmChangeBioButton.setBounds(100, 250, 130, 30);

        changeBioPanel.add(instructions);
        changeBioPanel.add(changeBioField);
        changeBioPanel.add(confirmChangeBioButton);
    }

    /**
     * change username page: when the user chooses to change their username. Consists of a small text box to enter
     * new username, and a button to confirm the change.
     */
    public void changeUsernamePage() {
        changeUsernamePanel = new JPanel(null);

        //create title for the "change username" page
        JLabel changeUsername = new JLabel("Enter your new Username");
        changeUsername.setBounds(100, 150, 300, 25);

        //create the text field for the "change username" page
        changeUsernameField = new JTextField();
        changeUsernameField.setBounds(100, 200, 250, 25);

        //create the save changed-username button
        confirmChangeUsernameButton = new JButton("Save Username");
        confirmChangeUsernameButton.setBounds(100, 250, 130, 30);

        //add them to the changeUsernamePanel in correct order
        changeUsernamePanel.add(changeUsername);
        changeUsernamePanel.add(changeUsernameField);
        changeUsernamePanel.add(confirmChangeUsernameButton);
    }

    /**
     * change password page: when the user chooses to change their password. Consists of a small text box to enter
     * new password, and a button to confirm the change.
     */
    public void changePasswordPage() {
        changePasswordPanel = new JPanel(null);

        //create title for the "change password" page
        JLabel changePassword = new JLabel("Old password:");
        changePassword.setBounds(100, 50, 300, 25);

        //create the text field for the "change password" page
        oldPasswordField = new JTextField();
        oldPasswordField.setBounds(100, 100, 250, 25);

        JLabel newPassword = new JLabel("New password:");
        newPassword.setBounds(100, 150, 300, 25);

        //create the text field for the "change password" page
        newPasswordField = new JTextField();
        newPasswordField.setBounds(100, 200, 250, 25);

        //create the save changed-password button
        confirmChangePasswordButton = new JButton("Save Password");
        confirmChangePasswordButton.setBounds(100, 250, 130, 30);

        //add them to the changePassowrdPanel in correct order
        changePasswordPanel.add(changePassword);
        changePasswordPanel.add(oldPasswordField);
        changePasswordPanel.add(newPassword);
        changePasswordPanel.add(newPasswordField);
        changePasswordPanel.add(confirmChangePasswordButton);
    }

    /**
     * an overall screen that displays the profile of the user. Includes 2 text labels: One for displaying username,
     * Another for displaying the biography
     */
    public void yourProfilePage() {
        yourProfilePanel = new JPanel(null);

        JLabel usernameTitle = new JLabel("Your Username:");
        usernameTitle.setBounds(100, 100, 100, 25);

        yourProfileUsernameLabel = new JLabel("your username goes here");
        yourProfileUsernameLabel.setBounds(100, 120, 200, 25);

        JLabel biographyTitle = new JLabel("Your Biography:");
        biographyTitle.setBounds(100, 200, 300, 25);

        //DEBUGGED: wrapping in html tags will automatically display runoff text on a new line for JLabel
        yourProfileBioLabel = new JLabel("<html>your bio stuff goes here i think." +
                "Here's to test what the scroll pane is like??? Here's just more test text." +
                "Here's more text to test this bit of the label." +
                "THis label is going to be replaced with the actual bio in Pixie.java</html>");
        yourProfileBioLabel.setBounds(0, 0, 300, 300);
        yourProfileBioLabel.setVerticalAlignment(JLabel.TOP);

        JPanel bioContainerPanel = new JPanel(null);
        bioContainerPanel.setBounds(100, 225, 300, 300);
        bioContainerPanel.add(yourProfileBioLabel);

        yourProfilePanel.add(usernameTitle);
        yourProfilePanel.add(yourProfileUsernameLabel);
        yourProfilePanel.add(biographyTitle);
        yourProfilePanel.add(bioContainerPanel);
    }

    /*
    Frame preview testing: Good idea to set up a temporary main method to see how the pages will look.
    Comment out or delete this main method later
    */
    public static void main(String[] args) {
        PixieYourProfile pixieYourProfile = new PixieYourProfile();
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(pixieYourProfile.yourProfilePanel);
        frame.add(panel);
        frame.setVisible(true);
    }
}
