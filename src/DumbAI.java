/**
 * Realization of AI interface using simplistic random placement strategy.
 */

import java.util.Random;

public class DumbAI implements AI {
    
    private Random random = new Random();
    private char piece;

    /*
     * TBD: Create additional private members if useful.
     */
    
    /**
     * Construct a DumbAI.
     * 
     * @param aiIsX Indicates whether the AI player's piece is
     *              the 'X'.
     */
    public DumbAI(boolean aiIsX) {
    	piece=(aiIsX)? 'X':'O';
    }

    public Move chooseMove(Board board) {
    	int i=random.nextInt(0, 3);
    	int j=random.nextInt(0, 3);
    	while (board.get(i, j)!=' ')
    	{
    		i=random.nextInt(0, 3);
    		j=random.nextInt(0, 3);
    	}
    	Move moveAI= new Move(i,j,piece);
    	return moveAI;
    	
    }
}
