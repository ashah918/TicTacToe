import javax.swing.SwingUtilities;
/**
 * This class initialises the thread of each client and creates a new frame window.
 * 
 * @author aashanashah
 * @version 1.1
 */
public class ClientMain {
	/**
	 * Create and initialize a new view and new Client. Connect Client to server through controller.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				View view = new View();
				Client controller = new Client(view);
				controller.start();
			}
		});
	}
}
