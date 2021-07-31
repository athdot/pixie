import javax.swing.*;
import java.awt.*;

/**
 * PixieSubmenus.java - PJ05
 * Contains the submenu layouts that the user can see when s/he clicks on each of the main menu options after log in.
 *
 * @author Group 8
 * @version July 28, 2021
 */

public class PixieSubmenus extends JComponent {

    //make these fields public because Pixie.java needs to access them
    //creating getters for larger pages is extremely redundant

    Color subMenuColor = new Color(114, 90, 122, 255);

    public JPanel blankPanel = new JPanel(null); //start off on this submenu panel

    //"YOUR PROFILE"
    public JPanel yourProfileSubmenuPanel;
    public JButton changeBioButton;
    public JButton changeUsernameButton;
    public JButton changePasswordButton;
    public JButton deleteAccountButton;

    //"CREATE POST SUBMENU" submenu components
    public JPanel createPostSubmenuPanel;
    public JButton writePostButton;
    public JButton importPostButton;

    //"VIEW YOUR POSTS" submenu components
    public JPanel yourPostsSubmenuPanel;
    public JTextField selectYourPostField;
    public JButton selectYourPostButton;

    //"VIEW YOUR COMMENTS" submenu components
    public JPanel yourCommentsSubmenuPanel;
    public JTextField selectCommentPostField; //post number of interest
    public JTextField selectCommentField; //comment number on that post
    public JButton selectCommentButton;

    //"VIEW ALL POSTS" submenu components
    public JPanel allPostsSubmenuPanel;
    public JTextField selectPostField;
    public JButton selectPostButton;

    //"SEARCH USER" submenu components
    public JPanel searchUserSubmenuPanel;
    public JTextField searchUserField;
    public JButton searchUserConfirmButton;

    public JButton searchUserViewProfile; //these options appear after searching and finding a user
    public JButton searchUserViewPosts;
    public JButton searchUserViewComments;

    public PixieSubmenus() {
        //creating an instance of this class also creates the submenus that will show on the appFrame
        yourProfileSubmenu();
        createPostSubmenu();
        yourPostsSubmenu();
        yourCommentsSubmenu();
        viewAllPostsSubmenu();
        searchUserSubmenu();
        blankPanelSubmenu();
    }

