package polarizedladder;

import java.util.Iterator;

import tree.Node;

//for this to work the Parent must keep a pointer?? to the child with minimum or max value??
public class MiniMaxAIPlayer extends AIPlayer{

	Board maxBoard;
	Board minBoard;

	public Board getMaxMove(Node<Board> n){
		
		int max =  0;
		int temp = 0;

		Iterator<Node<Board>> it = n.getChildren().iterator();
		maxBoard =it.next().getData(); 
		max = maxBoard.getHeuristic();

		while( it.hasNext() )
		{
            Board	boardTemp = it.next().getData();
			temp =  board.getHeuristic();
			if(temp <= max) maxBoard = boardTemp;
		}
		return maxBoard;
	}

	public Board getMinMove(Node<Board> n){

		int min =  0;
		int temp = 0;

		Iterator<Node<Board>> it = n.getChildren().iterator();
		minBoard =it.next().getData(); 
		min = maxBoard.getHeuristic();

		while( it.hasNext() )
		{
			Board	boardTemp = it.next().getData();
			temp =  board.getHeuristic();
			if(temp >= min) maxBoard = boardTemp;
		}
		return minBoard;

	}

}
