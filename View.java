import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This class creates a frame with the three panels: 3x3 TicTacToe board, A message
 * panel and a place for the client to write their name and submit it.
 *
 * @author aashanashah
 * @version 1.1
 */
public class View {

	JFrame frame = new JFrame();
	JButton btn00 = new JButton();
	JButton btn01 = new JButton();
	JButton btn02 = new JButton();
	JButton btn10 = new JButton();
	JButton btn11 = new JButton();
	JButton btn12 = new JButton();
	JButton btn20 = new JButton();
	JButton btn21 = new JButton();
	JButton btn22 = new JButton();
	JButton sub = new JButton("Submit");
	JTextField txt_name = new JTextField(20);
	JLabel Msg = new JLabel("Enter your player name...");
	/**
	 * Initializes panel and frame. Adds different components to the panel. Add
	 * menubar to frame.
	 */
	public void go() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel BtnPanel = new JPanel();
		BtnPanel.setLayout(new GridBagLayout());
		BtnPanel.setSize(400, 400);
		JPanel SPanel = new JPanel();
		JPanel NPanel = new JPanel();
		NPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.1;
		c.weighty = 0.1;
		c.fill = GridBagConstraints.BOTH;

		BtnPanel.add(btn00, c);
		c.gridx = 1;
		BtnPanel.add(btn01, c);
		c.gridx = 2;
		BtnPanel.add(btn02, c);
		c.gridy = 1;
		c.gridx = 0;
		BtnPanel.add(btn10, c);
		c.gridx = 1;
		BtnPanel.add(btn11, c);
		c.gridx = 2;
		BtnPanel.add(btn12, c);
		c.gridy = 2;
		c.gridx = 0;
		BtnPanel.add(btn20, c);
		c.gridx = 1;
		BtnPanel.add(btn21, c);
		c.gridx = 2;
		BtnPanel.add(btn22, c);
		BtnPanel.setBackground(Color.black);
		btn00.setEnabled(false);
		btn01.setEnabled(false);
		btn02.setEnabled(false);
		btn10.setEnabled(false);
		btn11.setEnabled(false);
		btn12.setEnabled(false);
		btn20.setEnabled(false);
		btn21.setEnabled(false);
		btn22.setEnabled(false);
		frame.add(BtnPanel, BorderLayout.CENTER);

		SPanel.add(txt_name);
		SPanel.add(sub);
		frame.add(SPanel, BorderLayout.SOUTH);

		NPanel.add(Msg);
		frame.add(NPanel, BorderLayout.NORTH);

		// Menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu menuC = new JMenu("Control");
		JMenu menuH = new JMenu("Help");
		JMenuItem mC = new JMenuItem("Exit");
		JMenuItem mH = new JMenuItem("Instruction");
		mC.addActionListener(new Quit());
		mH.addActionListener(new Help());
		menuC.add(mC);
		menuH.add(mH);
		menuBar.add(menuC);
		menuBar.add(menuH);
		frame.setJMenuBar(menuBar);

		frame.setTitle("Tic Tac Toe");
		frame.setSize(400, 500);
		frame.setVisible(true);
	}
	/**
	 * Quits system when exit is clicked
	 *
	 * @author aashanashah
	 */
	class Quit implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * Displays instructions when instructions is clicked
	 *
	 * @author aashanashah
	 */
	class Help implements ActionListener {
		/**
		 * @param e.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = "Some information about the game:\n" + "Criteria for a valid move:\n" + "-The move is not occupied by any mark.\n" + "-The move is made in the player’s turn.\n" + "-The move is made within the 3 x 3 board.\n"
					+ "The game would continue and switch among the opposite player until it reaches either one of the following conditions:\n" + "-Player 1 wins.\n" + "-Player 2 wins.\n" + "-Draw.\n";
			JOptionPane.showMessageDialog(new JFrame(), s, "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * Changes the frame, the message and disables the text field and submit button when client submits their name.
	 *
	 * @param name adds name to frame title, Message
	 */
	public void setFrame(String name) {
		frame.setTitle("Tic Tac Toe-Player: "+ name);
		Msg.setText("WELCOME " + name);
		txt_name.setEditable(false);
		sub.setEnabled(false);
	}
	/**
	 * Enables all the buttons when called
	 *
	 */
	public void enable() {
		btn00.setEnabled(true);
		btn01.setEnabled(true);
		btn02.setEnabled(true);
		btn10.setEnabled(true);
		btn11.setEnabled(true);
		btn12.setEnabled(true);
		btn20.setEnabled(true);
		btn21.setEnabled(true);
		btn22.setEnabled(true);
	}
}
