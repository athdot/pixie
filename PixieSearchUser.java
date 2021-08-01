import javax.swing.*;
import java.awt.*;

public class PixieSearchUser extends JComponent {
    public JPanel subSearchPanel;  //show the three "view" options
    public JPanel showProfilePanel;  //show user's profile, including names and bio
    public JPanel showPostsPanel;  //show user's posts
    public JPanel viewSearchContainerPanel;

    public JButton viewProfile;
    public JButton viewPosts;
    public JButton viewComments;
    Client client = new Client();
    PixieSubmenus pSubmenus = new PixieSubmenus();


    JLabel profileFormatNameLabel;  //show the prompt of "Username: "
    JLabel userNameShowLabel;  //this label should show the user name received from database
    JLabel profileFormatBioLabel;  //show the prompt of "User bio: "
    JLabel userProfileShowLabel;  ////this label should show the user Bio received from database
    JLabel userProfilePrompt;

    JLabel userPostsPrompt;


    public PixieSearchUser() {
        showSearchOptionPage();
        showViewProfilePage();
        showViewPosts();
    }

    /** after click the search button, if the username is found, then the main panel should pop up three options**/
    public void showSearchOptionPage() {
        subSearchPanel = new JPanel();

        subSearchPanel.setLayout(null);

        viewProfile = new JButton("View profile");
        viewProfile.setBounds(180, 100, 150, 25);
        viewPosts = new JButton("View posts");
        viewPosts.setBounds(180, 150, 150, 25);

        subSearchPanel.add(viewProfile);
        subSearchPanel.add(viewPosts);



    }
    public void showViewProfilePage() {
        showProfilePanel = new JPanel(null);

        profileFormatNameLabel = new JLabel("Username: ");
        profileFormatNameLabel.setBounds(150, 100, 200, 30);
        userNameShowLabel = new JLabel();
        userNameShowLabel.setBounds(180, 140, 200, 30);
        userProfileShowLabel = new JLabel();
        profileFormatBioLabel = new JLabel("User Bio: ");
        profileFormatBioLabel.setBounds(150, 240, 200, 30);
        userProfileShowLabel.setBounds(180, 280, 200, 30);
        showProfilePanel.add(profileFormatNameLabel);
        showProfilePanel.add(userNameShowLabel);
        showProfilePanel.add(userProfileShowLabel);
        showProfilePanel.add(profileFormatBioLabel);
    }

    public void showViewPosts() {
        showPostsPanel = new JPanel(null);
        String userPosts = "getUserPosts["+ pSubmenus.getSearchUserFieldText() + "]";
        userPostsPrompt = new JLabel(client.streamReader(userPosts));
        userPostsPrompt.setBounds(80, 150, 200, 30);
        showPostsPanel.add(userPostsPrompt);
        
    }

    public static void main(String[] args) {
        PixieSearchUser psu = new PixieSearchUser();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 500);
        frame.add(psu.subSearchPanel);
        frame.setVisible(true);
    }

}
