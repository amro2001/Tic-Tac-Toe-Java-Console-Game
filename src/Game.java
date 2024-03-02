/**
 * Represents the logic of the game in terms of detecting wins or draws.  Also
 * places new pieces for the human player or AI.
 */

public class Game {
    private Board board = new Board();
    private GameStatus status;
    private AI ai;
    private boolean playerIsX;
    

    /*
     * TBD: Create additional private members if useful.
     */

    /**
     * Construct a new Game according to the given parameters.
     */
    public Game(boolean playerIsX, boolean challenging) {
    	this.playerIsX=playerIsX;
    	if(challenging)
    	{
    		SmartAI smart= new SmartAI(!playerIsX);
    		ai=smart;
    	}
    	else
    	{
    		DumbAI dumb =new DumbAI(!playerIsX);
    		ai=dumb;
    	}
    }

    /**
     * Return a copy of the board's current contents.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the game's status.
     */
    private void updateStatus() {
    	//checking win 
    	
    	//checking rows first
    	char cell=' ';
    	boolean win= false;
    	for (int i=0;i<3;i++)
        {
    		if (board.get(i, 0)==board.get(i, 1)&&board.get(i, 1)==board.get(i, 2)&& board.get(i,0)!=' '){
    			win=true;
    			cell=board.get(i, 0);
    			break;
    		}
        }
    	if (win)
    	{
    		status=(cell=='X')? GameStatus.X_WON:GameStatus.O_WON;
    		return;
    	}
    	//checking columns
    	for (int i=0;i<3;i++)
        {
    		if (board.get(0, i)==board.get(1, i)&&board.get(1, i)==board.get(2, i)&& board.get(0,i)!=' '){
    			win=true;
    			cell=board.get(0, i);
    			break;
    		}
        }
    	if (win)
    	{
    		status=(cell=='X')? GameStatus.X_WON:GameStatus.O_WON;
    		return;
    	}
    	// checking diagonals
    	if (board.get(0, 0)==board.get(1, 1)&&board.get(1, 1)==board.get(2, 2)&& board.get(2,2)!=' ')
    	{
    		status=(board.get(0, 0)=='X')? GameStatus.X_WON:GameStatus.O_WON;
    		return;
    		
    	}
    	if (board.get(2, 0)==board.get(1, 1)&&board.get(1, 1)==board.get(0, 2)&& board.get(1,1)!=' ')
    	{
    		status=(board.get(1,1)== 'X')? GameStatus.X_WON:GameStatus.O_WON;
    		return;
    	}
    	//checking for empty spots
    	else if (!board.isFull())
    	{
    		status=GameStatus.IN_PROGRESS;
    		return;
    	}
    	else
    	{
    		status=GameStatus.DRAW;
        	return;
    		
    	}
    }
    public GameStatus getStatus() {
    	updateStatus();
    	return status;
    	

    }
    
    /**
     * Place a piece for the player on the board.
     * @param i i-coordinate of desired position.
     * @param j j-coordinate of desired position
     * @return true only if the coordinates of the desired position are in
     * range and the corresponding cell is empty.
     *
     * @precondition status == IN_PROGRESS
     *
     */
    public boolean placePlayerPiece(int i, int j) {
    	if ((i>2 || i<0 ||j >2|| j<0))
    	{
    		return false;
    	}
    	if( board.get(i, j)!= ' ' )
    	{
    		return false;
    	}
    	char piece=(playerIsX)? 'X':'O';
    	Move moveplayer=new Move(i, j ,piece);
    	board= new Board(board,moveplayer);
    	return true;
    }

    /**
     * @precondition status == IN_PROGRESS
     */
    public void aiPlacePiece() {
    	Move moveAI= ai.chooseMove(board);
    	board =new Board(board,moveAI);
    }
}
