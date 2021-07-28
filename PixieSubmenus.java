import javax.swing.*;
import java.awt.*;

/**
 * PixieSubmenus.java - PJ05
 * Contains the submenu layouts that the user can see when s/he clicks on each of the main menu options after log in.
 */

public class PixieSubmenus extends JComponent {

    //make these fields public because Pixie.java needs to access them
    //creating getters for larger pages is extremely redundant

    Color subMenuColor = new Color(121, 71, 154, 255);

    //SUBMENUS FOR EACH MAIN MENU OPTION
    public JPanel yourProfileSubmenuPanel;
    public JPanel createPostSubmenuPanel;
    public JPanel yourPostsSubmenuPanel;
    public JPanel yourCommentsSubmenuPanel;
    public JPanel allPostsSubmenuPanel;
    public JPanel searchUserSubmenuPanel;

    //"YOUR PROFILE"
    public JButton changeBioButton;
    public JButton changeUsernameButton;
    public JButton changePasswordButton;
    public JButton deleteAccountButton;

    //"CREATE POST"
    public JButton writePostButton;
    public JButton importPostButton;

    //"VIEW YOUR POSTS"
    public JTextField selectYourPostField;
    public JButton selectYourPostButton;

    public JButton editYourPostTitleButton; //these buttons appear after selecting your post
    public JButton editYourPostContentButton;
    public JButton commentOnYourPostButton;
    public JButton exportYourPostButton;
    public JButton deleteYourPostButton;

    //"VIEW YOUR COMMENTS"
    public JTextField selectYourCommentField;
    public JButton selectYourCommentButton;

    public JButton editYourCommentContentButton; //these buttons appear after selecting a comment
    public JButton deleteYourCommentButton;

    //"VIEW ALL POSTS"
    public JTextField selectPostField;
    public JButton selectPostButton;

    public JButton commentOnPostButton; //this button appears after selecting a post

