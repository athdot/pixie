import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientTest implements Runnable {
	final static int port = 1235;
	final private String host = "localhost";
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket socket;
	private Scanner sc;
	
	public ClientTest() {
		try  {
			this.socket = new Socket(host, port);
			sc = new Scanner(System.in);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ClientTest test = new ClientTest();
		new Thread(test).start();
		test.prompt();
	}
	
	public void prompt() {
		while (true) {
			System.out.println("Send to server:");
			String line = sc.nextLine();
			try {
				oos.writeObject(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				Object obj = ois.readObject();
				if (obj instanceof String) {
					System.out.println(obj);
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
