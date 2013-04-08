package polarizedladder;

import java.awt.Point;
import java.util.Iterator;

import tree.Tree;
import tree.Node;

public class AIPlayer extends Player{

	private char AIPlayerToken;
	private String AIPlayerString;
	private String OpponentString;

	private LadderPatternStrategy heuristics;
	
	private AIPlayer aip;
	private Player p;

	public AIPlayer(){
		
		super();

		heuristics = new LadderPatternStrategy();
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
	
		this.aip  			= ai;
		this.p 	  			= p;
		this.OpponentString = String.valueOf(p.playerToken);
		
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
		int defaultTreeDepth = 3;
		
		 // create new tree with board
		Tree<Board> searchTree = createTree(board);
       
        // generate all potential next moves
        createStateSpace(searchTree.getRoot(), searchList, defaultTreeDepth, AIPlayerString);
         
        return new Point(1, 1);																			// TODO: invalid move should not be returned. only valid moves should be returned.
        																								//		  or should be ignored at client object (i.e. null)
	}
	
	public Tree<Board> createTree(Board board)
	{
		 // create new tree with board
        Tree<Board> searchTree = new Tree<Board>(board);
        Node<Board> root       = new Node<Board>();
       
        searchTree.setRoot(root);
        root.setData(board);
        
        return searchTree;
	}
	
	public void createStateSpace(Node<Board> parentNode, SearchLists searchList, int depthOfTree, String currPlayer)
	{
		// generate all potential next moves
        Iterator<Point> openPoints       = searchList.it;												// TODO: BUG? Hash table needs to be new across search spaces
         int i = 0;                                                                                			// and shared with sub-trees.
		if (depthOfTree == 0)
			System.out.println("Terminated");  //when you run through the code you will see that this call is never reached
	/*	{   recursively you don't need any specific depth so see line 119
			while ( openPoints.hasNext() )
			{
				// prepare new board
				Board newBoard = new Board();
				newBoard.setState(board.cloneArray());
				 System.out.println("i" + i++); 
				// generate next move
				Point nextPoint 	  = openPoints.next();       	
				Position nextPosition = new Position(nextPoint, currPlayer);							// get next move	
				newBoard.setObjectPosition(nextPosition);
				newBoard.heuristic 	  = heuristics.calculate((Player) aip, this.p, newBoard);         	// calculate next move heuristics (at leaves only)
				
				// add next move child node to tree 
				Node<Board> nextChild = new Node<Board>();
				nextChild.setData(newBoard);
				parentNode.addChild(nextChild);
			}
		}*/
		else//this should be the only recursive call.. 
		{
			while (openPoints.hasNext())
			{ 
				
				// prepare new board
				Board newBoard = new Board();
				newBoard.setState(board.cloneArray());
				 System.out.println("i" + i++);
				// generate next move
				Point nextPoint 	  = openPoints.next();       	
				Position nextPosition = new Position(nextPoint, currPlayer);							// get next move	
				newBoard.setObjectPosition(nextPosition);
        	
				// add next move child node to tree 
				Node<Board> nextChild = new Node<Board>();
				nextChild.setData(newBoard);
				parentNode.addChild(nextChild);//but there is no parent and can't be with a bottom up build
			}//we need a top down recursive call from d = 0 to n
			
        	for (Node<Board> childNode : parentNode.getChildren())
        	{
        		// populate child sub-tree
        		String nextToken = (currPlayer == AIPlayerString) ? AIPlayerString : OpponentString;
        		 System.out.println("i" + i++);  
        		createStateSpace(childNode, searchList, depthOfTree - 1, nextToken);
        	}
		}
	}
}
