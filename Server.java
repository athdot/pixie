import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Account - CS180 PJ04
 * This is a simple class to manage a user Account
 * @author Charles Graham
 * @version 07/17/2021
 **/

public class Server implements Runnable {
	private static DataManagement data;
	private Account loggedAccount;
	private Socket connection;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public static void main(String[] args) {
		final int port = 1235;
		ServerSocket socket = null;
		data = new DataManagement();
		
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				Socket newConnection = socket.accept();
				Server server = new Server(newConnection);
				new Thread(server).start();
			} catch (IOException e) {
				e.printStackTrace();
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public Server(Socket newClient) {
		this.connection = newClient;
	}
	
	@Override
	public void run() {
		try {
			this.oos = new ObjectOutputStream(connection.getOutputStream());
			this.ois = new ObjectInputStream(connection.getInputStream());
			while (true) {
				Object obj = ois.readObject();
				if (obj instanceof String) {
					//obj is similar to streamReader request
					String request = obj.toString();
					
					String output = streamReader(request);
					
					oos.writeObject(output);
					oos.flush();
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Returns a return stream
	private String streamReader(String request) {
		//Requests are
		//login[dnsda,dansdnasn]
		
		// =-=- Loging in, everything else comes after this
		if (request.indexOf("login[") == 0) {
			//login[username,password]
			//Log in the user, check to see if a user exists first
			
			String[] loginVal = unpack(request, "login[").split(",");
			return "" + login(loginVal[0], loginVal[1]);
		} else if (request.indexOf("createAccount[") == 0) {
			//createAccount[username,password]
			//Create a new account if exists
			
			String[] loginVal = unpack(request, "createAccount[").split(",");
			if (data.accountExists(loginVal[0])) {
				return "false";
			} else {
				loggedAccount = new Account(loginVal[0], CryptoHash.getHash(loginVal[1]));
				data.setAccount(loggedAccount);
				return "true";
			}
		} else if (request.indexOf("deleteAccount") == 0) {
			data.deleteAccount(loggedAccount.getUsername());
			loggedAccount = null;
		} else if (request.indexOf("changeBio[") == 0) {
			//changeBio[newBio]
			loggedAccount.setBio(unpack(request, "changeBio["));
			data.setAccount(loggedAccount);
			return "true";
		} else if (request.indexOf("changeUsername[") == 0) {
			//changeUsername[newUser]
			String newUser = unpack(request, "changeUsername[");
			if (data.accountExists(newUser)) {
				return "false";
			}
			data.deleteAccount(loggedAccount.getUsername());
			data.findAndReplaceAll("post.csv", loggedAccount.getUsername(), newUser);
			loggedAccount.setUsername(newUser);
			data.setAccount(loggedAccount);
			return "true";
		} else if (request.indexOf("changePassword[") == 0) {
			//changePassword[oldPassword,newPassword]
			String[] pass = unpack(request, "changePassword[").split(",");
			try {
				Account temp = data.getAccount(loggedAccount.getUsername());
				if (temp.correctPassword(CryptoHash.getHash(pass[0]))) {
					loggedAccount.setPassword(CryptoHash.getHash(pass[1]));
					data.setAccount(loggedAccount);
					return "true";
				}
			} catch (Exception e) {
				return "false";
			}
			return "false";
		} else if (request.indexOf("post[") == 0) {
			//post[title, content]
			//we have to check if a post exists already, so it doesn't get overwritten
			String[] titleContent = unpack(request, "post[").split(",");
			for (int i = 2; i < titleContent.length; i++) {
				titleContent[1] += "," + titleContent[i];
			}
			Post temp = new Post(titleContent[0], loggedAccount.getUsername(), titleContent[1]);
			
			if (!data.postExists(temp)) {
				data.setPost(temp);
				return "true";
			} else {
				return "false";
			}
		} else if (request.indexOf("getUserPosts[") == 0) {
			//getUserPosts[username]
			ArrayList<Post> tempPost = data.getUserPosts(unpack(request, "getUserPosts["));
			//Send a post as string
			return StreamParse.postsToString(tempPost);
		} else if (request.indexOf("getUserComments[") == 0) {
			ArrayList<Post> temp = data.getUserComments(unpack(request, "getUserComments["));
			return StreamParse.postsToString(temp);
		} else if (request.indexOf("getRecentPosts[") == 0) {
			//getRecentPosts[start, end]
			String[] values = unpack(request, "getRecentPosts[").split(",");
			ArrayList<Post> temp = data.getRecentPosts(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
			return StreamParse.postsToString(temp);
		} else if (request.indexOf("getAllPosts") == 0) {
			//getRecentPosts[start, end]
			String values = unpack(request, "getAllPosts");
			ArrayList<Post> temp = data.getAllPosts();
			return StreamParse.postsToString(temp);
		} else if (request.indexOf("logout") == 0) {
			//logout
			logout();
			return "true";
		} else if (request.indexOf("editComment[") == 0) {
			//Edit
			//editComment[postTitle, postAuthor, commentIndex, comment]
			String[] textData = unpack(request, "editComment[").split(",");
			for (int i = 4; i < textData.length; i++) {
				textData[3] += "," + textData[i];
			}
			Post post = data.getPost(textData[0], textData[1]);
			ArrayList<Comment> comments = post.getComments();
			Comment oldComment = comments.get(Integer.parseInt(textData[2]));
			Comment newComment = new Comment(loggedAccount.getUsername(), textData[3], oldComment.getTimestamp());
			comments.set(Integer.parseInt(textData[2]), newComment);
			post.setComments(comments);
			data.setPost(post);
		} else if (request.indexOf("editPost[") == 0) {
			//editPost[postTitle, postAuthor, content]
			System.out.println(request);
			String[] textData = unpack(request, "editPost[").split(",");
			for (int i = 3; i < textData.length; i++) {
				textData[2] += "," + textData[i];
			}
			Post post = data.getPost(textData[0], textData[1]);
			post.setContent(textData[2]);
			data.setPost(post);
		} else if (request.indexOf("editTitle[") == 0) {
			//editTitle[oldTitle, postAuthor, newTitle]
			String[] textData = unpack(request, "editTitle[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			Post newPost = new Post(post);
			newPost.setTitle(textData[2]);
			
			if (!data.postExists(newPost)) {
				data.deletePost(post);
				data.setPost(newPost);
				return "true";
			} else {
				return "false";
			}
		} else if (request.indexOf("deletePost[") == 0) {
			//deletePost[title, author]
			String[] textData = unpack(request, "deletePost[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			data.deletePost(post);
		} else if (request.indexOf("addComment[") == 0) {
			//addComment[postTitle, postAuthor, commentText]
			String[] textData = unpack(request, "addComment[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			for (int i = 3; i < textData.length; i++) {
				textData[2] += "," + textData[i];
			}
			post.addComment(new Comment(loggedAccount.getUsername(), textData[2]));
			data.setPost(post);
		} else if (request.indexOf("deleteComment[") == 0) {
			//deleteComment[postTitle, postAuthor, commentIndex]
			String[] textData = unpack(request, "deleteComment[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			ArrayList<Comment> comments = post.getComments();
			comments.remove(Integer.parseInt(textData[2]));
			post.setComments(comments);
			data.setPost(post);
		} else if (request.indexOf("getPost[") == 0) {
			//getPost[postTitle, postAuthor]
			String[] textData = unpack(request, "getPost[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			return StreamParse.postToString(post);
		} else if (request.indexOf("getProfile[") == 0) {
			//getProfile[username]
			try {
				Account acc = data.getAccount(unpack(request, "getProfile[").toLowerCase());
				return StreamParse.accountToString(acc);
			} catch (Exception e) {
				e.printStackTrace();
				return "false";
			}
		} else if (request.indexOf("postSearch[") == 0) {
			//postSearch[phrase]
			ArrayList<Post> temp = data.getSimilarPosts(unpack(request, "postSearch["));
			return StreamParse.postsToString(temp);
		} else if (request.indexOf("userSearch[") == 0) {
			ArrayList<String> temp = data.getAssociatedUsers(unpack(request, "userSearch["));
			if (temp.size() == 0) {
				return "";
			}
			String returnValue = temp.get(0);
			for (int i = 0; i < temp.size(); i++) {
				returnValue += "," + temp.get(i);
			}
			return returnValue;
		}
		
		return "false";
	}
	
	private String unpack(String request, String beginning) {
		return request.replace(beginning, "").replace("]", "");
	}
	
	private boolean login(String username, String password) {
		try {
			Account temp = data.getAccount(username);
			if (temp.correctPassword(CryptoHash.getHash(password)) && loggedAccount == null) {
				loggedAccount = temp;
				return true;
			}
		} catch (Exception e) {
			//Incorrect username or password
		}
		return false;
	}
	
	private void logout() {
		loggedAccount = null;
	}
}
