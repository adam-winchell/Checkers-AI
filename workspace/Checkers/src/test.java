import java.util.ArrayList;
import java.util.HashMap;


public class test {

	public static void main(String[] args)
	{
//		HashMap<String, Piece> board = new HashMap<String, Piece>();
//
//		board.put("(1,1)", new Piece(true,new Position(1,1)));
//		
//		System.out.println(board.get("(1,1)"));
		
		Board board = new Board();
		board.insertPieceTesting(3,2,false);
		System.out.println(board.toString());
		
		Piece piece = board.getPieceTesting(2, 3);
		
		System.out.println("The piece's color is white: "+piece.getIsWhite());
		ArrayList<Move> moves = board.getMoves(piece);
		
		for(Move m: moves)
		{
			while(m.getNextMove() != null)
			{
				System.out.println(m.toString());
				m = m.getNextMove();
			}
		}
	}

}
