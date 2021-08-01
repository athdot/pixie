import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Client - PJ05
 * Each user who has the app open also has a Client object that communicates with the Server class.
 *
 * @author G8 C. Graham, N. Yao, ...
 * @version July 26, 2021
 */

public class Client {

	private final static int PORT = 31415; //pi port number
	private final static String HOST = "localhost";

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public Client() {
		try {
			//for each client, instantiate a Client object
			this.socket = new Socket(HOST, PORT);

			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String streamReader(String request) {
		String returnedValue = "";
		try {
			oos.writeObject(request);
			oos.flush();
			returnedValue = ois.readObject().toString();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Client streamReader method error!");
			e.printStackTrace();
		}

		return returnedValue;
	}

	public void end() {
		try {
			oos.close();
			ois.close();
			System.out.println("A user has ended the program.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
