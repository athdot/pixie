import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A framework to run basic tests on Group 8's Project 4
 *
 * NOTE: Delete profile.csv and post.csv before running this test. This program tests the functionality of the expected
 * app according to the provided requirements on the handout. Persistence of data is tested through login function, and
 * attempts to override existing data. Creating, editing, and deleting accounts, posts, and comments is also tested.
 * Other functions, including searching for user or view all posts, is touched upon.
 *
 * NOTE: Due to the existence of generated timestamps based on real time, the testcase may not work 100% of the time.
 * This may be caused by processing speed or simply running the program while time dials forward one second.
 * Assuming that it is not a hardware problem, try to run the testcase a couple of times before determining if the
 * program is following expected output formatting.
 *
 * @author Group 8
 * @version 7/21/2021
 */
public class RunLocalTest {
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful())
        {
            System.out.println("Excellent - Test ran successfully");
        }
        else
        {
            for (Failure failure : result.getFailures())
            {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        private final PrintStream originalOutput = System.out;
        private final InputStream originalSysin = System.in;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayInputStream testIn;

        @SuppressWarnings("FieldCanBeLocal")
        private ByteArrayOutputStream testOut;

        @Before
        public void outputStart() {
            testOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOut));
        }

        @After
        public void restoreInputAndOutput() {
            System.setIn(originalSysin);
            System.setOut(originalOutput);
        }

        private String getOutput() {
            return testOut.toString();
        }

        @SuppressWarnings("SameParameterValue")
        private void receiveInput(String str) {
            testIn = new ByteArrayInputStream(str.getBytes());
            System.setIn(testIn);
        }

        @Test(timeout = 1000)
        public void testApp() {
            try {
                // try inputs that run through all required categorical features of the app
                String input = "1\nwrongUser\nwrongPass\n" + //test invalid login
                        "2\nUser\nPass\n" + //account creation
                        "1\n1\nThis is my new bio\n" + //edit a field of your profile
                        "2\nnewUser\n5\n" + //more profile modification
                        "2\n1\nPost Title\nPost Message\n" + //create a new post
                        "3\n1\n2\nNew Message\n" +  //be able to edit posts

                        "7\n2\nUser\nPass\n" + //program must handle invalid accounts
                        "2\nUser2\nPass2\n" + //create 2nd account
                        "5\n2\n1\n1\nComment Message\n" + //comment of another user's post
                        "4\n1\n3\n1\n" + //comment deletion and editing
                        "6\nUser\n1\n4\n" + //search for a user and view their content
                        "7\n1\nUser\nPass\n" +
                        "1\n4\nY\n0\n"; //account deletion and exit function


                // Runs the program with the input values
                receiveInput(input);
                String timestamp = new Date().toString(); //relies on speed of computer
                Application.main(new String[0]);

                // Retrieves the output from the program
                String output = getOutput();

                // Pair the input with the expected result
                String expected = "+--------------------------------------------------+\n" +
                        "| Welcome to Group 8's Social Network Application! |\n" +
                        "|                                                  |\n" +
                        "| Written by:                                      |\n" +
                        "| Charles Graham, Nathan Yao, Mingrui Xia,         |\n" +
                        "| Jasmine Maduafokwa, and Sami Heathcote           |\n" +
                        "|                                                  |\n" +
                        "| Copyright: We have no money for a copyright      |\n" +
                        "|                                                  |\n" +
                        "| NOTE: Enter 0 as an action to QUIT.              |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| LOGIN PAGE                                       |\n" +
                        "| 1. Sign In                                       |\n" +
                        "| 2. Create New Account                            |\n" +
                        "+--------------------------------------------------+\n" +
                        "Your Username: Your Password: Username/Password is Wrong\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| LOGIN PAGE                                       |\n" +
                        "| 1. Sign In                                       |\n" +
                        "| 2. Create New Account                            |\n" +
                        "+--------------------------------------------------+\n" +
                        "Your Username: Your Password: Account Created\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "Username: user\n" +
                        "\n" +
                        "Bio: \n" +
                        "\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| YOUR PROFILE                                     |\n" +
                        "| 1. Change Bio                                    |\n" +
                        "| 2. Change Username                               |\n" +
                        "| 3. Change Password                               |\n" +
                        "| 4. Delete Account                                |\n" +
                        "| 5. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Enter your new bio: \n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "Username: user\n" +
                        "\n" +
                        "Bio: This is my new bio\n" +
                        "\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| YOUR PROFILE                                     |\n" +
                        "| 1. Change Bio                                    |\n" +
                        "| 2. Change Username                               |\n" +
                        "| 3. Change Password                               |\n" +
                        "| 4. Delete Account                                |\n" +
                        "| 5. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Enter your new username\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "Username: newuser\n" +
                        "\n" +
                        "Bio: This is my new bio\n" +
                        "\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| YOUR PROFILE                                     |\n" +
                        "| 1. Change Bio                                    |\n" +
                        "| 2. Change Username                               |\n" +
                        "| 3. Change Password                               |\n" +
                        "| 4. Delete Account                                |\n" +
                        "| 5. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| CREATE POST                                      |\n" +
                        "| 1. Write New Post                                |\n" +
                        "| 2. Import Post from CSV                          |\n" +
                        "| 3. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Enter a Post Title: \n" +
                        "Enter the Post's Message: \n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "Post 1\n" +
                        "| -- Post Title --\n" +
                        "| Author: newuser\n" +
                        "| Posted: " + timestamp + "\n" +
                        "| >> Post Message\n" +
                        "\n" +
                        "\n" +
                        "Enter the number of the post you would like to edit\n" +
                        "Enter 0 to return to the main screen: \n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| EDIT POST                                        |\n" +
                        "| 1. Edit Title                                    |\n" +
                        "| 2. Edit Content                                  |\n" +
                        "| 3. Add a Comment                                 |\n" +
                        "| 4. Export Post                                   |\n" +
                        "| 5. Delete Post                                   |\n" +
                        "| 6. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Enter the Post's new Message: \n" +
                        "editPost[Post Title,newuser,New Message]\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "Logging Out...\n" +
                        "+--------------------------------------------------+\n" +
                        "| Welcome to Group 8's Social Network Application! |\n" +
                        "|                                                  |\n" +
                        "| Written by:                                      |\n" +
                        "| Charles Graham, Nathan Yao, Mingrui Xia,         |\n" +
                        "| Jasmine Maduafokwa, and Sami Heathcote           |\n" +
                        "|                                                  |\n" +
                        "| Copyright: We have no money for a copyright      |\n" +
                        "|                                                  |\n" +
                        "| NOTE: Enter 0 as an action to QUIT.              |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| LOGIN PAGE                                       |\n" +
                        "| 1. Sign In                                       |\n" +
                        "| 2. Create New Account                            |\n" +
                        "+--------------------------------------------------+\n" +
                        "Your Username: Your Password: Account Created\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| CREATE POST                                      |\n" +
                        "| 1. Write New Post                                |\n" +
                        "| 2. Import Post from CSV                          |\n" +
                        "| 3. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Invalid Action\n" +
                        "Invalid Action\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| CREATE POST                                      |\n" +
                        "| 1. Write New Post                                |\n" +
                        "| 2. Import Post from CSV                          |\n" +
                        "| 3. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Enter a Post Title: \n" +
                        "Enter the Post's Message: \n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "Post 1\n" +
                        "| -- 1 --\n" +
                        "| Author: user\n" +
                        "| Posted: " + timestamp + "\n" +
                        "| >> Comment Message\n" +
                        "\n" +
                        "\n" +
                        "Select the post you want to edit your comment(s)\n" +
                        "Enter -1 to return to main menu, 0 to quit program\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| EDIT POST                                        |\n" +
                        "| 1. Add Comment                                   |\n" +
                        "| 2. Edit Comment                                  |\n" +
                        "| 3. Delete Comment                                |\n" +
                        "| 4. Export as CSV                                 |\n" +
                        "| 5. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Your Comments: \n" +
                        "Select a Comment to Delete: \n" +
                        "Not a valid number!\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "Enter the username of the user you want to view or enter -1 to return to the main screen: \n" +
                        "Search successful. User found.\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| VIEW                                             |\n" +
                        "| 1. View Profile                                  |\n" +
                        "| 2. View Posts                                    |\n" +
                        "| 3. View Comments                                 |\n" +
                        "| 4. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "Username: user\n" +
                        "\n" +
                        "Bio: \n" +
                        "\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| VIEW                                             |\n" +
                        "| 1. View Profile                                  |\n" +
                        "| 2. View Posts                                    |\n" +
                        "| 3. View Comments                                 |\n" +
                        "| 4. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "Logging Out...\n" +
                        "+--------------------------------------------------+\n" +
                        "| Welcome to Group 8's Social Network Application! |\n" +
                        "|                                                  |\n" +
                        "| Written by:                                      |\n" +
                        "| Charles Graham, Nathan Yao, Mingrui Xia,         |\n" +
                        "| Jasmine Maduafokwa, and Sami Heathcote           |\n" +
                        "|                                                  |\n" +
                        "| Copyright: We have no money for a copyright      |\n" +
                        "|                                                  |\n" +
                        "| NOTE: Enter 0 as an action to QUIT.              |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| LOGIN PAGE                                       |\n" +
                        "| 1. Sign In                                       |\n" +
                        "| 2. Create New Account                            |\n" +
                        "+--------------------------------------------------+\n" +
                        "Your Username: Your Password: \n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| MAIN MENU                                        |\n" +
                        "| 1. Your Profile                                  |\n" +
                        "| 2. Create Post                                   |\n" +
                        "| 3. View Your Posts                               |\n" +
                        "| 4. View Your Comments                            |\n" +
                        "| 5. View All Posts                                |\n" +
                        "| 6. Search User                                   |\n" +
                        "| 7. Logout                                        |\n" +
                        "+--------------------------------------------------+\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "Username: user\n" +
                        "\n" +
                        "Bio: \n" +
                        "\n" +
                        "=-=-=-=-=-=-=-=-=-=-=--=-=\n" +
                        "\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| YOUR PROFILE                                     |\n" +
                        "| 1. Change Bio                                    |\n" +
                        "| 2. Change Username                               |\n" +
                        "| 3. Change Password                               |\n" +
                        "| 4. Delete Account                                |\n" +
                        "| 5. Back                                          |\n" +
                        "+--------------------------------------------------+\n" +
                        "Are you sure you want to delete account User? (Y/N)\n" +
                        "Deleting account...\n" +
                        "+--------------------------------------------------+\n" +
                        "| Welcome to Group 8's Social Network Application! |\n" +
                        "|                                                  |\n" +
                        "| Written by:                                      |\n" +
                        "| Charles Graham, Nathan Yao, Mingrui Xia,         |\n" +
                        "| Jasmine Maduafokwa, and Sami Heathcote           |\n" +
                        "|                                                  |\n" +
                        "| Copyright: We have no money for a copyright      |\n" +
                        "|                                                  |\n" +
                        "| NOTE: Enter 0 as an action to QUIT.              |\n" +
                        "+--------------------------------------------------+\n" +
                        "\n" +
                        "Choose an Action:\n" +
                        "+--------------------------------------------------+\n" +
                        "| LOGIN PAGE                                       |\n" +
                        "| 1. Sign In                                       |\n" +
                        "| 2. Create New Account                            |\n" +
                        "+--------------------------------------------------+\n" +
                        "Exiting...";

                // Trims the output and verifies it is correct.
                output = output.replace("\r\n", "\n");
                assertEquals("Program does not meet all core.\n" +
                                "Otherwise, check timestamp output comparison.\n" +
                                "If only the timestamp is incorrect, the program passes this test.",
                        expected.trim(), output.trim());

            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    }
}
