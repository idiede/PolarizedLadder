/**
 * COMP6721
 * Polarized Ladder Game
 * March 2, 2013 
 */
package polarizedladder;

import java.awt.Point;
import java.util.Scanner;

public class PLGame {

	
	
/*	public static void main(String[] args) {
		
		
        PLGame game = new PLGame();
        Board board		 = new Board();
		WinPatternStrategy detectWin= new WinPatternStrategy(board);
		int gameType = game.displayMainMenu();
	    Player[] players = game.setGameType(gameType, board, detectWin);
		game.startGame(board, players, detectWin);
		
		}*/
	public void startGame(PLGame game, Board board, Player [] players, WinPatternStrategy detectWin){
		
		game.startHumanGame(board, players, detectWin);
		
	}
	private void startHumanGame(Board board, Player [] players, WinPatternStrategy detectWin) {
		
		boolean gameOver 	= false;	
		int playerTurn 		= 1;
		
		while (!gameOver) {
	
			board.printBoard();				// display board state
			
			// TODO: add win condition check
			if (board.isBoardFull()) {		// check win condition or draw
				
				System.out.printf("The game ended in a draw!\n");
				gameOver = true;
			}
			
			if (playerTurn == 1) {			// player turn
				
				Point playerMove = doPlayerTurn(players[0]);
				
				if ( players[0].setDisc(playerMove.y, playerMove.x) == false) 
				{
					
					doPlayerTurn(players[0]);
				}
				
				if ( detectWin.detectLadder(players[0].getPlayerToken(), 
						players[1].getPlayerToken(), playerMove) )
				{
					board.printBoard();
					System.out.printf( "%s Wins the Game! \n", players[0].getPlayerName() );
					gameOver = true;
				}
				
				playerTurn = 2;
			
			} else {
			
				Point playerMove = doPlayerTurn(players[1]);
				
				if ( players[1].setDisc(playerMove.y, playerMove.x) == false) 
				{
					doPlayerTurn(players[1]);
				}
				
				if ( detectWin.detectLadder(players[1].getPlayerToken(), 
						players[0].getPlayerToken(), playerMove) )
				{
					board.printBoard();
					System.out.printf( "%s Wins the Game! \n", players[1].getPlayerName() );
					gameOver = true;
				}
				
				playerTurn = 1;
			}
		}
		
		System.out.printf("Game over!\n");
	}
	
	private static Point doPlayerTurn(Player player) {
		
		System.out.printf("Please enter your next move %s (ex. 1A):", player.getPlayerName());
		
		// command line input scanner
		Scanner input 		= new Scanner(System.in);	
		
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
			return doPlayerTurn(player);
		}
		catch(NumberFormatException nf)
		{
			System.out.println("Invalid move.");
			return doPlayerTurn(player);
		}
	}
	
	public int displayMainMenu() {
		
		System.out.println("Welcome to the Polarized Ladders Game!");			
		System.out.println("Please select game type:");
		System.out.println("(1) Human vs Human");
		System.out.println("(2) Human vs AI");
		System.out.println("(3) AI vs Human");
		System.out.println("(4) Quit!");
		
		Scanner input 	= new Scanner(System.in);	// command line input scanner
		return input.nextInt();
	}
	
	Player[] setGameType(int gameType, Board board, WinPatternStrategy detectWin){
		   
		Player[] players = new Player[2];
		    
		if (gameType == 1) {
				
				// instantiate human players
				players[0] = new Player("Player One", 'o', 22, board);
				players[1] = new Player("Player Two", Character.toChars(8226)[0], 22, board);
				}
			
		if(gameType == 2){
			
			// instantiate human player(1) and AIPlayer(2)
			players[0] = new Player("Player One", 'o', 22, board);
			players[1] = new AIPlayer("Player Two", Character.toChars(8226)[0], 22, board);
			}
		
		if(gameType == 3){
			
				// instantiate AIPlayer(1) human player(2) 
			players[0] = new AIPlayer("Player One", 'o', 22, board);
			players[1] = new Player("Player Two", Character.toChars(8226)[0], 22, board);
		}
		
		//if(type == 4){
        else if (gameType == 4) {
			
        	System.out.println("Quiting Game.");
        	System.exit(0);
			
		} else if (gameType > 4)  {
			
			System.out.println("Game type not availabe at this time.");
			System.exit(0);
		}
		
		return players;
		
	}
	
	
}
