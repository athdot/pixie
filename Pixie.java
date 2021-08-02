import javax.swing.SwingUtilities;

/**
 * Pixie.java - PJ05
 * Apparently our app is branded as "Pixie". Pixes are mythical creatures in folklore and children's stories.
 * Pixie.java calls PixieThread and acts as the launcher for the app. Analyses server status to provide a real-time
 * updates.
 *
 * NOTE: DON'T FORGET TO RUN Server.java FIRST.
 *
 * @author Group 8
 * @version Aug 1, 2021
 */

public class Pixie implements Runnable {
	private final Client UPDATE_CLIENT = new Client();
	private static PixieThread pixieApp;
	
	public static void main(String[] args) {
        //run application on event dispatch thread (EDT)
		Pixie app = new Pixie();
		new Thread(app).start();
    }
	
	public Pixie() {
		pixieApp = new PixieThread();
		SwingUtilities.invokeLater(pixieApp);
	}

	@Override
	public void run() {
		if (UPDATE_CLIENT.serverStatus()) {
    		//Stops thread execution
    		return;
    	}
		
		// Configure the server thread for this object appropriately
		UPDATE_CLIENT.streamReader("updateStream");
		
		while (true) {
			//Wait for new update requests
			try {
				// Refreshing page
				UPDATE_CLIENT.getUpdate();
				pixieApp.refreshPage();
				Thread.sleep(1);
			} catch (Exception e) {
				System.out.println("Refreshing system failed");
				return;
			}
		}
		
	}
}
