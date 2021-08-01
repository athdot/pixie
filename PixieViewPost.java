import javax.swing.*;
import java.awt.*;

/**
 * PixieViewPost
 * carries all panel setups related to viewing posts: "View Your Posts" and "View All Posts".
 * Includes the panels that will replace the submenu panel when user enters a given post number
 *
 * @author Group 8
 * @version July 31, 2021
 */

public class PixieViewPost extends JComponent {

    Color subMenuColor = new Color(114, 90, 122, 255);

    //layout for displaying posts in "View Your Post" and "View All Posts" -- posts will be added in Pixie.java
    public JPanel viewPostsCommentsOutlinePanel;
    public JPanel viewPostsCommentsContainerPanel; //panel inside of viewPostOutlinePanel to contain JLabels for posts

    //layout for after the user selects a post from "view your post"
    public JPanel yourPostOptionsPanel;
    public JButton editYourPostButton; //these buttons appear after selecting your post
    public JButton commentOnYourPostButton;
    public JButton exportYourPostButton;
    public JButton deleteYourPostButton;

    //layout for after the user selects a post from "view all posts"
    public JPanel allPostsOptionsPanel;
    public JButton allPostsCommentButton; //this button appears after selecting a post

    //layout for "edit post" page -- cannot reuse "create post" panel because editing post algorithm is different
    public JPanel editPostPanel;
    public JTextField editPostTitleField;
    public JTextField editPostContentField;
    public JButton confirmEditPostButton;

    //layout for page where user can comment on selected post
    public JPanel commentOnPostPanel;
    public JTextField commentOnPostField;
    public JButton confirmCommentButton;

    public PixieViewPost() {
        viewPostsCommentsPanel();
        yourPostOptionsPanel();
        allPostsOptionsPanel();
        editPostPanel();
        commentOnPostPanel();
    }

    /**
     * a post panel that can be used for both "view all posts" and "view your post" page. Logic to add the posts
     * will be done in Pixie.java because this class doesn't know how many posts there are at a given time.
     * NEW: used for listing out "your posts", posts with your comments ("your comments"), and "all posts".
     */
    private void viewPostsCommentsPanel() {
        //idea: posts are added in a grid-fashion, from top to bottom, within viewPostsCommentsContainerPanel
        JPanel boundingBox = new JPanel(new BorderLayout());

        JScrollPane jsp = new JScrollPane(boundingBox);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setSize(360, 310);

        //inside jsp, add another JPanel to constrict JLabel posts that will be added
        viewPostsCommentsContainerPanel = new JPanel(new GridLayout(0, 1));
        boundingBox.add(viewPostsCommentsContainerPanel, BorderLayout.CENTER);

        //viewPostsCommentsOutlinePanel serves as a simple aesthetic empty border, added later to Pixie.java appPanelContent
        viewPostsCommentsOutlinePanel = new JPanel(new BorderLayout());
        viewPostsCommentsOutlinePanel.add(jsp, BorderLayout.CENTER);
    }

    /**
     * similar to submenu layout. This panel replaces the "view YOUR posts" submenu panel after selecting a post.
     */
    private void yourPostOptionsPanel() {
        yourPostOptionsPanel = new JPanel();
        yourPostOptionsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourPostOptionsPanel.setBackground(subMenuColor);

        JPanel yourPostOptionsPanelGrid = new JPanel();
        yourPostOptionsPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        yourPostOptionsPanelGrid.setBackground(subMenuColor);

        //these buttons appear after selecting your post
        editYourPostButton = new JButton("Edit Post");
        commentOnYourPostButton = new JButton("Create Comment");
        exportYourPostButton = new JButton("Export Post");
        deleteYourPostButton = new JButton("Delete Post");

        JLabel optionsTitle = new JLabel("Options");
        optionsTitle.setHorizontalAlignment(JLabel.CENTER);
        optionsTitle.setForeground(Color.white);

        yourPostOptionsPanelGrid.add(optionsTitle);
        yourPostOptionsPanelGrid.add(editYourPostButton);
        yourPostOptionsPanelGrid.add(commentOnYourPostButton);
        yourPostOptionsPanelGrid.add(exportYourPostButton);
        yourPostOptionsPanelGrid.add(deleteYourPostButton);

        yourPostOptionsPanel.add(yourPostOptionsPanelGrid);
    }

