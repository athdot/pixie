import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private final static int port = 1235;
	private final static String host = "localhost";
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket socket;
	
	public Client() {
		try  {
			this.socket = new Socket(host, port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String streamReader(String request) {
		String returnedValue = "";
		try {
			oos.writeObject(request);
			returnedValue = ois.readObject().toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return returnedValue;
	}
}
