import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingUtilities.invokeLater;
/**
 * Project 5 GUI--besides login page.
 *
 * <p>xx
 *
 * @author Mingrui Xia
 * @version july 27 2021
 */
public class ContentDisplay extends JComponent implements Runnable {
    JButton yourProfile;
    /**option 1 submenu options**/
    JButton changeBio;
    JButton changeBioAction;
    JFrame frame;
    JButton changeUsername;
    JButton changePassword;
    JButton deleteAccount;
    JTextField newBio;

    JButton createPost;
    /**option createPost buttons**/
    JButton writeNewPost;
    JButton importPost;


    JButton viewYourPosts;
    JButton viewYourComments;
    JButton viewAllPosts;
    JButton searchUser;
    JButton logout;
    ContentDisplay display;

    //subpanels under the Your Profile button.
    public JPanel subPanel;
    public JPanel subBioPanel;
    public JPanel subUsernamePanel;
    public JPanel subPasswordPanel;
    public JPanel deleteAccountPanel;

    //"Create post" panel
    public JPanel createPostPanel;
    public JPanel newPostPanel;
    public JPanel importCSVPanel;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            /** every thing inside "Your Profile"**/
            if (e.getSource() == yourProfile) {
                frame.repaint();  //to refresh
                frame.remove(subBioPanel);
                frame.remove(subUsernamePanel);
                frame.remove(subPasswordPanel);
                frame.remove(deleteAccountPanel);
                frame.remove(createPostPanel);
                frame.remove(importCSVPanel);
                frame.remove(newPostPanel);
                frame.add(subPanel);
                subPanel.setVisible(true);
            }
            if (e.getSource() == changeBio) {
                frame.repaint();
                frame.remove(subPanel);
                frame.remove(createPostPanel);
                frame.add(subBioPanel);
                subBioPanel.setVisible(true);
                //TODO: show the user name and the Bio after perform action. 
                
            }
            if (e.getSource() == changeUsername) {
                frame.repaint();
                frame.remove(subPanel);
                frame.remove(createPostPanel);
                
                frame.add(subUsernamePanel);
                subUsernamePanel.setVisible(true);
            }
            if (e.getSource() == changePassword) {
                frame.repaint();
                frame.remove(subPanel);
                frame.remove(createPostPanel);
                
                frame.add(subPasswordPanel);
                subPasswordPanel.setVisible(true);
            }
            if (e.getSource() == deleteAccount) {
                frame.repaint();
                frame.remove(subPanel);
                frame.remove(createPostPanel);
              
                frame.add(deleteAccountPanel);
                deleteAccountPanel.setVisible(true);
            }
            /** every thing inside "Create Post"**/
            if (e.getSource() == createPost) {
                frame.repaint();
                frame.remove(subPanel);
                frame.remove(subUsernamePanel);
                frame.remove(subPasswordPanel);
                frame.remove(deleteAccountPanel);
                frame.remove(importCSVPanel);
                frame.remove(newPostPanel);
                frame.add(createPostPanel);
                createPostPanel.setVisible(true);
            }
            if (e.getSource() == writeNewPost) {
                frame.repaint();
                frame.remove(subPanel);
                frame.remove(createPostPanel);
                frame.add(newPostPanel);
                newPostPanel.setVisible(true);
            }
            if (e.getSource() == importPost) {
                frame.repaint();
                frame.remove(subPanel);
                frame.remove(createPostPanel);
                frame.add(importCSVPanel);
                importCSVPanel.setVisible(true);
            }
        }
    };
    private Client client;


    public static void main(String[] args) {
        // Run class
        invokeLater(new ContentDisplay());
    }


    @Override
    public void run() {
        frame = new JFrame();
        frame.setTitle("Client");  //may change the title name later

        Container content = frame.getContentPane();

        JPanel panel = new JPanel();  //empty main panel
        subPanel = new JPanel();  //panel in the main window with contents
        JPanel sidePanel = new JPanel();  //panel of the side
        JPanel grid = new JPanel();

        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocation(450, 200);
        frame.add(panel);

        panel.setLayout(null);


        // Side panel (on west) design
        sidePanel.setLayout(new FlowLayout(4, 4, 4));
        sidePanel.setBackground(new Color(94, 156, 156));
        frame.add(sidePanel, BorderLayout.WEST);

        grid.setLayout(new GridLayout(10, 1, 5, 5));
        grid.setBackground(new Color(94, 156, 156));

        yourProfile = new JButton("Your Profile");
        createPost = new JButton("Create Post");
        viewYourPosts = new JButton("View Your Posts");
        viewYourComments = new JButton("View Your Comments");
        viewAllPosts = new JButton("View All Posts");
        searchUser = new JButton("Search User");
        logout = new JButton("Search User");

        grid.add(yourProfile);
        grid.add(createPost);
        grid.add(viewYourPosts);
        grid.add(viewYourComments);
        grid.add(viewAllPosts);
        grid.add(searchUser);
        grid.add(logout);
        // Add grid to west panel
        sidePanel.add(grid);
        yourProfile.addActionListener(actionListener);

        /**1.1 setting some panels under the "Your Profile" button**/
        //setting subpanel of changing Bio under Your Profile.
        frame.add(subPanel);
        subPanel.setLayout(null);

        changeBio = new JButton("Change Bio");
        changeBio.setBounds(180, 100, 150, 25);


        changeUsername = new JButton("Change username");
        changeUsername.setBounds(180, 150, 150, 25);
        changePassword = new JButton("Change Password");
        changePassword.setBounds(180, 200, 150, 25);
        deleteAccount = new JButton("Delete Account");
        deleteAccount.setBounds(180, 250, 150, 25);

        subPanel.add(changeBio);
        subPanel.add(changeUsername);
        subPanel.add(changePassword);
        subPanel.add(deleteAccount);
        subPanel.setVisible(false);

        //1.2 set subpanel under the "Change Bio"
        changeBio.addActionListener(actionListener);
        subBioPanel = new JPanel();
        frame.add(subBioPanel);  //panel from main window
        subBioPanel.setLayout(null);
        changeBioAction = new JButton("Change Bio");
        changeBioAction.setBounds(200, 200, 100, 25);
        JLabel bioChanger = new JLabel("Enter your new bio: ");
        bioChanger.setBounds(70, 117, 120, 30);
        newBio = new JTextField(40);
        //TODO: change Bio and store into the client server
        newBio.setBounds(200, 125, 160, 20);
        subBioPanel.add(changeBioAction);
        subBioPanel.add(bioChanger);
        subBioPanel.add(newBio);
        subBioPanel.setVisible(false);

        //1.3 panel of "change username"
        changeUsername.addActionListener(actionListener);
        subUsernamePanel = new JPanel();
        frame.add(subUsernamePanel);
        subUsernamePanel.setLayout(null);
        JButton changeUsernameAction = new JButton("Change Username");
        changeUsernameAction.setBounds(200, 200, 160, 25);
        JLabel usernamePrompt = new JLabel("Enter your new username: ");
        usernamePrompt.setBounds(60, 117, 160, 30);
        JTextField newUsername = new JTextField(40);
        //TODO: change username and store into the client server
        newUsername.setBounds(220, 125, 160, 20);
        subUsernamePanel.add(changeUsernameAction);
        subUsernamePanel.add(usernamePrompt);
        subUsernamePanel.add(newUsername);
        subUsernamePanel.setVisible(false);

        //1.4 panel of "change password"
        changePassword.addActionListener(actionListener);
        subPasswordPanel = new JPanel();
        frame.add(subPasswordPanel);
        subPasswordPanel.setLayout(null);
        JButton changePwAction = new JButton("Change Password");
        changePwAction.setBounds(200, 200, 160, 25);
        JLabel changePwPrompt = new JLabel("Enter your new password: ");
        changePwPrompt.setBounds(60, 117, 160, 30);
        JTextField newPw = new JTextField(70);
        //TODO: change password and store into the client server
        newPw.setBounds(220, 125, 160, 20);
        subPasswordPanel.add(changePwAction);
        subPasswordPanel.add(changePwPrompt);
        subPasswordPanel.add(newPw);
        subPasswordPanel.setVisible(false);

        //1.5 panel of "delete account"
        deleteAccount.addActionListener(actionListener);
        deleteAccountPanel = new JPanel();
        frame.add(deleteAccountPanel);
        deleteAccountPanel.setLayout(null);
        JButton deleteAction = new JButton("Yes");
        JButton cancelDel = new JButton("No");
        //TODO: add delete or cancel action to actionlistener.
        deleteAction.setBounds(130, 200, 70, 25);
        cancelDel.setBounds(250, 200, 70, 25);
        //TODO: add username into the prompt
        JLabel decision = new JLabel("Are you sure you want to delete account %s? (Y/N)");
        decision.setBounds(110, 117, 290, 30);
        deleteAccountPanel.add(deleteAction);
        deleteAccountPanel.add(cancelDel);
        deleteAccountPanel.add(decision);
        deleteAccountPanel.setVisible(false);


        /**2.1 "create post" subpanel!!**/

        createPost.addActionListener(actionListener);
        createPostPanel = new JPanel();
        frame.add(createPostPanel);
        createPostPanel.setLayout(null);

        writeNewPost = new JButton("Write New Post");
        writeNewPost.setBounds(180, 150, 150, 25);

        importPost = new JButton("Import Post from CSV");
        importPost.setBounds(180, 200, 150, 25);


        createPostPanel.add(writeNewPost);
        createPostPanel.add(importPost);
        createPostPanel.setVisible(false);

        // 2.2: create newPostPanel
        newPostPanel = new JPanel();
        writeNewPost.addActionListener(actionListener);
        frame.add(newPostPanel);
        newPostPanel.setLayout(null);
        JLabel enterTitlePrompt = new JLabel("Enter a Post Title: ");
        enterTitlePrompt.setBounds(60, 117, 160, 30);
        JTextField enteredTitle = new JTextField(60);
        enteredTitle.setBounds(220, 125, 160, 25);

        JLabel enterPostMsgPrompt = new JLabel("Enter the Post's Message: ");
        enterPostMsgPrompt.setBounds(60, 220, 160, 30);
        JTextField postMsg = new JTextField(256);
        postMsg.setBounds(220, 225, 270, 25);
        JButton confirmPost = new JButton("Confirm");
        //TODO: Store the title and post content into database after clicking the "Confirm"
        //TODO: and then pops up a successful message? Maybe?
        confirmPost.setBounds(250, 380, 150, 30);

        newPostPanel.add(enterTitlePrompt);
        newPostPanel.add(enteredTitle);
        newPostPanel.add(enterPostMsgPrompt);
        newPostPanel.add(postMsg);
        newPostPanel.add(confirmPost);
        newPostPanel.setVisible(false);

        // 2.3: Panel of importing CSV to create post
        importCSVPanel = new JPanel();
        importPost.addActionListener(actionListener);
        frame.add(importCSVPanel);
        importCSVPanel.setLayout(null);
        JLabel dirPrompt = new JLabel("Enter name of CSV file to import (including the extension)");
        dirPrompt.setBounds(60, 110, 380, 30);
        JTextField enterDir = new JTextField(100);
        enterDir.setBounds(90, 185, 270, 25);
        JButton confirmDir = new JButton("Confirm");
        confirmDir.setBounds(170, 270, 100, 30);
        //TODO: add a actionlistener to add the post through by clicking confirm
        //TODO: Show a successful message if there is the right directory, or error message.

        importCSVPanel.add(dirPrompt);
        importCSVPanel.add(enterDir);
        importCSVPanel.add(confirmDir);
        importCSVPanel.setVisible(false);



        /*viewYourPosts.addActionListener(actionListener);
        viewYourComments.addActionListener(actionListener);
        viewAllPosts.addActionListener(actionListener);
        searchUser.addActionListener(actionListener);
        logout.addActionListener(actionListener);*/

        // Allow elements to show
        frame.setVisible(true);

    }
}
