import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * PixieThread.java
 * Brings all of the layouts of the GUI together and provides functionality for the application
 *
 * Large comments -- https://fsymbols.com/generators/tarty/ -- 2nd option down, replace all   character with " "
 * Don't overuse! Only use this if a section becomes annoying to navigate. Additional navigation tips: collapse all
 * blocks which you're not interested in.
 *
 * @author Group 8
 * @version July 27, 2021
 */

public class PixieThread extends JComponent implements Runnable {

    //each user who has the app open also has a Client object that communicates with the Server class.
    private final Client CLIENT = new Client();

    String activeUsername; //for client communication with server: what username is currently logged in?
    JPanel activeSubmenuPanel; //the submenus of the main menu options: which one is currently shown?
    JPanel activeContentPanel; //the right-most panel of the app: what's currently on it?

    //total number of posts that showed up in "your posts", "your comments", and "all posts"
    //only used for "View Your ..." | editing and creating are not done at the same time for a single user
    int postsNumTotal;
    int postsChosenNum;
    int commentsChosenNum;

    //store a COPY of most recently displayed posts/posts with comments -- USER CONCURRENCY AND REAL-TIME SOLUTION
    //primarily used for "view all posts" and "search user" option because post numbers may shift/update
    ArrayList<Post> postsTemp;

    //searchedUser is used across "search user" function to obtain info from Server
    String searchedUser;

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

    PixieLoginPage plp = new PixieLoginPage();

    JPanel signInPanel = plp.signInPanel;
    JTextField signInUsernameField = plp.signInUsernameField;
    JTextField signInPasswordField = plp.signInPasswordField;
    JButton signInConfirmButton = plp.signInConfirmButton;

    JPanel createAccountPanel = plp.createAccountPanel;
    JTextField createAccountUsernameField = plp.createAccountUsernameField;
    JTextField createAccountPasswordField = plp.createAccountPasswordField;
    JTextField confirmPasswordField = plp.confirmPasswordField;
    JButton createAccountConfirmButton = plp.createAccountConfirmButton;

    /**
     * MAIN MENU'S SUBMENUS: bring the side-panel "sub-menus" and their components from PixieSubmenus.java
     */

    PixieSubmenus psm = new PixieSubmenus();

    JPanel blankSubmenuPanel = psm.blankPanel; //?

    JPanel yourProfileSubmenuPanel = psm.yourProfileSubmenuPanel;
    JButton changeBioButton = psm.changeBioButton;
    JButton changeUsernameButton = psm.changeUsernameButton;
    JButton changePasswordButton = psm.changePasswordButton;
    JButton deleteAccountButton = psm.deleteAccountButton;

    JPanel createPostSubmenuPanel = psm.createPostSubmenuPanel;
    JButton createNewPostButton = psm.createNewPostButton;
    JButton importPostButton = psm.importPostButton;

    JPanel yourPostsSubmenuPanel = psm.yourPostsSubmenuPanel;
    JTextField selectYourPostField = psm.selectYourPostField;
    JButton selectYourPostButton = psm.selectYourPostButton;

    JPanel yourCommentsSubmenuPanel = psm.yourCommentsSubmenuPanel;
    JTextField selectCommentPostField = psm.selectCommentPostField;
    JTextField selectCommentField = psm.selectCommentField;
    JButton selectCommentButton = psm.selectCommentButton;

    JPanel allPostsSubmenuPanel = psm.allPostsSubmenuPanel;
    JTextField selectPostField = psm.selectPostField;
    JButton selectPostButton = psm.selectPostButton;

    JPanel searchUserSubmenuPanel = psm.searchUserSubmenuPanel;
    JTextField searchUserField = psm.searchUserField;
    JButton searchUserConfirmButton = psm.searchUserConfirmButton;

    /**
     * YOUR PROFILE: bring panel setups for "Your Profile" page from PixieYourProfile
     */

    PixieYourProfile pyp = new PixieYourProfile();

    JPanel blankContentPanel = pyp.blankPanel; //if you want the menu to be empty

    JPanel changeBioPanel = pyp.changeBioPanel; //change bio page
    JTextField changeBioField = pyp.changeBioField;
    JButton confirmChangeBioButton = pyp.confirmChangeBioButton;

    JPanel changeUsernamePanel = pyp.changeUsernamePanel; //change username page
    JTextField changeUsernameField = pyp.changeUsernameField;
    JButton confirmChangeUsernameButton = pyp.confirmChangeUsernameButton;

