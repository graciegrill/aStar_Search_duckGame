/*
 * Take That!
 * by Scott Weiss
 *
 * This class does most of the work. It displays the labels and grid buttons.
 * It changes the grid state after each move.
 *
 * It also manages the computer players. You will be rewriting the method
 * makeComputerChoice so it intelligently picks a move based on the current game
state.
 *
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class Board extends JPanel {
	private Player rowP; // the two players
	private Player colP;
	private boolean isRowsTurn; // whose turn is it
	private int currentRow, currentCol; // row and column of last move
	private JLabel[] colLabels; // the labels that indicate which column to play in
	private JLabel[] rowLabels; // the labels to indicate the row to play in
	private Cell[][] cells; // the grid of numbers
	private JLabel messageLabel; // label for turn information, winner
	
	public boolean isRowsTurn() {return this.isRowsTurn;}
	public int getCurrentRow() {return this.currentRow;}
	public int getCurrentCol() {return this.currentCol;}
	public Player getRowPlayer() {return this.rowP;}
	public Player getColPlayer() {return this.colP;}
	
	public Board(int size, Player rp, Player cp, int min, int max, Random rand) {
		// Constructor
		// size is grid size (assume square)
		// rp & cp are players
		// min and max is range of possible numbers for a square
		// rand is random number generator
		rowP = rp; // remember player information
		colP = cp;
		isRowsTurn = rand.nextInt()%2 == 0; // determine who moves first
		rowLabels = new JLabel[size]; // make arrays of labels for row/column headings
		colLabels = new JLabel[size];
		cells = new Cell[size][size]; // make 2D array of Cells
		this.setLayout(new GridLayout(size+2, size+1)); // set layout for Board
		// one extra row to accommodate winner message
		// extra row and column for labels
		this.add(new JLabel("",JLabel.CENTER)); // blank label to line things up nicely
		for (int i=0; i < size; i++) // add column headers across the top
		{
			colLabels[i] = new JLabel(""+i,JLabel.CENTER); // label with column number
			colLabels[i].setForeground(Color.RED); // make it large and red
			colLabels[i].setFont(new Font(null, Font.BOLD, 20));
			colLabels[i].setVisible(false); // no numbers shown yet
			this.add(colLabels[i]);
		}
		for (int i=0; i < size; i++)
		{
			// make label for row first
			rowLabels[i] = new JLabel(""+i, JLabel.CENTER);
			rowLabels[i].setForeground(Color.RED);
			rowLabels[i].setFont(new Font(null, Font.BOLD, 20));
			rowLabels[i].setVisible(false);
			this.add(rowLabels[i]);
			// then make cells in that row
			for (int j=0; j < size; j++)
			{
				// create a new Cell object
				Cell tempCell = new Cell(i,j,this, min, max, rand);
				cells[i][j] = tempCell;
				this.add(tempCell); // add to panel
			}
		}
		if (isRowsTurn) // is it row player's turn to start?
		{
			// pick a random row
			currentRow = rand.nextInt(size);
			// make that row's label visible
			rowLabels[currentRow].setVisible(true);
		}
		else // do similar for columns
		{
			currentCol = rand.nextInt(size);
			colLabels[currentCol].setVisible(true);
		}
		// put text in the message label
		messageLabel = new JLabel("Time to play Take That");
		this.add(messageLabel);
	}
	public Board(Player rp, Player cp, boolean turn, int row, int col,  Cell[][] c) {
		rowP = rp;
		colP = cp;
		isRowsTurn = turn;
		currentRow = row;
		currentCol = col;
		cells = c;
	}
	
	public void nextTurn()
	// This method sets up the next player's turn
	{
		if (!gameOver()) // if there are still turns to play
		{
			if (isRowsTurn) // tell appropriate player to take its turn
				rowP.takeTurn();
			else
				colP.takeTurn();
		}
	}
	
	public void makeComputerMove()
	// This performs the move for the computer player
	// After determining what the move should be, it executes that move on the board
	{
		if (isRowsTurn) // is it row player's turn?
		{
			// tell user the computer player is thinking
			messageLabel.setText(rowP.getName()+" thinking");
			// determine the player's move
			int colChoice = makeComputerChoice();
			// attempt to make the move
			if (makeMove(currentRow, colChoice))
				cells[currentRow][colChoice].select(); // if legal move, change the grid
		}
		else // similar for column player
		{
			messageLabel.setText(colP.getName()+" thinking");
			int rowChoice = makeComputerChoice();
			if (makeMove(rowChoice, currentCol))
				cells[rowChoice][currentCol].select();
		}
		nextTurn(); // have the next player go
	}
	//Replaced with alpha beta call
	public int makeComputerChoice()
	// This method determines the square the computer player will move to
	// It returns the row or column of the move as appropriate
	// You will replace this code - right now, it makes a random legal move in the
	// appropriate row or column
	{
		BoardNode b = new BoardNode(clone());
		int g = b.alphabeta(isRowsTurn());
		System.out.println(g);
		return  g;
		
		
	}
	public boolean makeMove(int row, int col)
	// Execute move made by player
	// row, col - coordinates of selected square
	// val - number in the square
	// returns true if move is legal, false otherwise
	{
		if (isRowsTurn && row==currentRow) // if row's turn and square is in current row
		{
			rowP.addToScore(cells[row][col].getValue()); // update player's score
			rowLabels[currentRow].setVisible(false); // hide previous visible label
			currentCol = col; // update column to that of selected square
			colLabels[currentCol].setForeground(Color.RED); // display header for selected column
			colLabels[currentCol].setVisible(true);
			isRowsTurn = false; // switch to column player's turn
			cells[row][col].select(); // select given square
			if (gameOver()) // if the game is over
			{
				add(new JLabel(rowP.getWinner(colP))); // announce the winner
			}
			return true;
		}
		else if (!isRowsTurn && col==currentCol) // do analogous things for columns
		{
			colP.addToScore(cells[row][col].getValue());
			colLabels[currentCol].setVisible(false);
			currentRow = row;
			rowLabels[currentRow].setForeground(Color.RED);
			rowLabels[currentRow].setVisible(true);
			isRowsTurn = true;
			cells[row][col].select();
			if (gameOver())
				add(new JLabel(rowP.getWinner(colP)));
			return true;
		}
		else
			return false;
	}
	/*
	 * Isolated verion of make move for expand function that eliminates certain aspects of the board GUI so that this works effectively
	 */
	public boolean makeIsolatedMove(int row, int col)
	// Execute move made by player
	// row, col - coordinates of selected square
	// val - number in the square
	// returns true if move is legal, false otherwise
	{
		if (isRowsTurn && row==currentRow && (!cells[row][col].isSelected())) // if row's turn and square is in current row
		{
			rowP.addScore(cells[row][col].getValue()); // update player's score
			currentCol = col; // update column to that of selected square
			isRowsTurn = false; // switch to column player's turn
			cells[row][col].select(); // select given square
			return true;
		}
		else if ((!isRowsTurn) && col==currentCol && (!cells[row][col].isSelected())) // do analogous things for columns
		{
			colP.addScore(cells[row][col].getValue());
			
			currentRow = row;
			isRowsTurn = true;
			cells[row][col].select();
			return true;
		}
		else
			return false;
	}
	//determines if a move can be made at all
	public boolean canMakeMove(int row, int col) {
		return (isRowsTurn && row==currentRow) || (!isRowsTurn && col==currentCol);
	}
	public boolean gameOver()
	// determine if game is over (current player does not have an available square)
	// returns true if game is over, false otherwise
	{
		if (isRowsTurn) // row turn
		{
			for (int i=0; i < cells.length; i++) // search current row
				if (cells[currentRow][i].isSelected() == false) // if some square can be picked
					return false; // game is not over
			return true; // all squares picked - game is over
		}
		else // similar for columns
		{
			for (int j=0; j < cells.length; j++)
				if (cells[j][currentCol].isSelected() == false)
					return false;
			return true;
		}
	}
	public void setMessage(String mesg)
	// change the message label
	// mesg - String to display
	{
		messageLabel.setText(mesg);
	}
	
	public Cell[][] getCells() {
		return this.cells;
	}
	//Gets the max value in a particular row
	public int maxValueInRow(int n) {
		int value = 0;
		for (int i = 0; i < this.cells.length; i++) {
			if (!this.cells[n][i].isSelected()) {
				value = Math.max(value, this.cells[n][i].getValue());
			}
		}
		return value;
	}
	//Gets the max value in a particular column
	public int maxValueInCol(int n) {
		int value = 0;
		for (int i = 0; i < this.cells.length; i++) {
			if (!this.cells[i][n].isSelected()) {
				value = Math.max(value, this.cells[i][n].getValue());
			}
		}
		return value;
	}
	//This gets the minimum value in a row
	public int minValueInRow(int n) {
		int value = 0;
		for (int i = 0; i < this.cells.length; i++) {
			if (!this.cells[n][i].isSelected()) {
				value = Math.min(value, this.cells[n][i].getValue());
			}
		}
		return value;
	}
	//This gets the location of a maximum in a column
	public int maxLocInCol(int n) {
		int value = Integer.MIN_VALUE;
		int loc = 0;
		for (int i = 0; i < this.cells.length; i++) {
			if (!this.cells[i][n].isSelected()) {
				if(value<this.cells[i][n].getValue()){
					loc = i;
					value = this.cells[i][n].getValue();
				}

			}
		}
		return loc;
	}
	//This gets the maxiumum location in a row.
	public int maxLocInRow(int n) {
		int value = Integer.MAX_VALUE;
		int loc = 0;
		for (int i = 0; i < this.cells.length; i++) {
			if (!this.cells[n][i].isSelected()) {
				if(value<this.cells[n][i].getValue()){
					loc = i;
					value = this.cells[n][i].getValue();
				}

			}
		}
		return loc;
	}
	//This gets the minimum value in a column
	public int minValueInCol(int n) {
		int value = 0;
		for (int i = 0; i < this.cells.length; i++) {
			if (!this.cells[i][n].isSelected()) {
				value = Math.min(value, this.cells[i][n].getValue());
			}
		}
		return value;
	}
	//This clones every aspect of a board
	public Board clone() {
		Player rp = rowP.clone();
		Player cp = colP.clone();
		Cell[][] c = new Cell[cells.length][cells.length];
		for(int i = 0; i<cells.length;i++){
			for(int j = 0; j<cells.length;j++){
				c[i][j] = cells[i][j].clone();
			}
		}
		Board b = new Board(rp, cp, isRowsTurn, currentRow, currentCol, c);
		for(int i=0; i<cells.length; i++) {
	        for(int j=0; j<cells[i].length; j++) {
	            c[i][j].setBoard(b);
	        }
	    }
		rp.setBoard(b);
		cp.setBoard(b);
		return b;
	}
}