import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI Testing page changes & other stuff later. You can ignore this class.
 * https://stackoverflow.com/questions/19927824/jframe-freezes-after-calling-getcontentpane-removeall
 * https://stackoverflow.com/questions/1097366/java-swing-revalidate-vs-repaint
 */

public class GUITest extends JComponent implements Runnable {

    JFrame frame = new JFrame("TEST GUI");
    Container container = frame.getContentPane();

    JPanel panel1 = new JPanel(new BorderLayout());
    JPanel panel2 = new JPanel(new BorderLayout());

    JButton toPanel2 = new JButton("To Panel 2");
    JButton toPanel1 = new JButton("To Panel 1");

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == toPanel2) {
                switchPanel(panel2);
            }
            if (e.getSource() == toPanel1) {
                switchPanel(panel1);
            }
        }
    };

    public void switchPanel(JPanel newPanel) {
        container.removeAll();
        container.add(newPanel, BorderLayout.CENTER);

        //DEBUGGED: use repaint() and revalidate() to refresh and recalculate layout
        frame.repaint();
        frame.revalidate();
    }

    public void run() {

        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        toPanel1.setPreferredSize(new Dimension(100, 50));
        toPanel2.setPreferredSize(new Dimension(100, 50));

        panel1.add(toPanel2, BorderLayout.CENTER);
        panel2.add(toPanel1, BorderLayout.CENTER);

        toPanel2.addActionListener(actionListener);
        toPanel1.addActionListener(actionListener);

        container.setLayout(new BorderLayout());
        container.add(panel1, BorderLayout.CENTER);
    }

//    public void refreshPage() { //get the page to load the newest information
//        while (true) {
//            try {
//                Thread.sleep(2000); //refresh the page every 2 seconds
//                panel2.updateUI();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void changeInfo() { //update the information on the page
//        while (true) {
//            try {
//                infoLabel.setText("Info 1");
//                Thread.sleep(6000); //every 6 seconds, change information
//                infoLabel.setText("Info 2");
//                Thread.sleep(6000);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String[] args) {
        //run the GUI on event dispatch thread
        SwingUtilities.invokeLater(new GUITest());
    }
}
