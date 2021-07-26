import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest implements Runnable {

	private Socket connection;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private static ArrayList<String> changes;
	
	public static void main(String[] args) {
		changes = new ArrayList<String>();
		final int PORT = 1235;
		ServerSocket socket = null;
		
		try {
			socket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				Socket newConnection = socket.accept();
				ServerTest server = new ServerTest(newConnection);
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

	public ServerTest(Socket newClient) {
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
					oos.writeObject("Server received!");
					changes.add(obj.toString());
					oos.writeObject(String.join(", ", changes));
					oos.flush();
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
