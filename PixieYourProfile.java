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
    public JPanel changeUsernamePanel;
    public JPanel changeBioPanel;

    //CHANGE BIO - components of the change bio page
    public JTextField changeBioField;
    public JButton confirmBioEditButton;

    //CHANGE USERNAME - components of the change username page
    public JTextField newUsername;
    public JButton saveUsername;

    //FEATURES (NEW!) -- optional;
    //we do not have logic for this, it can be set up if there is time to spare later
    //we should focus on completing the objectives before moving onto fancier things -Nathan

//    public JButton emojiButton;
//    public JButton adjustText12Button;
//    public JButton adjustText14Button;
//    public JButton adjustText16Button;

    public PixieYourProfile() {
        //when creating an instance of PixieYourProfile, also set up the associated pages
        changeUsernamePage();
        changeBioPage();
    }

    public void changeUsernamePage() {
        changeUsernamePanel = new JPanel();
        changeUsernamePanel.setLayout(new GridLayout(4, 1, 5, 5));

        //create title for the "change username" page
        JLabel changeUsername = new JLabel("Change Username");
        changeUsername.setHorizontalAlignment(JLabel.CENTER);

        //create the text field for the "change username" page
        newUsername = new JTextField();
        newUsername.setBounds(200, 280, 80, 15);

        //create the save changed-username button
        saveUsername = new JButton("Save");

        //add them to the changeUsernamePanel in correct order
        changeUsernamePanel.add(changeUsername);
        changeUsernamePanel.add(newUsername);
        changeUsernamePanel.add(saveUsername);
    }

    // Change biography panel
    public void changeBioPage() {
        /*
        The "Change Bio" panel
         */
        changeBioPanel = new JPanel();
        changeBioPanel.setLayout(new GridLayout(3, 1));

        // Create Post stuff
//        Font font2 = new Font(Font.SANS_SERIF,  Font.PLAIN, 12);
//        editBioField = new JTextArea();
//        editBioField.setBounds(200,340,480,200);
//        editBioField.setFont(font2);

        JScrollPane scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create Title stuff
        Font font = new Font(Font.SANS_SERIF,  Font.BOLD, 20);

        changeBioField = new JTextField();
        changeBioField.setBounds(200,280,480,35);
        changeBioField.setFont(font);

        JLabel title = new JLabel("Enter Your New Bio:");
        title.setFont(font);
        title.setBounds(80, 100,50,20);

        confirmBioEditButton = new JButton("Done");
        confirmBioEditButton.setBounds(580,550,100,35);

//        ImageIcon icon = new ImageIcon("icons8-decrease-font-24.png");
//        adjustText12Button = new JButton(icon);
//        adjustText12Button.setBounds(200,240,140,30);
//
//        ImageIcon icon3 = new ImageIcon("icons8-a-24.png");
//        adjustText14Button = new JButton(icon3);
//        adjustText14Button.setBounds(370,240,140,30);
//
//        ImageIcon icon2 = new ImageIcon("icons8-increase-font-24.png");
//        adjustText16Button = new JButton(icon2);
//        adjustText16Button.setBounds(540,240,140,30);
//
//        ImageIcon icon4 = new ImageIcon("icons8-anime-emoji-30.png");
//        emojiButton = new JButton(icon4);
//        emojiButton.setBounds(200,550,100,35);
//
//        changeBioPanel.add(emojiButton);
//        changeBioPanel.add(adjustText12Button);
//        changeBioPanel.add(adjustText14Button);
//        changeBioPanel.add(adjustText16Button);

        changeBioPanel.add(changeBioField);
        changeBioPanel.add(confirmBioEditButton);
    }

    /*
    Frame preview testing: Good idea to set up a temporary main method to see how the pages will look.
    Comment out or delete this main method later
    */
    public static void main(String[] args) {
        PixieYourProfile pixieYourProfile = new PixieYourProfile();
        JFrame frame = new JFrame();
        frame.add(pixieYourProfile.changeBioPanel);
        frame.setVisible(true);
    }
}
