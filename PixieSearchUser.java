import javax.swing.*;
import java.awt.*;

public class PixieSearchUser extends JComponent {
    public JPanel subSearchPanel;  //show the three "view" options
    public JPanel showProfilePanel;  //show user's profile, including names and bio
    public JPanel showPostsPanel;  //show user's posts
    public JPanel showCommentsPanel;

    public JButton viewProfile;
    public JButton viewPosts;
    public JButton viewComments;
    Client client;
    JTextField userNameEnteredForSearch;
    JLabel userProfilePrompt;
    JLabel userPostsPrompt;
    Frame frame = new Frame();

    /** after click the search button, if the username is found, then the main panel should pop up three options**/
    private void showSearchOptionPage() {
        subSearchPanel = new JPanel();
        frame.add(subSearchPanel);
        subSearchPanel.setLayout(null);

        viewProfile = new JButton("View profile");
        viewProfile.setBounds(180, 100, 150, 25);
        viewPosts = new JButton("View posts");
        viewPosts.setBounds(180, 150, 150, 25);
        viewComments = new JButton("View comments");
        viewComments.setBounds(180, 200, 150, 25);


        subSearchPanel.add(viewProfile);
        subSearchPanel.add(viewPosts);
        subSearchPanel.add(viewComments);
        subSearchPanel.setVisible(false);

    }
    private void showViewProfilePage() {
        showProfilePanel = new JPanel(null);
        frame.add(showProfilePanel);
        String userSearched = "userSearch[" + userNameEnteredForSearch.getText() + "]";
        userProfilePrompt = new JLabel(client.streamReader(userSearched));
        userProfilePrompt.setBounds(80, 150, 200, 30);
        showProfilePanel.add(userProfilePrompt);
        //TODO: add the actionlistener
        showProfilePanel.setVisible(false);
    }

    private void showViewPosts() {
        showPostsPanel = new JPanel(null);
        frame.add(showPostsPanel);
        String userPosts = "getUserPosts["+ userNameEnteredForSearch.getText() + "]";
        userPostsPrompt = new JLabel(client.streamReader(userPosts));
        userPostsPrompt.setBounds(80, 150, 200, 30);
        showPostsPanel.add(userPostsPrompt);
        //TODO: add the actionlistener
        showPostsPanel.setVisible(false);
    }

    private void showViewComments() {
        showCommentsPanel = new JPanel(null);
        frame.add(showCommentsPanel);
        //TODO:
    }

}
