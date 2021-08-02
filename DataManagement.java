import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * DataManagement - Project 4, Social Network App
 * Handles all data calls, setters and getters. Create an instance of
 * this if you need a profile or anything
 *
 * @author Group 8, C. H. Graham, ...
 * @version July 19, 2021
 */

public class DataManagement {
    //For me to remember whats going on
    //All blocks are String[]
    //Every block is stored in a ArrayList<String[]>
    
    //This method deletes a post in the file system, if it exists
    public void deletePost(Post post) {
    	ArrayList<String[]> postList = readFile("post.csv");
    	postList.remove(getPost(postList, post.getTitle(), post.getAuthor()));
    	writeFile("post.csv", postList);
    }

    //This method can either edit a post, or create a new one
    public void setPost(Post post) {
        //Post file is organized as follows:
        //Post
        //[Title]
        //[User], [Timestamp], [Message]
        //[User], [Timestamp], [Message]
    	ArrayList<String[]> postList = readFile("post.csv");
    	int postExists = getPost(postList, post.getTitle(), post.getAuthor());
    	
    	if (postExists == -1) {
    		postList.add(0, post.toFile());
    	} else {
    		postList.remove(postExists);
    		//Set this index to portExists if we want chats to stay behind
    		postList.add(postExists, post.toFile());
    	}
    	
        writeFile("post.csv", postList);
    }
    
