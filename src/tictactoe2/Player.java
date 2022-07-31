package termProjectTicTacToe;

import java.util.HashMap;
import java.util.Scanner;

public class Player {

	private static HashMap<String, Character> playerInfo;
	private static int players;
	
	
	//Accessor Method(s) Below
	
	/**
	 * Returns the number of players in the game.
	 * @return
	 */
	public static int getPlayer()
	{
		return players;
	}
	
	
	/** 
	 * Returns the player names and the character for their piece.
	 * @return
	 */
	public static HashMap<String, Character> getPlayerInfo()
	{
		return playerInfo;
	}
	

	
	//Mutator Method(s) Below
	
	/**
	 * Requests player names and character for their piece and store the info in the HashMap playerInfo.
	 * @param player
	 */
	public static void requestInfo( int player )
	{
		Scanner sc = new Scanner(System.in);
		
		String name = "";
		Character piece = ' ';
		playerInfo = new HashMap<>();
		
		//Ascii value for naming Player A-J
		int ascii = 65;
		
		System.out.println("\n--- Enter Players' Information ---");
		
		for( int i = 0; i < player; i++ )
		{
			//ask for player's name
			System.out.println( "Player " + (char)ascii + ",");
			System.out.print("\tEnter your name: ");
			name = sc.next();			
			
			//ask for a character, checks if character is taken or is the same as row/column titles
			do
			{
				System.out.print("\tEnter a character for your piece (except x or X): ");
				piece = sc.next().charAt(0);
			} while( playerInfo.containsValue(piece) || Character.isDigit(piece) || piece == 'X' || piece == 'x' );
			
			//place player info into hashmap
			playerInfo.put( name + " (" + "Player " + (char)ascii + ")", piece);
			ascii++;
		}
		
		System.out.println("***** Player Registration Completed! *****\n");
		
	}
	
	
	/**
	 * Requests the number of players from the player and initialize instance variable players.
	 */
	public static void requestPlayers()
	{
		Scanner sc = new Scanner(System.in);
		boolean isInt = false;

		//ask for number of players
		System.out.println("--- Enter the Number of Players for Your Game ---");
		
		while( !isInt )
		{
			System.out.print("Please enter a number between 3 and 10: ");

			while( sc.hasNextInt() ) //make sure user enters an integer
			{
				players = sc.nextInt();
				if( players >= 3 && players <= 10 ) //make sure the number is between 3 and 10
				{
					isInt = true;
					break;
				}	
				else
					System.out.print("Please enter a number between 3 and 10: ");

			}
			
			sc = new Scanner(System.in); //clear scanner buffer

		}
	}
	
		
}
