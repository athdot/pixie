## **Team 8 Documentation**

Welcome to team 8's program. Below you will find instructions. for running our code,
as well as class descriptions and submission details.


### **Instructions for running our Program**

In order to use our application, first you must download all the relevant files, including
Pixie, PixieThread, PixieSubmenus, PixieYourProfile, PixieSearchUser, PixieViewComments, PixieViewPost, Client, Server,
StreamParse, Account, Client, CryptoHash, and Data Management. Once you have downloaded these to an IDE of your choice,
you can use the program by running the main method of the Server class to open the server, then running the Pixie class
main method to open the application.

### **Submission Details**

Sami - Submitted Report on Brightspace

Charles - Submitted Vocareum workspace

Nathan - Submitted Presentation on Brightspace

### **Class Descriptions**

#### **Account.java**

The Account.java class holds all information pertaining to the user.
The main functionality of this class is to store and retrieve the username
and passcode of a specific user. This class is used mostly when the user is logging in or retrieving their profile.

#### Client.java

The Client.java class is the class that connects to and sends information to/gets information from the server. It
deals mostly with PixieThread and Server, acting as a middle man between the main GUI class and the main file i/o class.

#### **Comment.java**

The Comment.java class manages information pertaining to a comment, including the author, message, timestamp,
and a toString() object. This class is mostly used as a getter class, passing comment information to other classes.

#### **DataManagement.java**

The DataManagement.java class deals with the file reading and writing for storing and accessing posts,
user information, and comments. This class mostly deals with the Backend class, indirectly sending information
to/getting information from the client through the server class.

#### Pixie.java

The Pixie.java class is the main running method for our application. It is used to create a connection with the
server, run PixieThread, and update the application as changes are made to the server from other client threads.

#### PixieThread.java

The PixieThread.java class is the main GUI class. It is where all the JPanels are brought together and user
interactions with the program are tracked to change the page/save information/get information from the server.

#### PixieSubmenus.java, PixieLoginPage.java, PixieCreatePost.java, PixieSearchUser.java, PixieViewComment.java,
PixieViewPost.java

The PixieSubmenus.java, PixieLoginPage.java, PixieCreatePost.java, PixieSearchUser.java, PixieViewComment.java,
and PixieViewPost.java classes deal with the setup of the GUI JPanel layouts. These classes deal only with the
PixieThread class, where all the layouts are brought together. The main purpose of these classes is organization,
to keep the PixieThread class from being super long.

#### **Post.java**

The Post.java class holds and organizes all the information pertaining to a certain post, including the title, author,
message, timestamp, and comments. This class is mostly used to retrieve post information and
print posts to the screen.

#### Server.java

The Server.java class is the part of our program that deals with file reading and writing, as well as running threads
for multiple user's applications at once. This class deals with the StreamParse, DataManagement, and PixieThread classes
to retrieve/store information in files and receive/send information to the client threads.

#### **StreamParse.java**

The StreamParse.java class focuses on parsing, converting objects to strings and vice versa. It is the class
that aids most with posting, account, and bio information of the user.