    //This method is used to get a post's index in the file
    private int getPost(ArrayList<String[]> postList, String title, String author) {
    	
    	for (int i = 0; i < postList.size(); i++) {
    		String[] block = postList.get(i);
    		if (block.length > 2 && block[1].equals(title) && block[2].split(",")[0].equals(author)) {
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    public void findAndReplaceAll(String filename, String find, String replace) {
    	ArrayList<String[]> blockFile = readFile(filename);
    	
    	for (int i = 0; i < blockFile.size(); i++) {
    		String[] block = blockFile.get(i);
    		for (int j = 0; j < block.length; j++) {
    			String[] line = block[j].split(",");
    			String lineRest = "";
    			if (line[0] != null && line[0].equals(find)) {
    				block[j] = replace;
    				for (int k = 1; k < line.length; k++) {
    					block[j] += "," + line[k];
    				}
    			}
    		}
    		blockFile.set(i, block);
    	}
    	
    	writeFile(filename, blockFile);
    }
    
    public boolean postExists(Post post) {
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	return (getPost(profileList, post.getTitle(), post.getAuthor()) > -1);
    }
    
    //This method turns a post 'block' into a post object, essentially parsing it
    public Post toPost(String[] postInput) {
    	//Creates a post from text
    	String[] content = postInput[2].split(",");
    	String textContent = content[2];
    	for (int i = 3; i < content.length; i++) {
    		textContent += "," + content[i];
    	}
    	Post out = new Post(postInput[1].replace("123COMMA_REP321", ","), content[0], textContent);
    	out.setTimeStamp(getDate(content[1]));
    	ArrayList<Comment> comments = new ArrayList<Comment>();
    	
    	for (int i = 3; i < postInput.length; i++) {
    		content = postInput[i].split(",");
        	textContent = content[2];
        	for (int j = 3; j < content.length; j++) {
        		textContent += "," + content[j];
        	}
        	comments.add(new Comment(content[0], textContent, getDate(content[1])));
    	}
    	
    	out.setComments(comments);
    	
    	return out;
    }
    
    //This method gets a toString representaion of a date object, and converts back
    private Date getDate(String input) {
    	try {
			return (Date) (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(input));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return new Date();
    }
    
    //This method returns a list of the most recent posts from the index
    //onward, excluding some if the actual post count is less than the index
    public ArrayList<Post> getRecentPosts(int startIndex, int endIndex) {
    	ArrayList<String[]> recentPosts = readFile("post.csv");
    	ArrayList<Post> postList = new ArrayList<Post>();
    	
    	if (endIndex == -1) {
    		return getAllPosts();
    	}
    	
    	for (int i = startIndex; i < endIndex; i++ ) {
    		if (i >= recentPosts.size() || i < 0 || recentPosts.get(i).length == 0) {
    			break;
    		}
    		postList.add(toPost(recentPosts.get(i)));
    	}
    	
    	return postList;
    }
    
    public ArrayList<Post> getAllPosts() {
    	ArrayList<String[]> recentPosts = readFile("post.csv");
    	ArrayList<Post> postList = new ArrayList<Post>();
    	
    	for (int i = 0; i < recentPosts.size(); i++) {
    		if (recentPosts.get(i).length > 0) {
    			postList.add(toPost(recentPosts.get(i)));
    		}
    	}
    	
    	return postList;
    }
    
    //Gets all posts a user has participated in at all
    public ArrayList<Post> getUserPosts(String user) {
    	ArrayList<String[]> recentPosts = readFile("post.csv");
    	ArrayList<Post> postList = new ArrayList<Post>();
    	
    	for (int i = 0; i < recentPosts.size(); i++) {
    		String[] block = recentPosts.get(i);
    		//Check main author
    		if (block.length > 2 && block[2].split(",")[0].equals(user)) {
    			postList.add(toPost(block));
    		}
    	}
    	
    	return postList;
    }
    
    // This method gets a list of posts with similar text somewhere in it
    public ArrayList<Post> getSimilarPosts(String phrase) {
    	ArrayList<String[]> postList = readFile("post.csv");
    	ArrayList<Post> output = new ArrayList<Post>();
    	
    	for (int i = 0; i < postList.size(); i++) {
    		String[] block = postList.get(i);
    		if (block.length > 0) {
    			for (int j = 0; j < block.length; j++) {
    				if (block[j].indexOf(phrase) > -1) {
    					output.add(toPost(block));
    					break;
    				}
    			}
    		}
    	}
    	
    	return output;
    }
    
    public ArrayList<String> getAssociatedUsers(String phrase) {
    	ArrayList<String[]> userList = readFile("profile.csv");
    	ArrayList<String> usernames = new ArrayList<String>();
    	
    	for (int i = 0; i < userList.size(); i++) {
    		String[] block = userList.get(i);
    		if (block.length > 1 && block[1].indexOf(phrase) > -1) {
    			usernames.add(block[1]);
    		}
    	}
    	
    	return usernames;
    }
    
    public Post getPost(String title, String author) {
    	ArrayList<String[]> recentPosts = readFile("post.csv");
    	int index = getPost(recentPosts, title, author);
    	return toPost(recentPosts.get(index));
    }
    
    public ArrayList<Post> getUserComments(String user) {
    	ArrayList<String[]> recentPosts = readFile("post.csv");
    	ArrayList<Post> postList = new ArrayList<Post>();
    	
    	for (int i = 0; i < recentPosts.size(); i++) {
    		String[] block = recentPosts.get(i);
    		//Check main author
    		
    		for (int j = 3; j < block.length; j++) {
    			if (block.length > 2 && block[j].split(",")[0].equals(user)) {
    	    		postList.add(toPost(block));
    	    		break;
    	    	}
    		}
    	}
    	
    	return postList;
    }
    
    //This method turns a post 'block' into a post object, essentially parsing it
    public Account toAccount(String[] accountInput) {
    	Account output = new Account(accountInput[1], accountInput[2]);
    	output.setBio(accountInput[3].replace("bio:", ""));
    	return output;
    }

    public Account getAccount(String accountName) {
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	return toAccount(profileList.get(getAccountIndex(profileList, accountName)));
    }
    
    public boolean accountExists(String accountName) {
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	return (getAccountIndex(profileList, accountName) > -1);
    }
    
    //This method gets the index in the file of an account
    private int getAccountIndex(ArrayList<String[]> accountList, String account) {
    	for (int i = 0; i < accountList.size(); i++) {
    		String[] block = accountList.get(i);
    		if (block.length > 1 && block[1].equals(account)) {
    			return i;
    		}
    	}
    	
    	return -1;
    }
    
    //This method can be used to both edit and create a new account
    public void setAccount(Account account) {
    	//An account object block will look like this:
    	//Profile
    	//[Username]
    	//[Password]
    	//[Bio]
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	int postExists = getAccountIndex(profileList, account.getUsername());
    	
    	if (postExists == -1) {
    		profileList.add(0, account.toFile());
    	} else {
    		profileList.remove(postExists);
    		//Set this index to portExists if we want chats to stay behind
    		profileList.add(0, account.toFile());
    	}
    	
        writeFile("profile.csv", profileList);
    }

    //This function deletes an account from the database
	public void deleteAccount(String accountName) {
    	ArrayList<String[]> profileList = readFile("profile.csv");
    	profileList.remove(getAccountIndex(profileList, accountName));
    	writeFile("profile.csv", profileList);
    }
    
    //This method reads a file and takes all object blocks and turns them into a list
    public static synchronized ArrayList<String[]> readFile(String fileName) {
        //Search an ammount of blocks
        ArrayList<String[]> fileLines = new ArrayList<String[]>();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
            	return fileLines;
            }
            BufferedReader bfr = new BufferedReader(new FileReader(file));
            String currentLine;
            
            ArrayList<String> block = new ArrayList<String>();
            
            while ((currentLine = bfr.readLine()) != null) {
                //Unsure of if this will work or not
            	if (currentLine.equals("")) {
            		String[] ammt = block.toArray(new String[0]);
            		if (ammt.length > -1) {
            			fileLines.add(ammt);
            			block = new ArrayList<String>();
            		}
            	} else {
            		block.add(currentLine);
            	}
            }
            fileLines.add(block.toArray(new String[0]));
        } catch (Exception e) {
            System.out.println("File Access Failed");
        }

        return fileLines;
    }

    //This method writes a list of object blocks to a file
	public static synchronized void writeFile(String fileName, ArrayList<String[]> fileLines) {
    	BufferedWriter bfw = null;
    	
        try {
            File file = new File(fileName);
            if (!file.exists()) {
	            file.createNewFile();
	        }
            bfw = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < fileLines.size(); i++) {
            	if (i > 0) {
            		bfw.write("\n");
            	}
            	for (int j = 0; j < fileLines.get(i).length; j++) {
            		bfw.write(fileLines.get(i)[j] + "\n");
            	}
            }
        } catch (Exception e) {
            System.out.println("File Write Failed");
        } finally {
            try {
                if (bfw != null)
                    bfw.close();
	        } catch (Exception ex) {
	            System.out.println("Error in closing the BufferedWriter" + ex);
	        }
        }
    }
}
