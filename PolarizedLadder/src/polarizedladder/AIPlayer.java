package polarizedladder;

import java.awt.Point;
import java.util.Iterator;

import tree.Tree;
import tree.Node;

public class AIPlayer extends Player{

	private char AIPlayerToken;
	private String AIPlayerString;
	
	AIPlayer aip;
	Player p;

	public AIPlayer(){
		super();

	}
	
	public AIPlayer(String playerName, char playerToken, int discs, Board board) {

		super.playerName  = playerName;
		super.playerToken = playerToken;
		super.discs 	  = discs;
		this.board        = board;
	
		AIPlayerToken 	  = playerToken;
		AIPlayerString 	  = String.valueOf(AIPlayerToken);
		
		System.out.println("AIPlayer " + playerName);
	}
		
	public Point doAIPlayerTurn(AIPlayer ai, Player p, SearchLists searchList){
		Point discCoordinates;
	
		this.aip  = ai;
		this.p 	  = p;
		
		try
		{
			discCoordinates = aip.move(this.board, searchList);
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

	private Point move(Board board, SearchLists searchList)
	{
		// local variables
		int defaultTreeDepth   = 3;
		
	    // create new tree with board
        Tree<Board> searchTree = new Tree<Board>(board);
        Node<Board> n 	       = new Node<Board>();
       
        searchTree.setRoot(n);
        n.setData(board);
              
        // generate all potential next moves
        LadderPatternStrategy heuristics = new LadderPatternStrategy();
        Iterator<Point> openPoints       = searchList.it;									// TODO: BUG. Hash table needs to be new across search spaces
                                                                                            // and shared with sub-trees.
        
        while (openPoints.hasNext())	
        {	
        	// prepare new board
        	Board newBoard = new Board();
        	newBoard.setState(board.cloneArray());
        	
        	// generate next move
        	Point nextPoint 	  = openPoints.next();       	
        	Position nextPosition = new Position(nextPoint, AIPlayerString);	// get next move	// TODO: depends on who is min/max (AIPlayerString)
        	newBoard.setObjectPosition(nextPosition);
        	
        	// calculate next move heuristics (at leaves only)
        	System.out.println(heuristics.calculate((Player) aip, this.p, newBoard));	// TODO: determine where to store heuristic

        	// add next move child node to tree 
        	Node<Board> nextChild = new Node<Board>();
        	nextChild.setData(newBoard);
        	n.addChild(nextChild);
        }
        
        return new Point(1, 1);	// TODO: invalid move should not be returned. only valid moves should be returned.
	}
}
