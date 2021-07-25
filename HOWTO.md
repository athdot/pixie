# Server Communication with Client.java
## Step 1
Make sure you have a 'Client' object initialized on the base level
```java
public class GUIClassThing {
    // Other variables...
    private Client client;
    //...Other variables

    public GUIClassThing() {
        client = new Client();
    }
    //...
}
```
## Step 2
Prepare your request for what you need, and put it inside client.streamReader(String string);
```java
String input = "login[username,password1234]";
client.streamReader(input);
```
## Step 3
Call whatever you need in a method inside your class. Ex:
```java
String worked = "login[" + username.toLowerCase() + "," + password + "]";
worked = client.streamReader(worked);

if (worked.equals("false")) {
    System.out.println(INVALID_ACCOUNT);
} else {
     localUsername = username;
     break;
}
```

## Callable Functions and their Input Strings
Use the below formats whenever you use Client.java
```java
String ex = ""; //Example string
String ret = ""; //Return value

//client.streamReader will always by default return "false" as a string

//Logging in
ex = "login[username,pass1234]"; //No spaces
ret = client.streamReader(ex); //Returns "true" on success as a string


//Creating an account
ex = "createAccount[username,pass1234]"; //No spaces
ret = client.streamReader(ex); //Returns "false" if account exists already


//Deleting an account
ex = "deleteAccount"; //No parameters, so no brackets needed
ret = client.streamReader(ex); //Returns "false" no matter what, deletes anyways


//Change Bio
ex = "changeBio[I am a bio]"; //Bio in brackets
ret = client.streamReader(ex); //Returns "true" if no errors


//Change Username
ex = "changeUsername[usernameNew]"; //Username in brackets
ret = client.streamReader(ex); //Returns "false" if username is taken


//Change Password
ex = "changePassword[pass1234,newpass1234]"; //No spaces, old pass, new pass
ret = client.streamReader(ex); //Returns "true" if old password was correct


//Add post
ex = "post[I am a post title,Post content]"; //Talk to Charles when you get to this
ret = client.streamReader(ex); //Returns "true" post doesnt exsist and got set


//Used to get the posts under an associated username
ex = "getUserPosts[user]"; //Username in brackets
ret = client.streamReader(ex); //Returns a string rep. of a list, convert with
                               //StreamParse.stringToPosts();


//Change Username
ex = "getUserComments[user]"; //Username in brackets
ret = client.streamReader(ex); //Returns a string rep. of a list of posts that the
                               //user has commented on, convert


//Get recent posts, can use this for all posts too
ex = "getRecentPosts[0,5]"; //Gets the most recent posts between index 0 and 5
ret = client.streamReader(ex); //Returns a string rep. of a list of posts between
                               //start,end. Convert


//Get all posts globally
ex = "getAllPosts"; //No brackets, there are no inputs for this one
ret = client.streamReader(ex); //Returns a string rep. of all posts in a list


//Logout user
ex = "logout"; //No brackets, there are no inputs for this one
ret = client.streamReader(ex); //Returns "true", meaningless


//Edit a comment
ex = "editComment[postTitle,postAuthor,commentIndex,newCommentText]";
ret = client.streamReader(ex); //Returns "false", meaningless
                               //Comment Index is the number comment it is on
                               //the post


//Edit a posts content
ex = "editPost[postTitle,postAuthor,content]";
ret = client.streamReader(ex); //Returns "false", meaningless
                               //Post title & author are for identifying post
                               //Change the content with this function


//Edit a posts title
ex = "editTitle[oldTitle,postAuthor,newTitle]";
ret = client.streamReader(ex); //Returns "false" if another post exists -w- title
                               //Old title and postAuthor are for finding
                               //Post, newTitle is the changed value


//Delete a post
ex = "deletePost[title,author]"; //Title and author for finding post
ret = client.streamReader(ex); //Returns "false", meaningless


//Add a comment to a post
ex = "addComment[postTitle,postAuthor,commentText]";
ret = client.streamReader(ex); //Returns "false", meaningless
                               //Post title and Author for identifying post
                               //Comment text is comment to be added


//Delete a comment on a post
ex = "deleteComment[postTitle,postAuthor,commentIndex]";
ret = client.streamReader(ex); //Returns "false", meaningless
                               //Post title and Author for identifying post
                               //Comment index is index of comment to remove


//Get a post
ex = "getPost[postTitle,postAuthor]"; //Title and author for finding post
ret = client.streamReader(ex); //Returns a string representation of a post


//Get a profile
ex = "getProfile[username]"; //Username is the username of user
ret = client.streamReader(ex); //Returns a string representation of a profile


//Search for a post
ex = "postSearch[phrase]"; //Phrase is the phrase to search for in all posts
ret = client.streamReader(ex); //Returns a string rep. of a list of posts associated


//Get a profile
ex = "userSearch[phrase]"; //Phrase is the name to find associated users with
ret = client.streamReader(ex); //Returns a comma seperated list of related usernames
```

## Extras
Come to me with any questions, or things needing to be added, and I will put it in for you!