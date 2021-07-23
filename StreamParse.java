import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * StreamParse - Project 4, Social Network App
 * Class used to convert to and from objects and strings
 *
 * @author Group 8, C. H. Graham, ...
 * @version July 20, 2021
 */

public class StreamParse {
	public static String postToString(Post post) {
		String[] lines = post.toFile();
		String output = lines[0];
		
		for (int i = 1; i < lines.length; i++) {
			output += "\n" + lines[i];
		}
		
		return output;
	}
	
	private static Date getDate(String input) {
    	try {
			return (Date) (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(input));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return new Date();
    }
	
	public static Post stringToPost(String post) {
		String[] postInput = post.split("\n");
		
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
	
	public static String postsToString(ArrayList<Post> posts) {
		if (posts.size() == 0) {
			return "";
		}
		
		String output = postToString(posts.get(0));
		
		for (int i = 1; i < posts.size(); i++) {
			output += "\n\n" + postToString(posts.get(i));
		}
		
		return output;
	}
	
	public static String accountToString(Account acc) {
		String[] lines = acc.toFile();
		String output = lines[0];
		
		for (int i = 1; i < lines.length; i++) {
			output += "\n" + lines[i];
		}
		
		return output;
	}
	
	public static Account stringToAccount(String acc) {
		String[] accountInput = acc.split("\n");
		Account output = new Account(accountInput[1], accountInput[2]);
    	output.setBio(accountInput[3].replace("bio:", ""));
    	return output;
	}
	
	public static ArrayList<Post> stringToPosts(String posts) {
		if (posts.equals("")) {
			return new ArrayList<Post>();
		}
		
		String[] postInput = posts.split("\n\n");
		ArrayList<Post> output = new ArrayList<Post>();
		
		for (int i = 0; i < postInput.length; i++) {
			output.add(stringToPost(postInput[i]));
		}
		
		return output;
	}
}
