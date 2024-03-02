/**
 * ConsoleRunner:  Prompts the user to determine the parameters of the Game
 * constructor.  Creates a Game and manages the alternating calls to the
 * ‘place’ methods in Game.  Prompts the user for inputs and outputs the state
 * of the board to the console.
 */

import java.util.Scanner;

public class ConsoleRunner {

    /**
     * Should the human player be the X?  Note that X always
     * goes first.
     */
    private boolean playerIsX;

    private Game game;
    private boolean challenging;
    
    // Use to process text input from the user.
    private Scanner scanner = new Scanner(System.in);

    /*
     * TBD: Create additional private members if useful.
     */

    /**
     * Constructor
     */
    public ConsoleRunner() {    
        /*
         * TBD
         *
         * Use the 'next' method of Scanner and the 'matches' of the String
         * class to process user responses as strings.
         */
    	scanner = new Scanner(System.in);
    	System.out.println("Do you want to play as X (Y/N):");
    	char response1=scanner.next().charAt(0);
    	System.out.println("Do you want a challenge (Y/N):");
    	char response2=scanner.next().charAt(0);
    	playerIsX=(response1=='Y')? true:false;
    	challenging=(response2=='Y')? true:false;
    	game= new Game(playerIsX,challenging);
    	Board display= game.getBoard();
    	String dis=display.toString();
    	System.out.println(dis);
    	
 
    }

    /**
     * Enter the main control loop which returns only at the end of the game
     * when one party has won or there has been a draw.
     */
    public void mainLoop() {
        /*
         * TBD
         *
         * Use the 'nextInt' method of Scanner class to read user responses as
         * integers.
         *
         * There is enough work to do here that you may want to introduce
         * private methods (i.e. helper methods).
         */
    	scanner = new Scanner(System.in);
    	boolean valid;
    	Board display=game.getBoard();
    	boolean turn=playerIsX;
    	while (game.getStatus()== GameStatus.IN_PROGRESS)
    	{
    		if (turn)
    		{
    	    	System.out.println("Enter desired x-coordinate:");
    	    	int x=scanner.nextInt();
    	    	System.out.println("Enter desired y-coordinate::");
    	    	int y=scanner.nextInt();
    	    	valid=game.placePlayerPiece(y, x);
    	    	if(!valid)
    	    	{
    	    		System.out.println("Enter New Co-ordinates:");
    	    		continue;
    	    	}	
    	    	System.out.println("After your move:");
    	    	
    		}
    		else
    		{
    			game.aiPlacePiece();
    			System.out.println("After AI move:");
    		}
    		turn=!turn;
    		display=game.getBoard();
    		String dis=display.toString();
        	System.out.println(dis);
    	}
    	
    	scanner.close();
    	if (game.getStatus()==GameStatus.X_WON)
    	{
    		if (playerIsX)
    		{
    			System.out.println("You Won!");
    		}
    		else
    		{
    			System.out.println("AI Won!");
    		}
    	}
    	else if(game.getStatus()==GameStatus.O_WON)
    	{
    		if (playerIsX)
    		{
    			System.out.println("AI Won!");
    		}
    		else
    		{
    			System.out.println("You Won!");
    		}
    	}
    	else
    	{
    		System.out.println("Draw!");
    	}
 
    }
}
