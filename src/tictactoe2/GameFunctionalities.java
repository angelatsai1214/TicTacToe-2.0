package termProjectTicTacToe;

import java.util.Scanner;

import java.util.HashMap;
import java.util.InputMismatchException;

public class GameFunctionalities {

	private static int winPieces;
	
	/**
	 * Returns the "in a row" pieces required to win as defined by players.
	 * @return
	 */
	public static int getWinPieces()
	{
		return winPieces;
	}
	
	/**
	 * Requests a row or column index location from the player.
	 * @param gameboard
	 * @param s
	 * @param dimension
	 * @return
	 */
	public static int requestPieceLocation( Gameboard gameboard, String s, int dimension )
	{
		System.out.println();
		
		Character input = ' ';
		int num = 0;
		boolean correct = false;
		Scanner sc = new Scanner(System.in);
		
		//s will be replaced by either 'Row' or 'Column'
		System.out.println("--- Enter the " + s + " You Want to Place Your Piece ---");

		do
		{
			
			//display appropriate prompting message
			if( dimension == 11 )
				System.out.print("Please enter a number between 0 and " + dimension + " or X for 10th " + s + ": ");
			else
				System.out.print("Please enter a number between 0 and " + dimension + ": ");
			
			try
			{
				//gets the first character of the player's input
				input = sc.next().charAt(0);
				
				if( Character.isDigit(input) )
				{
					int tempNum = Character.getNumericValue(input);
					
					//check if the digit is between 0 and player count
					if( tempNum >= 0 && tempNum < dimension )
					{
						correct = true;
						num = tempNum;
						System.out.println(num + " is taken as input");
					}
					else
					{
						throw new IndexOutOfBoundsException();
					}
				}
				else if( dimension == 11 && (input == 'X' || input == 'x') ) //check if the player is prompting 10th row/column
				{
					correct = true;
					num = 10;
				}
				else
				{
					throw new InputMismatchException();
				}
				
			} 
			catch( Exception e )
			{
				System.out.println("Input invalid!");
				gameboard.printGameboard();
			}
			
			sc = new Scanner(System.in); //clear scanner buffer
			
		} while( !correct );
		
		return num;
		
	}
	
	/**
	 * Requests the number of consecutive pieces required to win the game and store it in winPieces.
	 * @param players
	 */
	public static void requestPieces( int players )
	{
		Scanner sc = new Scanner(System.in);
		boolean isInt = false;
		
		System.out.println("\n--- Enter the Number of Pieces in a Row to Win ---");
		
		while( !isInt ) 
		{
			System.out.print("Please enter a number between 3 and " + (players + 1) + ": ");
			
			while( sc.hasNextInt() ) //check if the input is a number
			{
				winPieces = sc.nextInt();
				if( winPieces >= 3 && winPieces <= players+1 ) //check if the input is within bound
				{
					isInt = true;
					break;
				}
				else
					System.out.print("Please enter a number between 3 and " + (players + 1) + ": ");
				
			}
			
			sc = new Scanner(System.in); //clear scanner buffer
		}
	}

	/**
	 * Iterate through each player and run the game.
	 * @param gameboard
	 */
	public static void runTicTacToeGame( Gameboard gameboard )
	{
		//create scanner object
		Scanner sc = new Scanner(System.in);
		
		//get dimension for gameboard
		int dimension = gameboard.getDimension();
		
		boolean hasWinner = false;
		boolean success = false;
		
		while( !hasWinner ) //repeat if there's no winner or no tie
		{
			//iterate through each entry in the hashmap
			for( HashMap.Entry<String, Character> play: Player.getPlayerInfo().entrySet() )
			{
				
				//prompt a player
				System.out.println();
				System.out.println("--------------------------");
				System.out.println(play.getKey() + " Your Turn");
				System.out.println("--------------------------");
				
				gameboard.printGameboard();
				
				do
				{
					//get row and column selection
					int row = GameFunctionalities.requestPieceLocation(gameboard, "Row", dimension);
					int col = GameFunctionalities.requestPieceLocation(gameboard, "Column", dimension);
					
					//place the piece
					success = gameboard.placePiece( row, col, play.getValue());
					
				} while( !success ); //repeat if piece was not successfully placed
				
				//check if the player has won through consecutive vertical pieces
				hasWinner = GameLogic.checkVertical( gameboard, getWinPieces(), play.getKey(), play.getValue() );

				if( hasWinner )
					break;
				
				//check if the player has won through consecutive horizontal pieces
				hasWinner = GameLogic.checkHorizontal( gameboard, getWinPieces(), play.getKey(), play.getValue() );

				if( hasWinner )
					break;
				
				//check if the player has won through consecutive diagonal (upper left to lower right) pieces
				hasWinner = GameLogic.checkDiagonal1( gameboard, getWinPieces(), play.getKey(), play.getValue() );

				if( hasWinner )
					break;
				
				//check if the player has won through consecutive diagonal (lower left to upper right) pieces
				hasWinner = GameLogic.checkDiagonal2( gameboard, getWinPieces(), play.getKey(), play.getValue() );

				if( hasWinner )
					break;
				
				//check if the game is a tie
				hasWinner = GameLogic.checkTie(gameboard);
				if( hasWinner )
					break;
					
			}			
			
		}
	}
		
	
}