    //basic set up for the blank panel submenu
    private void blankPanelSubmenu() {
        blankPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 30, 4));
        blankPanel.setBackground(subMenuColor);
    }

    /**
     * Create the "Your Profile" submenu that will appear when the user clicks the "Your Profile" button that's on the
     * main menu. This panel will be a panel that appears on the side, listing out options on the "Your Profile" page.
     */
    private void yourProfileSubmenu() {
        yourProfileSubmenuPanel = new JPanel();
        yourProfileSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourProfileSubmenuPanel.setBackground(subMenuColor);

        JPanel yourProfileSubmenuPanelGrid = new JPanel();
        yourProfileSubmenuPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        yourProfileSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel yourProfileLabel = new JLabel("Your Profile");
        yourProfileLabel.setHorizontalAlignment(JLabel.CENTER);
        yourProfileLabel.setForeground(Color.white);

        changeBioButton = new JButton("Change Biography");
        changeUsernameButton = new JButton("Change Username");
        changePasswordButton = new JButton("Change Password");
        deleteAccountButton = new JButton("Delete Account");

        yourProfileSubmenuPanelGrid.add(yourProfileLabel);
        yourProfileSubmenuPanelGrid.add(changeBioButton);
        yourProfileSubmenuPanelGrid.add(changeUsernameButton);
        yourProfileSubmenuPanelGrid.add(changePasswordButton);
        yourProfileSubmenuPanelGrid.add(deleteAccountButton);

        yourProfileSubmenuPanel.add(yourProfileSubmenuPanelGrid);
    }

    /**
     * Create the "Create Post" submenu that will appear when the user clicks the "Create Post" button that's on the
     * main menu. Appears when user selects "Create Post" option
     */
    private void createPostSubmenu() {
        createPostSubmenuPanel = new JPanel();
        createPostSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        createPostSubmenuPanel.setBackground(subMenuColor);

        JPanel createPostSubmenuPanelGrid = new JPanel();
        createPostSubmenuPanelGrid.setLayout(new GridLayout(3, 1, 5, 5));
        createPostSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel createPostLabel = new JLabel("Create Post");
        createPostLabel.setHorizontalAlignment(JLabel.CENTER);
        createPostLabel.setForeground(Color.white);

        writePostButton = new JButton("Write New Post");
        importPostButton = new JButton("Import CSV Post");

        createPostSubmenuPanelGrid.add(createPostLabel);
        createPostSubmenuPanelGrid.add(writePostButton);
        createPostSubmenuPanelGrid.add(importPostButton);

        createPostSubmenuPanel.add(createPostSubmenuPanelGrid);
    }

    /**
     * Create the selection part of "Your Posts" page, where the user selects the post to edit
     */
    private void yourPostsSubmenu() {
        yourPostsSubmenuPanel = new JPanel();
        yourPostsSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourPostsSubmenuPanel.setBackground(subMenuColor);

        JPanel yourPostsSubmenuPanelGrid = new JPanel();
        yourPostsSubmenuPanelGrid.setLayout(new GridLayout(4, 1, 5, 5));
        yourPostsSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel yourPostsLabel = new JLabel("Your Posts");
        yourPostsLabel.setHorizontalAlignment(JLabel.CENTER);
        yourPostsLabel.setForeground(Color.white);

        JLabel selectPostLabel = new JLabel("Select Post #:");
        selectPostLabel.setHorizontalAlignment(JLabel.CENTER);
        selectPostLabel.setForeground(Color.white);

        selectYourPostField = new JTextField(5);
        selectYourPostButton = new JButton("Confirm");

        yourPostsSubmenuPanelGrid.add(yourPostsLabel);
        yourPostsSubmenuPanelGrid.add(selectPostLabel);
        yourPostsSubmenuPanelGrid.add(selectYourPostField);
        yourPostsSubmenuPanelGrid.add(selectYourPostButton);

        yourPostsSubmenuPanel.add(yourPostsSubmenuPanelGrid);
    }

    /**
     * Create the selection part of "Your Comments" page, where the user selects the comment to edit
     */
    private void yourCommentsSubmenu() {
        yourCommentsSubmenuPanel = new JPanel();
        yourCommentsSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourCommentsSubmenuPanel.setBackground(subMenuColor);

        JPanel yourCommentsSubmenuPanelGrid = new JPanel();
        yourCommentsSubmenuPanelGrid.setLayout(new GridLayout(6, 1, 5, 5));
        yourCommentsSubmenuPanelGrid.setBackground(subMenuColor);

        //title of the submenu panel
        JLabel yourCommentsLabel = new JLabel("Your Comments");
        yourCommentsLabel.setHorizontalAlignment(JLabel.CENTER);
        yourCommentsLabel.setForeground(Color.white);

        //select post part
        JLabel selectPostLabel = new JLabel("Select Post #:");
        selectPostLabel.setHorizontalAlignment(JLabel.CENTER);
        selectPostLabel.setForeground(Color.white);
        selectCommentPostField = new JTextField(5);

        //select comment part
        JLabel selectCommentLabel = new JLabel("Select Comment #:");
        selectCommentLabel.setHorizontalAlignment(JLabel.CENTER);
        selectCommentLabel.setForeground(Color.white);
        selectCommentField = new JTextField(5);

        selectCommentButton = new JButton("Confirm");

        yourCommentsSubmenuPanelGrid.add(yourCommentsLabel); //panel title
        yourCommentsSubmenuPanelGrid.add(selectPostLabel); //instruction and fields
        yourCommentsSubmenuPanelGrid.add(selectCommentPostField);
        yourCommentsSubmenuPanelGrid.add(selectCommentLabel);
        yourCommentsSubmenuPanelGrid.add(selectCommentField);
        yourCommentsSubmenuPanelGrid.add(selectCommentButton); //select comment button

        yourCommentsSubmenuPanel.add(yourCommentsSubmenuPanelGrid);
    }

    /**
     * Create the selection part of "View All Posts" page, where the user selects the post to edit
     */
    private void viewAllPostsSubmenu() {
        allPostsSubmenuPanel = new JPanel();
        allPostsSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        allPostsSubmenuPanel.setBackground(subMenuColor);

        JPanel allPostsSubmenuPanelGrid = new JPanel();
        allPostsSubmenuPanelGrid.setLayout(new GridLayout(4, 1, 5, 5));
        allPostsSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel allPostsLabel = new JLabel("All Posts");
        allPostsLabel.setHorizontalAlignment(JLabel.CENTER);
        allPostsLabel.setForeground(Color.white);

        JLabel selectPostLabel = new JLabel("Select Post #:");
        selectPostLabel.setHorizontalAlignment(JLabel.CENTER);
        selectPostLabel.setForeground(Color.white);

        selectPostField = new JTextField(5);
        selectPostButton = new JButton("Confirm");

        allPostsSubmenuPanelGrid.add(allPostsLabel);
        allPostsSubmenuPanelGrid.add(selectPostLabel);
        allPostsSubmenuPanelGrid.add(selectPostField);
        allPostsSubmenuPanelGrid.add(selectPostButton);

        allPostsSubmenuPanel.add(allPostsSubmenuPanelGrid);
    }

    /**
     * Create the search part of the "Search for User" page
     */
    private void searchUserSubmenu() {
        searchUserSubmenuPanel = new JPanel();
        searchUserSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        searchUserSubmenuPanel.setBackground(subMenuColor);

        JPanel searchUserSubmenuPanelGrid = new JPanel();
        searchUserSubmenuPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        searchUserSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel searchUserLabel = new JLabel("Search User");
        searchUserLabel.setHorizontalAlignment(JLabel.CENTER);
        searchUserLabel.setForeground(Color.white);

        JLabel searchUserInstructionLabel = new JLabel("Provide Username:");
        searchUserInstructionLabel.setHorizontalAlignment(JLabel.CENTER);
        searchUserInstructionLabel.setForeground(Color.white);

        searchUserField = new JTextField(5);
        searchUserConfirmButton = new JButton("Confirm");

        searchUserSubmenuPanelGrid.add(searchUserLabel);
        searchUserSubmenuPanelGrid.add(searchUserInstructionLabel);
        searchUserSubmenuPanelGrid.add(searchUserField);
        searchUserSubmenuPanelGrid.add(searchUserConfirmButton);

        searchUserSubmenuPanel.add(searchUserSubmenuPanelGrid);
    }
    
    public String getSearchUserFieldText() {
        return searchUserField.getText();
    }

}