    //"SEARCH USER"
    public JTextField searchUserField;
    public JButton searchUserButton;

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
    }

    private void yourProfileSubmenu() {
        /*
        Create the "Your Profile" submenu that will appear when the user clicks the "Your Profile" button that's on the
        main menu. This panel will be a panel that appears on the side, listing out options on the "Your Profile" page.
         */
        yourProfileSubmenuPanel = new JPanel();
        yourProfileSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourProfileSubmenuPanel.setBackground(subMenuColor);

        JPanel yourProfileSubmenuPanelGrid = new JPanel();
        yourProfileSubmenuPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        yourProfileSubmenuPanelGrid.setBackground(subMenuColor);

        changeBioButton = new JButton("Change Biography");
        changeUsernameButton = new JButton("Change Username");
        changePasswordButton = new JButton("Change Password");
        deleteAccountButton = new JButton("Delete Account");

        JLabel yourProfileLabel = new JLabel("Your Profile");
        yourProfileLabel.setHorizontalAlignment(JLabel.CENTER);
        yourProfileLabel.setForeground(Color.white);

        yourProfileSubmenuPanelGrid.add(yourProfileLabel);
        yourProfileSubmenuPanelGrid.add(changeBioButton);
        yourProfileSubmenuPanelGrid.add(changeUsernameButton);
        yourProfileSubmenuPanelGrid.add(changePasswordButton);
        yourProfileSubmenuPanelGrid.add(deleteAccountButton);

        yourProfileSubmenuPanel.add(yourProfileSubmenuPanelGrid);
    }

    private void createPostSubmenu() {
        /*
        Create the "Create Post" submenu that will appear when the user clicks the "Create Post" button that's on the
        main menu. Appears when user selects "Create Post" option
         */
        createPostSubmenuPanel = new JPanel();
        createPostSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        createPostSubmenuPanel.setBackground(subMenuColor);

        JPanel createPostSubmenuPanelGrid = new JPanel();
        createPostSubmenuPanelGrid.setLayout(new GridLayout(3, 1, 5, 5));
        createPostSubmenuPanelGrid.setBackground(subMenuColor);

        writePostButton = new JButton("Write New Post");
        importPostButton = new JButton("Import CSV Post");

        JLabel createPostLabel = new JLabel("Create Post");
        createPostLabel.setHorizontalAlignment(JLabel.CENTER);
        createPostLabel.setForeground(Color.white);

        createPostSubmenuPanelGrid.add(createPostLabel);
        createPostSubmenuPanelGrid.add(writePostButton);
        createPostSubmenuPanelGrid.add(importPostButton);

        createPostSubmenuPanel.add(createPostSubmenuPanelGrid);
    }

    private void yourPostsSubmenu() {
        /*
        Create the selection part of "Your Posts" page, where the user selects the post to edit
         */
        yourPostsSubmenuPanel = new JPanel();
        yourPostsSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourPostsSubmenuPanel.setBackground(subMenuColor);

        JPanel yourPostsSubmenuPanelGrid = new JPanel();
        yourPostsSubmenuPanelGrid.setLayout(new GridLayout(4, 1, 5, 5));
        yourPostsSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel yourPostsLabel = new JLabel("Your Posts");
        yourPostsLabel.setHorizontalAlignment(JLabel.CENTER);
        yourPostsLabel.setForeground(Color.white);

        JLabel selectPostLabel = new JLabel("Select Post Number:");
        selectPostLabel.setHorizontalAlignment(JLabel.CENTER);
        selectPostLabel.setForeground(Color.white);

        selectYourPostField = new JTextField(5);
        selectYourPostButton = new JButton("Confirm");

        yourPostsSubmenuPanelGrid.add(yourPostsLabel);
        yourPostsSubmenuPanelGrid.add(selectPostLabel);
        yourPostsSubmenuPanelGrid.add(selectYourPostField);
        yourPostsSubmenuPanelGrid.add(selectYourPostButton);

        yourPostsSubmenuPanel.add(yourPostsSubmenuPanelGrid);
    } //todo: create the related page for after the user selects the post of interest

    public void yourCommentsSubmenu() {
        /*
        Create the selection part of "Your Comments" page, where the user selects the comment to edit
         */
        yourCommentsSubmenuPanel = new JPanel();
        yourCommentsSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourCommentsSubmenuPanel.setBackground(subMenuColor);

        JPanel yourCommentsSubmenuPanelGrid = new JPanel();
        yourCommentsSubmenuPanelGrid.setLayout(new GridLayout(4, 1, 5, 5));
        yourCommentsSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel yourCommentsLabel = new JLabel("Your Comments");
        yourCommentsLabel.setHorizontalAlignment(JLabel.CENTER);
        yourCommentsLabel.setForeground(Color.white);

        JLabel selectCommentLabel = new JLabel("Select Comment Number:");
        selectCommentLabel.setHorizontalAlignment(JLabel.CENTER);
        selectCommentLabel.setForeground(Color.white);

        selectYourCommentField = new JTextField(5);
        selectYourCommentButton = new JButton("Confirm");

        yourCommentsSubmenuPanelGrid.add(yourCommentsLabel);
        yourCommentsSubmenuPanelGrid.add(selectCommentLabel);
        yourCommentsSubmenuPanelGrid.add(selectYourCommentField);
        yourCommentsSubmenuPanelGrid.add(selectYourCommentButton);

        yourCommentsSubmenuPanel.add(yourCommentsSubmenuPanelGrid);
    }

    public void viewAllPostsSubmenu() {
        /*
        Create the selection part of "View All Posts" page, where the user selects the post to edit
         */
        allPostsSubmenuPanel = new JPanel();
        allPostsSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        allPostsSubmenuPanel.setBackground(subMenuColor);

        JPanel allPostsSubmenuPanelGrid = new JPanel();
        allPostsSubmenuPanelGrid.setLayout(new GridLayout(4, 1, 5, 5));
        allPostsSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel allPostsLabel = new JLabel("View All Posts");
        allPostsLabel.setHorizontalAlignment(JLabel.CENTER);
        allPostsLabel.setForeground(Color.white);

        JLabel selectPostLabel = new JLabel("Select Post Number:");
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

    public void searchUserSubmenu() {
        /*
        Create the search part of the "Search for User" page
         */
        searchUserSubmenuPanel = new JPanel();
        searchUserSubmenuPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        searchUserSubmenuPanel.setBackground(subMenuColor);

        JPanel searchUserSubmenuPanelGrid = new JPanel();
        searchUserSubmenuPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        searchUserSubmenuPanelGrid.setBackground(subMenuColor);

        JLabel searchUserLabel = new JLabel("Search For User");
        searchUserLabel.setHorizontalAlignment(JLabel.CENTER);
        searchUserLabel.setForeground(Color.white);

        JLabel searchUserInstructionLabel = new JLabel("Provide the Username:");
        searchUserInstructionLabel.setHorizontalAlignment(JLabel.CENTER);
        searchUserInstructionLabel.setForeground(Color.white);

        searchUserField = new JTextField(5);
        searchUserButton = new JButton("Confirm");

        searchUserSubmenuPanelGrid.add(searchUserLabel);
        searchUserSubmenuPanelGrid.add(searchUserInstructionLabel);
        searchUserSubmenuPanelGrid.add(searchUserField);
        searchUserSubmenuPanelGrid.add(searchUserButton);

        searchUserSubmenuPanel.add(searchUserSubmenuPanelGrid);
    }

}
