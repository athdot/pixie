import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Pixie.java - PJ05
 * Apparently our app is branded as "Pixie". Pixes are mythical creatures in folklore and children's stories.
 * This class ties the GUI together by allowing all panels of the application to work together. Panels are created
 * within different classes for organization. Pixie.java creates an instance of all these panels to call them from
 * their external classes. Also listens for all actions across the program.
 *
 * NOTE: DON'T FORGET TO RUN Server.java FIRST
 *
 * Large comments -- https://fsymbols.com/generators/tarty/ -- 2nd option down, replace all   character with " "
 * Don't overuse! Only use this if a section becomes annoying to navigate!
 *
 * @author Group 8
 * @version July 27, 2021
 */

public class Pixie extends JComponent implements Runnable {

    //each user who has the app open also has a Client object that communicates with the Server class.
    private final Client CLIENT = new Client();

    String activeUsername; //for client communication with server: what username is currently logged in?
    JPanel activeSubmenuPanel; //the submenus of the main menu options: which one is currently shown?
    JPanel activeContentPanel; //the right-most panel of the app: what's currently on it?

    //store total number of posts/comments that showed up in "your posts", "your comments", and "all posts"
    int yourPostsNumTotal;
    int yourCommentsNumTotal;
    int allPostsNumTotal;

    //store chosen numbers for posts/comments -- remember the numbers that appeared
    int yourPostsChosenNum;
    int yourCommentsChosenNum;
    int allPostsChosenNum;

    //LOGIN FRAME: baseline features -- the login menu
    JFrame loginFrame; //login page frame
    JButton createAccountButton;
    JButton signInButton;

    //APP FRAME: panels for "mapping" purposes
    JFrame appFrame; //for rest of application
    JPanel appPanel; //panel containing all sub-menus and the content that they lead to
    JPanel appPanelContent; //content currently on the right-most panel of the appFrame

    //APP FRAME: baseline features -- the main menu
    JButton yourProfileButton;
    JButton createPostButton;
    JButton yourPostsButton;
    JButton yourCommentsButton;
    JButton allPostsButton;
    JButton searchUserButton;
    JButton logoutButton;

    /**
     * LOGIN FRAME: bring the panels and their components created for the login page from PixieLoginPage class
     */

    PixieLoginPage pixieLoginPage = new PixieLoginPage();

    JPanel signInPanel = pixieLoginPage.signInPanel;
    JTextField signInUsernameField = pixieLoginPage.signInUsernameField;
    JTextField signInPasswordField = pixieLoginPage.signInPasswordField;
    JButton signInConfirmButton = pixieLoginPage.signInConfirmButton;

    JPanel createAccountPanel = pixieLoginPage.createAccountPanel;
    JTextField createAccountUsernameField = pixieLoginPage.createAccountUsernameField;
    JTextField createAccountPasswordField = pixieLoginPage.createAccountPasswordField;
    JTextField confirmPasswordField = pixieLoginPage.confirmPasswordField;
    JButton createAccountConfirmButton = pixieLoginPage.createAccountConfirmButton;

    /**
     * MAIN MENU'S SUBMENUS: bring the side-panel "sub-menus" and their components from PixieSubmenus.java
     */

    PixieSubmenus pixieSubmenus = new PixieSubmenus();

    JPanel blankSubmenuPanel = pixieSubmenus.blankPanel; //?

    JPanel yourCommentsSubmenuPanel = pixieSubmenus.yourCommentsSubmenuPanel;
    JPanel allPostsSubmenuPanel = pixieSubmenus.allPostsSubmenuPanel;
    JPanel searchUserSubmenuPanel = pixieSubmenus.searchUserSubmenuPanel;

    JPanel yourProfileSubmenuPanel = pixieSubmenus.yourProfileSubmenuPanel;
    JButton changeBioButton = pixieSubmenus.changeBioButton;
    JButton changeUsernameButton = pixieSubmenus.changeUsernameButton;
    JButton changePasswordButton = pixieSubmenus.changePasswordButton;
    JButton deleteAccountButton = pixieSubmenus.deleteAccountButton;

    JPanel createPostSubmenuPanel = pixieSubmenus.createPostSubmenuPanel;
    JButton writePostButton = pixieSubmenus.writePostButton;
    JButton importPostButton = pixieSubmenus.importPostButton;