    /**
     * similar to submenu layout. This panel replaces the "view ALL posts" submenu panel when user has selected
     * a given post from the screen displaying all posts
     */
    private void allPostsOptionsPanel() {
        allPostsOptionsPanel = new JPanel();
        allPostsOptionsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        allPostsOptionsPanel.setBackground(subMenuColor);

        JPanel allPostsOptionsPanelGrid = new JPanel();
        allPostsOptionsPanelGrid.setLayout(new GridLayout(5, 1, 5, 5));
        allPostsOptionsPanelGrid.setBackground(subMenuColor);

        allPostsCommentButton = new JButton("Create Comment"); //this button appears after selecting a post

        JLabel optionsTitle = new JLabel("Options");
        optionsTitle.setHorizontalAlignment(JLabel.CENTER);
        optionsTitle.setForeground(Color.white);

        allPostsOptionsPanelGrid.add(optionsTitle);
        allPostsOptionsPanelGrid.add(allPostsCommentButton);

        allPostsOptionsPanel.add(allPostsOptionsPanelGrid);
    }

    /**
     * layout for editing the post (practically identical to "write new post" page)
     * REASON: Requires a duplicate panel because editing post logic is different than creating post
     */
    private void editPostPanel() {
        editPostPanel = new JPanel();
        editPostPanel.setLayout(null);

        Font instructionFont = new Font(Font.SANS_SERIF,  Font.BOLD, 12);

        JLabel titleInstruction = new JLabel("Post Title:");
        titleInstruction.setFont(instructionFont);
        titleInstruction.setBounds(100, 50, 300, 25);

        editPostTitleField = new JTextField();
        editPostTitleField.setBounds(100, 100, 250, 25);

        JLabel contentInstruction = new JLabel("Post Content:");
        contentInstruction.setFont(instructionFont);
        contentInstruction.setBounds(100, 150, 300, 25);

        editPostContentField = new JTextField();
        editPostContentField.setBounds(100, 200, 250, 25);

        confirmEditPostButton = new JButton("Finish Editing");
        confirmEditPostButton.setBounds(100, 250, 130, 30);

        editPostPanel.add(titleInstruction);
        editPostPanel.add(editPostTitleField);

        editPostPanel.add(contentInstruction);
        editPostPanel.add(editPostContentField);
        editPostPanel.add(confirmEditPostButton);
    }

    /**
     * creates layout for page allowing user to comment on the selected post
     */
    private void commentOnPostPanel() {
        commentOnPostPanel = new JPanel(null);

        //create title for the "create comment" page
        JLabel commentInstruction = new JLabel("Type your comment:");
        commentInstruction.setBounds(100, 150, 300, 25);

        //create the text field for the "create comment" page
        commentOnPostField = new JTextField();
        commentOnPostField.setBounds(100, 200, 250, 25);

        //create the save created comment button
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
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(810, 500);
        frame.setLocationRelativeTo(null);
        frame.add(pixieViewPost.viewPostsCommentsOutlinePanel);
        frame.setVisible(true);

        for (int i = 0; i < 10; i++) { //pretend there are 10 posts

            //this logic structure will have to be recreated in Pixie.java because we don't know how many posts
            //there are at a given time until we communicate with Server using Client

            //the <br/> is for break line. Added only for displaying JLabel. Should not affect actual post
            JLabel currentPostLabel = new JLabel("<html>Here's some text" +
                    "<br/>Hope this works. What if I write a new line???? This goes to the next line" +
                    " Yes! Finally! It goes to the next line.--NEW ISSUE: Variable size???<br/></html>");

            //DEBUGGED: fluid dimensions can be achieved with setMinimumSize, setMaximumSize, and setPreferredSize
            //COUGH; -- never mind it doesn't work; ignore this
            //ended up hardcoding a nested JScrollPane in Pixie.java -- "View Your Posts" page, etc.
            currentPostLabel.setMinimumSize(new Dimension(300, 0));
            currentPostLabel.setPreferredSize(new Dimension(300, 100));
            currentPostLabel.setMaximumSize(new Dimension(300, 500));
            currentPostLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            currentPostLabel.setVerticalAlignment(JLabel.CENTER);

            pixieViewPost.viewPostsCommentsContainerPanel.add(currentPostLabel);
        }

        JFrame frame2 = new JFrame();
        frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame2.setSize(810, 500);
        frame2.setLocationRelativeTo(null);
        frame2.add(pixieViewPost.yourPostOptionsPanel, BorderLayout.WEST);
        frame2.setVisible(true);
    }

}
