import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;


public interface Game 
{
	ArrayList<Move> getAllMoves();
	
	double getValue(boolean isWhite);
	
	ArrayList<Move> gameOver();
	
	void makeMove(Move m);
	
	void undoMove();

//	void printTurn();
}
