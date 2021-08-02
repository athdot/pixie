##Project 5 Test Cases

###Test 1: Account Creation

Steps:

1. Choose the "Create Account" button in the left panel.
2. Enter your username and password into the corresponding fields.
3. Click the "Create Account" button below the username and password fields.

Expected Result:

The program will provide pop-ups confirming account creation and welcoming the user to Pixie.
It will then load the Pixie application main page with the main menu and "your profile" menu on 
the left side of the screen with the user's profile shown on the right.

Test Status: Passed

###Test 2: User login

Steps:

1. Choose the "Sign In" button in left panel.
2. Enter your username and password into the corresponding fields.
3. Click the "Sign In" button below the username and password fields.

Expected Result: 

The program will provide a "welcome back" pop-up and load the Pixie application main page 
with the main menu and "your profile" menu on the left side of the screen and the user's profile
info on the right.

Test Status: Passed


###Test 3: Change Username

Steps:

1. Choose the "Your Profile" button under the main menu.
2. Choose the "Change Username" button under the "your profile" menu.
3. Enter a new username and confirm the change.

Expected Result:

The new username entered will become the username of the active user and will be stored in the system as such,
unless the new username has already been taken. In that case, the user will be notified that the username is unavailable,
and their username will remain the same as before.
If this user logs out, they should be able to log in with the new username and old password. 
Additionally, if they navigate to the "your profile" main page, their new username should be displayed.

Test Status: Passed

###Test 4: Change password

Steps:

1. Choose the "Your Profile" button under the main menu.
2. Choose the "Change Password" button under the "your profile" menu.
3. Enter the old password and the new password and confirm the change.

Expected Result:

If the entered old password was incorrect, the user will be given an error JPanel notifying them that the password
was incorrect. If the old password was correct, the new password will become the user's password and will be stored in
the system as such. 

Test Status: Passed

###Test 5: Delete Account

Steps:

1. Choose the "Your Profile" button under the main menu.
2. Choose the "Delete Account" button under the "your profile" menu.
3. Confirm the removal of your account by choosing "yes" on the pop-up menu

Expected Result:

The active user will be logged out, and their account will be deleted, removing their
information from the system. If they then try to log in with that account, the system should
not recognize their username as a valid username.

Test Status: Passed

###Test 6: Create New Post

Steps:

1. Choose the "Create Post" button under the main menu.
2. Choose the "Write New Post" button under the "Create Post" menu.
3. Enter your desired post title and content.
4. Choose "Create Post" under the text boxes to confirm creation.

Expected Result:

The post will be written to the system, and the program will produce a JPanel message
confirming the creation of the post was successful.

Test Status: Passed

###Test 7: Import Post from CSV File

Steps:

1. Choose the "Create Post" button under the main menu.
2. Choose the "Import CSV Post" button under the "Create Post" menu.
3. Enter the name of the CSV file containing your post information.
4. Choose "Confirm" under the text box to confirm creation of the post.

Expected Result:

If the name provided corresponds to a CSV file with the right information in it, the program
will convert the file information to a post, storing this post in the system. If the file doesn't exist,
or the information is not formatted correctly, the user will be notified via a JPanel error message.

Test Status: Passed

###Test 8: Comment on a Post

Steps:

1. Choose the "View All Posts" button under the main menu

Expected Result:

Test Status: Failed

###Test 9: Export Post to CSV File

Steps:

1.

Expected Result:

Test Status: Failed

###Test 10: Delete Post

Steps:

1.

Expected Result:

Test Status: Failed

###Test 11: Edit Post

Steps:

1.

Expected Result:

Test Status: Failed

###Test 12: Search for User

Steps:

1.

Expected Result:

Test Status: Failed

###Test 13: Logout of Account

Steps:

1. 

Expected Result:

Test Status: Failed

###Test 14: 