    JPanel yourPostsSubmenuPanel = pixieSubmenus.yourPostsSubmenuPanel;
    JTextField selectYourPostField = pixieSubmenus.selectYourPostField;
    JButton selectYourPostButton = pixieSubmenus.selectYourPostButton;

    /**
     * YOUR PROFILE: bring panel setups for "Your Profile" page from PixieYourProfile
     */

    PixieYourProfile pixieYourProfile = new PixieYourProfile();

    JPanel blankContentPanel = pixieYourProfile.blankPanel; //if you want the menu to be empty

    JPanel changeBioPanel = pixieYourProfile.changeBioPanel; //change bio page
    JTextField changeBioField = pixieYourProfile.changeBioField;
    JButton confirmChangeBioButton = pixieYourProfile.confirmChangeBioButton;

    JPanel changeUsernamePanel = pixieYourProfile.changeUsernamePanel; //change username page
    JTextField changeUsernameField = pixieYourProfile.changeUsernameField;
    JButton confirmChangeUsernameButton = pixieYourProfile.confirmChangeUsernameButton;

    JPanel changePasswordPanel = pixieYourProfile.changePasswordPanel; //change password page
    JTextField oldPasswordField = pixieYourProfile.oldPasswordField;
    JTextField newPasswordField = pixieYourProfile.newPasswordField;
    JButton confirmChangePasswordButton = pixieYourProfile.confirmChangePasswordButton;

    JPanel yourProfilePanel = pixieYourProfile.yourProfilePanel; //viewing your profile
    JLabel yourProfileUsernameLabel = pixieYourProfile.yourProfileUsernameLabel;
    JLabel yourProfileBioLabel = pixieYourProfile.yourProfileBioLabel;

    /**
     * CREATE POST: bring panel setups for "Create Post" page from PixieCreatePost
     */

    PixieCreatePost pixieCreatePost = new PixieCreatePost();
    
    JPanel createNewPostPanel = pixieCreatePost.createNewPostPanel; //"write new post" page
    JTextField createPostTitleField = pixieCreatePost.createPostTitleField;
    JTextField createPostContentField = pixieCreatePost.createPostContentField;
    JButton doneEditingPostButton = pixieCreatePost.doneEditingPostButton;

    JPanel importFromCSVPanel = pixieCreatePost.importFromCSVPanel; //"import from CSV" page
    JTextField importFromCSVField = pixieCreatePost.importFromCSVField;
    JButton importFromCSVButton = pixieCreatePost.importFromCSVButton;

    /**
     * VIEW YOUR POST/VIEW ALL POSTS: bring panel setups and components from PixieViewPost
     */

    PixieViewPost pixieViewPost = new PixieViewPost();

    JPanel viewPostsOutlinePanel = pixieViewPost.viewPostsOutlinePanel; //parent panel for viewing posts
    JPanel viewPostsContainerPanel = pixieViewPost.viewPostsContainerPanel; //add the JLabel posts into here

    JPanel viewYourPostOptionsPanel = pixieViewPost.viewYourPostOptionsPanel; //panel appears after selecting post
    JButton editYourPostButton = pixieViewPost.editYourPostButton; //buttons appear after selecting your post
    JButton commentOnYourPostButton = pixieViewPost.commentOnYourPostButton;
    JButton exportYourPostButton = pixieViewPost.exportYourPostButton;
    JButton deleteYourPostButton = pixieViewPost.deleteYourPostButton;

    JPanel viewAllPostOptionsPanel = pixieViewPost.viewAllPostOptionsPanel; //panel appears after selecting post
    JButton commentOnPostButton = pixieViewPost.commentOnPostButton; //appears after selecting a post

    JPanel commentOnPostPanel = pixieViewPost.commentOnPostPanel; //create/edit comment page
    JTextField commentOnPostField = pixieViewPost.commentOnPostField;
    JButton confirmCommentButton = pixieViewPost.confirmCommentButton;

    //FOR THE ENTIRE PROGRAM: Action listeners for all components that require action listeners
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            //█░░ █▀█ █▀▀ █ █▄░█
            //█▄▄ █▄█ █▄█ █ █░▀█

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
                    activeUsername = signInUsernameField.getText();
                    String profile = CLIENT.streamReader("getProfile[" + activeUsername + "]");
                    Account user = StreamParse.stringToAccount(profile);
                    yourProfileUsernameLabel.setText(activeUsername);
                    yourProfileBioLabel.setText("<html>" + user.getBio() + "</html>");
                    changeFrame(loginFrame, appFrame);

