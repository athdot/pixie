import javax.swing.*;
import javax.swing.border.LineBorder;
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

    //"CREATE NEW POST" (NEW!)
    public JTextField createTitle;
    public JTextArea createPost;
    public JButton doneEditingPostButton;

    // FEATURES (NEW!) -- add these only if we have spare time later
//    public JButton emojiButton;
//    public JButton adjustText12Button;
//    public JButton adjustText14Button;
//    public JButton adjustText16Button;

    public PixieCreatePost() {
        createNewPost();
    }

    // Create new button post panel
    public void createNewPost() {
        /*
        The "Write Post" panel
         */
        createNewPostPanel = new JPanel();
        createNewPostPanel.setLayout(null);

        createNewPostPanel.setBorder(new LineBorder(Color.black,3));

        // Create Post stuff
        Font font2 = new Font(Font.SANS_SERIF,  Font.PLAIN, 12);
        createPost = new JTextArea();
        createPost.setBounds(200,340,480,200);
        createPost.setFont(font2);

        JScrollPane scroll = new JScrollPane(createPost);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create Title stuff
        Font font = new Font(Font.SANS_SERIF,  Font.BOLD, 20);

        createTitle = new JTextField();
        createTitle.setBounds(200,280,480,35);
        createTitle.setFont(font);

        JLabel title = new JLabel(createTitle.getText());
        title.setFont(font);
        title.setBounds(80, 100,50,20);

        doneEditingPostButton = new JButton("Done");
        doneEditingPostButton.setBounds(580,550,100,35);

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

        createNewPostPanel.add(createTitle);
        createNewPostPanel.add(createPost);
        createNewPostPanel.add(doneEditingPostButton);
    }

    /*
    Frame preview testing: Good idea to set up a temporary main method to see how the pages will look.
    Comment out or delete this main method later.
    */
    public static void main(String[] args) {
        PixieCreatePost pixieCreatePost = new PixieCreatePost();
        JFrame frame = new JFrame();
        frame.add(pixieCreatePost.createNewPostPanel);
        frame.setVisible(true);
    }

}
