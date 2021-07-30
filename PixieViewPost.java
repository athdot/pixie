import javax.swing.*;
import java.awt.*;

/**
 * carries all panel setups related to viewing posts: "View Your Posts" and "View All Posts".
 * Includes the panels that will replace the submenu panel when user enters a given post number
 */

public class PixieViewPost extends JComponent {

    Color subMenuColor = new Color(114, 90, 122, 255);

    //layout for displaying posts in "View Your Post" and "View All Posts" -- posts will be added in Pixie.java
    public JPanel viewPostsOutlinePanel;
    public JPanel viewPostsContainerPanel; //panel inside of viewPostOutlinePanel to contain JLabels for posts

    //layout for after the user selects a post from "view your post"
    public JPanel viewYourPostOptionsPanel;
    public JButton editYourPostButton; //these buttons appear after selecting your post
    public JButton commentOnYourPostButton;
    public JButton exportYourPostButton;
    public JButton deleteYourPostButton;

    //layout for after the user selects a post from "view all posts"
    public JPanel viewAllPostOptionsPanel;
    public JButton commentOnPostButton; //this button appears after selecting a post

    //layout for page where user can comment on selected post
    public JPanel commentOnPostPanel;
    public JTextField commentOnPostField;
    public JButton confirmCommentButton;

    public PixieViewPost() {
        viewPostPanel();
        viewYourPostOptionsPanel();
        viewAllPostOptionsPanel();
        commentOnPostPanel();
    }

    /**
     * a post panel that can be used for both "view all posts" and "view your post" page. Logic to add the posts
     * will be done in Pixie.java because this class doesn't know how many posts there are at a given time.
     */
    private void viewPostPanel() {
        //idea: posts are added in a grid-fashion, from top to bottom, within viewPostsContainerPanel
        JPanel boundingBox = new JPanel(new BorderLayout());

        JScrollPane jsp = new JScrollPane(boundingBox);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBounds(100, 100, 300, 250);

        //inside jsp, add another JPanel to constrict JLabel posts that will be added
        viewPostsContainerPanel = new JPanel(new GridLayout(0, 1));
        boundingBox.add(viewPostsContainerPanel, BorderLayout.CENTER);

        //viewPostsOutlinePanel serves as a simple aesthetic empty border, added later to Pixie.java appPanelContent
        viewPostsOutlinePanel = new JPanel(null);
        viewPostsOutlinePanel.add(jsp);
    }

    /**
     * similar to submenu layout. This panel replaces the "view YOUR posts" submenu panel after selecting a post.
     */
    private void viewYourPostOptionsPanel() {
        viewYourPostOptionsPanel = new JPanel();
        viewYourPostOptionsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        viewYourPostOptionsPanel.setBackground(subMenuColor);

        JPanel viewYourPostOptionsPanelGrid = new JPanel();
        viewYourPostOptionsPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        viewYourPostOptionsPanelGrid.setBackground(subMenuColor);

        //these buttons appear after selecting your post
        editYourPostButton = new JButton("Edit Post");
        commentOnYourPostButton = new JButton("Create Comment");
        exportYourPostButton = new JButton("Export Post");
        deleteYourPostButton = new JButton("Delete Post");

        JLabel optionsTitle = new JLabel("Options");
        optionsTitle.setHorizontalAlignment(JLabel.CENTER);
        optionsTitle.setForeground(Color.white);

        viewYourPostOptionsPanelGrid.add(optionsTitle);
        viewYourPostOptionsPanelGrid.add(editYourPostButton);
        viewYourPostOptionsPanelGrid.add(commentOnYourPostButton);
        viewYourPostOptionsPanelGrid.add(exportYourPostButton);
        viewYourPostOptionsPanelGrid.add(deleteYourPostButton);

        viewYourPostOptionsPanel.add(viewYourPostOptionsPanelGrid);
    }

    /**
     * similar to submenu layout. This panel replaces the "view ALL posts" submenu panel when user has selected
     * a given post from the screen displaying all posts
     */
    private void viewAllPostOptionsPanel() {
        viewAllPostOptionsPanel = new JPanel();
        viewAllPostOptionsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        viewAllPostOptionsPanel.setBackground(subMenuColor);

        JPanel viewAllPostOptionsPanelGrid = new JPanel();
        viewAllPostOptionsPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        viewAllPostOptionsPanelGrid.setBackground(subMenuColor);

        commentOnPostButton = new JButton("Create Comment"); //this button appears after selecting a post

        JLabel optionsTitle = new JLabel("Options");
        optionsTitle.setHorizontalAlignment(JLabel.CENTER);
        optionsTitle.setForeground(Color.white);

        viewAllPostOptionsPanelGrid.add(optionsTitle);
        viewAllPostOptionsPanelGrid.add(commentOnPostButton);

        viewAllPostOptionsPanel.add(viewAllPostOptionsPanelGrid);
    }

    /**
     * creates layout for page allowing user to comment on the selected post
     */
    private void commentOnPostPanel() {
        commentOnPostPanel = new JPanel(null);

        //create title for the "change username" page
        JLabel commentInstruction = new JLabel("Type your comment:");
        commentInstruction.setBounds(100, 150, 300, 25);

        //create the text field for the "change username" page
        commentOnPostField = new JTextField();
        commentOnPostField.setBounds(100, 200, 250, 25);

        //create the save changed-username button
        confirmCommentButton = new JButton("Confirm");
        confirmCommentButton.setBounds(100, 250, 130, 30);

        //add them to the commentOnPostPanel in correct order
        commentOnPostPanel.add(commentInstruction);
        commentOnPostPanel.add(commentOnPostField);
        commentOnPostPanel.add(confirmCommentButton);
    }

    /**
     * temporary main method to test layouts created above.
     * Comment out later to avoid confusion about running the program.
     */
    public static void main(String[] args) {
        PixieViewPost pixieViewPost = new PixieViewPost();

        JFrame frame = new JFrame();
        frame.setSize(810, 500);
        frame.setLocationRelativeTo(null);
        frame.add(pixieViewPost.viewPostsOutlinePanel);
        frame.setVisible(true);

        for (int i = 0; i < 10; i++) { //pretend there are 10 posts

            //this logic structure will have to be recreated in Pixie.java because we don't know how many posts
            //there are at a given time until we communicate with Server using Client

            //the <br/> is for break line. Added only for displaying JLabel. Should not affect actual post
            JLabel currentPostLabel = new JLabel("<html>Here's some text" +
                    "<br/>Hope this works. What if I write a new line???? This goes to the next line" +
                    " Yes! Finally! It goes to the next line. thank God<br/></html>");

            //DEBUGGED: fluid dimensions can be achieved with setMinimumSize, setMaximumSize, and setPreferredSize
            currentPostLabel.setMinimumSize(new Dimension(300, 0));
            currentPostLabel.setPreferredSize(new Dimension(300, 100));
            currentPostLabel.setMaximumSize(new Dimension(300, 500));
            currentPostLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            currentPostLabel.setVerticalAlignment(JLabel.CENTER);

            pixieViewPost.viewPostsContainerPanel.add(currentPostLabel);
        }

        JFrame frame2 = new JFrame();
        frame2.setSize(810, 500);
        frame2.setLocationRelativeTo(null);
        frame2.add(pixieViewPost.viewYourPostOptionsPanel, BorderLayout.WEST);
        frame2.setVisible(true);
    }

}
