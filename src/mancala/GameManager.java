package mancala;


public class GameManager{
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
	private static boolean counterclockwise;
	private static MoveManager mm;
	private static GameBoard board;
	public static Rules rules;

	public static void setup(GameEnum gameEnum, GameBoard b)
	{
		rules=new Rules(name);
		boardType = gameEnum.getBoardType();
		numOfColumns = gameEnum.getColumns();
		numOfRows = gameEnum.getNumOfRows();
		name = gameEnum.getName();

		if(gameEnum.getBoardType() == BoardTypes.TWO_ROW_WITH_ENDS)
			hasEndBins = true;
		else
			hasEndBins = false;

		originCountry = gameEnum.getOriginCountry();
		numOfHoles = numOfColumns * numOfRows;
		initialSeedsPerBin = gameEnum.getInitialSeedsPerBin();
		counterclockwise=rules.getCounterclockwise();
		boardArray = new int[numOfHoles];
		board=b;
		setupBoardArray();
		printBoard();
		switch(name.toLowerCase()){
		case "wari":
			mm=new MoveManager(numOfColumns,numOfRows);
			break;
		case "oware 1":
			mm=new MoveManager(numOfColumns,numOfRows);
			break;
		case "vai lung thlan":
			mm=new VaiLungThlanMoveManager(numOfColumns,numOfRows);
			break;
		case "songo":
			mm=new SongoMoveManager(numOfColumns,numOfRows);
			break;
		case "adji boto":
			mm=new AdjiBotoMoveManager(numOfColumns,numOfRows);
			break;
		case "qelat 1":
			mm=new QelatMoveManager(numOfColumns,numOfRows);
		}
	}

	public static void printBoard(){	
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

		int seedsLeftToMove = boardArray[index];
		mm.move(x, y, board);
		/**boardArray[index] = 0;

		int currRow = x;

		while(seedsLeftToMove >0)
		{		
			if(counterclockwise){
				if(currRow % 2 == 1)
				{
					if(index != 0)
					{
						index--;
						int newVal = boardArray[index];
						if((!name.equals("SONGO"))||(index!=newVal))
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
						if((!name.equals("SONGO"))||(index!=newVal))
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
			else if(counterclockwise==false){
				if(currRow % 2 == 1){
					if(index < numOfColumns -1){
						index++;
						int newVal = boardArray[index];
						if((!name.equals("SONGO"))||(index!=newVal))
							newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}
					else if(index >= numOfColumns -1){
						if(currRow != numOfRows){
							currRow++;
						}
						else
							currRow = 1;

						index = (numOfRows-1)*numOfColumns+numOfColumns;

					}

				}
				else if(currRow % 2 == 0)
				{
					if(index >(currRow-1)*numOfColumns)
					{
						index--;
						int newVal = boardArray[index];
						if((!name.equals("SONGO"))||(index!=newVal))
							newVal++;
						boardArray[index] = newVal;
						seedsLeftToMove--;
					}


					else{
						if(currRow != numOfRows)
							currRow++;
						else
						{
							currRow = 1;

						}

						index = -1;

					}
				}
			}
		}*/
		printBoard();
	}

	public static boolean validMove(int index)
	{
		if(boardArray[index] <= 0){
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
}
