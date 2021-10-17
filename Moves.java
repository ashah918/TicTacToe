/**
 * Updates the game board and checks if a client won
 * 
 * @author aashanashah
 * @version 1.1
 */
public class Moves {
	int curplayer;
	int win;
	int nplay; 
	int count;
	int gameb[][];
	String ret;
	/**
	 * Constructor to initialise the board
	 */
	public Moves() {
		curplayer = 1;
		win = 0;
		nplay = 0;
		count = 0;
		gameb = new int[3][3];
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 3; c++) {
				gameb[r][c] = 0;
			}
		}
		ret = "";
	}
	/**
	 * Gets the next move (sees if its valid) and updates the board, also checks for a winner/draw
	 * 
	 * @param play Used to check the player and move
	 * @param n Used to check the number of players in the game currently
	 * @return string that has updated information in board, current player and winner
	 */
	public String NextMove(String play, int n) {
		ret = "";
		if (n==2) {
			String cp = String.valueOf(play.charAt(0));
			int cPlay = Integer.parseInt(cp);
			String row = String.valueOf(play.charAt(1));
			int r = Integer.parseInt(row);
			String column = String.valueOf(play.charAt(2));
			int c = Integer.parseInt(column);
			if(cPlay == curplayer && gameb[r][c]==0) {
				gameb[r][c] = cPlay;
				count++;
				if (curplayer == 1) {
					curplayer = 2;
				}
				else {
					curplayer = 1;
				}
			}
			for (r = 0; r < 3; ++r) {
				for (c = 0; c < 3; ++c) {
					ret += gameb[r][c];
				}
			}
			ret += curplayer;
			CheckC();
			CheckR();
			CheckD();
			if (count == 9 && win == 0) {
				win = 3;
			}
			ret += win;
			return ret;
		}
		else {
			return "not enough players";
		}
	}
	/**
	 * Checks if a player has won by putting their symbol in a column
	 */
	public void CheckC() {
		if (gameb[0][0] == gameb[1][0] && gameb[0][0] == gameb [2][0]) {
			if (gameb[1][0] == 1) {
				win = 1;
			}
			else if (gameb[1][0] == 2) {
				win = 2;
			}
		}
		if (gameb[0][1] == gameb[1][1] && gameb[0][1] == gameb [2][1]) {
			if (gameb[1][1] == 1) {
				win = 1;
			}
			else if (gameb[1][1] == 2) {
				win = 2;
			}
		}
		if (gameb[0][2] == gameb[1][2] && gameb[0][2] == gameb [2][2]) {
			if (gameb[1][2] == 1) {
				win = 1;
			}
			else if (gameb[1][2] == 2) {
				win = 2;
			}
		}
	}
	/**
	 * Checks if a player has won by putting their symbol in a row
	 */
	public void CheckR() {
		if (gameb[0][0] == gameb[0][1] && gameb[0][0] == gameb [0][2]) {
			if (gameb[0][1] == 1) {
				win = 1;
			}
			else if (gameb[0][1] == 2) {
				win = 2;
			}
		}
		if (gameb[1][0] == gameb[1][1] && gameb[1][0] == gameb [1][2]) {
			if (gameb[1][1] == 1) {
				win = 1;
			}
			else if (gameb[1][1] == 2) {
				win = 2;
			}
		}
		if (gameb[2][0] == gameb[2][1] && gameb[2][0] == gameb [2][2]) {
			if (gameb[2][1] == 1) {
				win = 1;
			}
			else if (gameb[2][1] == 2) {
				win = 2;
			}
		}
	}
	/**
	 * Checks if a player has won by putting their symbol in a diagonal
	 */
	public void CheckD() {
		if (gameb[0][0] == gameb[1][1] && gameb[0][0] == gameb [2][2]) {
			if (gameb[0][0] == 1) {
				win = 1;
			}
			else if (gameb[0][0] == 2) {
				win = 2;
			}
		}
		if (gameb[0][2] == gameb[1][1] && gameb[0][2] == gameb [2][0]) {
			if (gameb[0][2] == 1) {
				win = 1;
			}
			else if (gameb[0][2] == 2) {
				win = 2;
			}
		}
	}
	/**
	 * Reinitialises the game after each round
	 */
	void refresh() {
		curplayer = 1;
		count = 0;
		win = 0;
		nplay = 0;
		for(int i=0; i<3; i++) {
			for(int j=0;j<3; j++) {
				gameb[i][j] = 0;
			}
		}
		ret = "";
	}
}
