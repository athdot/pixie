import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingUtilities.invokeLater;

public class ContentDisplay extends JComponent implements Runnable {
    JButton yourProfile;
    //option 1 submenu options
    JButton changeBio;
    JButton changeBioAction;

    JButton changeUsername;
    JButton changePassword;
    JButton deleteAccount;
    JTextField newBio;

    JButton createPost;
    JButton viewYourPosts;
    JButton viewYourComments;
    JButton viewAllPosts;
    JButton searchUser;
    JButton logout;
    ContentDisplay display;

   /* ActionListener subActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == changeBio) {


            }
            if (e.getSource() == createPost) {

            }

        }
    };*/
    private Client client;

    public static void main(String[] args) {
        // Run class
        invokeLater(new ContentDisplay());
    }

    /*public void profileChange() {
        changeBio = new JButton("Change Bio");
        JLabel bioChanger = new JLabel("Enter your new bio: ");
        //JLabel password = new JLabel("Password ");
        // Adjusts movement of labels on the panel
        bioChanger.setBounds(80, 117, 80, 30);
        newBio = new JTextField(20);
        newBio.setBounds(160, 125, 100, 20);


        //password.setBounds(80, 147, 80, 30);

    }*/

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle("Client");  //may change the title name later

        Container content = frame.getContentPane();

        JPanel panel = new JPanel();  //empty main panel
        JPanel subPanel = new JPanel();  //panel in the main window with contents
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
        //yourProfile.addActionListener(actionListener);
        changeBio = new JButton("Change Bio");
        yourProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //option 1 submenu
                frame.add(subPanel);
                subPanel.setLayout(null);


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

                frame.setVisible(true);
                //subPanel.repaint();
            }
        });


        changeBio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent k) {

                Panel subPanel1 = new Panel();
                frame.add(subPanel1);  //panel from main window
                subPanel1.setLayout(null);
                //content.add(subPanel, BorderLayout.EAST);
                changeBioAction = new JButton("LLChange Bio");

                changeBioAction.setBounds(400, 200, 100, 25);
                JLabel bioChanger = new JLabel("Enter your new bio: ");

                //JLabel showBio = new JLabel("username: " + xxx.getUsername());
                bioChanger.setBounds(70, 117, 120, 30);
                newBio = new JTextField(40);
                newBio.setBounds(200, 125, 160, 20);
                subPanel1.add(changeBioAction);
                //changeBioAction.addActionListener(subActionListener);

                subPanel1.add(bioChanger);
                subPanel1.add(newBio);
                frame.setVisible(true);
                //TODO: show the user name and the Bio changed
            }
        });


        createPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.add(subPanel);
                subPanel.setLayout(null);
            }
        });

        /*viewYourPosts.addActionListener(actionListener);
        viewYourComments.addActionListener(actionListener);
        viewAllPosts.addActionListener(actionListener);
        searchUser.addActionListener(actionListener);
        logout.addActionListener(actionListener);*/

        // Allow elements to show
        frame.setVisible(true);

    }
}
