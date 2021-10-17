import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Connects to the clients, starts the threads and handles the inputs of the clients
 * 
 * @author aashanashah
 * @version 1.1
 */
public class Server {
	private ServerSocket serverSocket;
	private Moves m;
	int num;

	// The set of all the print writers for all the clients, used for broadcast.
	private Set<PrintWriter> writers = new HashSet<>();

	public Server(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		this.m = new Moves();
		num = 0;
	}
	/**
	 * Connects to the clients and starts their threads
	 */
	public void start() {
		var pool = Executors.newFixedThreadPool(200);
		int clientCount = 0;
		while (true) {
			try {
				Socket p1 = serverSocket.accept();
				System.out.println("Connected to client " + clientCount++);
				PrintWriter out1 = new PrintWriter(p1.getOutputStream(), true);

				Socket p2 = serverSocket.accept();
				System.out.println("Connected to client " + clientCount++);
				PrintWriter out2 = new PrintWriter(p2.getOutputStream(), true);

				out1.println("1");
				out2.println("2");
				Scanner inp1 = new Scanner(p1.getInputStream());
				Scanner inp2 = new Scanner(p2.getInputStream());

				while(!inp1.hasNextLine()) {}
				num++;
				String t = inp1.nextLine();
				while(!inp2.hasNextLine()) {}
				num++;
				t = inp2.nextLine();

				writers.add(out1);
				writers.add(out2);
				out1.println("Start.");
				out2.println("Start.");
				pool.execute(new Handler(p1, out1, inp1));
				pool.execute(new Handler(p2, out2, inp2));	

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Handles the clients inputs and broadcasts it to the other clients
	 * 
	 * @author aashanashah
	 * @version 1.1
	 */
	public class Handler implements Runnable {
		private Socket socket;
		private Scanner input;
		private PrintWriter output;
		/**
		 * Constructor to initialise the Handler class
		 * 
		 * @param s Socket that client connects to
		 * @param o Output that is broadcasted
		 * @param i Input of the Client
		 */
		public Handler(Socket s, PrintWriter o, Scanner i) {
			this.socket = s;
			this.output  = o;
			this.input = i;
		}
		/**
		 * Takes the input and processes it to broadcast to all clients, if client has no output, they have left
		 */
		@Override
		public void run() {
			try {
				while (input.hasNextLine()) {
					num = 2;
					var command = input.nextLine();
					String str = m.NextMove(command, num);
					// broadcast updated number to all clients
					for (PrintWriter writer : writers) {
						writer.println(str);
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				// client disconnected
				if (output != null) {
					writers.remove(output);
					for (PrintWriter w: writers) {
						w.println("Game Ends. One of the players left.");
					}
					num--;
					m.refresh();
				}
			}
		}
	}
}

