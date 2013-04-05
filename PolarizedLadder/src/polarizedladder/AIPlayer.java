package polarizedladder;

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
	
	return false;
	
}
}
