import javax.swing.*;
import java.awt.*;

/**
 * PixieSearchUser
 * Contains basic panel layouts for search user function. Includes: search user option panel and buttons.
 * Search user function will also use PixieViewPost -- container panel for displaying posts and comments
 *
 * @author Group 8
 * @version July 31, 2021
 */

public class PixieSearchUser extends JComponent {

    Color subMenuColor = new Color(114, 90, 122, 255);

    public JPanel searchUserOptionsPanel;  //show the three "view" options
    public JButton searchUserViewProfileButton;
    public JButton searchUserViewPostsButton;
    public JButton searchUserViewCommentsButton;

    public JPanel searchUserProfilePanel;
    public JLabel searchUserUsernameLabel;
    public JLabel searchUserBioLabel;

    public PixieSearchUser() {
        searchUserOptionsPanel();
        searchUserProfilePanel();
    }

    /**
     * after click the search button, if the username is found, then the main panel should pop up three options
     */
    private void searchUserOptionsPanel() {

        searchUserOptionsPanel = new JPanel();
        searchUserOptionsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        searchUserOptionsPanel.setBackground(subMenuColor);

        JPanel searchUserOptionsPanelGrid = new JPanel();
        searchUserOptionsPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        searchUserOptionsPanelGrid.setBackground(subMenuColor);

        //these buttons appear after selecting your post
        searchUserViewProfileButton = new JButton("View Profile");
        searchUserViewPostsButton = new JButton("View Posts");
        searchUserViewCommentsButton = new JButton("View Comments");

        JLabel optionsTitle = new JLabel("Options");
        optionsTitle.setHorizontalAlignment(JLabel.CENTER);
        optionsTitle.setForeground(Color.white);

        searchUserOptionsPanelGrid.add(optionsTitle);
        searchUserOptionsPanelGrid.add(searchUserViewProfileButton);
        searchUserOptionsPanelGrid.add(searchUserViewPostsButton);
        searchUserOptionsPanelGrid.add(searchUserViewCommentsButton);

        searchUserOptionsPanel.add(searchUserOptionsPanelGrid);
    }

    private void searchUserProfilePanel() {
        searchUserProfilePanel = new JPanel(null);

        JLabel usernameTitle = new JLabel("Username:");
        usernameTitle.setBounds(100, 100, 100, 25);

        searchUserUsernameLabel = new JLabel("your username goes here");
        searchUserUsernameLabel.setBounds(100, 120, 200, 25);

        JLabel biographyTitle = new JLabel("Biography:");
        biographyTitle.setBounds(100, 200, 300, 25);

        //DEBUGGED: wrapping in html tags will automatically display runoff text on a new line for JLabel
        searchUserBioLabel = new JLabel("<html>your biography goes here</html>");
        searchUserBioLabel.setBounds(0, 0, 300, 300);
        searchUserBioLabel.setVerticalAlignment(JLabel.TOP);

        JPanel bioContainerPanel = new JPanel(null);
        bioContainerPanel.setBounds(100, 225, 300, 300);
        bioContainerPanel.add(searchUserBioLabel);

        searchUserProfilePanel.add(usernameTitle);
        searchUserProfilePanel.add(searchUserUsernameLabel);
        searchUserProfilePanel.add(biographyTitle);
        searchUserProfilePanel.add(bioContainerPanel);
    }

    public static void main(String[] args) {
        PixieSearchUser psu = new PixieSearchUser();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 500);
        frame.add(psu.searchUserProfilePanel);
        frame.setVisible(true);
    }

}
