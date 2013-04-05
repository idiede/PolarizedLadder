package polarizedladder;

public class Main {
	
	
	/**
	 * main method executes polarized ladder game
	 * @param args n/a
	 */
	
	
public static void main(String[] args) {
		
		PLGame game = new PLGame();
        Board board = new Board();
		WinPatternStrategy detectWin= new WinPatternStrategy(board);
		int gameType = game.displayMainMenu();
	    Player[] players = game.setGameType(gameType, board,detectWin);
		game.startGame(game,board, players, detectWin, gameType);
		
		}
}
