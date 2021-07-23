## **Team 8 Documentation**

Welcome to team 8's program. Below you will find instructions. for running our code, as well as class descriptions and submission details.


### **Instructions for running our Program**

In order to use our application, first you must download all the relevant files, including Application, Account, Comment, Post, DataManagement, CryptoHash, Backend, and StreamParse. Once you have downloaded these to an IDE of your choice, you can run the program by running the main method of the Application class.

### **Submission Details**

Jasmine - Submitted Report on Brightspace

Charles - Submitted Vocareum workspace

### **Class Descriptions**


#### **Application.java**

The Application.java class is the main front end class for our program. It does all of the printing and receives all input. It also contains most of the logic for navigating the program and communicates with the back end classes, such as Backend and DataManagement classes to get the information it needs and store any new information. For testing, since this class is so broad, we tested each section of the main menu separately, inputting usual and unusual answers to requests in order to try and find holes in our code’s logic.

#### **StreamParse.java**

The StreamParse.java class focuses on parsing, converting objects to strings and vice versa. It is the class that aids most with posting, account, and bio information of the user. To test, we used test cases and printing to see if the string object,the post, was appended to the user’s file.

#### **Post.java**

The Post.java class holds and organizes all the information pertaining to a certain post, including the title, author, message, timestamp, and comments. This class is mostly used in the application class to retrieve post information and print posts to the screen. In order to test this class, we ran the Application main method with extra print lines to ensure the right values were passed from the various methods.

#### **Comment.java**

The Comment.java class manages information pertaining to a comment, including the author, message, timestamp, and a toString() object. This class is mostly used as a getter class, passing comment information to the Application class. In order to test this class, we ran the Application main method with additional print lines to ensure the right values were being passed from the comment class.

#### **Account.java**

The Account.java class holds all information pertaining to the user. 
The main functionality of this class is to store and retrieve the username 
and passcode of a specific user. This class interacts most with the application class, 
passing information to the main running methods, especially when the user is logging in. 
Since this class pertains to login details of a user, we tested it by creating new accounts, 
logging in, and deleting accounts in the Application class. This testing allowed us to see the correct values 
were passed from this class’s methods.

#### **Backend.java**

The Backend.java class’s main function, streamReader, takes a string from the Application class and calls the 
corresponding DataManagement method. This class’s main functionality is to provide a middle man between Application and
DataManagement. This organization will make the implementation of clients and servers in project 5 much easier.
In order to test the Backend class, we ran the main method of the Application class, printing
values passed to and from the backend functions to ensure the correct output is created.

#### **DataManagement.java**

The DataManagement.java class deals with the file reading and writing for storing and accessing posts,
user information, and comments. This class mostly deals with the Backend class, indirectly sending information
to/getting information from Application through the Backend class. Since the DataManagement class is directly connected
to the Backend class, this class was tested at the same time, printing the values retrieved from DataManagement
to find and fix errors in the output of the methods.



