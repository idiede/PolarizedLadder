package polarizedladder;

import java.awt.Point;

public class GameHeuristics 
{	
	private Point ladderPatterns[][] = 
		{ 			
			{new Point(0,0), new Point(1,0), new Point (1,1), new Point (2,1) , new Point (2,2)},			//  right ladder patterns
			{new Point(-1,0), new Point(0,0), new Point (0,1), new Point (1,1) , new Point (1,2)},
			{new Point (-1,-1), new Point (0,-1), new Point(0,0), new Point(1,0), new Point (1,1)},
			{new Point (-2,-1), new Point (-1,-1), new Point (-1,0), new Point(0,0), new Point(0,1)},
			{new Point (-2,-2), new Point (-1,-2), new Point (-1,-1), new Point(0,-1), new Point(0,0)},
			{new Point(0,0), new Point(-1,0), new Point (-1,1), new Point (-2,1) , new Point (-2,2)},		//	left ladder patterns
			{new Point(1,0), new Point(0,0), new Point (0,1), new Point (-1,1) , new Point (-1,2)},
			{new Point (1,-1), new Point (0,-1), new Point(0,0), new Point(-1,0), new Point (-1,1)},
			{new Point (2,-1), new Point (1,-1), new Point (1,0), new Point(0,0), new Point(0,1)},
			{new Point (2,-2),  new Point (1,-2), new Point (1,-1), new Point(0,-1), new Point(0,0)},
		};
	
	public int numberOfPossibleLadders(Player p, Player o, Board b)
	{		
		int heuristicScore	 = 0;							 
				
		String[][] board 	 = b.getState();			
		
		String playerToken   = p.getPlayerToken();	
		String opponentToken = o.getPlayerToken();

		// analyze every populated board point for potential ladders
		for (int i = b.BOARD_ROWS - 1; i >= 1 ; i--) 		 
		{	
			int jFrom   = i;
			int jTo 	= b.BOARD_COLS - i;
			
			for ( ; jFrom <= jTo; jFrom++) 					
			{
				if ( (board[i][jFrom]).equalsIgnoreCase(playerToken) )
				{
					// add player disc potential ladders
					heuristicScore += detectPossibleLadders( playerToken, board, new Point(jFrom, i) );
				}
				else if ( (board[i][jFrom]).equalsIgnoreCase(opponentToken) )
				{
					// subtract opponent's disc potential ladders
					heuristicScore -= detectPossibleLadders( opponentToken, board, new Point(jFrom, i) );
				}
			}
		}
		
		return heuristicScore;
	}

	private int detectPossibleLadders(String token, String[][] board, Point boardPoint)
	{
		int possibleLadders	= 0;
		
		// calculate potential ladders...
		for (int row = 0; row < ladderPatterns.length; row++)
		{		
			// ...for each possible ladder pattern
			Boolean currLadderBlocked = false;
			
			for (int col = 0; col < ladderPatterns[0].length; col++)	
			{
				int xPos = boardPoint.x + ladderPatterns[row][col].x;
				int yPos = boardPoint.y + ladderPatterns[row][col].y;
				
				try
				{
					if ( (board[yPos][xPos].equalsIgnoreCase(token)) || 
						(board[yPos][xPos].equalsIgnoreCase("_")) )
					{
						// ladder not blocked
					}
					else
					{
						// ladder blocked
						currLadderBlocked = true;
						break;
					}
				}
				catch (Exception e)
				{
					// array out of bounds position, ladder blocked
					currLadderBlocked = true;
					break;
				}
			}
			
			possibleLadders = (currLadderBlocked == true) ? possibleLadders : possibleLadders + 1;
		}
		
		return possibleLadders;
	}
	
	/*
	public static void main(String[] args) 
	{
		Board myBoard = new Board();
		
		// heuristic 1 test case
		myBoard.setPosition(3, 7, "*");		// player positions
		myBoard.setPosition(4, 7, "o");		// opponent positions
		
		myBoard.printBoard();
		
		GameHeuristics h = new GameHeuristics();
		Player p = new Player("Player 1", '*', 22, myBoard);
		Player o = new Player("Player 1", 'o', 22, myBoard);
		
		System.out.printf("Heuristic 1 Total: %d\n", h.numberOfPossibleLadders(p, o, myBoard) );
	}
	*/
}
