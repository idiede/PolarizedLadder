/**
 * COMP6721
 * Polarized Ladder Game
 * March 2, 2013 
 */
package polarizedladder;

import java.awt.Point;
import java.util.Arrays;

//import java.awt.Point;

public class Board {

	final int BOARD_ROWS = 8;
	final int BOARD_COLS  = 14;
	//openList
	String[][] board;
	//added Sunday night
	int heuristic;
	
	Point lastBoardPosition;

	int boardEmptySpaces;
	
	public Board() {
		
	    lastBoardPosition = new Point();
		
		board = new String[BOARD_ROWS][BOARD_COLS];
		
		for (int i = 0; i < BOARD_ROWS; i++) {
			
			for (int j = 0; j < BOARD_COLS; j++) {
				
				if ( (j == 0) && (i == 0) ) {
					board[i][j] = " ";
				}
				else if ( (i == 0) && (j >= 1) ) {
					
					// add x axis legend
					board[i][j] = Integer.toString(j);
					board[i][j] = Character.toString(Character.forDigit(j + 9, Character.MAX_RADIX)).toUpperCase();
					
				} else if ( (j == 0) && (i >= 1) )  {
				
					// add y axis legend
					board[i][j] = Integer.toString(i);
				
				} else if ( (i >= 1) && (j >= 1) ) {
					
					// initialize board space
					board[i][j] = " ";
				}
				
				resetBoard();
			}
		}
	}
	

	public void resetBoard() {
		
		boardEmptySpaces = 49;
		
		for (int i = BOARD_ROWS - 1; i >= 1 ; i--) {
			
			int jFrom   = i;
			int jTo 	= BOARD_COLS - i;
			
			for ( ; jFrom <= jTo; jFrom++) {
				
				board[i][jFrom] = "_";
			}
		}
	}
	
	public void printBoard()
	{
		for (int i = BOARD_ROWS - 1; i >= 0; i--) {
			
			for (int j = 0; j < BOARD_COLS; j++) {
				
				System.out.print( board[i][j] );
				System.out.print("	");
			}
			
			System.out.println();
		}
	}
	
	private boolean isLegalPosition(int i, int j) {
	
		try
		{
			if ( board[j][i].equalsIgnoreCase(" ") ) {
				
				// illegal position
				return false;
				
			} else if ( !board[j][i].equalsIgnoreCase("_") ) {
				
				// illegal position
				return false;
			
			} else {
				
				// legal position
				return true;
			}
		}
		catch (ArrayIndexOutOfBoundsException aiob)
		{
			return false;
		}
	}
	
	public boolean setPosition(int i, int j, String mark) {
		
		if (isLegalPosition (i, j)) {
			
			lastBoardPosition = new Point(i, j);
			
			board[j][i] = mark;
			boardEmptySpaces--;
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public boolean isBoardFull() {
		
		if (boardEmptySpaces == 0) {
			
			return true;
		
		} else {
			
			return false;
		}
	}
	
	public String[][] getState()
	{	
		return board.clone();
	}
	
	public void setState(String[][] board)
	{
		this.board = board;
	}
	
	public String[][] cloneArray() {
	    int length = board.length;
	    String[][] target = new String[length][board[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(board[i], 0, target[i], 0, board[i].length);
	    }
	    return target;
	}
	
	public boolean setObjectPosition(Position p){
		
		return setPosition(p.getI(),p.getJ(), p.getMark());
	}
	
	public int getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	
	public Point getLastBoardPosition() {
		return lastBoardPosition;
	}

	public void setLastBoardPosition(Point lastBoardPosition) {
		this.lastBoardPosition = lastBoardPosition;
	}
	
	public int getBoardEmptySpaces() {
		return boardEmptySpaces;
	}

	public void setBoardEmptySpaces(int boardEmptySpaces) {
		this.boardEmptySpaces = boardEmptySpaces;
	}

	
	/*
	public static void main(String[] args) 
	{
	
		Board myBoard = new Board();
		System.out.println(myBoard.setPosition(5, 5, "x"));
		myBoard.printBoard();
		
		Board newBoard = new Board(); 
		newBoard.setState(myBoard.cloneArray());
		newBoard.printBoard();
	}
	*/
}
