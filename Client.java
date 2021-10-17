import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * The program for each client such that the frame and its contents are updated according to the input
 * of each client
 * 
 * @author aashanashah
 * @version 1.1
 */
public class Client {
	int curplayer;
	int win;
	int pnum;
	String name;
	String nextmove;
	private View view;
	private Socket socket;
	private Scanner inp;
	private PrintWriter out;
	/**
	 * Constructor of Client to initialise the instance variables and to create the GUI for the client
	 * 
	 * @param view The GUI of the client
	 */
	public Client(View view) {
		this.view = view;
		view.go();
		nextmove = "";
		curplayer = 1;
		win = 0;
	}
	/**
	 * Connects the client to the correct IP address and socket, gets the input and output stream
	 * 
	 * @param view The GUI of the client
	 */
	public void start() {
		try {
			this.socket = new Socket("127.0.0.1", 2448);
			this.inp = new Scanner(socket.getInputStream());
			this.out = new PrintWriter(socket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Thread handler = new ClientHandler(socket);
		handler.start();
	}
	/**
	 * Handles the clients inputs by saving their name and analysing the commands to give a appropriate output
	 * 
	 * @author aashanashah
	 * @version 1.1
	 */
	class ClientHandler extends Thread {
		private Socket socket;
		/**
		 * Constructor of ClientHandler
		 * 
		 * @param socket Saves the same socket
		 */
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		/**
		 * Take the input, save the name and analyse the command
		 */
		public void run() {
			try {
				pnum = Integer.parseInt(inp.nextLine());
				saveName();
				readFromServer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Stores the next move of the player (by clicking a button on the button panel)
	 * 
	 * @author aashanashah
	 * @version 1.1
	 */
	class NextListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			nextmove = "";
			if(curplayer == pnum) {
				nextmove += Integer.toString(pnum);
				if(e.getSource()== view.btn00) {
					nextmove += "00";
				}
				else if(e.getSource()== view.btn01) {
					nextmove += "01";
				}
				else if(e.getSource()== view.btn02) {
					nextmove += "02";
				}
				else if(e.getSource()== view.btn10) {
					nextmove += "10";
				}
				else if(e.getSource()== view.btn11) {
					nextmove += "11";
				}
				else if(e.getSource()== view.btn12) {
					nextmove += "12";
				}
				else if(e.getSource()== view.btn20) {
					nextmove += "20";
				}
				else if(e.getSource()== view.btn21) {
					nextmove += "21";
				}
				else if(e.getSource()== view.btn22) {
					nextmove += "22";
				}
				out.println(nextmove);
			}
		}
	}
	/**
	 * Changes the text on the button according to the clients action
	 * 
	 * @param info Contains the gameboard, the current player and the winner to bet stored
	 */
	public void setPanel(String info) {
		curplayer = Integer.parseInt(String.valueOf(info.charAt(9)));
		win = Integer.parseInt(String.valueOf(info.charAt(10)));
		change(view.btn00, info.charAt(0));
		change(view.btn01, info.charAt(1));
		change(view.btn02, info.charAt(2));
		change(view.btn10, info.charAt(3));
		change(view.btn11, info.charAt(4));
		change(view.btn12, info.charAt(5));
		change(view.btn20, info.charAt(6));
		change(view.btn21, info.charAt(7));
		change(view.btn22, info.charAt(8));
	}
	/**
	 * Changes the button text to 'X' or 'O' depending on which client
	 */
	public void change(JButton b, char c) {
		if (c == '1') {
			b.setText("X");
			b.setForeground(Color.GREEN);
			b.setFont(new Font("Times New Roman", Font.BOLD, 32));
			b.setEnabled(true);
		}
		else if (c == '2') {
			b.setText("O");
			b.setForeground(Color.RED);
			b.setFont(new Font("Times New Roman", Font.BOLD, 32));
			b.setEnabled(true);
		}
	}
	/**
	 * When the submit button is pressed, checks the text and if valid then saves it and changes frame
	 * 
	 * @throws Exception
	 */
	public void saveName() throws Exception {
		try {
			view.sub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					if (!view.txt_name.getText().equals("")) {
						name = view.txt_name.getText();
						view.setFrame(name);
						nextmove = name;
						out.println(nextmove);
						nextmove = "";
					}
				}
			});
			aL();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Adds action listener to all the buttons so that when pressed the button changes according to client
	 * 
	 * @throws Exception
	 */
	public void aL() throws Exception {
		try {
			view.btn00.addActionListener(new NextListener());
			view.btn01.addActionListener(new NextListener());
			view.btn02.addActionListener(new NextListener());
			view.btn10.addActionListener(new NextListener());
			view.btn11.addActionListener(new NextListener());
			view.btn12.addActionListener(new NextListener());
			view.btn20.addActionListener(new NextListener());
			view.btn21.addActionListener(new NextListener());
			view.btn22.addActionListener(new NextListener());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Checks the input from the client and responds accordingly
	 * 
	 * @throws Exception
	 */
	public void readFromServer() throws Exception {
		try {
			while(!inp.hasNextLine()) {}
			while (inp.hasNextLine()) {
				var command = inp.nextLine();
				if (command.equals("Game Ends. One of the players left.")) {
					JOptionPane.showMessageDialog(new JFrame(), command, "Message", JOptionPane.INFORMATION_MESSAGE);
					socket.close();
					System.exit(0);
				}
				else if (command.equals("Start.")) {
					view.enable();
				}
				else {
					setPanel(command);
					if (pnum == curplayer) {
						view.Msg.setText("Your opponent has moved, now is your turn.");
					}
					else {
						view.Msg.setText("Valid move, wait for your opponent.");
					}
					if (win != 0) {
						if (win == 3) {
							JOptionPane.showMessageDialog(new JFrame(), "Draw.", "Message", JOptionPane.INFORMATION_MESSAGE);
						}
						else if (pnum == win) {
							JOptionPane.showMessageDialog(new JFrame(), "Congratulations. You Win.", "Message", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "You lose.", "Message", JOptionPane.INFORMATION_MESSAGE);
						}
						System.exit(0);
					}
				}
				while(!inp.hasNextLine()) {}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
}
