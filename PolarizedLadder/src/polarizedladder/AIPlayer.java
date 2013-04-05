package polarizedladder;
import java.awt.Point;
import java.util.Scanner;
public class AIPlayer extends Player{

	
public AIPlayer(){
	super();
	
}
public AIPlayer(String playerName, char playerToken, int discs, Board board) {
	
	  
	  super.playerName=playerName;
	  super.discs = discs;
	  this.board = board;
	  System.out.println("AIPlayer " + playerName);
	}

private boolean move(){
	
	//take a move
	//add a comment
	
	return false;
	
}

public Point doAIPlayerTurn(Player ai){
    
	System.out.println("AIPlayer " + playerName);
	System.out.printf("Please enter your next move %s (ex. 1A):", ai.getPlayerName());
	
	// command line input scanner
	Scanner input = new Scanner(System.in);	
	
	try
	{
		String playerMove 	= input.nextLine();
		
		// parse player move
		String rowTemp 	= playerMove.substring(0,1);	
		int row 		= Integer.parseInt(rowTemp);
		char [] colTemp = playerMove.toUpperCase().toCharArray();
		int col 		= Character.getNumericValue( colTemp[1] ) - 9;

		Point discCoordinates = new Point(col, row);	
		return discCoordinates;
	}
	catch (ArrayIndexOutOfBoundsException aiob)
	{
		System.out.println("Invalid move.");
		return doPlayerTurn(ai);
	}
	catch(NumberFormatException nf)
	{
		System.out.println("Invalid move.");
		return doPlayerTurn(ai);
	}
	
}
}
