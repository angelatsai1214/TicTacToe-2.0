package termProjectTicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class GameLogic {

	/**
	 * Checks if the player wins the game through lower right to upper left diagonal.
	 * @param gameboard
	 * @param winPieces
	 * @param player
	 * @param piece
	 * @return
	 */
	public static boolean checkDiagonal1( Gameboard gameboard, int winPieces, String player, char piece )
	{
		ArrayList<Integer> colIndex = new ArrayList<>();
		int dimension = gameboard.getDimension();
		ArrayList<Integer> rowIndex = new ArrayList<>();
		ArrayList<int []> location = new ArrayList<>();
		
		int rend = (dimension-1);
		int cstart = 0;
		int count = 0;
		boolean flag = false;

		//stores the diagonal row indexes into rowIndex
		for( int ri = 1; ri <= dimension; ri++ )
		{
			for( int rj = (dimension-ri); rj <= rend; rj++ )
			{
				rowIndex.add(rj);
        
				
				//operations to include the other half of the diagonal after crossing the main diagonal
				if( rj == (dimension-1) )
	               count++;
	              
	  	        if( count == dimension )
	  	        	flag = true;

	  	        if( flag && rj==rend )
	  	        {
	  	            rend--;
	                ri = (dimension-1);
	  	        }
	  	        
			}
		}
		
		//stores the diagonal column indexes into colIndex
		for( int ci = 0; ci < dimension; ci++ )
		{
			for( int cj = cstart; cj <= ci; cj++ )
			{
				colIndex.add(cj);
		
				//operations to include the other half of the diagonal after crossing the main diagonal
		        if( cj == (dimension-1) )
		        {
		          cstart++;
		          ci = (dimension-2);
		        }
					
			}
				
		}
			
		//store all the indexes that equal to the current player's piece into location (traversing diagonally)
	    for( int i = 0; i < rowIndex.size(); i++ )
	    {
	    	int r = rowIndex.get(i);
	    	int c = colIndex.get(i);
	    	
	    	//check if the indexes is occupied first
	    	if( gameboard.isOccupied(r, c) )
			{
				if( gameboard.getPiece(r, c) ==  piece )
				{
					int [] arr = new int[2];
					
					arr[0] = r;
					arr[1] = c;
										
					location.add(arr);
				}
			}
	    	
	    }
			
		if( location.size() >= winPieces )
		{
			int diagonalCount1 = 1;
			
			for( int index = 0; index < location.size()-1; index++ )
			{
				//get individual arrays from the ArrayList
				int [] arr1 = location.get(index);
				int [] arr2 = location.get(index+1);
				
				//checks if both the column and row indexes are consecutive
				if( (arr2[0] -1 == arr1[0]) && (arr2[1] -1 == arr1[1]) )
				{
					diagonalCount1++;
					if( diagonalCount1 == winPieces )
					{
						gameboard.printGameboard();
						System.out.println("Congratulations, " + player + "! You won!");
						return true;
					}
				}
				else
				{
					diagonalCount1 = 1;
				}	

			}
			
		}
		
		return false;
	}
	
	
	
	/**
	 * Checks if the player wins the game through upper left to lower right diagonal.
	 * @param gameboard
	 * @param winPieces
	 * @param player
	 * @param piece
	 * @return
	 */
	public static boolean checkDiagonal2( Gameboard gameboard, int winPieces, String player, char piece )
	{
		int dimension = gameboard.getDimension();
		ArrayList<int []> location = new ArrayList<>();
		ArrayList<Integer> rowIndex = new ArrayList<>();
		ArrayList<Integer> colIndex = new ArrayList<>();
		
		//declare and initialize helper variables
		int rstart = 0;
		int cend = 0;
		int count = 0;
		boolean flag = false;

		//stores the diagonal row indexes into rowIndex
	    for( int ri = 0; ri < dimension; ri++ )
		{
			for( int rj = rstart; rj <= ri; rj++ )
			{
				rowIndex.add(rj);
				
				//operations to include the other half of the diagonal after crossing the main diagonal
		        if( rj == (dimension-1) )
		        {
		          rstart++;
		          ri = (dimension-2);
		        }
			}
		}
		
		//stores the diagonal column indexes into colIndex
	    for( int ci = 0; ci < dimension; ci++ )
		{
			for( int cj = ci; cj >= cend; cj-- )
			{
	            colIndex.add(cj);
	            
				//operations to include the other half of the diagonal after crossing the main diagonal
	            if( cj == 0 )
	               count++;
	              
	  	        if( count == dimension )
	  	            flag = true;
	              
	  	        if( flag && (cj==cend) )
	  	        {
	  	            cend++;
	                ci = (dimension-2);
	  	        }
	  	        
	        }
	    }
	    
		//store all the indexes that equal to the current player's piece into location (traversing diagonally)
	    for( int i = 0; i < rowIndex.size(); i++ )
	    {
	    	int r = rowIndex.get(i);
	    	int c = colIndex.get(i);

			//check if the indexes is occupied first
	    	if( gameboard.isOccupied(r, c) )
			{
				if( gameboard.getPiece(r, c) ==  piece )
				{
					int [] arr = new int[2];
					
					arr[0] = r; //row index to 0th element
					arr[1] = c; //column index to 1st element
										
					location.add(arr);
				}
			}
	    	
	    }
		
		if( location.size() >= winPieces )
		{
			int diagonalCount2 = 1;
			
			for( int index = 0; index < location.size()-1; index++ )
			{
				//get individual arrays from the ArrayList
				int [] arr1 = location.get(index);
				int [] arr2 = location.get(index+1);
				
				try
				{
					//checks if both the column and row indexes are consecutive
					if( (arr1[0] + 1 == arr2[0]) && (arr2[1] + 1 == arr1[1]) )
					{
						diagonalCount2++;
						if( diagonalCount2 == winPieces )
						{
							gameboard.printGameboard();
							System.out.println("Congratulations, " + player + "! You won!");
							return true;
						}
					}
					else
					{
						
						diagonalCount2 = 1;
					}
					
				}
				catch( Exception e ) //in case there's out of bound error
				{
					continue;
				}
			}
			
		}
		
		return false;
	}
	
	
	/**
	 * Checks if the player wins the game through horizontal pieces.
	 * @param gameboard
	 * @param winPieces
	 * @param player
	 * @param piece
	 * @return
	 */
	public static boolean checkHorizontal( Gameboard gameboard, int winPieces, String player, char piece )
	{
		int dimension = gameboard.getDimension();
		ArrayList<int []> location = new ArrayList<>();
				
		//Traverse through the array row by row and store the indexes of each cell/box
		//that equals the current player's piece into the ArrayList location
		for( int i = 0; i < dimension; i++ )
		{
			for( int j = 0; j < dimension; j++ )
			{
				//check if the indexes is occupied first
				if( gameboard.isOccupied(i, j) )
				{
					if( gameboard.getPiece(i, j) ==  piece )
					{
						int [] arr = new int[2];
						
						arr[0] = i; //row index to 0
						arr[1] = j; //column index to 1
											
						location.add(arr);
					}
				}
				
			}
		}
			
		//check if the size of location is greater or equal to winPieces first
		if( location.size() >= winPieces )
		{
			int horizontalCount = 1;
			
			for( int index = 1; index < location.size(); index++ )
			{
				//get individual arrays from the ArrayList
				int [] arr1 = location.get(index);
				int [] arr2 = location.get(index-1);
				
				//check if the column indexes are the same and the row indexes are consecutive
				if( (arr1[0] == arr2[0]) && (arr1[1]-arr2[1]) == 1 )
				{
					horizontalCount++;
					if( horizontalCount == winPieces )
					{
						gameboard.printGameboard();
						System.out.println("Congratulations, " + player + "! You won!");
						return true;
					}
				} 
				else
				{
					horizontalCount = 1;
				}
			}
		}
		
		return false;
	}
	
	
	/**
	 * Checks if there's a tie.
	 * @param gameboard
	 * @return
	 */
	public static boolean checkTie( Gameboard gameboard )
	{
		int dimension = gameboard.getDimension();
		
		//traverse through the gameboard
		for( int i = 0; i < dimension; i++ )
		{
			for( int j = 0; j < dimension; j++ )
			{
				//if any location is NOT occupied, return false
				if( !gameboard.isOccupied(i, j) )
					return false;
			}
		}
		
		gameboard.printGameboard();
		System.out.print("Sorry, no one won, it's a tie!");
		return true;
	}
	
	
	/**
	 * Checks if a player wins the game through vertical pieces.
	 * @param gameboard
	 * @param winPieces
	 * @param player
	 * @param piece
	 * @return
	 */
	public static boolean checkVertical( Gameboard gameboard, int winPieces, String player, char piece )
	{
		int dimension = gameboard.getDimension();
		ArrayList<int []> location = new ArrayList<>();
		
		//Traverse through the array column by column and store the indexes of each
		//cell/box that equals the current player's piece into the ArrayList location
		for( int j = 0; j < dimension; j++ )
		{
			for( int i = 0; i < dimension; i++ )
			{
				//check if the indexes is occupied first
				if( gameboard.isOccupied(i, j) )
				{
					if( gameboard.getPiece(i, j) ==  piece )
					{	
						int [] arr = new int[2];
						
						arr[0] = i; //row index to 0
						arr[1] = j; //column index to 1
											
						location.add(arr);
					}
				}
				
			}
		}
		
		if( location.size() >= winPieces )
		{
			int verticalCount = 1;
			
			for( int index = 1; index < location.size(); index++ )
			{
				//get individual arrays from the ArrayList
				int [] arr1 = location.get(index);
				int [] arr2 = location.get(index-1);
				
				//check if row index is the same and the column index is consecutive
				if( (arr1[1] == arr2[1]) && (arr1[0]-arr2[0]) == 1 )
				{
					verticalCount++;
					if( verticalCount == winPieces )
					{
						gameboard.printGameboard();
						System.out.println("Congratulations, " + player + "! You won!");
						return true;
					}
				} 
				else
				{
					verticalCount = 1;
				}
			}
		}
		
		return false;
			
	}
	


}