    JPanel changePasswordPanel = pyp.changePasswordPanel; //change password page
    JTextField oldPasswordField = pyp.oldPasswordField;
    JTextField newPasswordField = pyp.newPasswordField;
    JButton confirmChangePasswordButton = pyp.confirmChangePasswordButton;

    JPanel yourProfilePanel = pyp.yourProfilePanel; //viewing your profile
    JLabel yourProfileUsernameLabel = pyp.yourProfileUsernameLabel;
    JLabel yourProfileBioLabel = pyp.yourProfileBioLabel;

    /**
     * CREATE POST: bring panel setups for "Create Post" page from PixieCreatePost
     */

    PixieCreatePost pcp = new PixieCreatePost();
    
    JPanel createNewPostPanel = pcp.createNewPostPanel; //"write new post" page
    JTextField createNewPostTitleField = pcp.createNewPostTitleField;
    JTextField createNewPostContentField = pcp.createNewPostContentField;
    JButton doneWritingPostButton = pcp.doneWritingPostButton;

    JPanel importFromCSVPanel = pcp.importFromCSVPanel; //"import from CSV" page
    JTextField importFromCSVField = pcp.importFromCSVField;
    JButton importFromCSVButton = pcp.importFromCSVButton;

    /**
     * VIEW YOUR POST/VIEW ALL POSTS: bring panel setups and components from PixieViewPost
     */

    PixieViewPost pvp = new PixieViewPost();

    //outline panel: content panel--view posts, comments | container panel: add JLabel posts/comments in here
    JPanel viewPostsCommentsOutlinePanel = pvp.viewPostsCommentsOutlinePanel;
    JPanel viewPostsCommentsContainerPanel = pvp.viewPostsCommentsContainerPanel;

    JPanel yourPostOptionsPanel = pvp.yourPostOptionsPanel; //panel appears after selecting post
    JButton editYourPostButton = pvp.editYourPostButton; //buttons appear after selecting your post
    JButton commentOnYourPostButton = pvp.commentOnYourPostButton;
    JButton exportYourPostButton = pvp.exportYourPostButton;
    JButton deleteYourPostButton = pvp.deleteYourPostButton;

    JPanel allPostsOptionsPanel = pvp.allPostsOptionsPanel; //panel appears after selecting post
    JButton allPostsCommentButton = pvp.allPostsCommentButton; //appears after selecting a post

    JPanel editPostPanel = pvp.editPostPanel;
    JTextField editPostTitleField = pvp.editPostTitleField;
    JTextField editPostContentField = pvp.editPostContentField;
    JButton confirmEditPostButton = pvp.confirmEditPostButton;

    JPanel commentOnPostPanel = pvp.commentOnPostPanel; //create/edit comment page
    JTextField commentOnPostField = pvp.commentOnPostField;
    JButton confirmCommentButton = pvp.confirmCommentButton;

    /**
     * VIEW YOUR COMMENTS: bring panel setups and components from PixieViewComment
     */

    PixieViewComment pvc = new PixieViewComment();

    JPanel yourCommentOptionsPanel = pvc.yourCommentOptionsPanel;
    JButton editCommentButton = pvc.editCommentButton;
    JButton deleteCommentButton = pvc.deleteCommentButton;

    JPanel editCommentPanel = pvc.editCommentPanel;
    JTextField editCommentField = pvc.editCommentField;
    JButton confirmEditCommentButton = pvc.confirmEditCommentButton;

    /**
     * SEARCH USER: to pop up on the active content panel with two buttons that "View Profile" and "View Posts"
     */

    PixieSearchUser psu = new PixieSearchUser();

    JPanel searchUserOptionsPanel = psu.searchUserOptionsPanel;  //show the three "view" options after finding user
    JButton searchUserViewProfileButton = psu.searchUserViewProfileButton;
    JButton searchUserViewPostsButton = psu.searchUserViewPostsButton;
    JButton searchUserViewCommentsButton = psu.searchUserViewCommentsButton;

    JPanel searchUserProfilePanel = psu.searchUserProfilePanel; //similar to "yourProfilePanel"
    JLabel searchUserUsernameLabel = psu.searchUserUsernameLabel;
    JLabel searchUserBioLabel = psu.searchUserBioLabel;

    private String currentPage;
    
