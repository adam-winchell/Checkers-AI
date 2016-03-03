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
		board.insertPieceTesting(3,4,false);
		
		Piece piece = board.getPieceTesting(2, 1);
		piece.setIsKing(true);
		
		board.deleteTesting(2,5);
		
		System.out.println(board.toString());
		
		System.out.println("The piece's color is white: "+piece.getIsWhite());
		ArrayList<Move> moves = board.getMoves(piece);
		
		
		for(Move m: moves)
		{
			System.out.println(m.toString());
			System.out.println("I jumped over: "+m.getPassedOverDuringJumpPosition());
			while(m.getNextMove() != null)
			{
				m=m.getNextMove();
				p(m.toString());
				System.out.println("I jumped over: "+m.getPassedOverDuringJumpPosition());
			}
			System.out.println("---------");
		}
		
		
		board.makeMove(moves.get(1));
		p(board.toString());
		board.undoMove();
		p(board.toString());
	}
	public static void p(String text)
	{
		System.out.println(text);
	}
	
	

}
