package polarizedladder;
import java.awt.Point;
import java.util.Iterator;
import java.util.List;


import java.awt.Point;
import tree.Tree;
import tree.Node;



public class AIPlayer extends Player{


	private char AIPlayerToken;
	private String AIPlayerString;

	public AIPlayer(){
		super();

	}
	public AIPlayer(String playerName, char playerToken, int discs, Board board) {


		super.playerName=playerName;
		super.discs = discs;
		this.board = board;
		System.out.println("AIPlayer " + playerName);
		AIPlayerToken = playerToken;
		AIPlayerString = String.valueOf(AIPlayerToken);
		
	}


	public Point doAIPlayerTurn(AIPlayer ai, SearchLists searchList){
		Point discCoordinates;
	
		AIPlayer aip = ai;
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

	private Point move(Board board, SearchLists searchList){
		
		    Point po = new Point(2,1);
		    Position p = new Position(po,AIPlayerString);
		    Board cb;
		    //create new tree with board
            Tree<Board> searchTree = new Tree<Board>(board);
            Node<Board> n = new Node<Board>();
            //List<Node<Board>> child;
           
            searchTree.setRoot(n);
            n.setData(board);
            
            /*
            Board b = (Board)searchTree.getRoot().getData();
            System.out.println("From tree root");
            b.printBoard();
            
            Node<Board> child = new Node<Board>();
            child.setParent(n);
            b.setObjectPosition(p);
            child.setData(b);
            n.addChild(child);
            Node<Board> outChild = new Node<Board>();
            outChild = n.getChildren().remove(0);//remove(child);
            cb =outChild.getData();
            System.out.println("From child");
            cb.printBoard();
            */
            
            // generate all potential next moves
          //  int i = 0;
            Iterator<Point> openPoints = searchList.it;
            while (openPoints.hasNext())	
            {	
            	Point nextPoint 				= openPoints.next();


		        	Position nextPosition 		= new Position(nextPoint, AIPlayerString);			// TODO: depends on who is min/max (AIPlayerString)
		        	
		        	//i++;
		        	//System.out.println("#" + i + " Point:" + nextPoint);
		        	
		        	Board newBoard				= new Board();
		        	String[][] oldBoard			= board.cloneArray();
		        	newBoard.setState(oldBoard);
		        	
		        	//System.out.println("X: " + nextPosition.i + " Y: " + nextPosition.j);
		        	
		        	newBoard.setObjectPosition(nextPosition);
		        	
		        	newBoard.printBoard();
		        	
		        	Node<Board> nextChild = new Node<Board>();
		        	nextChild.setData(newBoard);
		        	n.addChild(nextChild);
            	
            }
	
            /*
            Iterator<Node<Board>> it2 = n.getChildren().iterator();
            while( it2.hasNext() )
            {
            	it2.next().getData().printBoard();
            }
            */
            
        return po;
	}
}
