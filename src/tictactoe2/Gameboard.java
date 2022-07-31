package termProjectTicTacToe;

public class Gameboard {

	private int dimension;
	private char [][] gameboard;
	
	/**
	 * Constructs a gameboard object.
	 * @param numPlayer
	 * @param winPieces
	 */
	public Gameboard( int numPlayer )
	{
		dimension = numPlayer+1;
		
		//initialize gameboard with correct dimension
		int modSize = dimension*2+2;
		gameboard = new char[modSize][modSize];
		
		//Ascii value for titling rows and columns from 0 to numPlayer
	    int index1 = 48;
	    int index2 = 48;
		
	    //fill gameboard array with appropriate characters
		for( int i = 0; i < modSize; i++ )
		{
			for( int j = 0; j < modSize; j++ )
			{
				if( i % 2 == 0 )
				{
					//number the gameboard rows
					if( j == 0 && i != 0 )
			        {
			            if( i != 22 ) //22 is the i value for 10th row
			            	gameboard[i][j] = (char)index2;
			            else
			            	gameboard[i][j] = 'X';
			            index2++;
			            continue;
			        }
					
					if( j % 2 == 0 )
					{
						//number the gameboard columns
						if( i == 0 && j != 0 )
			            {
			              if( j != 22 ) //22 is the j value for 10th row
			            	  gameboard[i][j] = (char)index1;
			              else
			            	  gameboard[i][j] = 'X';
			              index1++;
			              continue;
			            }
					}
					else
					{
						gameboard[i][j] = '|';
					}
				}
				else
				{
					if( j % 2 == 0 || i == 1 )
					{
						gameboard[i][j] = '-';
					}
					else
					{
						gameboard[i][j] = '+';
					}
				}
			}
		}
		
		//get player name and characters
		Player.requestInfo(numPlayer);
		
	}
	
	//Accessor Method(s) Below
	
	/**
	 * Returns gameboard dimension.
	 * @return
	 */
	public int getDimension()
	{
		return dimension;
	}
	
	
	/**
	 * Returns the character at a specific location on the gameboard.
	 * @param row
	 * @param col
	 * @return
	 */
	public char getPiece( int row, int col )
	{
		return gameboard[2*row+2][2*col+2];
	}
	
	
	/**
	 * Checks if a position on gameboard is occpuied
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isOccupied( int row, int col )
	{
		//accounting in the space occupied by characters for gameboard borders ('+','-', etc.)
		if( gameboard[2*row+2][2*col+2] != '\0' ) //checks if the location has been initialized or not
			return true;
		else
			return false;
	}
	
	
	/**
	 * Prints out the gameboard.
	 */
	public void printGameboard()
	{
	    System.out.println();
		for( char[] row : gameboard)
	    {
	    	for( char col: row )
	    		System.out.print(col);
	    	System.out.println();
	    }
	}
	
	
	
	//Mutator Method(s) Below
	
	/**
	 * Places a piece on gameboard, returns true if the piece is successfully placed.
	 * @param row
	 * @param col
	 * @param piece
	 * @return
	 */
	public boolean placePiece( int row, int col, char piece)
	{
		
		//check if the location is occupied or not before placing the piece
		boolean occupied = isOccupied(row, col);
		boolean success = false;
		
		if( !occupied )
		{
			gameboard[2*row+2][2*col+2] = piece;
			success = true;
		}
		else
		{
			printGameboard();
			System.out.println("\n!!! Row " + row + " Column " + col + " is Already Occupied, Please Make New Selection !!!");
		}
		
		return success;
	}
	
	
	
}
