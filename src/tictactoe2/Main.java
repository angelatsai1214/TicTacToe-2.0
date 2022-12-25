package termProjectTicTacToe;

public class Main {

	public static void main(String[] args) {
		
		//ask for number of players
		Player.requestPlayers();
		int players = Player.getPlayer();
		
		//ask for number of consecutive pieces required to win
		GameFunctionalities.requestPieces(players);
		
		//create gameboard object
		Gameboard gameboard = new Gameboard(players);
		
		//run the game
		GameFunctionalities.runTicTacToeGame(gameboard);

	}

}
