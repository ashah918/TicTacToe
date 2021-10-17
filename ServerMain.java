import java.net.ServerSocket;
/**
 * This class initialises the server and the thread.
 * 
 * @author aashanashah
 * @version 1.1
 */
public class ServerMain {
	/**
	 * Connect to socket and set up thread.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		System.out.println("Server is Running...");
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.println("Server Stopped.");
			}
		}));

		try (var listener = new ServerSocket(2448)) {
			Server myServer = new Server(listener);
			myServer.start();
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
