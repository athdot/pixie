import java.util.ArrayList;
import java.util.Date; 

/**
 * Post - CS180 PJ04
 * A class representative of a social network post
 *
 * @author C. H. Graham
 * @version 07/17/2021
 *
 **/

public class Post {
    private String title;
    private String author;
    private String content;
    private Date timeStamp;
    private ArrayList<Comment> comments;
    //private ArrayList<Message> lowerMessages //For later

    public Post(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
        timeStamp = new Date();
        comments = new ArrayList<Comment>();
    }
    
    public Post(Post post) {
    	this.title = post.getTitle();
    	this.author = post.getAuthor();
    	this.content = post.getContent();
    	timeStamp = post.getTimeStamp();
    	comments = post.getComments();
    }
    
    //Set the timestamp
    public void setTimeStamp(Date date) {
    	timeStamp = date;
    }
    
    public void addComment(Comment comment) {
    	comments.add(comment);
    }
    
    public void setComments(ArrayList<Comment> comments) {
    	this.comments = comments;
    }
    
    public ArrayList<Comment> getComments() {
    	return comments;
    }

    //return author of post
    public String getAuthor() { return author; }

    //Return the time the message was sent
    public Date getTimeStamp() {
        return timeStamp;
    }

    //Get content
    public String getContent() {
        return content;
    }

    //Get title
    public String getTitle() {
        return title;
    }
    
    //toFile function
    public String[] toFile() {
    	ArrayList<String> output = new ArrayList<String>();
    	output.add("Post");
    	output.add(replaceCommas(title));
    	output.add(author + "," + timeStamp.toString() + "," + content);
    	
    	for (int i = 0; i < comments.size(); i++) {
    		output.add(comments.get(i).toFile());
    	}
    	
    	return output.toArray(new String[0]);
    }
    
    private String replaceCommas(String input) {
    	//Replace string commas with something the user is very unlikely to type
    	return input.replace(",", "123COMMA_REP321");
    }

    //change title
    public void setTitle(String title) { this.title = title; }

    //change content
    public void setContent(String content) { this.content = content; }

    //Display message
    public String toString() {
        String output = "\n";
        output += " [Title]: " + title.replace("123COMMA_REP321", ",") + "\n";
        output += " [Author]: " + author + "\n";
        output += " [Time]: " + timeStamp.toString() + "\n";
        output += " [Content]: " + content + "\n";
        
        if (comments.size() > 0) {
        	output += "\n";
        	output += "[Comments]: \n";
        	output += comments.get(0).toString();
        }
        
        for (int i = 1; i < comments.size(); i++) {
        	output += "|\n" + comments.get(i).toString();
        }
        return output;
    }
}
