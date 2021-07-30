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

1. Choose the "your profile" button under the main menu.
2. Choose the "change Username" button under the "your profile" menu.
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

1. Choose the "your profile" button under the main menu.
2. Choose the "change Password" button under the "your profile" menu.
3. Enter the old password and the new password and confirm the change.

Expected Result:

If the old password was entered incorrectly, the user will be given an error JPanel notifying them that the password
was incorrect. If the old password was correct, the new password will become the user's password