    /**
     * for the entire program: action listeners for every button on the GUI. Organized by main menu option
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	
            //█░░ █▀█ █▀▀ █ █▄░█
            //█▄▄ █▄█ █▄█ █ █░▀█

            //user chooses to navigate to the sign-in page
            if (e.getSource() == signInButton) {
            	currentPage = "NO_REFRESH";
                switchPanel(loginFrame, createAccountPanel, signInPanel);
            }

            //user chooses to navigate to the create account page
            if (e.getSource() == createAccountButton) {
            	currentPage = "NO_REFRESH";
                switchPanel(loginFrame, signInPanel, createAccountPanel);
            }

            //user chooses to sign into the account with provided username and password
            if (e.getSource() == signInConfirmButton) {
            	currentPage = "NO_REFRESH";
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
            	currentPage = "NO_REFRESH";
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
            	currentPage = "yourProfileButton";
                switchPanel(appPanel, activeSubmenuPanel, yourProfileSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = yourProfileSubmenuPanel;

                String profile = CLIENT.streamReader("getProfile[" + activeUsername + "]");
                Account user = StreamParse.stringToAccount(profile);
                yourProfileUsernameLabel.setText(activeUsername);
                yourProfileBioLabel.setText("<html>" + user.getBio() + "</html>");

                if (user.getBio().equals("")) { //if biography is empty
                    yourProfileBioLabel.setText("[empty]");
                }

                switchPanel(appPanelContent, activeContentPanel, yourProfilePanel, BorderLayout.CENTER);
                activeContentPanel = yourProfilePanel;
            }

            if (e.getSource() == changeBioButton) {
            	currentPage = "NO_REFRESH";
                switchPanel(appPanelContent, activeContentPanel, changeBioPanel, BorderLayout.CENTER);
                activeContentPanel = changeBioPanel;
            }

            if (e.getSource() == confirmChangeBioButton) {
            	currentPage = "NO_REFRESH";
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
            	currentPage = "NO_REFRESH";
                switchPanel(appPanelContent, activeContentPanel, changeUsernamePanel, BorderLayout.CENTER);
                activeContentPanel = changeUsernamePanel;
            }

            if (e.getSource() == confirmChangeUsernameButton) {
            	currentPage = "NO_REFRESH";
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

                if (newUsername.equalsIgnoreCase("false") ||
                        changeUsernameField.getText().equals(activeUsername)) {
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
            	currentPage = "NO_REFRESH";
                switchPanel(appPanelContent, activeContentPanel, changePasswordPanel, BorderLayout.CENTER);
                activeContentPanel = changePasswordPanel;
            }

            if (e.getSource() == confirmChangePasswordButton) {
            	currentPage = "NO_REFRESH";
                String changePassword = "changePassword[" + oldPasswordField.getText() + ","
                        + newPasswordField.getText() + "]";

                changePassword = CLIENT.streamReader(changePassword);

                if (newPasswordField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Password is too short",
                            "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                } //password can have spaces and commas

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
            	currentPage = "NO_REFRESH";
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
            	currentPage = "NO_REFRESH";
                switchPanel(appPanel, activeSubmenuPanel, createPostSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = createPostSubmenuPanel;

                switchPanel(appPanelContent, activeContentPanel, blankContentPanel, BorderLayout.CENTER);
                activeContentPanel = blankContentPanel;
            }

            //create post: user wants to write a new post
            if (e.getSource() == createNewPostButton) {
            	currentPage = "NO_REFRESH";
                switchPanel(appPanelContent, activeContentPanel, createNewPostPanel, BorderLayout.CENTER);
                activeContentPanel = createNewPostPanel;
            }

            //user is done editing the post and wants to save/create it
            if (e.getSource() == doneWritingPostButton) {
                String post = "post[" + createNewPostTitleField.getText().
                        replace(",", "123COMMA_REP321") + "," +
                        createNewPostContentField.getText() + "]";
                String worked = CLIENT.streamReader(post);

                if (createNewPostTitleField.getText().length() == 0 ||
                        createNewPostContentField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Title/content is too short!",
                            "Create post error", JOptionPane.INFORMATION_MESSAGE);
                }

                if (worked.equals("true")) {
                    JOptionPane.showMessageDialog(null, "Post has been written successfully!",
                            "Post written", JOptionPane.INFORMATION_MESSAGE);
                    createNewPostTitleField.setText("");
                    createNewPostContentField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Post was unable to be written",
                            "Something went wrong", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            //create post: user wants to go to import post from CSV page
            if (e.getSource() == importPostButton) {
            	currentPage = "NO_REFRESH";
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
                    e.getSource() == allPostsButton || e.getSource() == searchUserButton ||
                    e.getSource() == searchUserViewPostsButton || e.getSource() == searchUserViewCommentsButton) {
                //NOTE: keep this statement in-front of all logic that lists out posts
                viewPostsCommentsContainerPanel.removeAll();
            }

            //user clicks main menu button to go to "your posts" page
            if (e.getSource() == yourPostsButton) {
            	currentPage = "yourPostsButton";
                switchPanel(appPanel, activeSubmenuPanel, yourPostsSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = yourPostsSubmenuPanel;

                String getYourPosts = CLIENT.streamReader("getUserPosts[" + activeUsername + "]");
                postsTemp = StreamParse.stringToPosts(getYourPosts);
                displayPosts(postsTemp);

                switchPanel(appPanelContent, activeContentPanel, viewPostsCommentsOutlinePanel, BorderLayout.CENTER);
                activeContentPanel = viewPostsCommentsOutlinePanel;
            }

            //user clicks button to select a post number
            if (e.getSource() == selectYourPostButton) {
            	currentPage = "selectYourPostButton";
                postsChosenNum = 0;
                try {
                    postsChosenNum = Integer.parseInt(selectYourPostField.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Please provide a valid post number",
                            "Select post", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (1 <= postsChosenNum && postsChosenNum <= postsNumTotal) {
                    switchPanel(appPanel, activeSubmenuPanel, yourPostOptionsPanel, BorderLayout.WEST);
                    activeSubmenuPanel = yourPostOptionsPanel;
                    selectYourPostField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please provide a valid post number",
                            "Select post", JOptionPane.ERROR_MESSAGE);
                    selectYourPostField.setText("");
                    postsChosenNum = 0;
                }
            }

            //user wants to edit the selected post
            if (e.getSource() == editYourPostButton) {
            	currentPage = "NO_REFRESH";
                switchPanel(appPanelContent, activeContentPanel, editPostPanel, BorderLayout.CENTER);
                activeContentPanel = editPostPanel;

                Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based
                editPostTitleField.setText(selectedPost.getTitle());
                editPostContentField.setText(selectedPost.getContent());
            }

            if (e.getSource() == confirmEditPostButton) {

                if (editPostContentField.getText().length() == 0 || editPostTitleField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Title or content is too short!",
                            "Edit post", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Post selectedPost = postsTemp.get(postsChosenNum - 1);

                //first check if this edit is valid; try to change the post title
                String worked = CLIENT.streamReader("editTitle[" + 
                        selectedPost.getTitle().replace(",", "123COMMA_REP321") + "," +
                        selectedPost.getAuthor() + "," + 
                        editPostTitleField.getText().replace(",", "123COMMA_REP321") + "]");

                if (worked.equals("false")) { //edit is not valid
                    JOptionPane.showMessageDialog(null, "Two of your posts cannot have the" +
                                    " same name", "Edit post", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                CLIENT.streamReader("editPost[" + editPostTitleField.getText().replace(",", "123COMMA_REP321") + "," +
                        selectedPost.getAuthor() + "," + editPostContentField.getText() + "]");

                JOptionPane.showMessageDialog(null, "Post updated successfully!",
                        "Edit post", JOptionPane.INFORMATION_MESSAGE);

                editPostTitleField.setText("");
                editPostContentField.setText("");
            }

            //user clicks button to comment on his/her post
            if (e.getSource() == commentOnYourPostButton) {
            	currentPage = "NO_REFRESH";
                switchPanel(appPanelContent, activeContentPanel, commentOnPostPanel, BorderLayout.CENTER);
                activeContentPanel = commentOnPostPanel;
            }

            //user clicks button to confirm adding comment -- also used for commenting on others' posts
            if (e.getSource() == confirmCommentButton) {
                String commentContent = commentOnPostField.getText();

                if (commentContent.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Comment is too short!",
                            "Comment", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based

                CLIENT.streamReader("addComment[" + selectedPost.getTitle() +
                        "," + selectedPost.getAuthor() + "," + commentOnPostField.getText() + "]");

                JOptionPane.showMessageDialog(null, "Comment written successfully!",
                        "Comment", JOptionPane.INFORMATION_MESSAGE);
                commentOnPostField.setText("");
            }

            //user clicks on button to export the selected post
            if (e.getSource() == exportYourPostButton) {
                Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based

                String filename = selectedPost.getAuthor() + " - " + selectedPost.getTitle() + ".csv";
                ArrayList<String[]> exportFormat = new ArrayList<>();
                exportFormat.add(selectedPost.toFile());
                DataManagement.writeFile(filename, exportFormat);

                JOptionPane.showMessageDialog(null, "Post exported as " + filename,
                        "Export post", JOptionPane.INFORMATION_MESSAGE);
            }

            //user clicks button to delete selected post
            if (e.getSource() == deleteYourPostButton) {
                //make sure user didn't click "delete post" by accident
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this post?", "Delete post",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.NO_OPTION) {
                    return;
                }

                Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based

                CLIENT.streamReader("deletePost[" + selectedPost.getTitle() + "," +
                        selectedPost.getAuthor() + "]");

                JOptionPane.showMessageDialog(null, "Post deleted successfully!",
                        "Delete post", JOptionPane.INFORMATION_MESSAGE);

                //post has been deleted. Return user to "view your posts" initial screen
                yourPostsButton.doClick();
            }

            //█░█ █ █▀▀ █░█░█   █▄█ █▀█ █░█ █▀█   █▀▀ █▀█ █▀▄▀█ █▀▄▀█ █▀▀ █▄░█ ▀█▀ █▀
            //▀▄▀ █ ██▄ ▀▄▀▄▀   ░█░ █▄█ █▄█ █▀▄   █▄▄ █▄█ █░▀░█ █░▀░█ ██▄ █░▀█ ░█░ ▄█

            //user clicks main menu button to go to "your comments" page
            if (e.getSource() == yourCommentsButton) {
            	currentPage = "yourCommentsButton";
                switchPanel(appPanel, activeSubmenuPanel, yourCommentsSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = yourCommentsSubmenuPanel;

                String getPostsYouCommented = CLIENT.streamReader("getUserComments[" + activeUsername + "]");
                postsTemp = StreamParse.stringToPosts(getPostsYouCommented);
                displayPosts(postsTemp);

                switchPanel(appPanelContent, activeContentPanel, viewPostsCommentsOutlinePanel, BorderLayout.CENTER);
                activeContentPanel = viewPostsCommentsOutlinePanel;
            }

            //user selects a post number and the comment number
            if (e.getSource() == selectCommentButton) {
                try {
                    postsChosenNum = Integer.parseInt(selectCommentPostField.getText());
                    commentsChosenNum = Integer.parseInt(selectCommentField.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Please provide a valid post number",
                            "Select comment", JOptionPane.ERROR_MESSAGE);
                    selectCommentPostField.setText("");
                    selectCommentField.setText("");
                    return;
                }

                //make sure the post exists -- selection is not out of bounds
                if (1 > postsChosenNum || postsChosenNum > postsNumTotal) {
                    JOptionPane.showMessageDialog(null, "Please provide a valid post number",
                            "Select comment", JOptionPane.ERROR_MESSAGE);
                    selectCommentPostField.setText("");
                    selectCommentField.setText("");
                    return;
                }

                //find how many comments are in the select post by this user
                Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based

                ArrayList<Integer> validCommentNum = new ArrayList<>();
                for (int i = 0; i < selectedPost.getComments().size(); i++) {
                    if (selectedPost.getComments().get(i).getAuthor().equalsIgnoreCase(activeUsername)) {
                        validCommentNum.add(i + 1); //comment # is 1-based
                    }
                }

                //check if the user even has comments on this post
                if (validCommentNum.size() == 0) {
                    JOptionPane.showMessageDialog(null, "You have no comments on this post",
                            "Select comment", JOptionPane.ERROR_MESSAGE);
                    selectCommentPostField.setText("");
                    selectCommentField.setText("");
                    return;
                }

                //make sure the select comment is the current user's
                if (validCommentNum.contains(commentsChosenNum)) {
                    switchPanel(appPanel, activeSubmenuPanel, yourCommentOptionsPanel, BorderLayout.WEST);
                    activeSubmenuPanel = yourCommentOptionsPanel;
                    selectCommentPostField.setText("");
                    selectCommentField.setText("");
                    currentPage = "commentsChosenNum";
                } else {
                    JOptionPane.showMessageDialog(null, "Enter a valid comment number",
                            "Select comment", JOptionPane.ERROR_MESSAGE);
                    selectCommentPostField.setText("");
                    selectCommentField.setText("");
                    return;
                }
            }

            //user wants to edit selected comment
            if (e.getSource() == editCommentButton) {
                switchPanel(appPanelContent, activeContentPanel, editCommentPanel, BorderLayout.CENTER);
                activeContentPanel = editCommentPanel;
                
                currentPage = "NO_REFRESH";

                Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based
                Comment selectedComment = selectedPost.getComments().get(commentsChosenNum - 1);

                editCommentField.setText(selectedComment.getContent());
            }

            //user wants to confirm edit of selected comment
            if (e.getSource() == confirmEditCommentButton) {

                //get the post details required for editing comment with CLIENT.streamReader()
                Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based

                String postTitle = selectedPost.getTitle();
                String postAuthor = selectedPost.getAuthor();

                CLIENT.streamReader("editComment[" + postTitle + "," + postAuthor + "," +
                        commentsChosenNum + "," + editCommentField.getText() + "]");

                JOptionPane.showMessageDialog(null, "Comment updated successfully!",
                        "Edit comment", JOptionPane.INFORMATION_MESSAGE);
                editCommentField.setText("");
            }

            //user wants to delete the selected comment
            if (e.getSource() == deleteCommentButton) {
                //make sure user didn't click by accident
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this comment?", "Delete comment?",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    //get the post details required by CLIENT.streamReader() for deleting comment
                    Post selectedPost = postsTemp.get(postsChosenNum - 1); //post numbers are 1-based
                    String postTitle = selectedPost.getTitle();
                    String postAuthor = selectedPost.getAuthor();

                    //todo: THIS CRASHES THE APP -- CHARLES HELP
                    CLIENT.streamReader("deleteComment[" + postTitle + "," + postAuthor + "," +
                            (commentsChosenNum - 1) + "]"); //comment # is 1-based

                    JOptionPane.showMessageDialog(null, "Comment deleted successfully!",
                            "Delete comment", JOptionPane.INFORMATION_MESSAGE);

                    //comment has been deleted. Return user to "view your comments" initial screen
                    yourCommentsButton.doClick();
                }
            }

            //█░█ █ █▀▀ █░█░█   ▄▀█ █░░ █░░   █▀█ █▀█ █▀ ▀█▀ █▀
            //▀▄▀ █ ██▄ ▀▄▀▄▀   █▀█ █▄▄ █▄▄   █▀▀ █▄█ ▄█ ░█░ ▄█

            //user clicks main menu button to go to "view all posts" page
            if (e.getSource() == allPostsButton) {
                currentPage = "allPostsButton";
                switchPanel(appPanel, activeSubmenuPanel, allPostsSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = allPostsSubmenuPanel;

                String getAllPosts = CLIENT.streamReader("getAllPosts");
                postsTemp = StreamParse.stringToPosts(getAllPosts);
                displayPosts(postsTemp);

                switchPanel(appPanelContent, activeContentPanel, viewPostsCommentsOutlinePanel, BorderLayout.CENTER);
                activeContentPanel = viewPostsCommentsOutlinePanel;
            }

            //user tries to select a post from "view all posts"
            if (e.getSource() == selectPostButton) {
            	currentPage = "NO_REFRESH";
            	//Another grey area
            	
                try {
                    postsChosenNum = Integer.parseInt(selectPostField.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Enter a valid post number",
                            "Select post", JOptionPane.ERROR_MESSAGE);
                    selectPostField.setText("");
                    return;
                }

                //make sure selected number is not out of bounds
                if (1 > postsChosenNum || postsChosenNum > postsNumTotal) {
                    JOptionPane.showMessageDialog(null, "Enter a valid post number",
                            "Select post", JOptionPane.ERROR_MESSAGE);
                    selectPostField.setText("");
                    return;
                }

                switchPanel(appPanel, activeSubmenuPanel, allPostsOptionsPanel, BorderLayout.WEST);
                activeSubmenuPanel = allPostsOptionsPanel;
                selectPostField.setText("");
            }

            //user wants to comment on a post in "all posts" page -- reuse logic from "your posts"
            if (e.getSource() == allPostsCommentButton) {
                currentPage = "allPostsCommentButton";
                switchPanel(appPanelContent, activeContentPanel, commentOnPostPanel, BorderLayout.CENTER);
                activeContentPanel = commentOnPostPanel;
            }

            //█▀ █▀▀ ▄▀█ █▀█ █▀▀ █░█   █░█ █▀ █▀▀ █▀█
            //▄█ ██▄ █▀█ █▀▄ █▄▄ █▀█   █▄█ ▄█ ██▄ █▀▄

            //user clicks main menu button for search user
            if (e.getSource() == searchUserButton) {
            	currentPage = "searchUserButton";
                switchPanel(appPanel, activeSubmenuPanel, searchUserSubmenuPanel, BorderLayout.WEST);
                activeSubmenuPanel = searchUserSubmenuPanel;

                switchPanel(appPanelContent, activeContentPanel, blankContentPanel, BorderLayout.CENTER);
                activeContentPanel = blankContentPanel;
            }

            //user clicks button to search for provided username -- case insensitive
            if (e.getSource() == searchUserConfirmButton) {
            	currentPage = "NO_REFRESH";
                String searchReturn = CLIENT.streamReader("userSearch[" + searchUserField.getText() + "]");
                String[] relatedUsernames = searchReturn.split(",");

                boolean found = false;
                for (String username : relatedUsernames) {
                    if (searchUserField.getText().equalsIgnoreCase(username)) {
                        found = true;
                        searchedUser = username;
                    }
                }

                if (found) {
                    JOptionPane.showMessageDialog(null, "Found the user!",
                            "Search User", JOptionPane.INFORMATION_MESSAGE);
                    searchUserField.setText("");

                    switchPanel(appPanel, activeSubmenuPanel, searchUserOptionsPanel, BorderLayout.WEST);
                    activeSubmenuPanel = searchUserOptionsPanel;
                } else {
                    JOptionPane.showMessageDialog(null, "No search results for this name",
                            "Search User", JOptionPane.ERROR_MESSAGE);
                    searchUserField.setText("");
                }
            }

            //you want to see the searched user's profile
            if (e.getSource() == searchUserViewProfileButton) {
                currentPage = "searchUserViewProfileButton";
                String searchedAccountString = CLIENT.streamReader("getProfile[" + searchedUser + "]");
                Account searchedAccount = StreamParse.stringToAccount(searchedAccountString);
                searchUserUsernameLabel.setText(searchedAccount.getUsername());
                searchUserBioLabel.setText("<html>" + searchedAccount.getBio() + "</html>");

                if (searchedAccount.getBio().equals("")) { //if biography is empty
                    searchUserBioLabel.setText("[empty]");
                }

                switchPanel(appPanelContent, activeContentPanel, searchUserProfilePanel, BorderLayout.CENTER);
                activeContentPanel = searchUserProfilePanel;
            }

            //view the searched user's posts
            if (e.getSource() == searchUserViewPostsButton) {
            	currentPage = "searchUserViewPostsButton";

                String getUserPosts = CLIENT.streamReader("getUserPosts[" + searchedUser + "]");
                postsTemp = StreamParse.stringToPosts(getUserPosts);
                displayPosts(postsTemp);

                switchPanel(appPanelContent, activeContentPanel, viewPostsCommentsOutlinePanel, BorderLayout.CENTER);
                activeContentPanel = viewPostsCommentsOutlinePanel;
            }

            //view the searched user's comments
            if (e.getSource() == searchUserViewCommentsButton) {
            	currentPage = "searchUserViewCommentsButton";

                String getUserPosts = CLIENT.streamReader("getUserComments[" + searchedUser + "]");
                postsTemp = StreamParse.stringToPosts(getUserPosts);
                displayPosts(postsTemp);

                switchPanel(appPanelContent, activeContentPanel, viewPostsCommentsOutlinePanel, BorderLayout.CENTER);
                activeContentPanel = viewPostsCommentsOutlinePanel;
            }

            //LOGOUT -- user clicks main menu logout button
            if (e.getSource() == logoutButton) {
            	currentPage = "NO_REFRESH";
                //make sure user didn't click by accident
                int choice = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to log out?", "Logout?", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    changeFrame(appFrame, loginFrame);
                    CLIENT.streamReader("logout");
                    activeUsername = null;

                    switchPanel(appPanel, activeSubmenuPanel, blankSubmenuPanel, BorderLayout.WEST);
                    activeSubmenuPanel = blankSubmenuPanel;

                    switchPanel(appPanelContent, activeContentPanel, blankContentPanel, BorderLayout.CENTER);
                    activeContentPanel = blankContentPanel;
                }
            }
        }
    };

    /**
     * Safe end the program if a user closes out of a frame: logout of the account and shutdown client
     */
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
     * takes a formatted ArrayList of posts returned originally from CLIENT.streamReader
     * @param posts - ArrayList of posts
     */
    public void displayPosts(ArrayList<Post> posts) {
        postsNumTotal = 0;
        for (Post post : posts) { //start from the back, to get latest posts first
            String formattedPost = "<html>" + "[Post #" + ++postsNumTotal + "]: " +
                    post.toString().replace("\n", "<br/>") + "</html>";

            JLabel postLabel = new JLabel(formattedPost); //each post is displayed within a label
            postLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            postLabel.setVerticalAlignment(JLabel.CENTER);
            postLabel.revalidate();

            //hardcoded -- incorporate nested JScrollPane, allowing user to see all of a post
            JScrollPane jsp = new JScrollPane(postLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            // Hacky way to scroll inside the pane without dragging anything
            jsp.removeMouseWheelListener(jsp.getMouseWheelListeners()[0]);
            jsp.setPreferredSize(new Dimension(285, 170));

            viewPostsCommentsContainerPanel.add(jsp);
        }
    }

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
    
    public void refreshPage() {
    	// Handle a changed username/password
//    	String foreignUsername = CLIENT.streamReader("getUsername");
//    	if (CLIENT.streamReader("getProfile[" + foreignUsername + "]").equals("false")) {
//    		JOptionPane.showMessageDialog(null, "Login Credentials Changed, Log Back In",
//                    "Search User", JOptionPane.INFORMATION_MESSAGE);
//    		changeFrame(appFrame, loginFrame);
//            CLIENT.streamReader("logout");
//            activeUsername = null;
//    		return;
//    	}
    	
    	if (currentPage.equals("yourProfileButton")) {
    		yourProfileButton.doClick();
    	} else if (currentPage.equals("yourPostsButton")) {
    		yourPostsButton.doClick();
    	} else if (currentPage.equals("yourCommentsButton")) {
    		yourCommentsButton.doClick();
    	} else if (currentPage.equals("allPostsButton")) {
    		allPostsButton.doClick();
    	} else if (currentPage.equals("searchUserViewProfileButton")) {
    		searchUserViewProfileButton.doClick();
    	} else if (currentPage.equals("searchUserViewPostsButton")) {
    		searchUserViewPostsButton.doClick();
    	} else if (currentPage.equals("searchUserViewCommentsButton")) {
    		searchUserViewCommentsButton.doClick();
    	}
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
    	currentPage = "NO_REFRESH";
    	
    	if (CLIENT.serverStatus()) {
    		//Stops thread execution
    		return;
    	}

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
        
        try {
        	ImageIcon img = new ImageIcon("images/ico.png");
        	loginFrame.setIconImage(img.getImage());
        } catch (Exception e) {
        	// Could not load image, move on
        }

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

        appFrame = new JFrame("Pixie");
        appFrame.setSize(815, 500); //we will probably want the actual app to be larger
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.addWindowListener(windowAdapter);
        appFrame.setLocationRelativeTo(null);
        
        try {
        	ImageIcon img = new ImageIcon("images/ico.png");
        	appFrame.setIconImage(img.getImage());
        } catch (Exception e) {
        	// Could not load image, default java is fine
        }

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
        createNewPostButton.addActionListener(actionListener);
        doneWritingPostButton.addActionListener(actionListener);
        importPostButton.addActionListener(actionListener);
        importFromCSVButton.addActionListener(actionListener);

        //YOUR POSTS: buttons related to "your posts"
        yourPostsButton.addActionListener(actionListener);
        selectYourPostButton.addActionListener(actionListener);
        editYourPostButton.addActionListener(actionListener);
        commentOnYourPostButton.addActionListener(actionListener);
        confirmCommentButton.addActionListener(actionListener);
        exportYourPostButton.addActionListener(actionListener);
        deleteYourPostButton.addActionListener(actionListener);

        //YOUR COMMENTS
        yourCommentsButton.addActionListener(actionListener);
        selectCommentButton.addActionListener(actionListener);
        editCommentButton.addActionListener(actionListener);
        confirmEditCommentButton.addActionListener(actionListener);
        deleteCommentButton.addActionListener(actionListener);

        //ALL POSTS
        allPostsButton.addActionListener(actionListener);
        selectPostButton.addActionListener(actionListener);
        allPostsCommentButton.addActionListener(actionListener);
        editYourPostButton.addActionListener(actionListener);
        confirmEditPostButton.addActionListener(actionListener);

        //SEARCH USER
        searchUserButton.addActionListener(actionListener);
        searchUserConfirmButton.addActionListener(actionListener);
        searchUserViewProfileButton.addActionListener(actionListener);
        searchUserViewPostsButton.addActionListener(actionListener);
        searchUserViewCommentsButton.addActionListener(actionListener);
    }
}
