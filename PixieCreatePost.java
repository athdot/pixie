import javax.swing.*;
import java.awt.*;

/**
 * PixieCreatePost - PJ05
 * Contains all panel setups for the "Create Post" main menu option
 *
 * @author Group 8
 * @version July 28, 2021
 */

public class PixieCreatePost extends JComponent {

    //All panels related to the "Create Post" option
    public JPanel createNewPostPanel;
    public JPanel importFromCSVPanel;

    //"CREATE NEW POST" (NEW!)
    public JTextField createPostTitleField;
    public JTextField createPostContentField;
    public JButton doneEditingPostButton;

    //IMPORT FROM CSV OPTION -- components
    public JTextField importFromCSVField;
    public JButton importFromCSVButton;

    // FEATURES (NEW!)
    //we do not have logic for this, it can be set up if there is time to spare later
    //we should focus on completing the objectives before moving onto fancier things -Nathan

//    public JButton emojiButton;
//    public JButton adjustText12Button;
//    public JButton adjustText14Button;
//    public JButton adjustText16Button;

    public PixieCreatePost() {
        createNewPost();
        importFromCSV();
    }

    /**
     * setup for the "Create a New Post" page
     */
    public void createNewPost() {

        createNewPostPanel = new JPanel();
        createNewPostPanel.setLayout(null);

        Font instructionFont = new Font(Font.SANS_SERIF,  Font.BOLD, 12);

        JLabel titleInstruction = new JLabel("Post Title:");
        titleInstruction.setFont(instructionFont);
        titleInstruction.setBounds(100, 50, 300, 25);

        createPostTitleField = new JTextField();
        createPostTitleField.setBounds(100, 100, 250, 25);

        JLabel contentInstruction = new JLabel("Post Content:");
        contentInstruction.setFont(instructionFont);
        contentInstruction.setBounds(100, 150, 300, 25);

        createPostContentField = new JTextField();
        createPostContentField.setBounds(100, 200, 250, 25);

        doneEditingPostButton = new JButton("Create Post");
        doneEditingPostButton.setBounds(100, 250, 130, 30);

//        ImageIcon icon = new ImageIcon("icons8-decrease-font-24.png");
//        adjustText12Button = new JButton(icon);
//        adjustText12Button.setBounds(200,240,140,30);
//
//        ImageIcon icon3 = new ImageIcon("icons8-a-24.png");
//        adjustText14Button = new JButton(icon3);
//        adjustText14Button.setBounds(370,240,140,30);
//
//        ImageIcon icon2 = new ImageIcon("icons8-increase-font-24.png");
//        adjustText16Button = new JButton(icon2);
//        adjustText16Button.setBounds(540,240,140,30);
//
//        ImageIcon icon4 = new ImageIcon("icons8-anime-emoji-30.png");
//        emojiButton = new JButton(icon4);
//        emojiButton.setBounds(200,550,100,35);
//
//        createNewPostPanel.add(emojiButton);
//        createNewPostPanel.add(adjustText12Button);
//        createNewPostPanel.add(adjustText14Button);
//        createNewPostPanel.add(adjustText16Button);

        createNewPostPanel.add(titleInstruction);
        createNewPostPanel.add(createPostTitleField);

        createNewPostPanel.add(contentInstruction);
        createNewPostPanel.add(createPostContentField);
        createNewPostPanel.add(doneEditingPostButton);
    }

    /**
     * set up for the "Import from CSV" option
     */
    public void importFromCSV() {
        importFromCSVPanel = new JPanel(null);

        //create title for the "change username" page
        JLabel importFromCSV = new JLabel("Enter CSV Filename (include \".csv\"):");
        importFromCSV.setBounds(100, 150, 300, 25);

        //create the text field for the "change username" page
        importFromCSVField = new JTextField();
        importFromCSVField.setBounds(100, 200, 250, 25);

        //create the save changed-username button
        importFromCSVButton = new JButton("Confirm");
        importFromCSVButton.setBounds(100, 250, 130, 30);

        //add them to the changeUsernamePanel in correct order
        importFromCSVPanel.add(importFromCSV);
        importFromCSVPanel.add(importFromCSVField);
        importFromCSVPanel.add(importFromCSVButton);
    }

    /*
    Frame preview testing: Good idea to set up a temporary main method to see how the pages will look.
    Comment out or delete this main method later.
    */
    public static void main(String[] args) {
        PixieCreatePost pixieCreatePost = new PixieCreatePost();
        JFrame frame = new JFrame();
        frame.setSize(500, 800);
        frame.setLocationRelativeTo(null); //goes after setSize
        frame.add(pixieCreatePost.createNewPostPanel);
        frame.setVisible(true);
    }

}
