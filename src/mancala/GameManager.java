package mancala;


public class GameManager 
{
	private static int[] boardArray;
	private static GameManager instance;
	private static BoardTypes boardType;
	private static int numOfColumns;
	private static int numOfRows;
	private static String originCountry;
	private static String name;
	private static int numOfHoles;
	private static int initialSeedsPerBin;
	private static boolean hasEndBins;
	private static int turn;
	
	
	public static void setup(GameEnum gameEnum)
	{
		boardType = gameEnum.getBoardType();
		numOfColumns = gameEnum.getColumns();
		numOfRows = gameEnum.getNumOfRows();
		name = gameEnum.getName();
		turn = 1;
		
		if(gameEnum.getBoardType() == BoardTypes.TWO_ROW_WITH_ENDS)
			hasEndBins = true;
		else
			hasEndBins = false;
		
		originCountry = gameEnum.getOriginCountry();
		numOfHoles = numOfColumns * numOfRows;
		initialSeedsPerBin = gameEnum.getInitialSeedsPerBin();
		boardArray = new int[numOfHoles];
		setupBoardArray();
		printBoard();
	}
	
	public static void printBoard() 
	{	
		int rowsLeft = numOfRows;
		int i = 0;
		int loopCounter = 1;
		while(rowsLeft > 0)
		{
			System.out.print("{");
			
		while(i < (numOfColumns * loopCounter))
		{
			if(i != (numOfColumns * loopCounter) - 1)
			{
			System.out.print(boardArray[i] + ", ");
			}
			else
			{
				System.out.print(boardArray[i]);
			}
			i++;
			
		}
		loopCounter++;
		System.out.println("}");
		rowsLeft--;
		}
	}

	public static void setupBoardArray()
	{
		for(int x = 0 ; x < numOfHoles ; x++)
		{
			boardArray[x] = initialSeedsPerBin;
		}
	}
	
	public static void moveSeeds(int x, int y)
	{
		
		int[] boardArrayCopy = boardArray;
		int rowsToAdd = (numOfColumns) * (x-1);
		int index = 0;
			
				index = (rowsToAdd + y) - 1;
			
		int currRow = x;	
		int seedsLeftToMove = boardArray[index];
		
		if(turn == currRow){
			boardArray[index] = 0;
			while(seedsLeftToMove >0)
			{		
			
				if(currRow % 2 == 1)
				{
					if(index != 0)
					{
						index--;
						int newVal = boardArray[index];
						newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}
					
					else if(index == 0)
					{
						if(currRow != numOfRows)
							currRow++;
						else
							currRow = 1;
					
						index = numOfColumns - 1;
					
					}
				
				}
				else if(currRow % 2 == 0)
				{
					if(index < numOfHoles - 1)
					{
						index++;
						
						int newVal = boardArray[index];
						newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}
				
				
					else if(index >= numOfHoles - 1)
					{
						if(currRow != numOfRows)
							currRow++;
						else
						{
							currRow = 1;
						
						}
					
						index = (currRow * numOfColumns);;
					
					}
				}
			}
			setTurn();
			}
		else{
			System.out.println("Thats not your side of the board");
		}
		printBoard();
		
	}
	
	public static boolean validMove(int index)
	{
		if(boardArray[index] <= 0) 
		{
			return false;
		}
		else
		{
		return true;
		}
	}

	public static GameManager getInstance() {
		if(instance == null)
		{
			instance = new GameManager();
		}
		return instance;
	}
	
	/**
	 * switches the turn between player one and player 2
	 */
	public static void setTurn(){
		if (turn == 1){
			turn =2;
		}
		else{
			turn = 1;
		}
	}
}
