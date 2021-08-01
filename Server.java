import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Server - PJ05
 * Each client connects to Server. While Server is running, it is constantly open to new client connections. Server will
 * initialize a thread for each client that is constantly listening for requests from that respective client. Server
 * will write back requested information.
 *
 * @author G8 C. Graham, N. Yao, ...
 * @version July 26, 2021
 */

public class Server implements Runnable {

	private static DataManagement data; //to use DataManagement methods
	private Account loggedAccount; //account currently logged in
	private Socket connection; //socket used by a client connection

	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	private static final int PORT = 31415; //easy to remember: digits of pi
	private static final String V_ID = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	private static String versionUpdate;
	private String localVersion;

	public static void main(String[] args) {

		data = new DataManagement();
		ServerSocket serverSocket = null; //declare outside to expand scope
		versionUpdate = "AAA";

		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Waiting for the client to connect...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				Socket newConnection = serverSocket.accept(); //accept() waits until client connects
				Server server = new Server(newConnection);
				System.out.println("Client connected!");

				//run a separate thread for each client to perform server operations (?)
				new Thread(server).start();

			} catch (IOException e) {
				e.printStackTrace(); //connection failed

				try {
					serverSocket.close();
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
			localVersion = versionUpdate;
			this.oos = new ObjectOutputStream(connection.getOutputStream());
			this.ois = new ObjectInputStream(connection.getInputStream());

			while (true) { //if a client program is terminated, throws SocketException, a subclass of IOException
				Object obj = ois.readObject();
				if (obj instanceof String) {

					//obj is able to be passed into requestTree(String request) method
					String request = obj.toString();
					String output = requestTree(request);
					
					oos.writeObject(output);
					oos.flush();
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			try {
				this.oos.close();
				this.ois.close();
				System.out.println("A client has disconnected.");
			} catch (IOException e1) {
				e.printStackTrace();
			}
		}
	}

	//Returns a return stream
	private String requestTree(String request) {
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
			iterateVersion();
		} else if (request.indexOf("changeBio[") == 0) {
			//changeBio[newBio]
			loggedAccount.setBio(unpack(request, "changeBio["));
			data.setAccount(loggedAccount);
			iterateVersion();
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
			iterateVersion();
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
			
			iterateVersion();
			
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
			iterateVersion();
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
			iterateVersion();
		} else if (request.indexOf("editTitle[") == 0) {
			//editTitle[oldTitle, postAuthor, newTitle]
			String[] textData = unpack(request, "editTitle[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			Post newPost = new Post(post);
			newPost.setTitle(textData[2]);
			
			iterateVersion();
			
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
			iterateVersion();
		} else if (request.indexOf("addComment[") == 0) {
			//addComment[postTitle, postAuthor, commentText]
			String[] textData = unpack(request, "addComment[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			for (int i = 3; i < textData.length; i++) {
				textData[2] += "," + textData[i];
			}
			post.addComment(new Comment(loggedAccount.getUsername(), textData[2]));
			data.setPost(post);
			iterateVersion();
		} else if (request.indexOf("deleteComment[") == 0) {
			//deleteComment[postTitle, postAuthor, commentIndex]
			String[] textData = unpack(request, "deleteComment[").split(",");
			Post post = data.getPost(textData[0], textData[1]);
			ArrayList<Comment> comments = post.getComments();
			comments.remove(Integer.parseInt(textData[2]));
			post.setComments(comments);
			data.setPost(post);
			iterateVersion();
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
		} else if (request.indexOf("getsync") == 0) {
			//Returns 1 if synched, returns 0 if a refresh is needed
			return "" + (localVersion.equals(versionUpdate) ? 1 : 0);
		} else if (request.indexOf("resynched") == 0) {
			//Updates our local version
			this.localVersion = versionUpdate;
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
	
	// This function takes the update sum and iterates it if the user does anything
	private void iterateVersion () {
		// Handle race conditions
		synchronized(versionUpdate) {
			// Start by looking at the first digit
			String replace = "";
			boolean resolved = false;
			boolean loopedOver = true;
			int digit = 0;
			do {	
				String indChar = versionUpdate.substring(versionUpdate.length() - digit - 1, 
						versionUpdate.length() - digit);
				int indexOfChar = V_ID.indexOf(indChar);
				if (loopedOver) {
					indexOfChar++;
					loopedOver = false;
				}
				if (indexOfChar >= V_ID.length()) {
					loopedOver = true;
					indexOfChar = 0;
				}
				replace = V_ID.substring(indexOfChar, indexOfChar + 1) + replace;
				if (replace.length() == versionUpdate.length()) {
					versionUpdate = replace;
					resolved = true;
					continue;
				}
				digit++;
			} while(!resolved);
			
			System.out.println(versionUpdate);
		}
	}
}
