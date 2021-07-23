import java.util.Date;
import java.util.Scanner;

/**
 * Application - Project 4, Social Network App
 * Handles overall functionality of application, brings together Account, Comment, and Post
 *
 * @author Group 8
 * @version July 17, 2021
 */

public class Comment {

    private String author; //author who made the comment
    private String content; //content of the comment
    private Date timestamp; //timestamp of comment
    private static String[] emojis = {"\uD83D\uDE00", "\uD83D\uDE01", "\uD83D\uDE03", "\uD83D\uDE04",
        "\uD83D\uDE17", "\uD83D\uDE18", "\uD83D\uDE09", "\uD83D\uDE1C", "\uD83E\uDD29", "\uD83E\uDD2A",
        "\uD83E\uDD23", "\uD83D\uDE22", "\uD83D\uDE2D", "\uD83E\uDD70", "\uD83D\uDE0D", "\uD83D\uDE1B",
        "\uD83D\uDE1C", "\uD83D\uDC45", "\uD83E\uDD11", "\uD83E\uDD14", "\uD83E\uDD10", "\uD83D\uDE10",
        "\uD83D\uDE05", "\uD83D\uDE26", "\uD83D\uDE2E", "\uD83D\uDE30", "\uD83D\uDE36", "\uD83D\uDE3A",
        "\uD83E\uDD2C", "\uD83E\uDD2E", "\uD83E\uDD74", "\uD83D\uDE0E", "\uD83D\uDE0F", "\uD83D\uDE14",
        "\uD83D\uDE15", "\uD83D\uDE16"};

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
        timestamp = new Date();
    }
    
    public Comment(String author, String content, Date timestamp) {
    	this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    
    public String toFile() {
    	String output = "";
    	output += author + "," + timestamp.toString() + "," + content;
    	return output;
    }

    public String toString() {
        String output = "";
        output += "| Author: " + author + "\n";
        output += "| Posted: " + timestamp.toString() + "\n";
        output += "| >> " + content + "\n";
        return output;
    }
    
    public void emojis() {
        System.out.println("Emoji drop down menu: ");

        int bambi = 1;
        for (String i : emojis) {
            System.out.print((bambi++) + "." + " " + i + " ");
        }
        System.out.println();

    }

    public String emojiSelection() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select emoji number: ");

        int emoji = scanner.nextInt();
        scanner.nextLine();

        return emojis[emoji - 1];

    }
}
