import javax.swing.*;
import java.awt.*;

/**
 * carries all panel setups related to viewing posts: "View Your Posts" and "View All Posts".
 * Includes the panels that will replace the submenu panel when user enters a given post number
 */

public class PixieViewPost extends JComponent {

    //outline for "View Your Post" and "View All Posts" -- posts will be added in Pixie.java
    public JPanel viewPostsOutlinePanel;

    public JPanel viewPostsContainerPanel; //panel inside of viewPostOutlinePanel to contain JLabels for posts

    public PixieViewPost() {
        viewPostPanel();
    }



    private void viewPostPanel() {

        //idea: posts are added in a grid-fashion, from top to bottom, within viewPostsContainerPanel
        JPanel boundingBox = new JPanel(new BorderLayout());

        JScrollPane jsp = new JScrollPane(boundingBox);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setBounds(100, 100, 300, 300);

        //inside jsp, add another JPanel to constrict JLabel posts that will be added
        viewPostsContainerPanel = new JPanel(new GridLayout(0, 1));
        boundingBox.add(viewPostsContainerPanel, BorderLayout.CENTER);

        //viewPostsOutlinePanel serves as a simple aesthetic empty border, added later to Pixie.java appPanelContent
        viewPostsOutlinePanel = new JPanel(null);
        viewPostsOutlinePanel.add(jsp);
    }

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

            //DEBUGGED: variable dimensions can be achieved with setMinimumSize, setMaximumSize, and setPreferredSize
            currentPostLabel.setMinimumSize(new Dimension(300, 0));
            currentPostLabel.setPreferredSize(new Dimension(300, 100));
            currentPostLabel.setMaximumSize(new Dimension(300, 500));
            currentPostLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            currentPostLabel.setVerticalAlignment(JLabel.CENTER);

            pixieViewPost.viewPostsContainerPanel.add(currentPostLabel);
        }
    }

}
