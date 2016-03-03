import java.util.ArrayList;
import java.util.HashMap;


public class Board 
{
	private HashMap<String, Piece> board;
	private int whiteCount;
	private int blackCount;
	private final int ENGLISH_DRAUGHT_PIECE_COUNT = 12;
	private final int ENGLISH_DRAUGHT_BOARD_SIZE = 8;
	private final int ENGLISH_DRAUGHT_NUMBER_OF_ROWS_FOR_PIECES = 3;
	private final int ENGLISH_DRAUGHT_WHITE_OFFSET = ENGLISH_DRAUGHT_BOARD_SIZE - ENGLISH_DRAUGHT_NUMBER_OF_ROWS_FOR_PIECES;
	private final boolean WHITE =true;
	private final int LEFT = -1;
	private final int RIGHT = 1;
	private final int UP = -1;
	private final int  DOWN= 1;
	
	public Piece getPieceTesting(int x,int y)
	{
		return board.get(makeCords(x,y));
	}
	
	public void insertPieceTesting(int x, int y, boolean isWhite)
	{
		board.put(makeCords(x,y), new Piece(isWhite,new Position(x,y)));
	}
	
	public void deleteTesting(int x, int y)
	{
		board.remove(makeCords(x,y));
	}
	
	public Board()
	{
		board = new HashMap<String, Piece>();
		insertBlackPieces(board);
		insertWhitePieces(board);
		whiteCount = ENGLISH_DRAUGHT_PIECE_COUNT;
		blackCount = ENGLISH_DRAUGHT_PIECE_COUNT;
	}
	
	private String makeCords(int x, int y)
	{
		return "("+x+","+y+")";
	}
	
	private void insertWhitePieces(HashMap<String, Piece> board)
	{
		for(int j=0;j<ENGLISH_DRAUGHT_NUMBER_OF_ROWS_FOR_PIECES;j++)
		{
			for(int i=0;i<ENGLISH_DRAUGHT_BOARD_SIZE;i++)
			{
			
				if(j%2 == 0 && i%2 != 0)
				{
					board.put(makeCords(j,i), new Piece(true,new Position(j,i)));//j is the row number so its the x value
				}
				if(j%2 != 0 && i%2 == 0)
				{
					board.put(makeCords(j,i), new Piece(true,new Position(j,i)));
				}
			}
		}
	}
	
	private void insertBlackPieces(HashMap<String, Piece> board)
	{
		for(int j=ENGLISH_DRAUGHT_WHITE_OFFSET;j<ENGLISH_DRAUGHT_BOARD_SIZE;j++)
		{
			for(int i=0;i<ENGLISH_DRAUGHT_BOARD_SIZE;i++)
			{
			
				if(j%2 != 0 && i%2 == 0)
				{
					board.put(makeCords(j,i), new Piece(false,new Position(j,i)));//j is the row number so its the x value
				}
				if(j%2 == 0 && i%2 != 0)
				{
					board.put(makeCords(j,i), new Piece(false,new Position(j,i)));
				}
			}
		}
	}
	
	public ArrayList<Move> getMoves(Piece piece)
	{
		ArrayList<Move> moves = null;
		
		if(piece.getIsKing())
		{
			moves = getKingMoves(piece);
		}
		else if (piece.getIsWhite())
		{
			moves = getWhiteMoves(piece);
		}
		else
		{
			moves = getBlackMoves(piece);
		}
		return moves;
	}
	
	private ArrayList<Move> getKingMoves(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,move(piece,DOWN,LEFT));
		combineLists(result,move(piece,DOWN,RIGHT));
		combineLists(result,move(piece,UP,LEFT));
		combineLists(result,move(piece,UP,RIGHT));

