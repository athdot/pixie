import java.util.*;

/**
 * Account - CS180 PJ04
 * This is a simple class to manage a user Account
 * @author Charles Graham
 * @version 07/17/2021
 *
 **/

public class Account {
    // Basic login information
    private String username;
    private String password;

    //A short blurb about the user 
    private String bio;
    private static String[] names = {"carter", "language", "socrates", "love", "orange", "taper",
        "london", "trouble", "sandpaper", "stables", "ocean", "river", "elephant", "juice",
        "varsace", "blankets", "velvet", "castaways", "strange", "things", "cards", "beyonce",
        "polar", "zeus", "create", "pool", "canvas", "feather", "titties", "milkbox", "triangle",
        "redherring", "leonardo", "deadpool", "schoolishard", "masterschamber", "mindpower",
        "lordofthekings", "willofd", "goodmorning", "testsubject", "prickledpear"};

    //array list of comments and posts made by this account
    private ArrayList<Post> posts;
    private ArrayList<Comment> comments;

    private List<Account> accountInfo;
    private String profileFile = "profile.csv";

    
    public Account(String username, String password) {
        this.username = username;
        this.password = password; //will be encrypted from Application.java
        posts = new ArrayList<Post>();
        bio = ""; //user can set the bio later
    }

    public String getBio() {
        return bio;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String toString() { //Outputs a user Account as a printable string
        String lineBreak = "=-=-=-=-=-=-=-=-=-=-=--=-=";
        String output = lineBreak + "\n\n";
        output += "Username: " + username + "\n\n";
        output += "Bio: " + bio + "\n\n";

        for (int i = 0; i < posts.size(); i++) { //display a list of posts created by the user
            output += posts.get(i).toString() + "\n";
        }

        for (int i = 0; i < posts.size(); i++) { //display a list of comments created by the user
            output += posts.get(i).toString() + "\n";
        }

        output += lineBreak + "\n";
        return output;
    }
    
   //toFile function
    public String[] toFile() {
    	ArrayList<String> output = new ArrayList<String>();
    	output.add("Profile");
    	output.add(username);
    	output.add(password);
    	output.add("bio:" + bio); //We need to have bio: here, if its empty, parse issues
    	return output.toArray(new String[0]);
    }
    
    // Generates a random username or password
    public void computerGenerateName(String select) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        boolean usernameIsTaken = false;

        if (select.equals(getUsername())) {
            System.out.println("Select 1 to generate a unique username or" +
                    " select any other number to keep name: ");
        } else if (select.equals(getPassword())) {
            System.out.println("Select 1 to generate a unique password or" +
                    " select any other number to keep name: ");
        }

        int generate = scan.nextInt();
        scan.nextLine();


        try {
            // Program allows user to keep their old name if they later decide
            // to opt out of computer generated name chooser
            if (generate != 1 && !select.equals("")) {
                System.out.println(select);
            } else if (generate == 1) {
                // Formulate a randomized username unique to user
                String characters = "!/$%^&*#@+=";
                // Generate a random integer from 0 - 3000000
                int randomize = rand.nextInt(3000000);

                int randomize2 = rand.nextInt(characters.length());
                int randomize3 = rand.nextInt(characters.length());
                int randomize4 = rand.nextInt(characters.length());

                // Loop through private static names list
                String name = names[(int) (Math.random() * names.length)];

                char randChar = characters.charAt(randomize2);
                char randChar2 = characters.charAt(randomize3);
                char randChar3 = characters.charAt(randomize4);

                // Create unique username
                if (select.equals(getUsername())) {
                    setUsername("user" + randomize + randChar);
                    System.out.println(getUsername());
                    // Create unique password
                } else if (select.equals(getPassword())) {
                    setPassword(name + randomize + randChar);
                    System.out.println(getPassword());
                }
                // User can choose to create their own name if they don't want
                // a computer generated name only if they didn't already have a name
            }
            
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Unable to randomize!");
        }
    }
	
    public String getPassword() {
        return password;
    }
    
    public boolean correctPassword(String input) {
        return password.equals(input);
    }

/*    //Make sure the password matches through the encryption
    public boolean verifyPass(String input) {
        input = cryptoHashFunction(input);
        return input.equals(password);
    }*/

    //Set user bio, user can set their bio
    public void setBio(String bio) {
        this.bio = bio;
    }

    //Add a post to the account (move this into Application.java, since it's a basic function?)
    public void addPost(Post post) {
        posts.add(post); //newest posts for this account go the back
/*            Date newPostTime = post.getTimeStamp();
            boolean added = false; //if user is adding a post, it would be the most recent???
            for (int i = 0; i < posts.size(); i++) {
                Date timeStamp = posts.get(i).getTimeStamp();
                //Returns an array formatted like this:
                //int[year,day,hour,minute]

                if (newPostTime.compareTo(timeStamp) > 0) {
                    posts.add(i, post);
                    i = posts.size();
                    added = true;
                    continue;
                }
            }

            if (!added) {
                posts.add(post);
            }*/
    }

    public void deletePost(Post post) {
        //thinking about putting this in Application
        //ALSO: addPost, deleteAccount and editPost
    }

    //Set the username
    public void setUsername(String username) {
        this.username = username;
    }

    //Set the password (password should be encrypted in Application.java)
    public void setPassword(String password) {
        this.password = password;
    }

    //get the user comments
    public ArrayList<Comment> getComments() {
        return comments;
    }
}
