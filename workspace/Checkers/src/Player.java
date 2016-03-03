
import java.util.Random;
import java.util.ArrayList;


public class Player 
{
	public void printMoves(ArrayList<Move> moves)
	{
		for(Move m: moves)
		{
			System.out.println(m);
		}
		System.out.println("-------------");
	}
	
	public Move firstOption(Board board)
	{
		ArrayList<Move> moves = board.getAllMoves();
		return moves.get(0);
	}

	public Move alphaBeta(Board board, int depth, boolean randomOn,boolean isWhite)
	{
		ArrayList<Move> moves = board.getAllMoves();
		Move best = new Move( new Position(-1,-1),new Position(-1,-1));

		double a = Double.NEGATIVE_INFINITY;
		for(Move m: moves)
		{
			board.makeMove(m);
			double value = alphaBetaMin(board, depth-1, a, Double.POSITIVE_INFINITY,isWhite);
			board.undoMove();
			
			if(a < value)
			{
				a = value;
				best = m;
			}
		}
		if(randomOn)
		{
			if(Math.random()*100 < 5)//random exploring
			{
				//System.out.println("chose a random move");
				Random r = new Random();
				int num = r.nextInt(moves.size());
				
				best = moves.get(num);
				
			}
		}
		
		return best;
	}

	public double alphaBetaMin(Board board, int depth,double a, double b,boolean isWhite)
	{	
		ArrayList<Move> moves = board.gameOver();

		if(depth <= 0 || moves == null)
		{
			return board.getValue(isWhite);
		}

		double value = Double.POSITIVE_INFINITY;
		for(Move m: moves)
		{
			board.makeMove(m);
			value = Math.min(value, alphaBetaMax(board, depth-1, a, b,isWhite));
			b = Math.max(b, value);
			board.undoMove();

			if(b <= a)
			{
				break; //prune
			}
		}
		return value;

	}

	public double alphaBetaMax(Board board, int depth,double a, double b,boolean isWhite)
	{	
		ArrayList<Move> moves = board.gameOver();

		if(depth <= 0 || moves == null)
		{
			return board.getValue(isWhite);
		}

		double value = Double.NEGATIVE_INFINITY;
		for(Move m: moves)
		{
			board.makeMove(m);
			value = Math.max(value, alphaBetaMin(board, depth-1, a, b,isWhite));
			a= Math.max(a, value);
			board.undoMove();

			if(b <= a)
			{
				break;//prune
			}

		}
		return value;
	}



}