		return result;
	}
	
	private void combineLists(ArrayList<Move> mainList, ArrayList<Move> otherList)
	{
		for(Move m: otherList)
		{
			if(m != null)
			{
				System.out.println(m);
				mainList.add(m);
			}
		}
	}
	
	private ArrayList<Move> getWhiteMoves(Piece piece)
	{	
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,move(piece,DOWN,LEFT));
		combineLists(result,move(piece,DOWN,RIGHT));
		
		return result;
	}
	
	private ArrayList<Move> getBlackMoves(Piece piece)
	{		
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,move(piece,UP,LEFT));
		combineLists(result,move(piece,UP,RIGHT));
		
		return result;
	}
	
	private ArrayList<Move> getMovesJumping(Piece piece)
	{
		ArrayList<Move> moves = null;
		
		if(piece.getIsKing())
		{
			moves = getKingMovesJumping(piece);
		}
		else if (piece.getIsWhite())
		{
			moves = getWhiteMovesJumping(piece);
		}
		else
		{
			moves = getBlackMovesJumping(piece);
		}
		
		return moves;
	}
	
	private void p(String text)
	{
		System.out.println(text);
	}
	
	private ArrayList<Move> getKingMovesJumping(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,moveJumping(piece,DOWN,LEFT));
		combineLists(result,moveJumping(piece,DOWN,RIGHT));
		combineLists(result,moveJumping(piece,UP,LEFT));
		combineLists(result,moveJumping(piece,UP,RIGHT));
		
		return result;
	}
	
	private ArrayList<Move> getWhiteMovesJumping(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,moveJumping(piece,DOWN,LEFT));
		combineLists(result,moveJumping(piece,DOWN,RIGHT));
		
		return result;
	}
	
	private ArrayList<Move> getBlackMovesJumping(Piece piece)
	{
		ArrayList<Move> result = new ArrayList<Move>();
		
		combineLists(result,moveJumping(piece,UP,LEFT));
		combineLists(result,moveJumping(piece,UP,RIGHT));
		
		return result;
	}
	private ArrayList<Move> moveJumping(Piece piece, int rowDirectionMove, int columnDirectionMove)
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		Position position = piece.getPosition();
		Position destinationPosition = new Position(position.getX()+rowDirectionMove, position.getY()+columnDirectionMove);
		
		if(destinationPosition.isValid())
		{
			Piece destinationPiece = board.get(destinationPosition.getCords());
			
			if(destinationPiece != null && destinationPiece.getIsWhite() != piece.getIsWhite())
			{
				//we have the potential to jump
				Position jumpPosition = new Position(position.getX()+2*rowDirectionMove, position.getY()+2*columnDirectionMove);
				if(jumpPosition.isValid())
				{
					Piece jumpPiece = board.get(jumpPosition.getCords());
					if(jumpPiece == null)
					{
						//we can jump
						Move move = new Move(position,jumpPosition);
						
						//setting up a temporary piece below to use in the next set of calls
						Piece temp = new Piece(piece.getIsWhite(),jumpPosition,piece.getIsKing());
						
						addPieceTemporary(jumpPosition, temp); //to occupy the space so jumping bug is squashed

						ArrayList<Move> potentialJumps = getMovesJumping(temp);
						
						for(Move m: potentialJumps)
						{
							Move temporary = move;
							temporary.setNextMove(m);
							moves.add(temporary); 
						}
						if(potentialJumps.isEmpty())
						{
							moves.add(move);
						}
						
						removeTemporaryPiece(jumpPosition); //removed temporary piece
					}
				}
				
			}
		}
		
		return moves;
	}
	/*
	 * UP means a smaller value in the array,
	 * the x cordinate of the position is the row number
	 */
	
	private ArrayList<Move> move(Piece piece, int rowDirectionMove, int columnDirectionMove)
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		Position position = piece.getPosition();
		Position destinationPosition = new Position(position.getX()+rowDirectionMove, position.getY()+columnDirectionMove);
		
		if(destinationPosition.isValid())
		{
			Piece destinationPiece = board.get(destinationPosition.getCords());
			
			if(destinationPiece == null)
			{
				//piece can move here
				moves.add(new Move(position,destinationPosition));
			}
			else if(destinationPiece.getIsWhite() != piece.getIsWhite())
			{
				//we have the potential to jump
				Position jumpPosition = new Position(position.getX()+2*rowDirectionMove, position.getY()+2*columnDirectionMove);
				if(jumpPosition.isValid())
				{
					Piece jumpPiece = board.get(jumpPosition.getCords());
					if(jumpPiece == null)
					{
						//we can jump
						Move move = new Move(position,jumpPosition);
						
						//setting up a temporary piece below to use in the next set of calls
						Piece temp = new Piece(piece.getIsWhite(),jumpPosition,piece.getIsKing());
						
						addPieceTemporary(jumpPosition, temp); //to occupy the space so jumping bug is squashed
						
						ArrayList<Move> potentialJumps = getMovesJumping(temp);
						
						for(Move m: potentialJumps)
						{
							Move temporary = move;
							temporary.setNextMove(m);
							moves.add(temporary); 
						}
						if(potentialJumps.isEmpty())
						{
							moves.add(move);
						}
						
						removeTemporaryPiece(jumpPosition); //removed temporary piece
					}
				}
				
			}
		}
		
		return moves;
	}
	
	private void addPieceTemporary(Position pos, Piece piece)
	{
		board.put(makeCords(pos.getX(),pos.getY()), piece);
	}
	
	private void removeTemporaryPiece(Position pos)
	{
		board.remove(makeCords(pos.getX(),pos.getY()));
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		
		for(int j=0;j<ENGLISH_DRAUGHT_BOARD_SIZE;j++)
		{
			for(int i=0;i<ENGLISH_DRAUGHT_BOARD_SIZE;i++)
			{
				Piece temp = board.get(makeCords(j,i));
				
				if(temp == null)
				{
					buff.append(" - ");
				}
				else if(temp.getIsWhite())
				{
					buff.append(" O ");
				}
				else
				{
					buff.append(" X ");
				}
				
				if(i==7)
				{
					buff.append("\n");
				}
			}
		}
		
		return buff.toString();
	}
}