                    JOptionPane.showMessageDialog(null,
                            String.format("Welcome back, %s!", user.getUsername()), "Welcome",
                            JOptionPane.INFORMATION_MESSAGE);

                    signInUsernameField.setText("");
                    signInPasswordField.setText("");
                    createAccountUsernameField.setText("");
                    createAccountPasswordField.setText("");
                    confirmPasswordField.setText("");
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
                    activeUsername = createAccountUsernameField.getText();
                    String profile = CLIENT.streamReader("getProfile[" + activeUsername + "]");
                    Account user = StreamParse.stringToAccount(profile);
                    yourProfileUsernameLabel.setText(activeUsername);
                    yourProfileBioLabel.setText("<html>" + user.getBio() + "</html>");
                    changeFrame(loginFrame, appFrame);

                    JOptionPane.showMessageDialog(null,
                            String.format("Welcome, %s!", user.getUsername()), "Welcome",
                            JOptionPane.INFORMATION_MESSAGE);

                    signInUsernameField.setText("");
                    signInPasswordField.setText("");
                    createAccountUsernameField.setText("");
                    createAccountPasswordField.setText("");
                    confirmPasswordField.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                }
            }

            //█▄█ █▀█ █░█ █▀█   █▀█ █▀█ █▀█ █▀▀ █ █░░ █▀▀
            //░█░ █▄█ █▄█ █▀▄   █▀▀ █▀▄ █▄█ █▀░ █ █▄▄ ██▄

            //user clicks main menu button to go to "your profile" page
            if (e.getSource() == yourProfileButton) {
                switchPanel(appPanel, activeSubmenuPanel, yourProfileSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = yourProfileSubmenuPanel;

                String profile = CLIENT.streamReader("getProfile[" + activeUsername + "]");
                Account user = StreamParse.stringToAccount(profile);
                yourProfileUsernameLabel.setText(activeUsername);
                yourProfileBioLabel.setText("<html>" + user.getBio() + "</html>");
                switchPanel(appPanelContent, activeContentPanel, yourProfilePanel, BorderLayout.CENTER);
                activeContentPanel = yourProfilePanel;
            }

            if (e.getSource() == changeBioButton) {
                switchPanel(appPanelContent, activeContentPanel, changeBioPanel, BorderLayout.CENTER);
                activeContentPanel = changeBioPanel;
            }

            if (e.getSource() == confirmChangeBioButton) {
                String changeBio = "changeBio[" + changeBioField.getText() + "]";
                changeBio = CLIENT.streamReader(changeBio);

                if (changeBio.equalsIgnoreCase("false")) {
                    JOptionPane.showMessageDialog(null, "Something went wrong",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Biography changed successfully!",
                            "Biography changed", JOptionPane.INFORMATION_MESSAGE);
                    changeBioField.setText("");
                }
            }
            
            // YOUR PROFILE: CHANGE USERNAME BUTTON -- user wants to go to change username page
            if (e.getSource() == changeUsernameButton) {
                switchPanel(appPanelContent, activeContentPanel, changeUsernamePanel, BorderLayout.CENTER);
                activeContentPanel = changeUsernamePanel;
            }

            if (e.getSource() == confirmChangeUsernameButton) {
                String newUsername = "changeUsername[" + changeUsernameField.getText() + "]";

                newUsername = CLIENT.streamReader(newUsername);
                
                if (changeUsernameField.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "No spaces should be in the username",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return; //exit the method, ignore the rest.
                } else if (changeUsernameField.getText().contains(",")) {
                    JOptionPane.showMessageDialog(null, "No commas should be in the username",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (changeUsernameField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Username is too short",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (newUsername.equalsIgnoreCase("false") || changeUsernameField.getText().equals(activeUsername)) {
                    JOptionPane.showMessageDialog(null, "Username is taken",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Username changed successfully!",
                            "Username changed", JOptionPane.INFORMATION_MESSAGE);
                    activeUsername = changeUsernameField.getText();
                    changeUsernameField.setText("");
                }
            }
            
            // YOUR PROFILE: CHANGE PASSWORD BUTTON -- user wants to go to change password page
            if (e.getSource() == changePasswordButton) {
                switchPanel(appPanelContent, activeContentPanel, changePasswordPanel, BorderLayout.CENTER);
                activeContentPanel = changePasswordPanel;
            }

            if (e.getSource() == confirmChangePasswordButton) {
                String changePassword = "changePassword[" + oldPasswordField.getText() + ","
                        + newPasswordField.getText() + "]";

                changePassword = CLIENT.streamReader(changePassword);
                
                if (newPasswordField.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "No spaces should be in the password",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return; //exit the method, ignore the rest.
                } else if (newPasswordField.getText().contains(",")) {
                    JOptionPane.showMessageDialog(null, "No commas should be in the password",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (newPasswordField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Password is too short",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (changePassword.equalsIgnoreCase("false")) {
                    JOptionPane.showMessageDialog(null, "Old password was incorrect",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Password changed successfully!",
                            "Password changed", JOptionPane.INFORMATION_MESSAGE);
                    oldPasswordField.setText("");
                    newPasswordField.setText("");
                }
            }
            
            //user clicks "delete account" button on "your profile" menu
            if (e.getSource() == deleteAccountButton) {
                // Makes sure user didn't click delete button by accident
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete your account?", "Delete?",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your Account Has Been Deleted",
                            "Account Deleted", JOptionPane.INFORMATION_MESSAGE);

                    activeUsername = createAccountUsernameField.getText();
                    CLIENT.streamReader("deleteAccount");
                    changeFrame(appFrame, loginFrame);
                }
            }

            //█▀▀ █▀█ █▀▀ ▄▀█ ▀█▀ █▀▀   █▀█ █▀█ █▀ ▀█▀
            //█▄▄ █▀▄ ██▄ █▀█ ░█░ ██▄   █▀▀ █▄█ ▄█ ░█░

            //user goes to "create post" page submenu
            if (e.getSource() == createPostButton) {
                switchPanel(appPanel, activeSubmenuPanel, createPostSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = createPostSubmenuPanel;

                switchPanel(appPanelContent, activeContentPanel, blankContentPanel, BorderLayout.CENTER);
                activeContentPanel = blankContentPanel;
            }

            //create post: user wants to write a new post
            if (e.getSource() == writePostButton) {
                switchPanel(appPanelContent, activeContentPanel, createNewPostPanel, BorderLayout.CENTER);
                activeContentPanel = createNewPostPanel;
            }

            //user is done editing the post and wants to save/create it
            if (e.getSource() == doneEditingPostButton) {
                String post = "post[" + createPostTitleField.getText() + "," + createPostContentField.getText() + "]";
                String worked = CLIENT.streamReader(post);

                if (worked.equals("true")) {
                    JOptionPane.showMessageDialog(null, "Post has been added successfully!",
                            "Post added", JOptionPane.INFORMATION_MESSAGE);
                    createPostTitleField.setText("");
                    createPostContentField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Post was unable to be added",
                            "Something went wrong", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            //create post: user wants to go to import post from CSV page
            if (e.getSource() == importPostButton) {
                switchPanel(appPanelContent, activeContentPanel, importFromCSVPanel, BorderLayout.CENTER);
                activeContentPanel = importFromCSVPanel;
            }

            //create post: user confirms the CSV file name for import
            if (e.getSource() == importFromCSVButton) {

                //ask Charles to integrate this part into the Server? -- for organization?
                String filename = importFromCSVField.getText();

                try {
                    ArrayList<String[]> importBlock = DataManagement.readFile(filename);

                    //test username: cannot import post for someone else
                    if (importBlock.get(0)[2].split(",")[0].equals(activeUsername)) {

                        String postTitle = importBlock.get(0)[1].split(",")[0];
                        String postContent = importBlock.get(0)[2].split(",")[2];
                        String worked = CLIENT.streamReader("post[" + postTitle + "," + postContent + "]");

                        if (worked.equals("true")) {
                            JOptionPane.showMessageDialog(null,
                                    "Post has been added successfully!", "Post added",
                                    JOptionPane.INFORMATION_MESSAGE);
                            importFromCSVField.setText("");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "You cannot import this post",
                                "Something went wrong", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Post was unable to be imported",
                            "Something went wrong", JOptionPane.ERROR_MESSAGE);
                }
            }

            //█░█ █ █▀▀ █░█░█   █▄█ █▀█ █░█ █▀█   █▀█ █▀█ █▀ ▀█▀ █▀
            //▀▄▀ █ ██▄ ▀▄▀▄▀   ░█░ █▄█ █▄█ █▀▄   █▀▀ █▄█ ▄█ ░█░ ▄█

            //remove any list of posts everytime you click a main menu option (except logout)
            if (e.getSource() == yourProfileButton || e.getSource() == createPostButton ||
                    e.getSource() == yourPostsButton || e.getSource() == yourCommentsButton ||
                    e.getSource() == allPostsButton || e.getSource() == searchUserButton) {
                //NOTE: keep this statement in-front of all logic that lists out the posts
                viewPostsContainerPanel.removeAll();
            }

            //user clicks main menu button to go to "your posts" page
            if (e.getSource() == yourPostsButton) {
                switchPanel(appPanel, activeSubmenuPanel, yourPostsSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = yourPostsSubmenuPanel;

                switchPanel(appPanelContent, activeContentPanel, viewPostsOutlinePanel, BorderLayout.CENTER);
                activeContentPanel = viewPostsOutlinePanel;

                String getYourPosts = CLIENT.streamReader("getUserPosts[" + activeUsername + "]");
                ArrayList<Post> yourPosts = StreamParse.stringToPosts(getYourPosts);

                yourPostsNumTotal = 0;
                for (int i = yourPosts.size(); i > 0; i--) { //start from the back, to get latest posts first

                    Post post = yourPosts.get(i - 1);
                    String formattedPost =  "<html>" + "Post #" + ++yourPostsNumTotal + ": " +
                            post.toString().replace("\n", "<br/>") + "<br/></html>";
                    JLabel postLabel = new JLabel(formattedPost); //each post is displayed within a label

                    //fluid dimensions can be achieved with setMinimumSize, setMaximumSize, and setPreferredSize
                    postLabel.setMinimumSize(new Dimension(300, 0));
                    postLabel.setPreferredSize(new Dimension(300, 100));
                    postLabel.setMaximumSize(new Dimension(300, 500));
                    postLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    postLabel.setVerticalAlignment(JLabel.CENTER);

                    viewPostsContainerPanel.add(postLabel);
                }
            }

            if (e.getSource() == selectYourPostButton) {
                yourPostsChosenNum = 0;
                try {
                    yourPostsChosenNum = Integer.parseInt(selectYourPostField.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Please provide a valid post number",
                            "Select post", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (1 <= yourPostsChosenNum && yourPostsChosenNum <= yourPostsNumTotal) {
                    switchPanel(appPanel, activeSubmenuPanel, viewYourPostOptionsPanel, BorderLayout.WEST);
                    activeSubmenuPanel = viewYourPostOptionsPanel;
                } else {
                    JOptionPane.showMessageDialog(null, "Please provide a valid post number",
                            "Select post", JOptionPane.ERROR_MESSAGE);
                    yourPostsChosenNum = 0;
                }
            }

            //todo: complete edit post, create comment, export post, delete post below -- scenario: after selecting a post
            //todo: "View Your Comments" and "View All Posts" will have similar logic to "View Your Posts"

            //█░█ █ █▀▀ █░█░█   █▄█ █▀█ █░█ █▀█   █▀▀ █▀█ █▀▄▀█ █▀▄▀█ █▀▀ █▄░█ ▀█▀ █▀
            //▀▄▀ █ ██▄ ▀▄▀▄▀   ░█░ █▄█ █▄█ █▀▄   █▄▄ █▄█ █░▀░█ █░▀░█ ██▄ █░▀█ ░█░ ▄█

            //user clicks main menu button to go to "your comments" page
            if (e.getSource() == yourCommentsButton) {
                switchPanel(appPanel, activeSubmenuPanel, yourCommentsSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = yourCommentsSubmenuPanel;
            }

            //█░█ █ █▀▀ █░█░█   ▄▀█ █░░ █░░   █▀█ █▀█ █▀ ▀█▀ █▀
            //▀▄▀ █ ██▄ ▀▄▀▄▀   █▀█ █▄▄ █▄▄   █▀▀ █▄█ ▄█ ░█░ ▄█

            //user clicks main menu button to go to "view all posts" page
            if (e.getSource() == allPostsButton) {
                switchPanel(appPanel, activeSubmenuPanel, allPostsSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = allPostsSubmenuPanel;
            }

            //█▀ █▀▀ ▄▀█ █▀█ █▀▀ █░█   █░█ █▀ █▀▀ █▀█
            //▄█ ██▄ █▀█ █▀▄ █▄▄ █▀█   █▄█ ▄█ ██▄ █▀▄

            //user clicks main menu button for search user
            if (e.getSource() == searchUserButton) {
                switchPanel(appPanel, activeSubmenuPanel, searchUserSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = searchUserSubmenuPanel;

                switchPanel(appPanelContent, activeContentPanel, blankContentPanel, BorderLayout.CENTER);
                activeContentPanel = blankContentPanel;
            }

            //LOGOUT -- user clicks main menu logout button
            if (e.getSource() == logoutButton) {
                //make sure user didn't click by accident
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to log out?", "Logout?", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    changeFrame(appFrame, loginFrame);
                    CLIENT.streamReader("logout");
                    activeUsername = null;
                }
            }
        }
    };

    //FOR THE ENTIRE PROGRAM: safe end the program a user after user X's out
    WindowAdapter windowAdapter = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            loginFrame.dispose();
            appFrame.dispose();
            CLIENT.streamReader("logout");
            CLIENT.end();
        }
    };

    /**
     * Call this method when you want to switch from one panel to another on the given frame
     * @param frame - frame that you are currently on (loginFrame or applicationFrame)
     * @param oldPanel - panel that you want to remove from the frame
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
     * OVERLOADED
     * @param parentPanel - panel on which the panel you want to switch is laying
     * @param oldPanel - panel that you want to remove from the parentPanel
     * @param newPanel - panel that you want to display on the parentPanel
     * @param borderLayoutLoc - BorderLayout constant: NORTH, EAST, SOUTH, WEST, CENTER
     */
    public void switchPanel(JPanel parentPanel, JPanel oldPanel, JPanel newPanel, String borderLayoutLoc) {
        parentPanel.remove(oldPanel);
        parentPanel.add(newPanel, borderLayoutLoc);

        parentPanel.repaint();
        parentPanel.revalidate();
    }

    /**
     * dispose of one frame and start the other (loginFrame or applicationFrame)
     * @param oldFrame - frame you want to dispose of
     * @param newFrame - frame you want to show
     */
    public void changeFrame(JFrame oldFrame, JFrame newFrame) {
        oldFrame.dispose();
        newFrame.setVisible(true);
    }

    /**
     * idea: let there be 2 JFrames in total. One JFrame is for the login page, another for the app itself.
     * Create the base layout of these frames here (e.g., login page has a side panel containing options to sign in
     * or create a new account.
     *
     * JFrames use EXIT_ON_CLOSE: program should end when the user closes a frame. Only one frame will be shown at a
     * time in this program. Closing a frame means the user wants to quit entirely.
     */
    public void run() {

        Color menuColor = new Color(53, 92, 125);

        /*
        Create the JFrame for the login page. Build the side menu bar/panel which is shared by all pages of the login
        page. The side menu bar/panel contains button options to sign in or create a new account.
        */

        loginFrame = new JFrame("Pixie Login");
        loginFrame.setSize(500, 400);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.addWindowListener(windowAdapter);
        loginFrame.setLocationRelativeTo(null);

        //side panel creation (called it "loginFrameMenu")
        JPanel loginFrameMenu = new JPanel();
        loginFrameMenu.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        loginFrameMenu.setBackground(menuColor);

        //grid implementation within the side panel/login frame menu
        JPanel loginFrameMenuGrid = new JPanel();
        loginFrameMenuGrid.setLayout(new GridLayout(5, 1, 5, 5));
        loginFrameMenuGrid.setBackground(menuColor);

        // side panel menu label
        JLabel loginMenuLabel = new JLabel("LOGIN PAGE");
        loginMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        loginMenuLabel.setForeground(Color.white);

        // Log in and create account buttons
        createAccountButton = new JButton("Create Account");
        signInButton = new JButton("Sign In");

        // Add buttons to grid
        loginFrameMenuGrid.add(loginMenuLabel);
        loginFrameMenuGrid.add(signInButton);
        loginFrameMenuGrid.add(createAccountButton);

        // Add grid to west panel
        loginFrameMenu.add(loginFrameMenuGrid);

        //add baseline features to the loginFrame
        loginFrame.add(loginFrameMenu, BorderLayout.WEST);
        loginFrame.add(signInPanel); //start off on the sign in panel
        loginFrame.setVisible(true);

        /*
        Create the JFrame for the rest of the app (which will contain the main menu and all sub-menus)
        */

        appFrame = new JFrame("Pixie App");
        appFrame.setSize(810, 500); //we will probably want the actual app to be larger
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.addWindowListener(windowAdapter);
        appFrame.setLocationRelativeTo(null);

        //side panel creation
        JPanel appFrameMenu = new JPanel();
        appFrameMenu.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        appFrameMenu.setBackground(menuColor);

        //side panel grid
        JPanel appFrameMenuGrid = new JPanel();
        appFrameMenuGrid.setLayout(new GridLayout(8, 1, 5, 5));
        appFrameMenuGrid.setBackground(menuColor);

        // side panel menu label
        JLabel mainMenuLabel = new JLabel("MAIN MENU");
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        mainMenuLabel.setForeground(Color.white);

        // Log in and create account buttons
        yourProfileButton = new JButton("Your Profile");
        createPostButton = new JButton("Create Post");
        yourPostsButton  = new JButton("View Your Posts");
        yourCommentsButton = new JButton("View Your Comments");
        allPostsButton = new JButton("View All Posts");
        searchUserButton = new JButton("Search For User");
        logoutButton = new JButton("Logout");

        // Add buttons to grid
        appFrameMenuGrid.add(mainMenuLabel);
        appFrameMenuGrid.add(yourProfileButton);
        appFrameMenuGrid.add(createPostButton);
        appFrameMenuGrid.add(yourPostsButton);
        appFrameMenuGrid.add(yourCommentsButton);
        appFrameMenuGrid.add(allPostsButton);
        appFrameMenuGrid.add(searchUserButton);
        appFrameMenuGrid.add(logoutButton);

        // Add grid to west panel
        appFrameMenu.add(appFrameMenuGrid);

        //start off on the sign in panel
        appFrame.add(appFrameMenu, BorderLayout.WEST);
        appPanel = new JPanel(new BorderLayout());
        appPanel.add(blankSubmenuPanel, BorderLayout.WEST); //start off on a blank submenu; actually nice idea sami
        activeSubmenuPanel = blankSubmenuPanel;

        appPanelContent = new JPanel(new BorderLayout()); //actions on the submenu will change this panel
        appPanelContent.add(blankContentPanel, BorderLayout.CENTER); //start out on a blank screen
        activeContentPanel = blankContentPanel;
        appPanel.add(appPanelContent);
        appFrame.add(appPanel);

//        appFrame.setVisible(true); //for testing app layout, bypass sign in phase

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
        yourCommentsButton.addActionListener(actionListener);
        allPostsButton.addActionListener(actionListener);
        searchUserButton.addActionListener(actionListener);
        logoutButton.addActionListener(actionListener);

        //YOUR PROFILE options if user goes to "Your Profile" submenu
        yourProfileButton.addActionListener(actionListener);
        changeBioButton.addActionListener(actionListener);
        changeUsernameButton.addActionListener(actionListener);
        changePasswordButton.addActionListener(actionListener);
        deleteAccountButton.addActionListener(actionListener);
        confirmChangeUsernameButton.addActionListener(actionListener);
        confirmChangeBioButton.addActionListener(actionListener);
        confirmChangePasswordButton.addActionListener(actionListener);

        //CREATE POST buttons for writing/editing and importing a post
        createPostButton.addActionListener(actionListener);
        writePostButton.addActionListener(actionListener);
        doneEditingPostButton.addActionListener(actionListener);
        importPostButton.addActionListener(actionListener);
        importFromCSVButton.addActionListener(actionListener);

        //YOUR POSTS: buttons related to your posts
        yourPostsButton.addActionListener(actionListener);
        selectYourPostButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        //run application on event dispatch thread (EDT)
        SwingUtilities.invokeLater(new Pixie());
    }
}
