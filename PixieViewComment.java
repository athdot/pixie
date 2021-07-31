import javax.swing.*;
import java.awt.*;

/**
 * PixieViewComment
 * contains panel setups for "View Your Comment" main menu option.
 *
 * @author Group 8, N. Yao
 * @version July 31, 2021
 */

public class PixieViewComment extends JComponent {

    Color subMenuColor = new Color(114, 90, 122, 255);

    //options menu that appears after user selects a post that s/he commented on
    public JPanel yourCommentOptionsPanel;
    public JButton editCommentButton;
    public JButton deleteCommentButton;

    //edit comment page -- don't reuse PixieViewPost's editComment field; editing comment uses different algorithm
    public JPanel editCommentPanel;
    public JTextField editCommentField;
    public JButton confirmEditCommentButton;

    public PixieViewComment() {
        yourCommentOptionsPanel();
        editCommentPanel();
    }

    /**
     * this side panel appears after the user selects a valid post # and comment #
     */
    private void yourCommentOptionsPanel() {
        yourCommentOptionsPanel = new JPanel();
        yourCommentOptionsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        yourCommentOptionsPanel.setBackground(subMenuColor);

        JPanel yourCommentOptionsPanelGrid = new JPanel();
        yourCommentOptionsPanelGrid.setLayout(new GridLayout(4, 1, 5, 5));
        yourCommentOptionsPanelGrid.setBackground(subMenuColor);

        JLabel optionsTitle = new JLabel("Options");
        optionsTitle.setHorizontalAlignment(JLabel.CENTER);
        optionsTitle.setForeground(Color.white);

        //these buttons appear after selecting your post
        editCommentButton = new JButton("Edit Comment");
        deleteCommentButton = new JButton("Delete Comment");

        yourCommentOptionsPanelGrid.add(optionsTitle);
        yourCommentOptionsPanelGrid.add(editCommentButton);
        yourCommentOptionsPanelGrid.add(deleteCommentButton);

        yourCommentOptionsPanel.add(yourCommentOptionsPanelGrid);
    }

    /**
     * page for EDIT COMMENT -- stores setup into editCommentPanel
     */
    private void editCommentPanel() {
        editCommentPanel = new JPanel(null);

        //create title for the "create comment" page
        JLabel commentInstruction = new JLabel("Type your comment:");
        commentInstruction.setBounds(100, 150, 300, 25);

        //create the text field for the "edit comment" page
        editCommentField = new JTextField();
        editCommentField.setBounds(100, 200, 250, 25);

        //create the save changed-comment button
        confirmEditCommentButton = new JButton("Confirm");
        confirmEditCommentButton.setBounds(100, 250, 130, 30);

        //add them to the editCommentPanel in correct order
        editCommentPanel.add(commentInstruction);
        editCommentPanel.add(editCommentField);
        editCommentPanel.add(confirmEditCommentButton);
    }

}
