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
	
	private int[] ladderDiscWeights = {0, 1, 11, 111, 1111, 11111};		// weights for 0, 1, 2, 3, 4, or 5 ladder discs
	
	public int calculate(Player p, Player o, Board b)
	{		
		int numPossLadders	 = 0;	// heuristic: player possible ladders - opponent possible ladders
		int ladderDiscs		 = 0;	// heuristic: weighted sum of ladder discs
				
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
					numPossLadders += detectPossibleLadders( playerToken, board, new Point(jFrom, i) );
					ladderDiscs	   += numberOfLadderDiscs( playerToken, board, new Point(jFrom, i) );
				}
				else if ( (board[i][jFrom]).equalsIgnoreCase(opponentToken) )
				{
					// subtract opponent's disc potential ladders
					numPossLadders -= detectPossibleLadders( opponentToken, board, new Point(jFrom, i) );
					ladderDiscs    -= numberOfLadderDiscs( opponentToken, board, new Point(jFrom, i) );
				}
			}
		}
		
		return (numPossLadders + ladderDiscs);
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
				
				// TODO: is ladder blocked? This requires finding ladder pattern midpoint.
				
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
	
	private int numberOfLadderDiscs(String token, String[][] board, Point boardPoint)
	{
		int numOfLadderDiscs = 0;
		
		for (int row = 0; row < ladderPatterns.length; row++)			// calculate non-blocked ladders discs...
		{		
			Boolean currLadderBlocked = false;
			int numOfLadDiscsDetected = 0;
			
			for (int col = 0; col < ladderPatterns[0].length; col++)	// ...for each possible ladder pattern	
			{
				int xPos = boardPoint.x + ladderPatterns[row][col].x;
				int yPos = boardPoint.y + ladderPatterns[row][col].y;
				
				// TODO: is ladder blocked? This requires finding ladder pattern midpoint.
				
				try
				{
					if ( (board[yPos][xPos].equalsIgnoreCase(token)) || 
						(board[yPos][xPos].equalsIgnoreCase("_")) )
					{
						// ladder not blocked
						if ( (board[yPos][xPos].equalsIgnoreCase(token)) )
						{
							// detected disc, record detection.
							numOfLadDiscsDetected++;
						}
					}
					else
					{
						// ladder blocked
						currLadderBlocked 		= true;
						numOfLadDiscsDetected 	= 0;	// do not count blocked ladders 
						break;
					}
				}
				catch (Exception e)
				{
					// array out of bounds position, ladder blocked
					currLadderBlocked 		= true;
					numOfLadDiscsDetected 	= 0;		// do not count blocked ladders
					break;
				}
			}
			
			numOfLadderDiscs = (currLadderBlocked == false) ? 
					numOfLadderDiscs + ladderDiscWeights[numOfLadDiscsDetected] : 
						numOfLadderDiscs;
		}
		
		return numOfLadderDiscs;
	}
	
	public boolean isLadderBlocked(String token, String[][] board, Point boardPoint)
	{
		// check left ladder blocks
		Point p1 = new Point(boardPoint.x - 1, boardPoint.y - 1);	// lower left block
		Point p2 = new Point(boardPoint.x + 1, boardPoint.y + 1);	// upper right block
		
		// if board points p1 and p2 are blocked...
		if ( !(board[p1.y][p1.x].equalsIgnoreCase(token)) &&
				!(board[p1.y][p1.x].equalsIgnoreCase("_")) &&
				!(board[p2.y][p2.x].equalsIgnoreCase(token)) &&
				!(board[p2.y][p2.x].equalsIgnoreCase("_")) )
		{
			// win blocked
			System.out.println("Left Win Blocked!");
			return true;
		}
		
		// check right ladder blocks
		Point p3 = new Point(boardPoint.x - 1, boardPoint.y + 1);	// upper left block
		Point p4 = new Point(boardPoint.x + 1, boardPoint.y - 1);	// lower right block
		
		// if board points p3 and p4 are blocked...
		if ( !(board[p3.y][p3.x].equalsIgnoreCase(token)) &&
				!(board[p3.y][p3.x].equalsIgnoreCase("_")) &&
				!(board[p4.y][p4.x].equalsIgnoreCase(token)) &&
				!(board[p4.y][p4.x].equalsIgnoreCase("_")) )
		{
			// win blocked
			System.out.println("Right Win Blocked!");
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) 
	{
		/*
		// Heuristics Test Case 1 
		Board boardA = new Board();			// state a
		
		boardA.setPosition(2, 8, "*");		// player positions
		boardA.setPosition(3, 8, "*");
		boardA.setPosition(3, 9, "*");
		boardA.setPosition(4, 9, "*");
		
		boardA.setPosition(3, 6, "o");		// opponent positions
		boardA.setPosition(3, 7, "o");
		boardA.setPosition(4, 7, "o");
		boardA.setPosition(4, 8, "o");
		
		boardA.printBoard();
		
		GameHeuristics hA = new GameHeuristics();
		Player pA 		 = new Player("Player 1", '*', 22, boardA);
		Player oA 		 = new Player("Player 1", 'o', 22, boardA);
		int scoreA 		 = hA.calculate(pA, oA, boardA);
		System.out.printf("Board A Heuristics Score = %d\n", scoreA);
		
		Board boardB = new Board();			// state b
				
		boardB.setPosition(2, 8, "*");		// player positions
		boardB.setPosition(3, 8, "*");
		boardB.setPosition(3, 9, "*");
		boardB.setPosition(5, 8, "*");
		
		boardB.setPosition(3, 6, "o");		// opponent positions
		boardB.setPosition(3, 7, "o");
		boardB.setPosition(4, 7, "o");
		boardB.setPosition(4, 8, "o");
		
		boardB.printBoard();
		
		GameHeuristics hB = new GameHeuristics();
		Player pB 		  = new Player("Player 1", '*', 22, boardB);
		Player oB 		  = new Player("Player 1", 'o', 22, boardB);
		int scoreB		  = hB.calculate(pB, oB, boardB);
		
		System.out.printf("Board B Heuristics Score = %d\n", scoreB);
		*/
	}
}
